package Config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("usuarioLogado") != null;
        boolean loginRequest = uri.endsWith("/login.jsp") 
                            || uri.endsWith("/cadastro_usuario.jsp") 
                            || uri.contains("/LoginController") 
                            || uri.contains("/login") 
                            || uri.contains("/usuario/inserir");                           
        if (loggedIn || loginRequest) {
            if (loggedIn && (uri.endsWith("/cadastro_usuario.jsp") || uri.endsWith("/login.jsp"))) {
                res.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    public void destroy() {}
}