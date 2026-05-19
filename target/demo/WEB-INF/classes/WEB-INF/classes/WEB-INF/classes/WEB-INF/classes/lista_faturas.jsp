<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Fatura" %>
<%@ page import="dao.FaturaDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Faturamento - Relatório</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        .pago { color: green; font-weight: bold; }
        .pendente { color: red; font-weight: bold; }
        a { color: #0066cc; margin-right: 15px; }
        .btn { padding: 5px 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        .btn-deletar { background-color: #f44336; }
    </style>
</head>
<body>
    <h2>Relatório de Faturas</h2>
    
    <a href="faturamento.jsp">Lançar Nova Fatura</a> | <a href="index.jsp">Voltar para Início</a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Cód. Cliente</th>
                <th>Descrição</th>
                <th>Valor (R$)</th>
                <th>Vencimento</th>
                <th>Status</th>
                <th>Ação</th>
            </tr>
        </thead>
        <tbody>
            <% 
                String clienteIdStr = request.getParameter("clienteId");
                FaturaDAO dao = new FaturaDAO();
                List<Fatura> faturas;
                if (clienteIdStr != null && !clienteIdStr.isEmpty()) {
                    int clienteId = Integer.parseInt(clienteIdStr);
                    faturas = dao.listarPorCliente(clienteId);
                } else {
                    faturas = dao.listarTodas();
                }
                if (faturas != null && !faturas.isEmpty()) {
                    for (Fatura f : faturas) {
            %>
            <tr>
                <td><%= f.getIdFatura() %></td>
                <td><%= f.getIdCliente() %></td>
                <td><%= f.getDescricao() %></td>
                <td>R$ <%= String.format("%.2f", f.getValor()) %></td>
                <td><%= f.getDataVencimento() %></td>
                <td class="<%= f.getStatus().equalsIgnoreCase("Pago") ? "pago" : "pendente" %>">
                    <%= f.getStatus() %>
                </td>
                <td>
                    <% if (f.getStatus().equalsIgnoreCase("Pendente")) { %>
                        <a href="<%= request.getContextPath() %>/fatura/marcarPago?idFatura=<%= f.getIdFatura() %>"><button class="btn">Marcar Pago</button></a>
                    <% } %>
                    <a href="<%= request.getContextPath() %>/fatura/deletar?idFatura=<%= f.getIdFatura() %>"><button class="btn btn-deletar">Deletar</button></a>
                </td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="7">Nenhuma fatura encontrada.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>