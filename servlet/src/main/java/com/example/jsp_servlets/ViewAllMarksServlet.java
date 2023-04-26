package com.example.jsp_servlets;

import database.AuthenticationService;
import database.DataBase;
import database.Mark;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ViewAllMarksServlet", value = "/viewMarks")
public class ViewAllMarksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AuthenticationService.getInstance().isAuthenticated()) {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/notLoggedIn.html");
            requestsDispatcher.forward(request, response);
            return;
        }

        int studentId = AuthenticationService.getInstance().getStudentId();
        ArrayList<Mark> marks = DataBase.getInstance().getAllMarks(studentId);
        request.setAttribute("marks", marks);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewAllMarks.jsp");
        requestsDispatcher.forward(request, response);
    }
}