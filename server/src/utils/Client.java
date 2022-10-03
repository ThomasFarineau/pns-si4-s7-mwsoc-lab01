package utils;

public class Client {
    private String mail;
    private String pwd;

    public Client(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }
}
