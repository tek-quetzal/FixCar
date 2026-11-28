package by.web.servlets;

import by.fixcar.exceptions.ValidationException;
import by.fixcar.service.services.MechanicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/mechanics")
public class MechanicServlet extends HttpServlet {

    MechanicService mechanicService = new MechanicService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("create".equals(action)){
            req.getRequestDispatcher("/WEB-INF/jsp/mechanic-form.jsp").forward(req,resp);
        }
        else if("change".equals(action)){
            int mechanicId = Integer.parseInt(req.getParameter("mechanicId"));
            req.setAttribute("mechanic",mechanicService.getById(mechanicId));
            req.getRequestDispatcher("/WEB-INF/jsp/mechanic-form.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("mechanics", mechanicService.getAll());
            req.getRequestDispatcher("/WEB-INF/jsp/mechanics.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String mechanicName = req.getParameter("mechanicName");
        String mechanicSpecialization = req.getParameter("mechanicSpecialization");

        try {
            if ("change".equals(action)) {
                int mechanicId = Integer.parseInt(req.getParameter("mechanicId"));
                mechanicService.update(mechanicId, mechanicName, mechanicSpecialization);
            } else if ("create".equals(action)) {
                mechanicService.create(mechanicName, mechanicSpecialization);
            } else if ("delete".equals(action)) {
                int mechanicId = Integer.parseInt(req.getParameter("mechanicId"));
                mechanicService.delete(mechanicId);
            }

            resp.sendRedirect(req.getContextPath() + "/mechanics");
        }
        catch(ValidationException e){
            if ("delete".equals(action)) {
                req.setAttribute("error", e.getMessage());
                req.setAttribute("mechanics", mechanicService.getAll());
                req.getRequestDispatcher("/WEB-INF/jsp/mechanics.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/mechanic-form.jsp").forward(req,resp);
        }
    }
}
