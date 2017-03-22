package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.item.Book;

@WebServlet("/searchBook")
public class SearchBook extends HttpServlet {
    PrintJSP print = new PrintJSP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");
        String page = "/searchBook.jsp";
        req.setAttribute("action","/searchBook");
        printSearchBook(req, resp, page, all, find, search, query);
    }


    public void printSearchBook(HttpServletRequest req, HttpServletResponse resp, String page, String all, String find, String search, String query) throws ServletException, IOException {
        if (all != null) {
            print.printJSP(req, resp, page, new Book().findBook(1));
        }
        if (find != null) {
            if (search != null) {
                switch (find) {
                    case "findBookByName":
                        print.printJSP(req, resp, page, new Book(query, "", "", "", "").findBook(2));
                        break;
                    case "findBookByDescription":
                        print.printJSP(req, resp, page, new Book("", query, "", "", "").findBook(3));
                        break;
                    case "findBookByAuthorName":
                        print.printJSP(req, resp, page, new Book("", "", query, "", "").findBook(4));
                        break;
                    case "findBookByAuthorPatronymic":
                        print.printJSP(req, resp, page, new Book("", "", "", query, "").findBook(5));
                        break;
                    case "findBookByAuthorSurname":
                        print.printJSP(req, resp, page, new Book("", "", "", "", query).findBook(6));
                        break;
                    case "findBookByAll":
                        print.printJSP(req, resp, page, new Book(query, "", "", "", "").findBook(7));
                        break;
                }
            }
        }
    }


}

