package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispetcherServlet extends HttpServlet {

    public void forward(String to, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        dispatcher.forward(req, resp);
    }
}
