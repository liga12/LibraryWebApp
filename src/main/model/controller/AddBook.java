package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.item.Book;

@WebServlet("/addBook")
public class AddBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String nameAuthor = req.getParameter("nameAuthor");
        String patronymic = req.getParameter("patronymic");
        String surname = req.getParameter("surname");
        PrintJSP print = new PrintJSP();
        String page = "/addBook.jsp";


        if (new Book(name, description, nameAuthor, patronymic, surname).addBook()) {
            print.printJSP(req, resp, page, "Книга добавлена");
        } else {
            print.printJSP(req, resp, page, "Книга уже есть в база");
        }

    }
}
