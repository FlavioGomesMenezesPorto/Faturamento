package Controller;

import java.io.IOException;

import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet(urlPatterns = {"/ClienteController", "/cliente/*"})
public class ClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String pathInfo = request.getPathInfo();
        if ("deletar".equals(action) || (pathInfo != null && pathInfo.equals("/deletar"))) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                ClienteDAO dao = new ClienteDAO();
                dao.deletar(id);
            }
            response.sendRedirect(request.getContextPath() + "/lista_clientes.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/lista_clientes.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpfCnpj = request.getParameter("cpfCnpj");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        ClienteDAO dao = new ClienteDAO();
        
        if (dao.cpfCnpjExiste(cpfCnpj)) {
            response.sendRedirect("index.jsp?erro=CPF/CNPJ ja cadastrado");
            return;
        }

        Cliente cliente = new Cliente(nome, cpfCnpj, email, telefone);
        dao.salvar(cliente);

        response.sendRedirect("index.jsp?sucesso=Cliente cadastrado");
    }
}