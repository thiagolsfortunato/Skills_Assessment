package br.com.fatec.dao;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Answer;
import br.com.fatec.entity.Competence;
import br.com.fatec.entity.Question; 

public class DaoQuestion {
	private static String getUnansweredQuestions(Long id) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String question = null ;
		ResultSet rs = null;
		try {
			String query = "select min(qst_code) as code from question where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = ?);";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			cmd.setString(1, id.toString());
			rs = cmd.executeQuery();
			if (rs != null) {
				rs.next();
				question = rs.getString("code");;
			}
		} catch (SQLException e) {
			System.out.println("an error occurred while taking the issue"+e);
		} finally {
			rs.close();
			return question;
		}
	}
	
	public static Question getQuestion(Long id) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		Question qst = new Question();
		List<Answer> answers = new LinkedList<Answer>();
		ResultSet rs = null;
		
		try {
			String question = getUnansweredQuestions(id);
			String query = "select * from  (select question.qst_code as qst_code,question.qst_question  ,qst_introduction from question "
					 +"where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = ?) order by question.qst_code ) "
					 +"as question where question.qst_code = ?;";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			cmd.setString(1, id.toString());
			cmd.setString(2, question);
			rs = cmd.executeQuery();
			
			if (rs != null ){
				rs.next();
				qst.setCode(Long.parseLong(rs.getString("question.qst_code")));
				qst.setQuestion(rs.getString("question.qst_question"));
				qst.setIntroduction(rs.getString("question.qst_introduction"));
				answers = getAnswers(Long.parseLong(rs.getString("question.qst_code")));
				qst.setAnswers(answers);
			}
			
		} catch (SQLException e) {
			System.out.println("It was not possible to get the issue "+e);
		} finally {
			rs.close();
			conn.close();
			return qst;
		}
	}
	
	public static List<Answer> getAnswers(Long id) throws SQLException {

		ConnectionMySql connection = new ConnectionMySql();
		
		List<Answer> answers = new LinkedList<Answer>();
		List<Competence> competencies = new LinkedList<Competence>();
		Answer answer = null;
		ResultSet rs = null;
		
		try {
			String query = "select * from alternatives where qst_code = ?;";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			cmd.setString(1, id.toString());
			rs = cmd.executeQuery();
			while (rs.next()){
				answer = new Answer();
				answer.setCode(Long.parseLong(rs.getString("alt_code")));
				answer.setDescription(rs.getString("alt_description"));
				competencies = getCompetencies(Long.parseLong(rs.getString("alt_code")));
				answer.setCompetencies(competencies);
				answers.add(answer);
			}
		} catch (SQLException e) {
			System.out.println("It was not possible to get the answers "+e);
		} finally {
			return answers;
		}
	}
	
	public static List<Competence> getCompetencies(Long id) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		List<Competence> competencies = new LinkedList<Competence>();
		Competence competence = null;
		ResultSet rs = null;
		
		try {
			String query = "select competence.com_code,alt_com.alt_code, com_kind, rsc_weight from competence"
							+" inner join alt_com on alt_com.com_code = competence.com_code  where alt_com.alt_code = ?;";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			cmd.setString(1, id.toString());
			rs = cmd.executeQuery();
			while (rs.next()){
				competence = new Competence();
				competence.setCode(Long.parseLong(rs.getString("competence.com_code")));
				competence.setKind(rs.getString("com_kind"));
				competence.setWeight(Integer.parseInt(rs.getString("rsc_weight")));
				competencies.add(competence);
			}
			
		} catch (SQLException e) {
			System.out.println("It was not possible to get the competencies "+e);
		} finally {
			return competencies;
		}
	}
	
	public static void main(String[] args) {
		DaoQuestion dao = new DaoQuestion();
		try {
			Question d = new Question();
			d = dao.getQuestion((long)1);
			//System.out.println(d.getCode());
			//System.out.println(d.getQuestion());
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
