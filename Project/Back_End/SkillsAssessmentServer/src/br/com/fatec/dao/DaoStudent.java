package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.model.user.User;
import br.com.fatec.model.student.Student;

public class DaoStudent {

	public static Student searchStudentByCode(Integer code) throws SQLException {
		Student student = new Student();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select STD_CODE, STD_NAME, STD_RA, STD_CPF, STD_BIRTH, STD_CEP, STD_ADDRESS, STD_NEIGHBORHOOD, STD_CITY, STD_UF, STD_NUMBER, STD_COMPLEMENT, STD_TELEPHONE, STD_CELLPHONE, STD_REGISTRATION_DATE, STD_USER_REGISTER, STUDENT.USR_CODE, USR_USERNAME, USR_PASSWORD, USR_SITUATION,USR_VERIFIED,USR_KIND from STUDENT INNER JOIN USER ON STUDENT.USR_CODE = USER.USR_CODE where STUDENT.USR_CODE = " + code +";"; //join table with User to bring the rest of the information
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			student.setNumber(Long.parseLong(rs.getString("STD_CODE"))); // CONVERT TO LONG
			student.setName(rs.getString("STD_NAME"));
			student.setRa(rs.getString("STD_RA"));
			student.setCpf(rs.getString("STD_CPF"));
			//student.setBirthDay(new Date(rs.getString("STD_BIRTH"))); // CONVERTER TO DATE
			student.setCep(rs.getString("STD_CEP"));
			student.setAddress(rs.getString("STD_ADDRESS"));
			student.setNeighborhood(rs.getString("STD_NEIGHBORHOOD"));
			student.setCity(rs.getString("STD_CITY"));
			student.setUf(rs.getString("STD_UF"));
			student.setNumberHouse(Integer.parseInt(rs.getString("STD_NUMBER")));
			student.setComplement(rs.getString("STD_COMPLEMENT"));
			student.setTelephone(rs.getString("STD_TELEPHONE"));
			student.setCellphone(rs.getString("STD_CELLPHONE"));
			//student.setRegister(new Date(rs.getString("STD_REGISTRATION_DATE")));
			student.setUser_register(Long.parseLong(rs.getString("STD_USER_REGISTER")));
			student.setUserCode(Integer.parseInt(rs.getString("STUDENT.USR_CODE")));
			student.setPassword(rs.getString("USR_PASSWORD"));
			student.setUserName(rs.getString("USR_USERNAME"));
			student.setSituation(Integer.parseInt(rs.getString("USR_SITUATION")));
			student.setVerification(Integer.parseInt(rs.getString("USR_VERIFIED")));
			student.setKindPerson(rs.getString("USR_KIND"));
		}
		
		rs.close();
		conn.close();
		return student;
	}

	/*public void adiciona(Usuario usuario) {
		String sql = "INSERT INTO usuario(nome,cpf,email,telefone) VALUES(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getCpf());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getTelefone());
			stmt.execute();
			stmt.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	try

	{
		// Registra JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		// Abrindo a conexão: ATENÇÃO OS DOIS PARÂMETROS VAZIOS("") SÃO USUÁRIO
		// E SENHA, RESPECTIVAMENTE.
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/escola?zeroDateTimeBehavior=convertToNull", "", "");

		// Executa a query de inserção
		java.sql.Statement st = conn.createStatement();
		st.executeUpdate("INSERT INTO aluno (id,nome,cpf) VALUES (" + this.jTextFieldId.getText() + ",'"
				+ this.jTextFieldNome.getText() + "','" + this.jTextFieldCPF.getText() + "')");

		JOptionPane.showMessageDialog(rootPane, "Aluno inserido");
	} catch(SQLException||

	ClassNotFoundException e)

	{
		JOptionPane.showMessageDialog(rootPane, e);
	}

	String sql = "INSERT INTO Users (username, password, fullname, email) VALUES (?, ?, ?, ?)";

	PreparedStatement statement = conn.prepareStatement(
			sql);statement.setString(1,"bill");statement.setString(2,"secretpass");statement.setString(3,"Bill Gates");statement.setString(4,"bill.gates@microsoft.com");

	int rowsInserted = statement.executeUpdate();if(rowsInserted>0)

	{
		System.out.println("A new user was inserted successfully!");
	}

	String sql = "UPDATE Users SET password=?, fullname=?, email=? WHERE username=?";

	PreparedStatement statement = conn.prepareStatement(
			sql);statement.setString(1,"123456789");statement.setString(2,"William Henry Bill Gates");statement.setString(3,"bill.gates@microsoft.com");statement.setString(4,"bill");

	int rowsUpdated = statement.executeUpdate();if(rowsUpdated>0)

	{
		System.out.println("An existing user was updated successfully!");
	}

	String sql = "DELETE FROM Users WHERE username=?";

	PreparedStatement statement = conn.prepareStatement(sql);statement.setString(1,"bill");

	int rowsDeleted = statement.executeUpdate();if(rowsDeleted>0)

	{
		System.out.println("A user was deleted successfully!");
	}*/

}