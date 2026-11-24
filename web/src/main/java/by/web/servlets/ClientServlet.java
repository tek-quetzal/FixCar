package by.web.servlets;


import by.fixcar.model.Client;
import by.fixcar.service.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/clients")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService = new ClientService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientService.getAll();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/jsp/clients.jsp").forward(req,resp);
    }



}
