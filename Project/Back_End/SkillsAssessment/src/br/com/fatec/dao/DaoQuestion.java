package br.com.fatec.dao;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.question.Question; 

public class DaoQuestion {
	private static List getUnansweredQuestions(Long id) throws SQLException {

		Connection conn = ConnectionMySql.getConnection();
		List questions = new ArrayList();
		ResultSet rs = null;
		try {
			String query = "select min(question.qst_code) from question where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = ?) order by question.qst_code;";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			cmd.setString(1, id.toString());
			rs = cmd.executeQuery();
			while (rs.next()) {
				questions.add(rs.getString("question.qst_code"));
			}
		} catch (SQLException e) {
			// TODO: handle exceptions
		} finally {
			rs.close();
			conn.close();
			return questions;
		}
	}

	/*private static int getNextQuestion(List ids) {
		int nextQuestion = Integer.parseInt((String) ids.get(0));
		if (ids.size() > 1) {
			for (int i = 1; i < ids.size() - 1; i++) {
				if (Integer.parseInt((String) ids.get(0)) < nextQuestion) {
					nextQuestion = (Integer) ids.get(i);
				}
			}
		}
		return nextQuestion;
	}*/

	@SuppressWarnings("finally")
	public static Question getQuestion(Long id) throws SQLException {

		Connection conn = ConnectionMySql.getConnection();
		Question qst = new Question();
		ResultSet rs = null;
		try {
			String query = "select question.qst_question from  (select question.qst_code as qst_code,question.qst_question  ,qst_introduction,alternatives.alt_code,alt_description,"
					+" competence.com_code,competence.com_kind"
					+" from question inner join alternatives on alternatives.qst_code = question.qst_code "
					+" inner join alt_com on alt_com.alt_code = alternatives.alt_code "
					+" inner join competence on alt_com.com_code = competence.com_code"
					+" where question.qst_situation <> 1 and question.qst_code not in (select quiz.qst_code from quiz where std_code = 1) order by question.qst_code ) "
					+" as question where question.qst_code = 3;";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			//cmd.setString(1, id.toString());
			rs = cmd.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("question.qst_question"));
				//qst.setCode(Long.parseLong(rs.getString("question.qst_code")));
			}
		} catch (SQLException e) {
			// TODO: handle exceptions
		} finally {
			rs.close();
			conn.close();
			return qst;
		}
	}

	public static void main(String[] args) {
		DaoQuestion dao = new DaoQuestion();
		try {
			//List q = dao.getUnansweredQuestions((long) 1);
			
//			for(Object g: q){
//			System.out.println(g);
//			}
//
//			int num = dao.getNextQuestion(q);
//			System.out.println(num);
			Question d = new Question();
			d = dao.getQuestion((long)1);
			System.out.println(d.getCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
