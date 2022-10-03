package exceptions;

public class SignUpFailed extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("E-mail déjà existant !");
    }
}
