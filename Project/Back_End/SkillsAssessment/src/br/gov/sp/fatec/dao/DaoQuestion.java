package br.gov.sp.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.gov.sp.fatec.entity.Answer;
import br.gov.sp.fatec.entity.Competence;
import br.gov.sp.fatec.entity.Question;

public class DaoQuestion {
	
	@SuppressWarnings("finally")
	public static boolean insertQuestion(Connection conn, Question question) throws SQLException{
		String query = "INSERT INTO question (qst_introduction, qst_question, qst_situation, qst_type) "
						+ "VALUES (?, ?, ?, ?);";
		boolean returnQuestion = false;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, question.getIntroduction());
			stmt.setString(2, question.getQuestion());
			stmt.setInt(3, 1);
			stmt.setString(4, question.getType());
			if ( stmt.executeUpdate() != 0 ) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if( generatedKeys.next() ){
					Long codeQuestion = generatedKeys.getLong(1);
					for (int i = 0; i < question.getAnswers().size(); i++) {
						returnQuestion = insertAnswer(conn, codeQuestion, question.getAnswers().get(i));
					}
				}
				generatedKeys.close();
			}
			stmt.close();
		}finally {
			return returnQuestion;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateQuestion(Connection conn, Question question) throws SQLException{
		boolean returnUpdate = false;
		boolean statusQuestion = false;
		boolean statusAnswers = false;
		boolean statusComepetence = false;
		
		String updateQuestion = "UPDATE question SET qst_question = ?, qst_introduction = ?, qst_situation = ?, qst_type = ? "
								+ "WHERE qst_code = ?;";
		try {
			PreparedStatement stmt0 = conn.prepareStatement(updateQuestion);
			stmt0.setString(1, question.getQuestion());
			stmt0.setString(2, question.getIntroduction());
			stmt0.setLong(3, question.getSituation());
			stmt0.setString(4, question.getType());
			stmt0.setLong(5, question.getCode());
			if( stmt0.executeUpdate() != 0 ){
				statusQuestion = true;
				System.out.println("the question has been successfully updated!");			
			}
			stmt0.close();
			
			for(int i = 0; i < question.getAnswers().size(); i++){
			
				String updateAnswer = "UPDATE alternatives SET alt_description = ? WHERE qst_code = ? AND alt_code = ?;";
				PreparedStatement stmt1 = conn.prepareStatement(updateAnswer);
				stmt1.setString(1, question.getAnswers().get(i).getDescription());
				stmt1.setLong(2, question.getCode());
				stmt1.setLong(3, question.getAnswers().get(i).getCode());
				
				if( stmt1.executeUpdate() != 0 ){
					statusAnswers = true;
					System.out.println("the alternative has been successfully updated!");
				} else {
					statusAnswers = false;
				}
				stmt1.close();
				
				for(int j = 0; j < question.getAnswers().get(i).getCompetencies().size() ; j++){
					
					String updateComepetence = "UPDATE alt_com SET alt_code = ?, com_code = ? ,rsc_weight = ? WHERE rsc_code = ?;";
					PreparedStatement stmt2 = conn.prepareStatement(updateComepetence);
					stmt2.setLong(1, question.getAnswers().get(i).getCode());
					stmt2.setLong(2, question.getAnswers().get(i).getCompetencies().get(j).getCode());
					stmt2.setLong(3, question.getAnswers().get(i).getCompetencies().get(j).getWeight());
					stmt2.setLong(4, question.getAnswers().get(i).getCompetencies().get(j).getRscCode());

					
					if( stmt2.executeUpdate() != 0 ){
						statusComepetence = true;
						System.out.println("the competence has been successfully updated!");
					} else {
						statusComepetence = false;
					}
					stmt2.close();
				}
					
			}
			//validação se deu tudo certo
			if ( statusQuestion && statusAnswers && statusComepetence ){
				returnUpdate =  true;
			}
		}
		finally {
			return returnUpdate;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteQuestion(Connection conn, Long code) throws SQLException {
		boolean transaction = false;
		boolean statusQuestion = false;
		boolean statusAnswers = false;
		boolean statusComepetence = false;
		try {
			Question question = DaoQuestion.searchQuestionByCode(conn, code);
			for( int i = 0; i < question.getAnswers().size(); i++){
				
				String deleteCompetencies = "DELETE FROM alt_com WHERE alt_code = ?";
				PreparedStatement stmt0 = conn.prepareStatement(deleteCompetencies);
				stmt0.setLong( 1 , question.getAnswers().get(i).getCode() );
				if ( stmt0.executeUpdate() != 0 ) {
					statusComepetence = true;
					System.out.println("Was deletd a competence");
				}
				stmt0.close();
			}
			
			String deleteAnswers = "DELETE FROM alternatives WHERE qst_code = ?";
			PreparedStatement stmt1 = conn.prepareStatement(deleteAnswers);
			stmt1.setLong(1, question.getCode());
			if ( stmt1.executeUpdate() != 0 ) {
				statusAnswers = true;
				System.out.println("Was deletd a answer");
			}
			stmt1.close();
			
			String deleteQuestion = "DELETE FROM question WHERE qst_code = ?";
			PreparedStatement stmt2 = conn.prepareStatement(deleteQuestion);
			stmt2.setLong(1, question.getCode());
			if ( stmt2.executeUpdate() != 0 ) {
				statusQuestion = true;
				System.out.println("Was deletd a question");
			}
			stmt2.close();
			//validação se deu tudo certo
			if ( statusQuestion && statusAnswers && statusComepetence ){
				transaction =  true;
			}
		} finally {
			return transaction;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Question> searchAllQuestion(Connection conn) throws SQLException {
		List<Question> listQuestion = new ArrayList<>();
		String query = "SELECT q.qst_code, q.qst_question, q.qst_situation, q.qst_introduction, q.qst_type "
						+ "FROM question q ;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				listQuestion = DaoQuestion.buildQuestions( conn, rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return listQuestion;
		}
	}
	
	@SuppressWarnings("finally")
	public static Question searchQuestionByCode(Connection conn, Long codeQuestion) throws SQLException {
		Question question = new Question();
		String query = "SELECT qst_code, qst_question, qst_situation, qst_introduction, qst_type "
						+ "FROM question WHERE qst_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, codeQuestion);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				question = DaoQuestion.buildQuestion( conn, rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return question;
		}
	}
	
	
	
	//AUXILIA INSERT QUESTION
	@SuppressWarnings("finally")
	public static boolean insertAnswer(Connection conn, Long codeQuestion, Answer answer) throws SQLException{
		String query = "INSERT INTO alternatives (alt_description, qst_code) values (?, ?);";
		boolean returnAnswer = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, answer.getDescription());
			stmt.setLong(2, codeQuestion );
			if ( stmt.executeUpdate() != 0 ) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if( generatedKeys.next() ){
					Long codeAnswer = generatedKeys.getLong(1);
					for (int i = 0; i < answer.getCompetencies().size(); i++) {
						returnAnswer = insertCompetenceInAnswer(conn, codeAnswer, answer.getCompetencies().get(i));
					}
				}
				generatedKeys.close();
			}
			stmt.close();
		}finally {
			return returnAnswer;
		}
	}
	
	//AUXILIA GETQUESTION
	@SuppressWarnings("finally")
	public static List<Answer> getAnswers(Connection conn, Long id) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		String query = "SELECT * FROM alternatives WHERE qst_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				answers = buildAnswersToQuestion( conn,  rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return answers;
		}
	}
	//AUXILIA GET_ANSWER QUE AUXILIA GET_QUESTION
	@SuppressWarnings("finally")
	public static List<Competence> getCompetencies(Connection conn, Long id) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		String query = "SELECT alt_com.rsc_code, comp.com_code, alt_com.alt_code, comp.com_type, alt_com.rsc_weight "
						+ "FROM competence comp INNER JOIN alt_com on alt_com.com_code = comp.com_code "
						+ "WHERE alt_com.alt_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				competencies = buildCompetenciesToQuestion( rs );
			}
			rs.close();
			stmt.close();
		} finally {
			return competencies;
		}
	}
		
	
	//AUXILIA INSERT ALTERNATIVE QUE AUXILIA INSERT QUESTION
	@SuppressWarnings("finally")
	public static boolean insertCompetenceInAnswer(Connection conn, Long altCode, Competence competence) throws SQLException{
		String query = "INSERT INTO alt_com (alt_code, com_code, rsc_weight) values (?, ?, ?);";
		boolean returnCompetence = false;
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, altCode);
			stmt.setLong(2, competence.getCode());
			stmt.setLong(3, competence.getWeight());
			if ( stmt.executeUpdate() != 0 ) {
				returnCompetence = true;
			}
			stmt.close();
		}finally {
			return returnCompetence;
		}
	}
		
	private static List<Question> buildQuestions( Connection conn, ResultSet rs ) throws SQLException {
		List<Question> questions = new LinkedList<Question>();
		do {
			questions.add(buildQuestion( conn, rs ));
		} while ( rs.next() );
		return questions;
	}

	private static Question buildQuestion( Connection conn, ResultSet rs ) throws SQLException {
		Question question = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		
		answers = getAnswers(conn, rs.getLong("qst_code") );
		question.setCode(rs.getLong("qst_code"));
		question.setQuestion(rs.getString("qst_question"));
		question.setSituation(rs.getInt("qst_situation"));
		question.setIntroduction(rs.getString("qst_introduction"));
		question.setType(rs.getString("qst_type"));
		
		question.setAnswers(answers);
		
		return question;
	}
	
	private static List<Competence> buildCompetenciesToQuestion( ResultSet rs ) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			competencies.add(buildCompetenceToQuestion( rs ));
		} while ( rs.next() );
		return competencies;
	}
	
	private static Competence buildCompetenceToQuestion( ResultSet rs ) throws SQLException {
		Competence competence = new Competence();
		competence.setRscCode(rs.getLong("alt_com.rsc_code") );
		competence.setCode(rs.getLong("comp.com_code") );
		competence.setType(rs.getString("comp.com_type"));
		competence.setWeight(rs.getInt("alt_com.rsc_weight"));
		competence.setAltCode(rs.getLong("alt_com.alt_code"));
		return competence;
	}	
	
	private static List<Answer> buildAnswersToQuestion( Connection conn, ResultSet rs ) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		do {
			answers.add(buildAnswerToQuestion( conn, rs ));
		} while ( rs.next() );
		return answers;
	}
	
	private static Answer buildAnswerToQuestion( Connection conn, ResultSet rs ) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		Answer answer = new Answer();
		
		answer.setCode( rs.getLong("alt_code" ) );
		answer.setDescription( rs.getString("alt_description") );
		answer.setCodeQuestion( rs.getLong("qst_code") );
		competencies = getCompetencies( conn, answer.getCode() );
		
		answer.setCompetencies(competencies);
		
		return answer;
	}
	

}
