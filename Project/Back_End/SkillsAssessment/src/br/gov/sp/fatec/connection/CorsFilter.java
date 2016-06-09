package br.gov.sp.fatec.connection;
import java.util.HashMap;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;


public class CorsFilter {
	 private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();
	    
	    static {
	    	corsHeaders.put("Access-Control-Allow-Origin", "*");
	        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
	        corsHeaders.put("Access-Control-Allow-Headers", "token, Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Foo-Headers");
	        corsHeaders.put("Access-Control-Allow-Credentials", "true");
	        corsHeaders.put("Access-Control-Max-Age", "86400");
	        
	        corsHeaders.put("Access-Control-Request-Methods", "GET, POST, PUT, DELETE");
	        
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
