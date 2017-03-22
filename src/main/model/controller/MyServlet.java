package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getParameter("login");

        resp.setContentType("text/thml");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write("<h1>Succes!!</h1>");
        out.flush();
        out.close();

    }
}
