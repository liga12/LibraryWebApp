package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.item.Book;
import staff.user.Librarian;

@WebServlet("/issueBookSecond")
public class IssieBookSecond extends HttpServlet {
    String bookId;
    LocalDate returnDate;
    int userId = Integer.valueOf(IssueBook.userId);
    PrintJSP print = new PrintJSP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String add = req.getParameter("add");
        bookId = req.getParameter("bookId");
        String date = req.getParameter("date");
        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");
        String page = "/issueBookSecond.jsp";
        req.setAttribute("action", "/issueBookSecond");


        if (all != null || find != null) {
            new SearchBook().printSearchBook(req, resp, page, all, find, search, query);
        }


        if (add != null) {
            if (new Book().findBookById(Integer.valueOf(bookId)).size() > 0) {
                if (!date.equals("")) {
                        returnDate = LocalDate.now().plusDays(Long.parseLong(date));
                        issueBook(req, resp, page);
                } else {
                    returnDate = LocalDate.now().plusDays(14);
                    issueBook(req, resp, page);
                }
            } else {
                print.printJSP(req, resp, page, "Такой ID не существует");
            }
        }
    }


    public void issueBook(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        if (new Librarian().issueBook(userId, Integer.valueOf(bookId), returnDate)) {
            print.printJSP(req, resp, page, "Книга добавлена аренду");
        } else {
            print.printJSP(req, resp, page, "Книга уже в аренде");
        }
    }
}
