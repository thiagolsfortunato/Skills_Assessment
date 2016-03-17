package br.com.fatec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Student;
import br.com.fatec.entity.User;

public class DaoStudent {
	
	@SuppressWarnings("finally")
	public static Long insertStudent(Student student) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		ResultSet idStudent = null;
		Long idInserted = null;
		
		User user = new User();
		user.setEmail(student.getEmail());
		user.setPassword(student.getPassword());
		user.setKindPerson(student.getKindPerson());

		try {
			conn.conect();
			String sql = "insert into STUDENT (std_name,std_ra,std_cpf,std_birth,std_cep,"
					+ "std_address,std_neighborhood,std_city,std_uf,std_number,std_complement,std_telephone,"
					+ "std_cellphone,std_registration_date,usr_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Long codeUser = DaoUser.insertUser(user);
			if(codeUser != null){
				conn.setStatement(conn.getConnection().prepareStatement( (sql), Statement.RETURN_GENERATED_KEYS));
				conn.getStatement().setString(1, student.getName());
				conn.getStatement().setString(2, student.getRa());
				conn.getStatement().setString(3, student.getCpf());
				conn.getStatement().setString(4, student.getBirthDay());
				conn.getStatement().setString(5, student.getCep());
				conn.getStatement().setString(6, student.getAddress());
				conn.getStatement().setString(7, student.getNeighborhood());
				conn.getStatement().setString(8, student.getCity());
				conn.getStatement().setString(9, student.getUf());
				conn.getStatement().setInt(10, student.getNumberHouse());
				conn.getStatement().setString(11, student.getComplement());
				conn.getStatement().setString(12, student.getTelephone());
				conn.getStatement().setString(13, student.getCellphone());
				conn.getStatement().setString(14, student.getRegister());//VERIFICAR SE VAI SER STRING ou DATE
				conn.getStatement().setLong(15, codeUser);
				
				if ( conn.executeSql() ) {
					System.out.println(">>Student has been successfully inserted!<<");
					idStudent = conn.getStatement().getGeneratedKeys();
					if (idStudent.next()) {
						System.out.println("id std_cod: " + idStudent.getLong(1));
						idInserted =  idStudent.getLong(1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.close();
			return idInserted;

		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deleteStudent(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean delete = false;
		try {
			conn.conect();
			String sql = "delete from STUDENT where USR_CODE = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setLong(1, code);
			if (conn.executeSql()) {
				delete = DaoUser.deleteUser(code);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getStatement().close();
			conn.close();
			return delete;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean updateStudent(Student student) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean update = false;
		try {
			String sql = "update STUDENT set std_name = ?, std_ra = ?, std_cpf = ?, std_birth = ?, std_cep = ?, std_address = ?,"
					+ "std_neighborhood = ?, std_city = ?, std_uf = ? , std_number = ?, std_complement = ?, std_telephone = ?, "
					+ " std_cellphone = ?, std_registration_date = ? where usr_code = ?";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setString(1, student.getName());
			conn.getStatement().setString(2, student.getRa());
			conn.getStatement().setString(3, student.getCpf());
			conn.getStatement().setString(4, student.getBirthDay());
			conn.getStatement().setString(5, student.getCep());
			conn.getStatement().setString(6, student.getAddress());
			conn.getStatement().setString(7, student.getNeighborhood());
			conn.getStatement().setString(8, student.getCity());
			conn.getStatement().setString(9, student.getUf());
			conn.getStatement().setInt(10, student.getNumberHouse());
			conn.getStatement().setString(11, student.getComplement());
			conn.getStatement().setString(12, student.getTelephone());
			conn.getStatement().setString(12, student.getCellphone());
			conn.getStatement().setString(12, student.getRegister());
			conn.getStatement().setLong(12, student.getUserCode());
			if (conn.executeSql()) {
				update = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getStatement().close();
			conn.close();
			return update;
		}

	}

	public static Student searchStudentById(Long idStudent) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		 //join table with User to bring the rest of the information
		String query = "SELECT * FROM student s INNER JOIN "
						+ "user u ON s.usr_code = u.usr_code WHERE s.usr_code = ?;";
		Student student = new Student();
		try{
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));//PREPARO A QUERY
			conn.getStatement().setString( 1, idStudent.toString() );//SETO A QUERY
			
			if(conn.executeQuery()){
				System.out.println("entrou no if da DAO");
				student = buildStudent(conn.returnRegister());
			}
			return student;
		} catch (Exception e){
			throw new RuntimeException(e);
		
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
		}
		
	}

	public static List<Student> findAll() throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		String query = "SELECT * FROM student s INNER JOIN "
		 				+ "USER u WHERE s.usr_code = u.usr_code;";// ORDER BY std_code DESC
		try{
			 conn.conect();
			 conn.setStatement(conn.getConnection().prepareStatement(query));//PREPARO A QUERY
			 if(conn.executeQuery()){
				 return buildStudents(conn);
			 }
		 } catch (Exception e){
			 throw new RuntimeException(e);
		 
		 } finally {
			 conn.getResultset().close();
			 conn.getStatement().close();
			 conn.close();;
		 }
		return null;
	}

	//AUXILIAR A CONSTRUIR UMA LISTA DE ALUNOS
	private static List<Student> buildStudents(ConnectionMySql conn) throws SQLException{
		List<Student> students = Lists.newArrayList();
		do{
			students.add( buildStudent(conn.returnRegister()) );
		}while(conn.nextRegister());
		return students;
	}
	//AUXILIA A CONSTRUIR UM UNICO ALUNO
	private static Student buildStudent(ResultSet rs) throws SQLException {
		Student student = new Student();
			student.setIdStudent( Long.parseLong(rs.getString("STD_CODE")) );
			student.setName( rs.getString("STD_NAME") );
			//terminar outros atributos
			
		return student;
	} 

}