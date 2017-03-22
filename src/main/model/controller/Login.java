package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.autorization.Security;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String log = req.getParameter("log");
        Security security = new Security(login, password, null);
        String authorization = security.getAuthorization();
        PrintJSP print = new PrintJSP();


        if (log != null && authorization != null) {
            switch (authorization) {
                case "Client":
                    print.printJSPSession(req, resp, "/client.jsp");
                    break;
                case "Administrator":
                    print.printJSPSession(req, resp, "/administrator.jsp");
                    break;
                case "Librarian":
                    print.printJSPSession(req, resp, "/librarian.jsp");
                    break;
            }
        } else {
            req.setAttribute("error", "Неверный логин или пароль");
            printPage(req, resp);
        }

    }

    private void printPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}
