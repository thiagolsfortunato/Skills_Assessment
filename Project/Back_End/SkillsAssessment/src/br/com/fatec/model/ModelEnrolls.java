package br.com.fatec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.connection.ConnectionFactory;
import br.com.fatec.dao.DaoEnrolls;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.entity.Enrolls;
import br.com.fatec.entity.User;

public class ModelEnrolls {
	
	//conexão com o banco de dados
	private Connection conn = null;
	
	@SuppressWarnings("finally")
	public boolean insertEnrolls(Enrolls enrolls, User user){
		
		boolean statusUser = false;
		boolean statusEnrolls = false;
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			statusUser = DaoUser.insertUser(conn, user);
			//resultado da inserção do usuario + estudante
			statusEnrolls = DaoEnrolls.insertEnrolls(conn, enrolls, user.getUserCode());
			
			if(statusEnrolls && statusUser ) {
				conn.commit(); //se deu tudo certo comita!
				transaction = true;
			}
			else conn.rollback();
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateEnrolls(Enrolls enrolls){
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction =  DaoEnrolls.updateEnrolls(conn, enrolls);
			
			if (transaction) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteEnrolls(Long code){
		
		boolean transaction = false;
		
		try{
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			transaction = DaoEnrolls.deleteEnrolls(conn, code);
			
			if (transaction) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}

	@SuppressWarnings("finally")
	public Enrolls searchEnrollsByCode(Long code) {
		Enrolls enrolls = null;
		try{
			conn = new ConnectionFactory().getConnection();
			enrolls = DaoEnrolls.searchEnrollsById(conn, code);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return enrolls;
		}
	}

	@SuppressWarnings("finally")
	public List<Enrolls> searchAllEnrolls(){
		List<Enrolls> enrolls = null;
		try{
			conn = new ConnectionFactory().getConnection();
			enrolls = DaoEnrolls.searchAllEnrolls(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return enrolls;
		}
	}
}
