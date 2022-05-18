package personal_project.exception;

public class JwtExpiredTokenException extends RuntimeException {
    public JwtExpiredTokenException(String message) {
        super(message);
    }
}
