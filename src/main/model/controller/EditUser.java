package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.FinderUser;
import staff.user.Librarian;

@WebServlet("/editUser")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String add = req.getParameter("add");
        String searchUser = req.getParameter("searchUser");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        PrintJSP print = new PrintJSP();
        String page = "/editUser.jsp";

        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");

        if (all != null || find != null){
            new SearchUser().printSearchUser(req, resp, page, all, find, search, query);
        }
        if (add != null) {
            if (checkId(id)) {
                if (new Librarian().findUserById(Integer.valueOf(id)).size() > 0) {
                    new Librarian(name, patronymic, surname, phone).editClient(Integer.valueOf(id));
                    print.printJSP(req, resp, page, "Пользователь добавлен");
                } else {
                    print.printJSP(req, resp, page, "Такой ID не существует");
                }
            } else {
                print.printJSP(req, resp, page, "Ввели не корректный ID");
            }
        }
    }

    public boolean checkId(String id) {
        boolean result = true;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                result = false;
                break;
            }
        }
        return result;
    }
}
