package com.example.spring.Student;

public class AuthStudent {
    static boolean isAuthenticated = false;
    static int authenticatedId = -1;

    public static boolean isAuthenticated() {
        return isAuthenticated;
    }

    public static int getAuthenticatedId() {
        return authenticatedId;
    }

    public static void setAuthenticatedId(int authenticatedId) {
        AuthStudent.authenticatedId = authenticatedId;
    }

    public static void setIsAuthenticated(boolean isAuthenticated) {
        AuthStudent.isAuthenticated = isAuthenticated;
    }
}
