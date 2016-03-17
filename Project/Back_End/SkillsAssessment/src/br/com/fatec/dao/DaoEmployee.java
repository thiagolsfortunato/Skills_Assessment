package br.com.fatec.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.google.common.collect.Lists;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Employee;
import br.com.fatec.entity.User;

public class DaoEmployee {

	@SuppressWarnings("finally")
	public static boolean addEmployee(Employee employee) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean insert = false;
		User user = new User();
		user.setEmail(employee.getEmail());
		user.setPassword(employee.getPassword());
		user.setKindPerson(employee.getKindPerson());

		try {
			conn.conect();
			String sql = "insert into EMPLOYEE (emp_name, emp_cpf, emp_birth, emp_cep, emp_address, emp_neighborhood,"
					+ "emp_city, emp_number, emp_telephone, emp_cellphone, emp_registration_date, usr_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setString(1, employee.getName());
			conn.getStatement().setString(2, employee.getCpf());
			conn.getStatement().setDate(3, employee.getBirthDay());
			conn.getStatement().setString(4, employee.getCep());
			conn.getStatement().setString(5, employee.getAddress());
			conn.getStatement().setString(6, employee.getNeighborhood());
			conn.getStatement().setString(7, employee.getCity());
			conn.getStatement().setLong(8, employee.getNumberHouse());
			conn.getStatement().setString(9, employee.getTelephone());
			conn.getStatement().setString(10, employee.getCellphone());
			conn.getStatement().setDate(11, employee.getRegistration_date());
			conn.getStatement().setLong(12, DaoUser.insertUser(user));

			if (conn.executeSql()) {
				insert = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return insert;

		}
	}

	@SuppressWarnings("finally")
	public static boolean deleteEmployee(Long code) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean delete = false;
		try {
			conn.conect();
			String sql = "delete from Employee where USR_CODE = ?;";
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setLong(1, code);
			if (conn.executeSql()) {
				delete = DaoUser.deleteUser(code);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return delete;
		}
	}

	@SuppressWarnings({ "null", "finally" })
	public static List<Employee> searchAll() throws SQLException {
		List<Employee> listEmployee = null;
		ConnectionMySql conn = new ConnectionMySql();
		try {

			String query = "select (emp_name, emp_cpf, emp_birth, emp_cep, emp_address, emp_neighborhood,"
					+ "emp_city, emp_number, emp_telephone, emp_cellphone, emp_registration_date,usr_code) from EMPLOYEE ;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			if (conn.executeQuery()) {
				listEmployee = buildEmployees(conn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return listEmployee;
		}
	}

	@SuppressWarnings("finally")
	public static Employee searchByCode(Long code) throws SQLException {
		Employee employee = new Employee();
		ConnectionMySql conn = new ConnectionMySql();
		try {

			String query = "select emp_name, emp_cpf, emp_birth, emp_cep, emp_address, emp_neighborhood,"
					+ "emp_city, emp_number, emp_telephone, emp_cellphone, emp_registration_date,usr_code from EMPLOYEE where USR_CODE = ?;";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(query));
			conn.getStatement().setLong(1, code);
			if (conn.executeQuery()) {
				employee = buildEmployee(conn.returnRegister());
			}
		} catch (Exception e) {
			System.out.println("erro "+e);
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return employee;
		}
	}

	@SuppressWarnings("finally")
	public static boolean update(Employee employee) throws SQLException {
		ConnectionMySql conn = new ConnectionMySql();
		boolean update = false;
		try {
			String sql = "update employee set emp_name = ?, emp_cpf = ?, emp_birth = ?, emp_cep = ?, emp_address = ?, emp_neighborhood = ?,"
					+ "emp_city = ?, emp_number = ?, emp_telephone = ? , emp_cellphone = ?, emp_registration_date = ? "
					+ " where usr_code = ?";
			conn.conect();
			conn.setStatement(conn.getConnection().prepareStatement(sql));
			conn.getStatement().setString(1, employee.getName());
			conn.getStatement().setString(2, employee.getCpf());
			conn.getStatement().setDate(3, employee.getBirthDay());
			conn.getStatement().setString(4, employee.getCep());
			conn.getStatement().setString(5, employee.getAddress());
			conn.getStatement().setString(6, employee.getNeighborhood());
			conn.getStatement().setString(7, employee.getCity());
			conn.getStatement().setLong(8, employee.getNumberHouse());
			conn.getStatement().setString(9, employee.getTelephone());
			conn.getStatement().setString(10, employee.getCellphone());
			conn.getStatement().setDate(11, employee.getRegistration_date());
			conn.getStatement().setLong(12, employee.getUserCode());
			if (conn.executeSql()) {
				update = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			conn.getResultset().close();
			conn.getStatement().close();
			conn.close();
			return update;
		}

	}

	private static List<Employee> buildEmployees(ConnectionMySql conn) throws SQLException {
		List<Employee> employee = Lists.newArrayList();
		do {
			employee.add(buildEmployee(conn.returnRegister()));
		} while (conn.nextRegister());
		return employee;
	}

	private static Employee buildEmployee(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setName(rs.getString("EMP_NAME"));
		employee.setCpf(rs.getString("EMP_CPF"));
		//employee.setBirthDay(Date.valueOf(rs.getString("EMP_BIRTH")));
		employee.setCep(rs.getString("EMP_CEP"));
		employee.setAddress(rs.getString("EMP_ADDRESS"));
		employee.setNeighborhood(rs.getString("EMP_NEIGHBORHOOD"));
		employee.setNumberHouse(Integer.parseInt(rs.getString("EMP_NUMBER")));
		employee.setTelephone(rs.getString("EMP_TELEPHONE"));
		employee.setCellphone(rs.getString("EMP_CELLPHONE"));
		//employee.setRegistration_date(Date.valueOf(rs.getString("EMP_REGISTRATION_DATE")));
		employee.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
		return employee;
	}
}
