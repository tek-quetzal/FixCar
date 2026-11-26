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
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        if("change".equals(action) && id!=null) {
            req.setAttribute("user", clientService.getById(Integer.parseInt(id)));
            req.getRequestDispatcher("/WEB-INF/jsp/client-form.jsp").forward(req, resp);
        }
        else if("create".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/client-form.jsp").forward(req, resp);
        }
        else {
            List<Client> clients = clientService.getAll();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("/WEB-INF/jsp/clients.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("clientName");
        String phone = req.getParameter("clientPhone");

        if("change".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            clientService.update(id,name,phone);
        }

        else if("create".equals(action)){
            clientService.create(name,phone);
        }
        else if("delete".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            clientService.delete(id);
        }

        resp.sendRedirect(req.getContextPath() + "/clients");
    }




}
