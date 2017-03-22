package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.Librarian;

@WebServlet("/returnBookSecond")
public class ReturnBookSecond extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idRent = req.getParameter("idRent");
        String add = req.getParameter("add");
        String page = "/returnBookSecond.jsp";
        PrintJSP print = new PrintJSP();

        if (add != null) {
            if (new EditUser().checkId(idRent)) {
                int rent = Integer.valueOf(idRent);
                if (new Librarian().findByRent(Integer.valueOf(idRent)).size() > 0) {
                    if (new Librarian().returnBook(rent)) {
                        print.printJSP(req, resp, page, "Книга получена у клиента");
                    } else {
                        print.printJSP(req, resp, page, "Книга не получена у клиента");
                    }
                } else {
                    String mas = "ID " + idRent + " нет в аренде";
                    print.printJSP(req, resp, page, mas);
                }
            }
            else {
                print.printJSP(req, resp, page, "Ввели не корректный ID");
            }
        }
    }
}
