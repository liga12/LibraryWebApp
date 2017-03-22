package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.Librarian;

@WebServlet("/issueBook")
public class IssueBook extends HttpServlet {
    static String userId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");
        userId = req.getParameter("userId");
        String next = req.getParameter("next");
        String page = "/issueBookFirst.jsp";
        PrintJSP print = new PrintJSP();
        req.setAttribute("action", "/issueBook");

        if (all != null || find != null) {
            new SearchUser().printSearchUser(req, resp, page, all, find, search, query);
        }

        if (next != null) {
            if (new Librarian().findUserById(Integer.valueOf(userId)).size() > 0) {
                req.setAttribute("action", "/issueBookSecond");
                print.printJSP(req, resp, "/issueBookSecond.jsp");
            } else {
                print.printJSP(req, resp, page, "Такой ID не существует");
            }

        }
    }
}

