package br.com.fatec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;

import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Question;

public class DaoQuestion {
	
	@SuppressWarnings("finally")
	public static boolean insertQuestion(Question question) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		PreparedStatement insert = null;
		boolean returnQuestion = false;
		try {
			conn.conect();
			String query = "INSERT INTO question (qst_introduction,qst_question,qst_situation) VALUES (?, ?, ?);";
			conn.setStatement(conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS));
			insert = conn.getStatement();
			conn.getStatement().setString(1, question.getIntroduction());
			conn.getStatement().setString(2, question.getQuestion());
			conn.getStatement().setInt(3, 1);
			//conn.getStatement().setInt(3, question.getSituation());
			if (conn.executeSql()) {
				ResultSet generatedKeys = insert.getGeneratedKeys();
				if(generatedKeys.next()){
					Long codeQuestion = generatedKeys.getLong(1);
					for (int i = 0; i < question.getAnswers().size(); i++) {
						returnQuestion = insertAnswer(codeQuestion, question.getAnswers().get(i));
					}
				}
			}
		}finally {
			conn.close();
			return returnQuestion;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateQuestion(Question question) throws SQLException{
	
		ConnectionMySql conn = new ConnectionMySql();
		boolean returnUpdate = false;
		
		try {
			conn.conect();
			String updateQuestion = "UPDATE question SET qst_question = ?, qst_introduction = ?, qst_situation = ? WHERE qst_code = ?;";
			
			conn.setStatement(conn.getConnection().prepareStatement(updateQuestion));
			conn.getStatement().setString(1, question.getQuestion());
			conn.getStatement().setString(2, question.getIntroduction());
			conn.getStatement().setLong(3, question.getSituation());
			conn.getStatement().setLong(4, question.getCode());
			if(conn.executeSql()){
				System.out.println("the question has been successfully updated!");			
			}
			
			for(int i = 0; i < question.getAnswers().size(); i++){
			
				String updateASnswer = "UPDATE alternatives SET alt_description = ? WHERE qst_code = ? AND alt_code = ?; ";
				conn.setStatement(conn.getConnection().prepareStatement(updateASnswer));
				conn.getStatement().setString(1, question.getAnswers().get(i).getDescription());
				conn.getStatement().setLong(2, question.getCode());
				conn.getStatement().setLong(3, question.getAnswers().get(i).getCode());
				
				if(conn.executeSql()){
					System.out.println("the alternative has been successfully updated!");
				}
			
				
				for(int j = 0; j < question.getAnswers().get(i).getCompetencies().size() ; j++){
					
					String updateComepetence = "UPDATE alt_com SET alt_code = ?, com_code = ? ,rsc_weight = ? WHERE rsc_code = ?; ";
					conn.setStatement(conn.getConnection().prepareStatement(updateComepetence));
					conn.getStatement().setLong(1, question.getAnswers().get(i).getCode());
					conn.getStatement().setLong(2, question.getAnswers().get(i).getCompetencies().get(j).getCode());
					conn.getStatement().setLong(3, question.getAnswers().get(i).getCompetencies().get(j).getWeight());
					conn.getStatement().setLong(4, question.getAnswers().get(i).getCompetencies().get(j).getRscCode());
					
					
					//show current command
					System.out.println(conn.getStatement());
					
					if(conn.executeSql()){
						System.out.println("the competence has been successfully updated!");
					}
					
				}
			}
			returnUpdate =  true;
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			conn.close();
			return returnUpdate;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteQuestion(Long code) throws SQLException {
		ConnectionMySql conn0 = new ConnectionMySql();
		ConnectionMySql conn1 = new ConnectionMySql();
		ConnectionMySql conn2 = new ConnectionMySql();
		boolean delete = false;
		try {
			Question question = DaoQuestion.searchQuestionByCode(code);
			for( int i = 0; i < question.getAnswers().size(); i++){
				conn0.conect();
				String deleteCompetencies = "delete from alt_com where alt_code = ?";
				conn0.setStatement(conn0.getConnection().prepareStatement(deleteCompetencies));
				conn0.getStatement().setLong( 1, question.getAnswers().get(i).getCode() );
				if (conn0.executeSql()) {
					System.out.println("Was deletd a competence");
				}
			}
			
			conn1.conect();
			String deleteAnswers = "delete from alternatives where qst_code = ?";
			conn1.setStatement(conn1.getConnection().prepareStatement(deleteAnswers));
			conn1.getStatement().setLong(1, question.getCode());
			if (conn1.executeSql()) {
				System.out.println("Was deletd a answer");
			}
			
			conn2.conect();
			String deleteQuestion = "delete from question where qst_code = ?";
			conn2.setStatement(conn2.getConnection().prepareStatement(deleteQuestion));
			conn2.getStatement().setLong(1, question.getCode());
			if (conn2.executeSql()) {
				System.out.println("Was deletd a question");
			}
			delete = true;
		} finally {
			conn0.close();
			conn1.close();
			conn2.close();
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Question> searchAllQuestion() throws SQLException {
		List<Question> listQuestion = new ArrayList<>();
		ConnectionMySql conn = new ConnectionMySql();
		String query = "select q.qst_code, q.qst_question, q.qst_situation, q.qst_introduction from question q ;";
		try {
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()) {
				listQuestion = DaoQuestion.buildQuestions(conn);
			} 
		} finally {
			conn.close();
			return listQuestion;
		}
	}
	
	@SuppressWarnings("finally")
	public static Question searchQuestionByCode(Long codeQuestion) throws SQLException {
		Question question = new Question();
		ConnectionMySql conn = new ConnectionMySql();
		String query = "SELECT qst_code, qst_question, qst_situation, qst_introduction FROM question WHERE qst_code = ?;";
		try {
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, codeQuestion);
			if (conn.executeQuery()) {
				question = DaoQuestion.buildQuestion(conn.returnRegister());
			} 
		} finally {
			conn.close();
			return question;
		}
	}
	
	
	
	//AUXILIA INSERT QUESTION
	@SuppressWarnings("finally")
	public static boolean insertAnswer(Long codeQuestion, Answer answer) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		PreparedStatement insert = null;
		boolean returnAnswer = false;
		try {
			conn.conect();
			String query = "insert into alternatives (alt_description,qst_code) values (?,?);";
			conn.setStatement(conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS) );
			insert = conn.getStatement();
			conn.getStatement().setString(1, answer.getDescription());
			conn.getStatement().setLong(2, codeQuestion );
			if (conn.executeSql()) {
				ResultSet generatedKeys = insert.getGeneratedKeys();
				if(generatedKeys.next()){
					Long codeAnswer = generatedKeys.getLong(1);
					for (int i = 0; i < answer.getCompetencies().size(); i++) {
						returnAnswer = insertCompetenceInAnswer(codeAnswer, answer.getCompetencies().get(i));
					}
				}
			}
		}finally {
			conn.close();
			return returnAnswer;
		}
	}
	
	//AUXILIA GETQUESTION
	@SuppressWarnings("finally")
	public static List<Answer> getAnswers(Long id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Answer> answers = new LinkedList<Answer>();
		try {
			conn.conect();
			String query = "SELECT * FROM alternatives WHERE qst_code = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, id);
			if (conn.executeQuery()) {
				answers = buildAnswersToQuestion(conn);
			}
		} finally {
			conn.close();
			return answers;
		}
	}
	//AUXILIA GET_ANSWER QUE AUXILIA GET_QUESTION
	@SuppressWarnings("finally")
	public static List<Competence> getCompetencies(Long id) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		List<Competence> competencies = new LinkedList<Competence>();
		try {
			conn.conect();
			String query = "SELECT alt_com.rsc_code, comp.com_code, alt_com.alt_code, comp.com_type, alt_com.rsc_weight FROM competence comp"
					+ " INNER JOIN alt_com on alt_com.com_code = comp.com_code WHERE alt_com.alt_code = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, id);
			if (conn.executeQuery()) {
				competencies = buildCompetenciesToQuestion(conn);
			}
		} finally {
			return competencies;
		}
	}
		
	
	//AUXILIA INSERT ALTERNATIVE QUE AUXILIA INSERT QUESTION
	@SuppressWarnings("finally")
	public static boolean insertCompetenceInAnswer(Long altCode, Competence competence) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		boolean returnCompetence = false;
		try {
			conn.conect();
			String query = "insert into alt_com (alt_code,com_code,rsc_weight) values (?,?,?);";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, altCode);
			conn.getStatement().setLong(2, competence.getCode());
			conn.getStatement().setLong(3, competence.getWeight());
			if (conn.executeSql()) {
				returnCompetence = true;
			}
		}finally {
			conn.close();
			return returnCompetence;
		}
	}
		
	private static List<Question> buildQuestions(ConnectionMySql conn) throws SQLException {
		List<Question> questions = new LinkedList<Question>();
		do {
			questions.add(buildQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return questions;
	}

	private static Question buildQuestion(ResultSet rs) throws SQLException {
		Question question = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		answers = getAnswers( rs.getLong("qst_code") );
		
		question.setCode(rs.getLong("qst_code"));
		question.setQuestion(rs.getString("qst_question"));
		question.setSituation(rs.getInt("qst_situation"));
		question.setIntroduction(rs.getString("qst_introduction"));
		
		question.setAnswers(answers);
		
		return question;
	}
	
	private static List<Competence> buildCompetenciesToQuestion(ConnectionMySql conn) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			competencies.add(buildCompetenceToQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return competencies;
	}
	
	private static Competence buildCompetenceToQuestion(ResultSet rs) throws SQLException {
		Competence competence = new Competence();
		competence.setRscCode(rs.getLong("alt_com.rsc_code") );
		competence.setCode(rs.getLong("comp.com_code") );
		competence.setType(rs.getString("comp.com_type"));
		competence.setWeight(rs.getInt("alt_com.rsc_weight"));
		competence.setAltCode(rs.getLong("alt_com.alt_code"));
		return competence;
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
		Answer answer = new Answer();
		
		answer.setCode( rs.getLong("alt_code" ) );
		answer.setDescription( rs.getString("alt_description") );
		answer.setCodeQuestion( rs.getLong("qst_code") );
		competencies = getCompetencies( answer.getCode() );
		
		answer.setCompetencies(competencies);
		
		return answer;
	}
	

}
