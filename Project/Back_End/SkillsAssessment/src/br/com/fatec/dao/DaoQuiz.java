package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.Quiz;

public class DaoQuiz{

	@SuppressWarnings("finally")
	public static Integer getValidQuestions(Connection conn, Long userId) throws SQLException {
		Integer count = null;
		String query = "select count(*) as questions "
				+ "from question where question.qst_situation <> 1 and qst_code "
				+ "not in (select qst_code from quiz where usr_code = ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				count = rs.getInt("questions");
			}
			rs.close();
			stmt.close();
		} finally {
			return count;
		}
	}
	@SuppressWarnings("finally")
	public static Integer getQuestionAmount(Connection conn) throws SQLException {
		Integer count = null;
		String query = "select count(*) as questions from question "
				+ "where question.qst_situation <> 1;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				count = rs.getInt("questions");
			}
			rs.close();
			stmt.close();
		} finally {
			return count;
		}
	}

	@SuppressWarnings("finally")
	private static String getUnansweredQuestions(Connection conn, Long id) throws SQLException {
		String question = null;
		String query = "select min(qst_code) as code from question "
				+ "where question.qst_situation <> 1 and question.qst_code "
				+ "not in (select quiz.qst_code from quiz where usr_code = ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				question = getNumberOfQuestion(rs);
			}
			rs.close();
			stmt.close();
		} finally {
			return question;
		}
	}

	@SuppressWarnings("finally")
	public static Question getQuestion(Connection conn, Long idUser) throws SQLException {
		Question qst = new Question();
		String query = "select * from  (select question.qst_code as qst_code,question.qst_question  ,qst_introduction from question "
				+ "where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where usr_code = ?) order by question.qst_code ) "
				+ "as question where question.qst_code = ?;";
		try {
			String question = getUnansweredQuestions(conn, idUser);
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, idUser.toString());
			stmt.setString(2, question);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				qst = buildQuestion( conn, rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return qst;
		}
	}

	@SuppressWarnings("finally")
	public static List<Answer> getAnswers(Connection conn, Long id) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		String query = "SELECT * FROM alternatives WHERE qst_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id.toString());
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				answers = buildAnswersToQuestion( conn, rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return answers;
		}
	}

	@SuppressWarnings("finally")
	public static List<Competence> getCompetencies(Connection conn, Long id) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		String query = "select competence.com_code,alt_com.alt_code, com_type, rsc_weight from competence"
				+ " inner join alt_com on alt_com.com_code = competence.com_code  where alt_com.alt_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id.toString());
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				competencies = buildCompetenciesToQuestion(rs);
			}
			rs.close();
			stmt.close();
		} finally {
			return competencies;
		}
	}

	@SuppressWarnings("finally")
	public static boolean insertQuiz(Connection conn, Quiz quiz) throws SQLException {
		boolean returnInsert = false;
		String sql = "INSERT INTO quiz (usr_code,qst_code,alt_code,quz_date) "
				+ "VALUES (?, ?, ?,date_format(now(), '%Y-%m-%d'));";
		try {
		//String insert = " insert into quiz (usr_code,qst_code,alt_code,quz_date,quz_duration) values (?,?,?,date_format(now(), '%Y-%m-%d', null);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, quiz.getUser());
			stmt.setLong(2, quiz.getQuestion());
			stmt.setLong(3, quiz.getAnswer());
			//conn.getStatement().setString(4, quiz.getDuration());
			if( stmt.executeUpdate() != 0 ){
				System.out.println("the question has been successfully inserted!");
				returnInsert =  true;
			}
			stmt.close();
		}finally {
			return returnInsert;
		}
	}

	private static String getNumberOfQuestion(ResultSet rs) throws SQLException {
//		String numberQuestion;
//		numberQuestion = rs.getString("code");
//		return numberQuestion;
		return rs.getString("code");
	}

	private static Question buildQuestion(Connection conn, ResultSet rs) throws SQLException {
		Question question = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		question.setCode(rs.getLong("question.qst_code"));
		question.setQuestion(rs.getString("question.qst_question"));
		question.setIntroduction(rs.getString("question.qst_introduction"));
		//answers = getAnswers(question.getCode());
		answers = getAnswers(conn, rs.getLong("question.qst_code"));
		question.setAnswers(answers);
		return question;
	}

	private static List<Answer> buildAnswersToQuestion( Connection conn, ResultSet rs ) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		do {
			answers.add(buildAnswerToQuestion( conn, rs ));
		} while ( rs.next() );
		return answers;
	}

	private static Answer buildAnswerToQuestion(Connection conn, ResultSet rs) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		Answer answer = new Answer();
		answer.setCode(rs.getLong("alt_code"));
		answer.setDescription(rs.getString("alt_description"));
		competencies = getCompetencies(conn, rs.getLong("alt_code"));
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

	private static List<Competence> buildCompetenciesToQuestion( ResultSet rs ) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			competencies.add(buildCompetenceToQuestion( rs ));
		} while ( rs.next() );
		return competencies;
	}
	

}
