	package supun.com.main.exception;
	
	import org.springframework.http.HttpStatus;
	import org.springframework.web.bind.annotation.ResponseStatus;


	// class for the custom exception
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class ResourceNotFoundException extends Exception{
	
		private static final long serialVersionUID = 1L;
	
		public ResourceNotFoundException(String message){
	    	super(message);
	    }
	}
