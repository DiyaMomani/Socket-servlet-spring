package com.example.spring.mark;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MarksGetter implements RowMapper<ArrayList<Mark>> {
    private final ArrayList<String> coursesNames = new ArrayList<>(
            Arrays.asList(
                    "cpp_mark",
                    "java_mark",
                    "ruby_mark"
            )
    );

    @Override
    public ArrayList<Mark> mapRow(ResultSet resultSet, int i) throws SQLException {
        ArrayList<Mark> marks = new ArrayList<>();
        for (String cName : coursesNames) {
            marks.add(new Mark(cName, resultSet.getDouble(cName)));
        }
        return marks;
    }
}
