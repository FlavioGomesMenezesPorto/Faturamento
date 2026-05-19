<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Faturamento - Lançar Fatura</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 500px; margin: 0 auto; }
        h1 { color: #333; }
        form { background-color: #f4f4f4; padding: 20px; border-radius: 5px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 3px; }
        input[type="submit"] { background-color: #4CAF50; color: white; cursor: pointer; margin-top: 20px; }
        input[type="submit"]:hover { background-color: #45a049; }
        .sucesso { color: green; font-weight: bold; background-color: #d4edda; padding: 10px; border-radius: 3px; margin-bottom: 10px; }
        .erro { color: red; font-weight: bold; background-color: #f8d7da; padding: 10px; border-radius: 3px; margin-bottom: 10px; }
        a { color: #0066cc; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lançar Nova Fatura</h1>
        
        <% if ("true".equals(request.getParameter("sucesso"))) { %>
            <div class="sucesso">✓ Fatura gerada com sucesso!</div>
        <% } %>
        
        <% if ("true".equals(request.getParameter("erro"))) { %>
            <div class="erro">✗ Erro ao gerar fatura. Verifique os dados.</div>
        <% } %>

        <form action="<%= request.getContextPath() %>/fatura/inserir" method="post">
            <label>ID do Cliente (quem vai pagar):</label>
            <input type="number" name="idCliente" required><br>
            
            <label>Descrição (Ex: Mensalidade Sistema):</label>
            <textarea name="descricao" required rows="3"></textarea><br>
            
            <label>Valor (R$):</label>
            <input type="number" step="0.01" name="valor" required><br>
            
            <label>Data de Vencimento:</label>
            <input type="date" name="dataVencimento" required><br>
            
            <input type="submit" value="Gerar Fatura">
        </form>
        
        <br>
        <a href="index.jsp">Voltar para Início</a> | <a href="lista_faturas.jsp">Ver Faturas</a>
    </div>
</body>
</html>