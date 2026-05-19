<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Sistema de Faturamento</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background-color: #f0f2f5; }
        .login-container { background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); width: 300px; }
        h2 { text-align: center; color: #333; margin-top: 0; }
        form { display: flex; flex-direction: column; }
        label { margin-bottom: 5px; font-weight: bold; }
        input[type="email"], input[type="password"] { padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        input[type="submit"]:hover { background-color: #45a049; }
        .links { text-align: center; margin-top: 15px; }
        .links a { color: #0066cc; text-decoration: none; font-size: 14px; }
        .links a:hover { text-decoration: underline; }
        .erro { color: red; font-size: 14px; text-align: center; margin-bottom: 10px; }
        .sucesso { color: green; font-size: 14px; text-align: center; margin-bottom: 10px; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Entrar no Sistema</h2>
        
        <% 
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {
        %>
            <div class="erro"><%= erro %></div>
        <% 
            } 
            String sucesso = request.getParameter("sucesso");
            if (sucesso != null) {
        %>
            <div class="sucesso"><%= sucesso %></div>
        <% 
            } 
        %>

        <form action="<%= request.getContextPath() %>/login.jsp" method="post">
            <label>E-mail:</label>
            <input type="email" name="email" required>
            
            <label>Senha:</label>
            <input type="password" name="senha" required>
            
            <input type="submit" value="Entrar">
        </form>
        
        <div class="links">
            <a href="cadastro_usuario.jsp">Ainda não tem conta? Cadastre-se</a>
        </div>
    </div>
</body>
</html>