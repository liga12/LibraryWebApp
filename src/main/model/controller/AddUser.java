package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.Librarian;

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String add = req.getParameter("add");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        PrintJSP print = new PrintJSP();
        String page = "/addUser.jsp";

        if (add != null) {
            if (new Librarian(name, patronymic, surname, phone).addClient()) {
                print.printJSP(req, resp, page, "Пользователь добавлен");
            } else {
                print.printJSP(req, resp, page, "Такой пользователь уже существует");
            }
        }
    }
}
