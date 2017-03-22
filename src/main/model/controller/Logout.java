package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LIGA on 07.11.2016.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        if (logout != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        }
    }
}
