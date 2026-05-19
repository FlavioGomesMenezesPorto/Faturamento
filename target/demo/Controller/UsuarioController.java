package Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;
import service.UsuarioService;

@WebServlet(urlPatterns = { "/usuario", "/usuario/*" })
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PREFIXO_APP = "";
    
    private final UsuarioService usuarioService = new UsuarioService();

    private String extrairRota(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty() || "/".equals(pathInfo)) {
            return "/listar";
        }
        if (!pathInfo.startsWith("/")) {
            pathInfo = "/" + pathInfo;
        }
        return pathInfo;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rota = extrairRota(request);

        switch (rota) {
            case "/inserir":
                salvarNovo(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
        }
    }

    private void salvarNovo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(nome, email, senha);

        try {
            usuarioService.salvar(usuario);
            
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            
            response.sendRedirect(request.getContextPath() + "/index.jsp?sucesso=Conta criada com sucesso! Cadastre seu primeiro cliente.");
            
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/cadastro_usuario.jsp?erro=" + e.getMessage());
        }
    }
}