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

	@SuppressWarnings("deprecation")
	public static Student searchStudentByCode(Integer code) throws SQLException {
		Student student = new Student();
		Connection conn = ConnectionMySql.getConnection();
		String query = "select alu_codigo , alu_nome, alu_ra  from aluno where usu_codigo = " + code +";"; //MUDEI
		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			student.setNumber(Long.parseLong(rs.getString(Student.COL_CODIGO))); // CONVERT TO LONG
			student.setName(rs.getString(Student.COL_NOME));
			student.setRa(rs.getString(Student.COL_RA));
			/*student.setCpf(rs.getString(Student.COL_CPF));
			student.setBirthDay(new Date(rs.getString(Student.COL_NASCIMENTO))); // CONVERTER TO DATE
			student.setCep(rs.getString(Student.COL_CEP));
			student.setAddress(rs.getString(Student.COL_ENDERECO));
			student.setNeighborhood(rs.getString(Student.COL_BAIRRO));
			student.setCity(rs.getString(Student.COL_CIDADE));
			student.setUf(rs.getString(Student.COL_UF));
			student.setNumberHouse(Integer.parseInt(rs.getString(Student.COL_NUMERO)));
			student.setComplement(rs.getString(Student.COL_COMPLEMENTO));
			student.setTelephone(rs.getString(Student.COL_TELEFONE));
			student.setCellphone(rs.getString(Student.COL_CELULAR));
			student.setRegister(new Date(rs.getString(Student.COL_DATA_CADASTRO)));
			student.setUser_register(Long.parseLong(rs.getString(Student.COL_USUARIO_CADASTRO)));
			student.getUser().setUserCode(Integer.parseInt(rs.getString(Student.COL_USUARIO_CODIGO)));
			student.getUser().setUserName(rs.getString(User.COL_LOGIN));
			student.getUser().setPassword(rs.getString(User.COL_SENHA));
			student.getUser().setSituation(Integer.parseInt(rs.getString(User.COL_SITUACAO)));
			student.getUser().setVerification(Integer.parseInt(rs.getString(User.COL_VERIFICACAO)));
			student.getUser().setKindPerson(rs.getString(User.COL_TIPO));
			student.getUser().setToken(rs.getString(User.COL_TOKEN));*/
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