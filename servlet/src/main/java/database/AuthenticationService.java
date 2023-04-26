package database;

public class AuthenticationService {
    DataBase dataBase = DataBase.getInstance();
    private boolean isAuthenticated = false;
    private int studentId;
    private static AuthenticationService instance = null;

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    private AuthenticationService() { }

    private void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean authenticate(int id, String password) {
        if (!dataBase.isRegisteredStudent(id)) {
            isAuthenticated = false;
        }
        if (!dataBase.isValidPassword(id, password)) {
            isAuthenticated = false;
        }
        else {
            isAuthenticated = true;
            setStudentId(id);
        }
        return isAuthenticated;
    }
}
