<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Faturamento - Clientes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 500px; margin: 0 auto; }
        h1 { color: #333; }
        form { background-color: #f4f4f4; padding: 20px; border-radius: 5px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="submit"] { 
            width: 100%; 
            padding: 8px; 
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] { 
            background-color: #4CAF50; 
            color: white; 
            cursor: pointer;
            margin-top: 20px;
        }
        input[type="submit"]:hover { background-color: #45a049; }
        .nav-links { margin: 20px 0; }
        .nav-links a { 
            display: inline-block; 
            margin-right: 15px; 
            padding: 10px 20px; 
            background-color: #2196F3; 
            color: white; 
            text-decoration: none;
            border-radius: 3px;
        }
        .nav-links a:hover { background-color: #0b7dda; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Sistema de Faturamento</h1>
        
        <div class="nav-links">
            <a href="faturamento.jsp">Lançar Fatura</a>
            <a href="lista_faturas.jsp">Visualizar Faturas</a>
            <a href="lista_clientes.jsp">Visualizar Clientes</a>
        </div>

        <h2>Cadastro de Cliente</h2>
        <% 
            String erro = request.getParameter("erro");
            if (erro != null) {
        %>
            <div style="color: red; margin-bottom: 15px;"><%= erro %></div>
        <% 
            } 
            String sucesso = request.getParameter("sucesso");
            if (sucesso != null) {
        %>
            <div style="color: green; margin-bottom: 15px;"><%= sucesso %></div>
        <% 
            } 
        %>
        <form action="ClienteController" method="post">
            <label>Nome:</label>
            <input type="text" name="nome" required><br>
            
            <label>CPF/CNPJ:</label>
            <input type="text" name="cpfCnpj" required><br>
            
            <label>Email:</label>
            <input type="email" name="email"><br>
            
            <label>Telefone:</label>
            <input type="text" name="telefone"><br>
            
            <input type="submit" value="Salvar Cliente">
        </form>
    </div>
</body>
</html>
