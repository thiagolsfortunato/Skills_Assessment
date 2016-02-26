package br.com.fatec.controller;

import static spark.Spark.get;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class EmployeeRoutes {
	ModelEmployee modelEmployee = new ModelEmployee();
	/*
	 * CODIGO COMENTADO PARA ALTERAR A CLASSE PARA O NOVO FORMATO DE JSON
	 * PASSANDO COMO PARAMETRO UM OBJETO !!!!!
	 */

	public void getEmployee() {

		/*
		 * get("/employee/add/userEmployee" (req,res)) public static
		 * ArrayList<String> readJsonFromUrl(String url) throws IOException,
		 * JSONException { InputStream is = new URL(url).openStream(); try {
		 * BufferedReader rd = new BufferedReader(new InputStreamReader(is,
		 * Charset.forName("UTF-8"))); String jsonText = readAll(rd); JSONArray
		 * json = new JSONArray(jsonText);
		 * 
		 * ArrayList<String> listdata = new ArrayList<String>();
		 * 
		 * if (json != null) { for (int i=0;i<json.length();i++){
		 * listdata.add(json.get(i).toString()); } } return listdata; } finally
		 * { is.close(); } }
		 */

		// get(new
		// Route("/employee/add/:code/:name/:cpf/:birthday/:cep/:address/:neighborhood/:city/:uf/:numberHouse/:complement/:telephone/:cellphone/:register/:codeUser")

		// ADD
		get("/employee/add/:code/:name/:cpf/:birthday/:cep/:address/:neighborhood/:city/:uf/:numberHouse/:complement/:telephone/:cellphone/:register/:codeUser",
				(request, response) -> {
					Long code = Long.parseLong(request.params(":code"));
					String name = request.params(":name");
					String cpf = request.params(":cpf");
					Date birthday = Date.valueOf(request.params(":birthday"));
					String cep = request.params(":cep");
					String address = request.params(":address");
					String neighborhood = request.params(":neighborhood");
					String city = request.params(":city");
					String uf = request.params(":uf");
					Integer numberHouse = Integer.parseInt(request.params(":numberHouse"));
					String complement = request.params(":complement");
					String telephone = request.params(":telephone");
					String cellphone = request.params(":cellphone");
					Date register = Date.valueOf(request.params(":register"));
					Long codeUser = Long.parseLong(request.params(":codeUser"));

					JSONArray jsonResult = new JSONArray();
					JSONObject jsonObj = new JSONObject();

					boolean resultAddEmployee = modelEmployee.addEmployee(new Employee(code, name, cpf, birthday, cep,
							address, neighborhood, city, uf, numberHouse, complement, telephone, cellphone, register),
							codeUser);

					try {
						jsonObj.put("ResultAddEmployee", resultAddEmployee);
						jsonResult.put(jsonObj);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					return jsonResult;
				});

		// delete
		get("/employee/delete/:code", (request, response) -> {
			Long code = Long.parseLong(request.params(":code"));

			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			boolean resultDeleteEmployee = modelEmployee.deleteEmployee(code);

			try {
				jsonObj.put("ResultDeleteEmployee", resultDeleteEmployee);
				jsonResult.put(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return jsonResult;

		});

		// update decidir como fazer !!

		get("/employee/update/:code/:name/:cpf/:birthday/:cep/:address/:neighborhood/:city/:uf/:numberHouse/:complement/:telephone/:cellphone/:register/:codeUser",
				(request, response) -> {
					Long code = Long.parseLong(request.params(":code"));
					String name = request.params(":name");
					String cpf = request.params(":cpf");
					Date birthday = Date.valueOf(request.params(":birthday"));
					String cep = request.params(":cep");
					String address = request.params(":address");
					String neighborhood = request.params(":neighborhood");
					String city = request.params(":city");
					String uf = request.params(":uf");
					Integer numberHouse = Integer.parseInt(request.params(":numberHouse"));
					String complement = request.params(":complement");
					String telephone = request.params(":telephone");
					String cellphone = request.params(":cellphone");
					Date register = Date.valueOf(request.params(":register"));
					Long codeUser = Long.parseLong(request.params(":codeUser"));

					Employee employeeUpdate = modelEmployee.searchEmployeeByCode(code);

					JSONArray jsonResult = new JSONArray();
					JSONObject jsonObj = new JSONObject();

					employeeUpdate.setName(name);
					employeeUpdate.setCpf(cpf);

					boolean resultAddEmployee = modelEmployee.addEmployee(new Employee(code, name, cpf, birthday, cep,
							address, neighborhood, city, uf, numberHouse, complement, telephone, cellphone, register),
							codeUser);

					try {
						jsonObj.put("ResultAddEmployee", resultAddEmployee);
						jsonResult.put(jsonObj);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					return jsonResult;
				});

		// search all
		get("/employee/searchAll", (request, response) -> {
			List<Employee> listEmployee = modelEmployee.searchAllEmployee();
			JSONArray jsonResult = new JSONArray();

			for (Employee employee : listEmployee) {
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj.put("code", employee.getCode());
					jsonObj.put("name", employee.getName());
					jsonObj.put("cpf", employee.getCpf());
					jsonObj.put("birth", employee.getBirthDay());
					jsonObj.put("cep", employee.getCep());
					jsonObj.put("address", employee.getAddress());
					jsonObj.put("neighbothood", employee.getNeighborhood());
					jsonObj.put("city", employee.getCity());
					jsonObj.put("number", employee.getNumberHouse());
					jsonObj.put("telephone", employee.getTelephone());
					jsonObj.put("cellphone", employee.getCellphone());
					jsonObj.put("registration_date", employee.getRegistration_date());
					jsonObj.put("user_code", employee.getUserCode());

					jsonResult.put(jsonObj);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return jsonResult;
		});

		// search by id

		get("/employee/searchById/:id", (request, response) -> {
			Long code = Long.parseLong(request.params(":id"));
			Employee employee = modelEmployee.searchEmployeeByCode(code);

			JSONArray jsonResult = new JSONArray();
			JSONObject jsonObj = new JSONObject();

			try {
				jsonObj.put("code", employee.getCode());
				jsonObj.put("name", employee.getName());
				jsonObj.put("cpf", employee.getCpf());
				jsonObj.put("birth", employee.getBirthDay());
				jsonObj.put("cep", employee.getCep());
				jsonObj.put("address", employee.getAddress());
				jsonObj.put("neighbothood", employee.getNeighborhood());
				jsonObj.put("city", employee.getCity());
				jsonObj.put("number", employee.getNumberHouse());
				jsonObj.put("telephone", employee.getTelephone());
				jsonObj.put("cellphone", employee.getCellphone());
				jsonObj.put("registration_date", employee.getRegistration_date());
				jsonObj.put("user_code", employee.getUserCode());

			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonResult.put(jsonObj);
			return jsonResult;
		});
	}
}
