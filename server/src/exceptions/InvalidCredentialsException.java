package exceptions;

public class InvalidCredentialsException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Identifiants incorrects !");
    }
}
