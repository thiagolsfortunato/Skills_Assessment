package br.gov.sp.fatec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.gov.sp.fatec.entity.Answer;
import br.gov.sp.fatec.entity.Competence;
import br.gov.sp.fatec.entity.Question;
import br.gov.sp.fatec.entity.Quiz;
import br.gov.sp.fatec.entity.Result;

public class DaoQuiz{
	static Long aux = null;
	static Competence auxCom = null;
	
	@SuppressWarnings("finally")
	public static Integer getValidQuestions(Connection conn, Long userId) throws SQLException {
		Integer count = null;
		String query = "select count(*) as questions "
				+ "from question where question.qst_situation <> 0 and qst_code "
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
				+ "where question.qst_situation <> 0;";
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
				+ "where question.qst_situation <> 0 and question.qst_code "
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
		String query = "select * from (select question.qst_code as qst_code, question.qst_question, qst_introduction, qst_type from question "
				+ "where question.qst_situation <> 0 and question.qst_code not in (select quiz.qst_code from quiz where usr_code = ?) order by question.qst_code ) "
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

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, quiz.getUser());
			stmt.setLong(2, quiz.getQuestion());
			stmt.setLong(3, quiz.getAnswer());
	
			if( stmt.executeUpdate() != 0 ){
				System.out.println("the question has been successfully inserted!");
				returnInsert =  true;
			}
			stmt.close();
		}finally {
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Long>  getCompetenciesQuiz(Connection conn, Long userCode) throws SQLException {
		List<Long> competencies = new LinkedList<Long>();
		String query = "select distinct com.com_code as code from competence com inner join alt_com alc on com.com_code ="
				+ " alc.com_code inner join alternatives alt on alt.alt_code = alc.alt_code inner join quiz quz on "
				+ "quz.alt_code = alt.alt_code where quz.usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, userCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				competencies = buildCompetenciesToQuiz(rs);
			}
			rs.close();
			stmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			return competencies;
		}
	}
	
	@SuppressWarnings("finally")
	public static Long calculateAverage(Connection conn, Long comCode, Long userCode) throws SQLException {
		Long sumCompetencie = null;
		
		String query = "select sum(alc.rsc_weight) as sum_com from quiz quz inner join alternatives alt on quz.alt_code = "
				+ " alt.alt_code inner join alt_com alc on alc.alt_code = alt.alt_code where alc.com_code = ? and quz.usr_code = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, comCode);
			stmt.setLong(2, userCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				sumCompetencie = rs.getLong("sum_com");
			}
			rs.close();
			stmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		finally {
			return sumCompetencie;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean insertAverage(Connection conn, Long com_code, Long userCode) throws SQLException {
		Long userResult = null;
		Long competenceSum = null;
		boolean returnInsert = false;
		String sql = "INSERT INTO average (rst_code, com_code, avr_final) VALUES (?, ?, ?);";
		try {
			userResult = DaoEnrolls.getResult(conn, userCode);
			competenceSum = DaoQuiz.calculateAverage(conn, com_code,userCode);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userResult);
			stmt.setLong(2, com_code);
			stmt.setLong(3, competenceSum);
			if( stmt.executeUpdate() != 0 ){
				System.out.println("the average has been successfully inserted!");
				returnInsert =  true;
			}
			stmt.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		finally {
			return returnInsert;
		}
	}
	
	@SuppressWarnings("finally")
	public static Result getResultStudent(Connection conn, Long userCode) throws SQLException{
		Result result  = new Result();
		String query = "SELECT DISTINCT usr.usr_code, usr.usr_name, usr.usr_ra, erl.ern_period, erl.ern_year,com.com_code,com.com_type,rst.rst_comment,"
				+ "avr.avr_final,crs.crs_name,ist.ist_company "
				+ "FROM result rst INNER JOIN average avr ON rst.rst_code = avr.rst_code "
				+ "INNER JOIN competence com ON com.com_code = avr.com_code "
				+ "INNER JOIN user usr on usr.usr_code = rst.usr_code "
				+ "INNER JOIN enrolls erl ON erl.usr_code = usr.usr_code "
				+ "INNER JOIN course crs ON crs.crs_code = erl.crs_code "
				+ "INNER JOIN ist_crs itc ON itc.crs_code = crs.crs_code "
				+ "INNER JOIN institution ist ON ist.ist_code = itc.ist_code "
				+ "WHERE usr.usr_code = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, userCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				result = buildResult(conn, rs);
			}
			rs.close();
			stmt.close();
		} finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Result> getResultStudents(Connection conn, Long instCode) throws SQLException{
		List<Result> result  = new LinkedList<>();
		String query = "select distinct usr.usr_code, usr.usr_name, usr.usr_ra, erl.ern_period, erl.ern_year,com.com_code,com.com_type,avr.avr_final,crs.crs_name,ist.ist_company,rst.rst_comment"
				+" from result rst inner join average avr on rst.rst_code = avr.rst_code inner join competence com on com.com_code = avr.com_code "
				+" inner join user usr on usr.usr_code = rst.usr_code inner join enrolls erl on erl.usr_code = usr.usr_code "
				+" inner join course crs on crs.crs_code = erl.crs_code inner join ist_crs itc on itc.crs_code = crs.crs_code "
				+" inner join  institution ist on ist.ist_code = itc.ist_code where ist.ist_code = ? order by usr.usr_code;";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, instCode);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				result = buildResultQuiz(conn, rs);
			}
			rs.close();
			stmt.close();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean getAverage(Connection conn, Long userCode) throws SQLException {
		boolean returnInsert = false;
		//boolean returnUpdateResult = false;
		try {
			List<Long> competencies = DaoQuiz.getCompetenciesQuiz(conn,userCode);
			if(competencies != null){
				for (int i = 0; i < competencies.size(); i++) {
					returnInsert = DaoQuiz.insertAverage(conn, competencies.get(i), userCode);
				}
				if(returnInsert != false){
					returnInsert = DaoEnrolls.updateResult(conn, userCode);
				}
				if(returnInsert != false){
					returnInsert = DaoEnrolls.setVerified(conn, userCode, 1);
				}
			}
		}finally {
			return returnInsert;
		}
	}

	private static String getNumberOfQuestion(ResultSet rs) throws SQLException {
		return rs.getString("code");
	}
	private static List<Result> buildResultQuiz( Connection conn, ResultSet rs ) throws SQLException {
		List<Result> results = new LinkedList<Result>();
		do {
			results.add(buildResultStudents(conn,rs));
		} while ( rs.next() );
		return results;
	}
	
	private static Result buildResultStudents(Connection conn, ResultSet rs) throws SQLException {
		Result result  = new Result();
		Long aux2 = rs.getLong("usr_code");
		result.setComments(rs.getString("rst_comment"));
		result.setCourse(rs.getString("crs_name"));
		result.setInstitution(rs.getString("ist_company"));
		result.setPeriod(rs.getInt("ern_period"));
		result.setRa(rs.getString("usr_ra"));
		result.setName(rs.getString("usr_name"));
		result.setYear(rs.getInt("ern_year"));
		result.setCompetencies(buildResultsStudent(conn,rs));
		return result;
	}
	
	private static List<Competence> buildResultsStudent( Connection conn, ResultSet rs ) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		aux = rs.getLong("usr_code");
		do {
			Long aux2 = rs.getLong("usr_code");
			if(aux != aux2){
				auxCom = new Competence();
				auxCom.setCode(rs.getLong("com_code"));
				auxCom.setType(rs.getString("com_type"));
				auxCom.setWeight(rs.getInt("avr_final"));
				System.out.println(auxCom.getWeight());
				break;
			}else if(auxCom == null){
				Competence competence = new Competence();
				competence.setCode(rs.getLong("com_code"));
				competence.setType(rs.getString("com_type"));
				competence.setWeight(rs.getInt("avr_final"));
				competencies.add(competence);
			}else{
				Competence competence2 = new Competence();
				competence2.setCode(rs.getLong("com_code"));
				competence2.setType(rs.getString("com_type"));
				competence2.setWeight(rs.getInt("avr_final"));
				competencies.add(competence2); 
				
				Competence competence = new Competence();
				competence.setCode(auxCom.getCode());
				competence.setType(auxCom.getType());
				competence.setWeight(auxCom.getWeight());
				competencies.add(competence);
				auxCom = null;
			}
		} while ( rs.next());
		return competencies;
	}
	
	
	private static Result buildResult(Connection conn, ResultSet rs) throws SQLException {
		Result result  = new Result();
		result.setUserCode(rs.getLong("usr.usr_code"));
		result.setComments(rs.getString("rst_comment"));
		result.setCourse(rs.getString("crs_name"));
		result.setInstitution(rs.getString("ist_company"));
		result.setPeriod(rs.getInt("ern_period"));
		result.setRa(rs.getString("usr_ra"));
		result.setName(rs.getString("usr_name"));
		result.setYear(rs.getInt("ern_year"));
		result.setCompetencies(buildResults(conn,rs));
		return result;
	}
	
	private static List<Competence> buildResults( Connection conn, ResultSet rs ) throws SQLException {
		List<Competence> competencies = new LinkedList<Competence>();
		do {
			Competence competence = new Competence();
			competence.setCode(rs.getLong("com_code"));
			competence.setType(rs.getString("com_type"));
			competence.setWeight(rs.getInt("avr_final"));
			competencies.add(competence);
		} while ( rs.next() );
		return competencies;
	}
	
	private static Question buildQuestion(Connection conn, ResultSet rs) throws SQLException {
		Question question = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		question.setCode(rs.getLong("question.qst_code"));
		question.setQuestion(rs.getString("question.qst_question"));
		question.setIntroduction(rs.getString("question.qst_introduction"));
		question.setType(rs.getString("question.qst_type"));
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
	
	private static List<Long> buildCompetenciesToQuiz( final ResultSet rs ) throws SQLException {
		List<Long> competencies = new LinkedList<Long>();
		do {
			competencies.add(rs.getLong("code"));
		} while ( rs.next() );
		return competencies;
	}

}
