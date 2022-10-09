package exceptions;

public class InvalidCredentialsException extends Exception {

    /**
     * Exception thrown when the user tries to log in with invalid credentials
     */
    @Override
    public void printStackTrace() {
        System.out.println("Invalid credentials !");
    }
}
