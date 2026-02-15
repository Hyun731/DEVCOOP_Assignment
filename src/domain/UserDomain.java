package domain;

public class UserDomain {
    private String id;
    private String pw;

    private UserDomain() {}

    public static UserDomain create(String id, String pw) {
        UserDomain userDomain = new UserDomain();
        userDomain.id = id;
        userDomain.pw = pw;
        return userDomain;
    }

    public String getId() {
        return id;
    }

    public boolean isPassword(String pw) {
        return this.pw.equals(pw);
    }
}
