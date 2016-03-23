package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Question;

public class DAOQuiz {

	@SuppressWarnings("finally")
	private static Integer getValidQuestions() throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		Integer count = null;
		try {
			conn.conect();
			String query = "select count(*) as questions from question where question.qst_situation <> 1;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()) {
				count = Integer.parseInt(conn.returnRegister().getString("questions"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return count;
		}
	}

	@SuppressWarnings("finally")
	private static String getUnansweredQuestions(Long id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		String question = null;
		try {
			conn.conect();
			String query = "select min(qst_code) as code from question where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where usr_code = ?);";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, id);
			if (conn.executeQuery()) {
				question = getNumberOfQuestion(conn.returnRegister());
			}
		} catch (SQLException e) {
			System.out.println("an error occurred while taking the issue" + e);
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			return question;
		}
	}

	@SuppressWarnings("finally")
	public static Question getQuestion(Long idUser) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		Question qst = new Question();
		try {
			conn.conect();
			String question = getUnansweredQuestions(idUser);
			String query = "select * from  (select question.qst_code as qst_code,question.qst_question  ,qst_introduction from question "
					+ "where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where usr_code = ?) order by question.qst_code ) "
					+ "as question where question.qst_code = ?;";

			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, idUser.toString());
			conn.getStatement().setString(2, question);
			if (conn.executeQuery()) {
				qst = buildQuestion(conn.returnRegister());
			}
		} catch (SQLException e) {
			System.out.println("It was not possible to get the issue " + e);
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return qst;
		}
	}

	@SuppressWarnings("finally")
	public static List<Answer> getAnswers(Long id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Answer> answers = new LinkedList<Answer>();
		try {
			conn.conect();
			String query = "select * from alternatives where qst_code = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, id.toString());
			if (conn.executeQuery()) {
				answers = buildAnswersToQuestion(conn);
			}
		} catch (SQLException e) {
			System.out.println("It was not possible to get the answers " + e);
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			return answers;
		}
	}

	@SuppressWarnings("finally")
	public static List<Competence> getCompetencies(Long id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Competence> competencies = new LinkedList<Competence>();
		try {
			conn.conect();
			String query = "select competence.com_code,alt_com.alt_code, com_type, rsc_weight from competence"
					+ " inner join alt_com on alt_com.com_code = competence.com_code  where alt_com.alt_code = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, id.toString());
			if (conn.executeQuery()) {
				competencies = buildCompetenciesToQuestion(conn);
			}
		} catch (SQLException e) {
			System.out.println("It was not possible to get the competencies " + e);
			throw new RuntimeException(e);
		} finally {
			return competencies;
		}
	}

	public static boolean insertQuiz(Question question) throws SQLException {

		return false;
	}

	private static String getNumberOfQuestion(ResultSet rs) throws SQLException {
		String numberQuestion;
		numberQuestion = rs.getString("code");
		return numberQuestion;
	}

	private static Question buildQuestion(ResultSet rs) throws SQLException {
		Question question = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		question.setCode(Long.parseLong(rs.getString("question.qst_code")));
		question.setQuestion(rs.getString("question.qst_question"));
		question.setIntroduction(rs.getString("question.qst_introduction"));
		answers = getAnswers(Long.parseLong(rs.getString("question.qst_code")));
		question.setAnswers(answers);
		return question;
	}

	private static List<Answer> buildAnswersToQuestion(ConnectionMySql conn) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		do {
			answers.add(buildAnswerToQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return answers;
	}

	private static Answer buildAnswerToQuestion(ResultSet rs) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		Answer answer = null;
		answer = new Answer();
		answer.setCode(Long.parseLong(rs.getString("alt_code")));
		answer.setDescription(rs.getString("alt_description"));
		competencies = getCompetencies(Long.parseLong(rs.getString("alt_code")));
		answer.setCompetencies(competencies);
		return answer;
	}

	private static Competence buildCompetenceToQuestion(ResultSet rs) throws SQLException {
		Competence competence = new Competence();
		competence.setCode(Long.parseLong(rs.getString("competence.com_code")));
		competence.setType(rs.getString("com_type"));
		competence.setWeight(Integer.parseInt(rs.getString("rsc_weight")));
		return competence;
	}

	private static List<Competence> buildCompetenciesToQuestion(ConnectionMySql conn) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			competencies.add(buildCompetenceToQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return competencies;
	}

}
