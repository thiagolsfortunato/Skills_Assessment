package br.com.fatec.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;

public class DaoEmployee {

	public void searchForName() throws SQLException {
		Connection conn = ConnectionMySql.getConnection();
		String query = "select * from funcionario;";
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("fun_nome"));
		}
		rs.close();
	}
}
