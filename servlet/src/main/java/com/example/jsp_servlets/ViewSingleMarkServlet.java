package com.example.jsp_servlets;
import database.AuthenticationService;
import database.DataBase;
import database.Mark;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@WebServlet(name = "ViewSingleMarkServlet", value = "/viewSingle")
public class ViewSingleMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (AuthenticationService.getInstance().isAuthenticated()) {
            directToPage = "/chooseCourse.jsp";
            request.setAttribute("action", "viewSingle");
        } else {
            directToPage = "/notLoggedIn.html";
        }

        RequestDispatcher requestsDispatcher = request.getRequestDispatcher(directToPage);
        requestsDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AuthenticationService.getInstance().isAuthenticated()) {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/notLoggedIn.html");
            requestsDispatcher.forward(request, response);
            return;
        }

        int studentId = AuthenticationService.getInstance().getStudentId();
        String courseName = request.getParameter("course");
        Mark mark = DataBase.getInstance().getCourseMark(courseName, studentId);
        request.setAttribute("mark", mark);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewMark.jsp");
        requestsDispatcher.forward(request, response);
    }
}
