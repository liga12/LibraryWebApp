package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.autorization.Security;

public class StartServlet extends DispetcherServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String all = req.getParameter("all");
        String sec = req.getParameter("second");
        Security security = new Security(login, password, null);
        String autoriz = security.getAuthorization();

        if (req.getParameter("register") != null && autoriz != null) {
            if (autoriz.equals("Client")) {
                super.forward("/client.jsp", req, resp);
            }
            if (autoriz.equals("Administrator")) {
                super.forward("/administrator.jsp", req, resp);
            }
            if (autoriz.equals("Librarian")) {
                super.forward("/librarian.jsp", req, resp);
            }
        } else {
            super.forward("/errorAutor.jsp", req, resp);
        }

    }


}


