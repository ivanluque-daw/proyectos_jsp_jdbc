package org.iesvdm.app_jdbc.servlet;

import java.io.*;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.iesvdm.app_jdbc.dao.UserDAO;
import org.iesvdm.app_jdbc.dao.UserDAOImpl;
import org.iesvdm.app_jdbc.model.User;

@WebServlet(name = "AccessServlet", value = "/app")
public class AccessServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/access.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user = userDAO.findByUsernameAndPassword(username, password);

        if (user.isPresent()) {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/panel.jsp");
        } else {
            request.setAttribute("error", "El nombre o la contraseña son incorrectos.");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/access.jsp");
        }

        dispatcher.forward(request, response);
    }
}