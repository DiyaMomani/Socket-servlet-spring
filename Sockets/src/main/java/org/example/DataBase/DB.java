package org.example.DataBase;



import org.example.Studnt.Course;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private Connection connection;

    public DB() {
        connect();
    }

    private void connect() {
        final String HOST = "jdbc:mysql://localhost:3306/School?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
        final String DATABASE_USER = "root";
        final String DATABASE_PASSWORD = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to the database");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createTable(String createString) {
        if (createString == null) {
            throw new NullPointerException();
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createString);
            System.out.println("Created Table");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private Course getCourseStatistics(String courseName, String type) {
        if (courseName == null) {
            throw new NullPointerException();
        }

        courseName = courseName.toLowerCase();
        if (!courseName.equals("ruby") && !courseName.equals("cpp") && !courseName.equals("java")) {
            throw new IllegalStateException("Invalid course name");
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "select " + type + "(" + courseName + "_mark) from MARKS";
            ResultSet rs = statement.executeQuery(sql);

            Course result = null;
            while (rs.next()) {
                result = new Course(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public Double getCourseAverage(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "AVG").getMark();
    }

    public Double getCourseMinMark(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "MIN").getMark();
    }

    public Double getCourseMaxMark(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "MAX").getMark();
    }
    public Course getMedian(String courseName){
        Course course = null;
        try {
            Statement statement =connection.createStatement();
            statement.execute("SET @row_index := -1");
            ResultSet result = statement.executeQuery(
                    "SELECT AVG( subq." + courseName + "_mark) as median_value " +
                            "FROM (" +
                            "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                            "_mark    FROM MARKS" +
                            "    ORDER BY " + courseName +
                            "_mark  ) AS subq" +
                            "  WHERE subq.row_index " +
                            "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))"
            );
            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                course = new Course(courseName , result.getDouble(1));
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Course> getAllCourses(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select cpp_mark, java_mark, ruby_mark from MARKS where id=" + id);

            ArrayList<Course> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Course("CPP Course", rs.getDouble(1)));
                result.add(new Course("Java Course", rs.getDouble(2)));
                result.add(new Course("Ruby Course", rs.getDouble(3)));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public Course getCourse(String courseName, int id) {
        if (courseName == null) {
            throw new NullPointerException();
        }

        courseName = courseName.toLowerCase();
        if (!courseName.equals("ruby") && !courseName.equals("cpp") && !courseName.equals("java")) {
            throw new IllegalStateException("Invalid course name");
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select " + courseName + "_mark from MARKS where id=" + id);

            Course result = null;
            while (rs.next()) {
                result = new Course(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public boolean isRegisteredStudent(int id) {
        try {
            String sql = "select name from STUDENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public boolean isValidPassword(int id, String enteredPassword) {
        if (enteredPassword == null) {
            throw new NullPointerException();
        }

        try {
            String sql = "select password from STUDENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            String password = null;
            while (rs.next()) {
                password = rs.getString(1);
            }

            return password.equals(enteredPassword);
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }


}