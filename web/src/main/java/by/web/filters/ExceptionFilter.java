package by.web.filters;

import by.fixcar.service.exceptions.NotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/*")
public class ExceptionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(req, res);
        }
        catch(NotFoundException e){
            res.setStatus(404);
            req.setAttribute("errorMessage",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error-404.jsp").forward(req,res);

        }
    }
}
