package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.user.FinderUser;

@WebServlet("/searchUser")
public class SearchUser extends HttpServlet {
    PrintJSP print = new PrintJSP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String all = req.getParameter("all");
        String find = req.getParameter("find");
        String search = req.getParameter("search");
        String query = req.getParameter("query");
        String page = "/searchUser.jsp";
        req.setAttribute("action", "/searchUser");

            printSearchUser(req, resp, page, all, find, search, query);
    }


    public void printSearchUser(HttpServletRequest req, HttpServletResponse resp, String page, String all, String find, String search, String query) throws ServletException, IOException {

        if (all != null) {
            print.printJSP(req, resp,page, new  FinderUser().findUser(1));
        }
        if (find != null) {
            if (search != null) {
                switch (find) {
                    case "findUserByName":
                        print.printJSP(req, resp,page,  new FinderUser(query, "", "", "").findUser(2));
                        break;
                    case "findUserBySurname":
                        print.printJSP(req, resp,page, new  FinderUser("", "", query, "").findUser(3));
                        break;
                    case "findUserByPatronymic":
                        print.printJSP(req, resp,page, new FinderUser("", query, "", "").findUser(4));
                        break;
                }
            }
        }
    }
}