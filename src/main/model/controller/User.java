package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.Administrator;
import staff.user.Librarian;

/**
 * Created by LIGA on 07.11.2016.
 */
@WebServlet("/user")
public class User extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String searchBook = request.getParameter("searchBook");
        String searchUser = request.getParameter("searchUser");
        String allAuthorization = request.getParameter("allAuthorization");
        String addAuthorization = request.getParameter("addAuthorization");
        String addBook = request.getParameter("addBook");
        String findAllBookByRent = request.getParameter("findAllBookByRent");
        String findAllRent = request.getParameter("findAllRent");
        String addUser = request.getParameter("addUser");
        String editUser = request.getParameter("editUser");
        String issueBook = request.getParameter("issueBook");
        String returnBook = request.getParameter("returnBook");

        PrintJSP print = new PrintJSP();

        if (searchBook != null ){
            request.setAttribute("action","/searchBook");
            print.printJSP(request, response,"/searchBook.jsp");
        }
        if (searchUser != null ){
            request.setAttribute("action","/searchUser");
            print.printJSP(request, response,"/searchUser.jsp");
        }
        if (allAuthorization != null ){
            print.printJSP(request, response,"/findAllAuthorization.jsp", new Administrator().findAllAutorization());
        }
        if (addAuthorization != null ){
            print.printJSP(request, response,"/addAuthorization.jsp");
        }
        if (addBook != null ){
            print.printJSP(request, response,"/addBook.jsp");
        }
        if (findAllBookByRent != null ){
            print.printJSP(request, response,"/findAllBookByRent.jsp", new Librarian().findAllBookByRent());
        }
        if (findAllRent != null ){
            print.printJSP(request, response,"/findAllRent.jsp", new Librarian().findAllRent());
        }
        if (addUser != null ){
            print.printJSP(request, response,"/addUser.jsp");
        }
        if (editUser != null ){
            request.setAttribute("action","/editUser");
            print.printJSP(request, response,"/editUser.jsp");
        }
        if (issueBook != null ){
            request.setAttribute("action","/issueBook");
            print.printJSP(request, response,"/issueBookFirst.jsp");
        }
        if (returnBook != null ){
            request.setAttribute("action","/returnBook");
            print.printJSP(request, response,"/returnBook.jsp");
        }
    }
}
