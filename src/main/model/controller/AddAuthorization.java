package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.autorization.Security;

@WebServlet("/addAuthorization")
public class AddAuthorization extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String add = req.getParameter("add");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        String page = "/addAuthorization.jsp";
        PrintJSP print = new PrintJSP();

        if (add != null) {
            if (new Security(login, null, null).loginExist()) {
                new Security(login, password, Integer.valueOf(type)).addAuthorization();
                print.printJSP(req, resp, page, "Данные добавлены");
            } else {
                print.printJSP(req, resp, page, "Логин существуе");
            }
        }
    }
}
