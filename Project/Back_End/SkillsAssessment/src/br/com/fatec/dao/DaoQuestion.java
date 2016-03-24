package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Course;
import br.com.fatec.entity.Question;
import br.com.fatec.entity.User;

public class DaoQuestion {
	
	@SuppressWarnings("finally")
	public static boolean insertQuestion(Question question) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		boolean returnQuestion = false;
		try {
			conn.conect();
			String query = "insert into question (qst_introduction,qst_question,qst_situation) values (?,?,?);";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, question.getIntroduction());
			conn.getStatement().setString(2, question.getQuestion());
			conn.getStatement().setInt(3, question.getSituation());
			if (conn.executeSql()) {
				for (int i = 0; i < question.getAnswers().size(); i++) {
					returnQuestion = insertAnswer(question.getAnswers().get(i));
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			conn.getResultset().close();
			conn.getStatement().close();
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
			String updateQuestion = "update question set qst_question = ? ,qst_introduction = ? ,qst_situation = ? where qst_code = ?;";
			
			conn.setStatement(conn.getConnection().prepareStatement(updateQuestion));
			conn.getStatement().setString(1, question.getQuestion());
			conn.getStatement().setString(2, question.getIntroduction());
			conn.getStatement().setLong(3, question.getSituation());
			conn.getStatement().setLong(4, question.getCode());
			
			for(int i = 0; i < question.getAnswers().size(); i++){
				ConnectionMySql conn2 = new ConnectionMySql();
				conn2.conect();
				String updateASnswer = "update alternatives  set alt_description = ? where 	qst_code = ?; ";
				conn2.setStatement(conn.getConnection().prepareStatement(updateASnswer));
				conn2.getStatement().setString(1, question.getAnswers().get(i).getDescription());
				conn2.getStatement().setLong(2, question.getCode());
				
				if(conn2.executeSql())  System.out.println("the alternative has been successfully updated!");
				conn2.close();
				for(int j = 0; j < question.getAnswers().get(j).getCompetencies().size() ; j++){
					ConnectionMySql conn3 = new ConnectionMySql();
					conn3.conect();
					String updateComepetence = "update alt_com  set alt_code = ? ,com_code = ? ,rsc_weight = ?  where alt_code = ?; ";
					conn3.setStatement(conn.getConnection().prepareStatement(updateComepetence));
					conn3.getStatement().setLong(1, question.getAnswers().get(j).getCompetencies().get(j).getAltCode());
					conn3.getStatement().setLong(2, question.getAnswers().get(j).getCompetencies().get(j).getCode());
					conn3.getStatement().setLong(3, question.getAnswers().get(j).getCompetencies().get(j).getWeight());
					conn3.getStatement().setLong(4, question.getAnswers().get(i).getCode());
					
					if(conn.executeSql())  System.out.println("the competence has been successfully updated!");
					conn3.close();
				}
			}		
			if(conn.executeSql()){
				System.out.println("the question has been successfully updated!");
					returnUpdate =  true;	
			}
		} catch (Exception e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		}finally {
			conn.close();
			return returnUpdate;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteQuestion(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		ConnectionMySql conn1 = new ConnectionMySql();
		ConnectionMySql conn2 = new ConnectionMySql();
		boolean delete = false;
		try {
			
			Question question = DaoQuestion.searchQuestionByCode(code);
			conn.conect();
			String deleteCompetencies = "delete from alt_com where alt_code = ?";
			conn.setStatement(conn.getConnection().prepareStatement(deleteCompetencies));
			conn.getStatement().setLong(1,(long)2);
			if (conn.executeSql()) {
				System.out.println("Was deletd a competence");
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
		} catch (Exception e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		} finally {
			conn.close();
			conn1.close();
			conn2.close();
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Question> searchAllQuestion() {
		List<Question> listQuestion = new ArrayList<>();
		ConnectionMySql conn = new ConnectionMySql();
		String query = "select q.qst_code, q.qst_question, q.qst_situation, q.qst_introduction from question q ;";
		try {
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()) {
				listQuestion = DaoQuestion.buildQuestions(conn);
			} 
		} catch (SQLException e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		} finally {
			conn.close();
			return listQuestion;
		}
	}
	
	@SuppressWarnings("finally")
	public static Question searchQuestionByCode(Long codeQuestion) {
		Question question = new Question();
		ConnectionMySql conn = new ConnectionMySql();
		String query = "select q.qst_code, q.qst_question, q.qst_situation, q.qst_introduction from question q where qst_code = ?;";
		try {
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, codeQuestion);
			if (conn.executeQuery()) {
				question = DaoQuestion.buildQuestion(conn.returnRegister());
			} 
		} catch (SQLException e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		} finally {
			conn.close();
			return question;
		}
	}
	
	
	
	
	@SuppressWarnings("finally")
	public static boolean insertAnswer(Answer answer) throws SQLException{
		ConnectionMySql conn = new ConnectionMySql();
		boolean returnAnswer = false;
		try {
			conn.conect();
			String query = "insert into alternatives (alt_description,qst_code) values (?,?);";
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setString(1, answer.getDescription());
			conn.getStatement().setLong(2, answer.getCode());
			if (conn.executeSql()) {
				for (int i = 0; i < answer.getCompetencies().size(); i++) {
					returnAnswer = insertCompetenceInAnswer(answer.getCode(), answer.getCompetencies().get(i));
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return returnAnswer;
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
	
	
	
	private static Answer buildAnswerToQuestion(ResultSet rs) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		Answer answer = null;
		answer = new Answer();
		answer.setCode(Long.parseLong(rs.getString("alt_code")));
		answer.setDescription(rs.getString("alt_description"));
		answer.setCodeQuestion(rs.getLong("qst_code"));
		competencies = getCompetencies(Long.parseLong(rs.getString("alt_code")));
		answer.setCompetencies(competencies);
		return answer;
	}
	
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			conn.getResultset().close();
			conn.getStatement().close();
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
		answers = getAnswers(Long.parseLong(rs.getString("q.qst_code")));
		question.setCode(rs.getLong("q.qst_code"));
		question.setQuestion(rs.getString("qst_question"));
		question.setSituation(rs.getInt("qst_situation"));
		question.setIntroduction(rs.getString("qst_introduction"));
		question.setAnswers(answers);
		return question;
	}
	
	private static Competence buildCompetenceToQuestion(ResultSet rs) throws SQLException {
		Competence competence = new Competence();
		competence.setCode(Long.parseLong(rs.getString("competence.com_code")));
		competence.setType(rs.getString("com_type"));
		competence.setWeight(Integer.parseInt(rs.getString("rsc_weight")));
		competence.setAltCode(rs.getLong("alt_com.alt_code"));
		return competence;
	}

	private static List<Competence> buildCompetenciesToQuestion(ConnectionMySql conn) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			competencies.add(buildCompetenceToQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return competencies;
	}
	
	
	private static List<Answer> buildAnswersToQuestion(ConnectionMySql conn) throws SQLException {
		List<Answer> answers = new LinkedList<Answer>();
		do {
			answers.add(buildAnswerToQuestion(conn.returnRegister()));
		} while (conn.nextRegister());
		return answers;
	}

	
	public static void main(String[] args) {
		/*List<Question> questions = DaoQuestion.searchAllQuestion();
		if(questions !=null){
			for(Question q:questions){
				System.out.println("Questão: "+q.getQuestion());
				System.out.println("Introdução: "+q.getIntroduction());
				System.out.println("Código: "+q.getCode());
				for(Answer a:q.getAnswers()){
					System.out.println("Coodigo da resposta: "+a.getCode());
					System.out.println("descrição: "+a.getDescription());
					for(Competence c:a.getCompetencies()){
						System.out.println("Codigo da competencia: "+c.getCode());
						System.out.println("Peso da competencia na questão: "+c.getWeight());
						System.out.println("Tipo: "+c.getType());
					}
				}
			}
		}
		
		Question q = new Question();
		q = DaoQuestion.searchQuestionByCode((long)1);
		System.out.println("Introdução: "+q.getIntroduction());
		q.setIntroduction("agora vai!!!!!!!");
		try {
			DaoQuestion.updateQuestion(q);
		} catch (SQLException e) {
			System.out.println("erro: "+e);
			e.printStackTrace();
		}
		*/
		/* Question q2 = new Question();
		q2 = DaoQuestion.searchQuestionByCode((long)2);
		System.out.println("Introdução: "+q2.getIntroduction());
		
		System.out.println("Questão: "+q2.getQuestion());
		System.out.println("Introdução: "+q2.getIntroduction());
		System.out.println("Código: "+q2.getCode());
		for(Answer a:q2.getAnswers()){
			System.out.println("Coodigo da resposta: "+a.getCode());
			System.out.println("descrição: "+a.getDescription());
			for(Competence c:a.getCompetencies()){
				System.out.println("Codigo da competencia: "+c.getCode());
				System.out.println("Peso da competencia na questão: "+c.getWeight());
				System.out.println("Tipo: "+c.getType());
			}
		}*/
		
		try {
			boolean result = DaoQuestion.deleteQuestion((long)2);
		} catch (SQLException e) {
			System.out.println("erro: "+e);
			e.printStackTrace();
		}
		
	}
}
