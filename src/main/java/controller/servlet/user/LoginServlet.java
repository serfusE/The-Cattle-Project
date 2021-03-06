package controller.servlet.user;

import service.impl.AdminServiceImpl;
import util.Neo4jSessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name_content = request.getParameter("name_field");
        String password_content = request.getParameter("password_field");

        AdminServiceImpl adminService = new AdminServiceImpl();

        if (!name_content.equals("") && !password_content.equals("") && adminService.login(name_content, password_content)) {

            HttpSession session = request.getSession();
            session.setAttribute("account", name_content);
            session.setMaxInactiveInterval(60 * 60 * 24 * 7);

            response.sendRedirect("/admin/query.jsp");

        } else {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.include(request, response);

            PrintWriter out = response.getWriter();
            out.print("<br/><br/><br/><p style='text-align: center; color: #D0021B; font-family: Menlo-Regular;'>错误：认证失败，请检查填入的信息。</p>");
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        Neo4jSessionFactory.getInstance().close();
    }
}
