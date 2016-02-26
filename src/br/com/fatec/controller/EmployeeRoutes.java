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
 * 

	public void getEmployee() {
		// ADD
		get(new Route(
				"/employee/add/:code/:name/:cpf/:birthday/:cep/:address/:neighborhood/:city/:uf/:numberHouse/:complement/:telephone/:cellphone/:register/:codeUser") {
			@Override
			public Object handle(Request request, Response response) {
				// allows everyone to access the resource
				response.header("Access-Control-Allow-Origin", "*");

				Long code = Long.parseLong(request.params(":preco"));
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

				// falta implementação add user

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
			}
		});

		// delete
		get(new Route("/employee/delete/:code") {

			@Override
			public Object handle(Request request, Response response) {
				// allows everyone to access the resource
				response.header("Access-Control-Allow-Origin", "*");

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
			}
		});

		// update decidir como fazer !!

		/*get(new Route("/employee/update/:employee") {

			@Override
			public Object handle(Request request, Response response) {
				// allows everyone to access the resource
				response.header("Access-Control-Allow-Origin", "*");

				List<Employee> listEmployee = modelEmployee.updateEmployee(, code);
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
			}
		});

		// search all
		get(new Route("/employee/searchAll") {

			@Override
			public Object handle(Request request, Response response) {

				// allows everyone to access the resource
				response.header("Access-Control-Allow-Origin", "*");

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
			}
		});

		// search by id
		get(new Route("/employee/searchById/:id") {

			@Override
			public Object handle(Request request, Response response) {

				// allows everyone to access the resource
				response.header("Access-Control-Allow-Origin", "*");

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
			}
		});
	}
*/
}
