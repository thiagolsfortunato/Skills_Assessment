package br.com.fatec.controller;

import static spark.Spark.post;
import static spark.Spark.get;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.fatec.dao.DaoEmployee;
import br.com.fatec.dao.DaoStudent;
import br.com.fatec.dao.DaoUser;
import br.com.fatec.dao.Token;
import br.com.fatec.model.ModelEmployee;
import br.com.fatec.model.ModelStudent;
import br.com.fatec.model.ModelUser;
import br.com.fatec.model.employee.Employee;
import br.com.fatec.model.student.Student;
import br.com.fatec.model.token.TokenInfo;
import br.com.fatec.model.user.User;
//import br.com.fatec.model.user.UserLogin;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserRoutes {
	private String loginData = null;
	private String token = null;

	public void getLogin() {
		ModelUser login = new ModelUser();

		//minha idéia, é só devolver o TOKEN do usuário
		post("/login", (req, res) -> {
//			token = req.headers("token");
			loginData = req.body();
			Gson gson = new Gson();

			User user = gson.fromJson(loginData, User.class);
			System.out.println("Senha: " + user.getPassword());
			try{
				user = login.getLogin(user.getUserName(), user.getPassword());

				/*if ( user.getKindPerson().equals("student") ) {
					System.out.println("aqui funfou");
					System.out.println("TOKEN: " + user.getToken());
					ModelStudent modelSt = new ModelStudent();
					Student stu = modelSt.searchStudentById(user.getUserCode());
					if( user.getToken() == null ){
						TokenInfo token = Token.verifyToken( Token.createJsonWebToken(String.valueOf(user.getUserCode()), Long.parseLong("1")) ); 
						return Token.verifyToken( Token.createJsonWebToken(String.valueOf(user.getUserCode()), Long.parseLong("1")) );
					}				
				} */
				
				return user.getToken();
				// res.status(400);
			} catch (NullPointerException e){
				e.printStackTrace();
				return "ops, algum erro com LOGIN, verifique os campos!";
			}
			return null;
		} , JsonUtil.json());
	}
	
	public void getToken() {
		//aqui, apartir do token saber a quem pertence e fazer sub-chamadas
		get("/token", (req, res) -> {
			token = req.headers("token");
						
			try{
				TokenInfo tokenInfo = Token.verifyToken(token);
				switch (tokenInfo.getkindPerson()){ //ATENÇÃO-> ADICIONAR ATRIBUTO!
					case "student":
						//alguma coisa.
						//chama StudentRoutes
							//passando id;
						break;
					case "employee":
						//alguma coisa.
						//chama EmployeeRoutes
						//passando id;
						break;
					default:
						//alguma coisa.
						break;	
				}
				
			} catch (NullPointerException e){
				e.printStackTrace();
				return "ops, algum erro com TOKEN!";
			}
			return null;
		} , JsonUtil.json());
	}


}
