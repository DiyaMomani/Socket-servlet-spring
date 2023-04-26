package com.example.jsp_servlets;
import database.AuthenticationService;
import database.DataBase;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ViewCourseAnalyticsServlet", value = "/viewAnalytics")
public class ViewCourseAnalyticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (AuthenticationService.getInstance().isAuthenticated()) {
            directToPage = "/chooseCourse.jsp";
            request.setAttribute("action", "viewAnalytics");
        }
        else {
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
        Double minMark = DataBase.getInstance().getCourseMinMark(courseName);
        Double maxMark = DataBase.getInstance().getCourseMaxMark(courseName);
        Double average = DataBase.getInstance().getCourseAverage(courseName);
        Double median = DataBase.getInstance().getMedian(courseName).getMark();
        request.setAttribute("median",median);
        request.setAttribute("minMark", minMark);
        request.setAttribute("maxMark", maxMark);
        request.setAttribute("average", average);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewAnalytics.jsp");
        requestsDispatcher.forward(request, response);
    }
}
