package com.example.jsp_servlets;
import database.AuthenticationService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    private AuthenticationService authenticationService = AuthenticationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (authenticationService.isAuthenticated()) {
            directToPage = "/loggedIn.html";
        } else {
            directToPage = "/login.html";
        }

        RequestDispatcher requestsDispatcher = request.getRequestDispatcher(directToPage);
        requestsDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authenticationService.isAuthenticated()) {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/loggedIn.html");
            requestsDispatcher.forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");

        authenticationService.authenticate(id, password);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (authenticationService.isAuthenticated()) {
            out.println("<h1> Logged In </h1>");
        } else {
            out.println("<h1> Invalid Credentials </h1>");
        }
        out.println("</body></html>");
    }
}
