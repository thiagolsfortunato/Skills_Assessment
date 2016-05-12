package br.com.fatec.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;

public class DaoQuiz{

	@SuppressWarnings("finally")
	public static Integer getValidQuestions(Long userId) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		Integer count = null;
		try {
			conn.conect();
			String query = "select count(*) as questions from question where question.qst_situation <> 1 and qst_code not in (select qst_code from quiz where usr_code = ?);";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, userId);
			if (conn.executeQuery()) {
				count = Integer.parseInt(conn.returnRegister().getString("questions"));
			}
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return count;
		}
	}
	@SuppressWarnings("finally")
	public static Integer getQuestionAmount() throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		Integer count = null;
		try {
			conn.conect();
			String query = "select count(*) as questions from question where question.qst_situation <> 1;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()) {
				count = Integer.parseInt(conn.returnRegister().getString("questions"));
			}
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
		} finally {
			return competencies;
		}
	}

	@SuppressWarnings("finally")
	public static boolean insertQuiz(Quiz quiz) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		ResultSet idUser = null;
		boolean returnInsert = false;
		try {
		//String insert = " insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (?,?,?,date_format(now(), '%Y-%m-%d', null);";
		String insert = " insert into quiz (usr_code,qst_code,alt_code,quz_date) values (?,?,?,date_format(now(), '%Y-%m-%d'));";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(insert));
			conn.getStatement().setLong(1, quiz.getUser());
			conn.getStatement().setLong(2, quiz.getQuestion());
			conn.getStatement().setLong(3, quiz.getAnswer());
			//conn.getStatement().setString(4, quiz.getDuration());
			System.out.println(conn.getStatement());
			if(conn.executeSql()){
				System.out.println("the question has been successfully inserted!");
				returnInsert =  true;
			}
		}finally {
			conn.close();
			return returnInsert;
		}
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
	
	public static void main(String[] args) {
		try {
			Integer d = DaoQuiz.getQuestionAmount();
			System.out.println(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
