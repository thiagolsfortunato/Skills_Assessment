package br.com.fatec.connection;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.utils.IOUtils;

public class CorsFilter {
	 private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();
	    
	    static {
	    	corsHeaders.put("Access-Control-Allow-Origin", "*");
	        //corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
	        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Foo-Headers");
	        //corsHeaders.put("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Accept, Content-Length, Accept-Encoding, Accept-Language, Access-Control-Request-Method, Connection, Host, Origin, Referer, User-Agent, Client-Security-Token");
	        corsHeaders.put("Access-Control-Allow-Credentials", "true");
	        corsHeaders.put("Access-Control-Max-Age", "86400");
	        
	        corsHeaders.put("Access-Control-Request-Methods", "GET, POST, PUT, DELETE");
	        //corsHeaders.put("Access-Control-Request-Headers", "X-Requested-With,Access-Control-Request-Method,Access-Control-Request-Headers, accept, Content-Type");
	    }

	    public final static void apply() {
	        Filter filter = new Filter() {
	            @Override
	            public void handle(Request request, Response response) throws Exception {
	                corsHeaders.forEach((key, value) -> {
	                    response.header(key, value);
	                });
	            }
	        };
	        
	        Spark.before(filter);
	    }
	    
	        

		public static HashMap<String, String> getCorsheaders() {
			return corsHeaders;
		}
	   
	    
}
