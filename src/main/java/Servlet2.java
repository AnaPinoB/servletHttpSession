import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Servlet2() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

       
        response.setHeader("Cache-Control", "no-store");

        
        HttpSession sesion = request.getSession(false);

        
        if (sesion == null || sesion.getAttribute("uname") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/index.html");
            return;
        }

        
        String nombre = (String) sesion.getAttribute("uname");

        
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html lang='es'>");
        out.print("<head>");
        out.print("<meta charset='UTF-8'>");
        out.print("<title>Sesión iniciada</title>");
        out.print("</head>");
        out.print("<body>");

        out.print("<h1>Hola, " + escaparHtml(nombre) + "</h1>");
        out.print("<p>Tienes una sesión activa.</p>");

        out.print("<form action='CerrarSesion' method='post'>");
        out.print("<button type='submit'>Cerrar sesión</button>");
        out.print("</form>");

        out.print("</body>");
        out.print("</html>");

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    private String escaparHtml(String texto) {
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
