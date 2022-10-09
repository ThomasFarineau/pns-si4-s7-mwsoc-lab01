package exceptions;

public class SignUpFailed extends Exception {
    /**
     * Exception thrown when the user tries to sign up with an already existing email
     */
    @Override
    public void printStackTrace() {
        System.out.println("Email already used !");
    }
}
