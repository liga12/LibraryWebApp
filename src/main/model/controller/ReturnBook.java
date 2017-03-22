package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.Librarian;

@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");
        String idUser = req.getParameter("idUser");
        String next = req.getParameter("next");
        String page = "/returnBook.jsp";
        PrintJSP print = new PrintJSP();
        req.setAttribute("action", "/returnBook");

        if (all != null || find != null) {
            new SearchUser().printSearchUser(req, resp, page, all, find, search, query);
        }

        if (next != null) {
            if (new EditUser().checkId(idUser)){
                if (new Librarian().findUserById(Integer.valueOf(idUser)).size() > 0) {
                    List<List<String>> list = new Librarian().findUserByRent(Integer.valueOf(idUser));
//                    req.setAttribute("action", "/returnBookSecond");
                    print.printJSP(req, resp, "/returnBookSecond.jsp", list);
                } else {
                    print.printJSP(req, resp, page, "Такой ID не существует");
                }
            }else {
                print.printJSP(req, resp, page, "Ввели не корректный ID");
            }
        }



    }
}
