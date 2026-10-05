import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Servlet1() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");

        
        String nombre = request.getParameter("userName");

        if (nombre == null
                || !nombre.trim().matches("[\\p{L} ]{2,50}")) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            PrintWriter out = response.getWriter();
            out.print("<h2>El nombre debe tener entre 2 y 50 "
                    + "caracteres, solo letras y espacios.</h2>");
            out.print("<a href='index.html'>Volver al formulario</a>");
            return;
        }

        nombre = nombre.trim();

       
        HttpSession sesionAnterior = request.getSession(false);

        
        if (sesionAnterior != null) {
            sesionAnterior.invalidate();
        }

       
        HttpSession sesion = request.getSession(true);

       
        sesion.setAttribute("uname", nombre);

        
        response.sendRedirect(
                request.getContextPath() + "/Servlet2");
    }
}