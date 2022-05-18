package personal_project.exception;

public class LoginValidationException extends RuntimeException {
    public LoginValidationException(String message) {
        super(message);
    }
}
