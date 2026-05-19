<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Cliente" %>
<%@ page import="dao.ClienteDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Faturamento - Relatório de Clientes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        a { color: #0066cc; margin-right: 15px; }
    </style>
</head>
<body>
    <h2>Relatório de Clientes</h2>
    
    <a href="index.jsp">Voltar para Início (Cadastro)</a> | <a href="lista_faturas.jsp">Ver Faturas</a> | <a href="faturamento.jsp">Lançar Fatura</a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF/CNPJ</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ClienteDAO dao = new ClienteDAO();
                List<Cliente> clientes = dao.listarTodos();
                if (clientes != null && !clientes.isEmpty()) {
                    for (Cliente c : clientes) {
            %>
            <tr>
                <td><%= c.getIdCliente() %></td>
                <td><%= c.getNome() %></td>
                <td><%= c.getCpfCnpj() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getTelefone() %></td>
                <td>
                    <a href="lista_faturas.jsp?clienteId=<%= c.getIdCliente() %>" style="text-decoration:none;" title="Ver Faturas">👁️</a>
                    <a href="<%= request.getContextPath() %>/cliente/deletar?id=<%= c.getIdCliente() %>" style="text-decoration:none;" onclick="return confirm('Tem certeza que deseja apagar este cliente?')" title="Apagar Cliente">🗑️</a>
                </td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="6">Nenhum cliente encontrado.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>