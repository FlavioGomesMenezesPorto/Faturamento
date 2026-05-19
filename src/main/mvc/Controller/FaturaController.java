package Controller;

import java.io.IOException;

import dao.FaturaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Fatura;

@WebServlet("/FaturaController")
public class FaturaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            String dataVencimento = request.getParameter("dataVencimento");
            
            Fatura fatura = new Fatura(idCliente, descricao, valor, dataVencimento, "Pendente");
            FaturaDAO dao = new FaturaDAO();
            dao.salvar(fatura);
            
            response.sendRedirect("faturamento.jsp?sucesso=true");
        } catch (NumberFormatException e) {
            response.sendRedirect("faturamento.jsp?erro=true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("marcarPago".equals(action)) {
            String idFaturaStr = request.getParameter("idFatura");
            try {
                int idFatura = Integer.parseInt(idFaturaStr);
                FaturaDAO dao = new FaturaDAO();
                dao.marcarComoPago(idFatura);
            } catch (NumberFormatException e) {
                // erro silencioso
            }
        } else if ("deletar".equals(action)) {
            String idFaturaStr = request.getParameter("idFatura");
            try {
                int idFatura = Integer.parseInt(idFaturaStr);
                FaturaDAO dao = new FaturaDAO();
                dao.deletar(idFatura);
            } catch (NumberFormatException e) {
                // erro silencioso
            }
        }

        response.sendRedirect("lista_faturas.jsp");
    }
}
