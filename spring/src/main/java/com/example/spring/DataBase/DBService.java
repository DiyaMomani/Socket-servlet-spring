package com.example.spring.DataBase;

import com.example.spring.mark.Mark;
import com.example.spring.mark.MarkGetter;
import com.example.spring.mark.MarksGetter;
import com.example.spring.mark.PasswordGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DBService {
    private JdbcTemplate jdbc;

    @Autowired
    public DBService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean isValidStudent(int studentId) {
        final String SQL = "select password from STUDENTS where id=" + studentId;
        int size = jdbc.query(SQL, new PasswordGetter()).size();
        return (size > 0);
    }

    public boolean isValidPassword(int studentId, String password) {
        final String SQL = "select password from STUDENTS where id=" + studentId;
        String correctPassword = jdbc.query(SQL, new PasswordGetter()).get(0);
        return correctPassword.equals(password);
    }

    public ArrayList<Mark> getAllStudentMarks(int studentId)  {
        final String SQL = "select cpp_mark, java_mark, ruby_mark from MARKS where id=" + studentId;
        ArrayList<Mark> courses = jdbc.query(SQL, new MarksGetter()).get(0);
        return courses;
    }

    public Mark getStudentMark(int studentId, String courseName)  {
        final String SQL = "select " + courseName + "_mark from MARKS where id=" + studentId;
        Double mark = jdbc.query(SQL, new MarkGetter()).get(0);
        return new Mark(courseName, mark);
    }

    public Mark getAverage(String courseName) {
        final String SQL = "select AVG(" + courseName + "_mark) from MARKS";
        Double mark = jdbc.query(SQL, new MarkGetter()).get(0);
        return new Mark(courseName, mark);
    }

    public Mark getMax(String courseName) {
        final String SQL = "select MAX(" + courseName + "_mark) from MARKS";
        Double mark = jdbc.query(SQL, new MarkGetter()).get(0);
        return new Mark(courseName, mark);
    }

    public Mark getMin(String courseName) {
        final String SQL = "select MIN(" + courseName + "_mark) from MARKS";
        Double mark = jdbc.query(SQL, new MarkGetter()).get(0);
        return new Mark(courseName, mark);
    }
    public Mark getMedian(String courseName){
        final String SQL = "SELECT AVG( subq." + courseName + "_mark) as median_value " +
                "FROM (" +
                "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                "_mark    FROM MARKS" +
                "    ORDER BY " + courseName +
                "_mark  ) AS subq" +
                "  WHERE subq.row_index " +
                "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))";
        jdbc.execute("SET @row_index := -1");
        Double mark = jdbc.queryForObject(SQL , Double.class);
        return new Mark(courseName, mark);
    }
}
