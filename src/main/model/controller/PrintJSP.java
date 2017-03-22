package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintJSP {
    public void printJSPSession(HttpServletRequest req, HttpServletResponse resp, String user) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        createJSP(req, resp);
    }
//
    public void printJSP(HttpServletRequest req, HttpServletResponse resp, String[] pages) throws ServletException, IOException {
        createJSP(req, resp,pages);
    }

    public void printJSP(HttpServletRequest req, HttpServletResponse resp, String pages) throws ServletException, IOException {
        createJSP(req, resp,pages);
    }

//    public void printOneJSP(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
//        RequestDispatcher dispatcherSecond = req.getRequestDispatcher(jsp);
//        dispatcherSecond.include(req, resp);
//    }
//
    public void printJSP(HttpServletRequest req, HttpServletResponse resp, String jsp, String data) throws ServletException, IOException {
        req.setAttribute("data", data);
        createJSP(req, resp, jsp);
    }
//
    public void printJSP(HttpServletRequest req, HttpServletResponse resp, String jsp, List<List<String>> list) throws ServletException, IOException {
        req.setAttribute("list", list);
        req.setAttribute("listSize", list.size());
        createJSP(req, resp, jsp);
    }
//


    public void createJSP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String user = (String) req.getSession().getAttribute("user");
        RequestDispatcher dispatcherFirst = req.getRequestDispatcher(user);
        dispatcherFirst.include(req, resp);
    }

    public void createJSP(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        createJSP(req, resp);
            RequestDispatcher dispatcherSecond = req.getRequestDispatcher(page);
            dispatcherSecond.include(req, resp);

    }

    public void createJSP(HttpServletRequest req, HttpServletResponse resp, String[] pages) throws ServletException, IOException {
        createJSP(req, resp);
        for (String page : pages) {
            RequestDispatcher dispatcherSecond = req.getRequestDispatcher(page);
            dispatcherSecond.include(req, resp);
        }
    }


}
