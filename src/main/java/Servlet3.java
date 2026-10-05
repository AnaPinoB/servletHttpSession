import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CerrarSesion")
public class Servlet3 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Servlet3() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-store");

       
        HttpSession sesion = request.getSession(false);

        
        if (sesion != null) {
            sesion.invalidate();
        }

       
        response.sendRedirect(
                request.getContextPath() + "/index.html");
    }
}