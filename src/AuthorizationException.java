import java.io.Serializable;

public class AuthorizationException extends Exception implements Serializable {
	public AuthorizationException(String message){
		super(message);
	}
}
