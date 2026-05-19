package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Config.MySqlSingleton;
import model.Usuario;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try {
            MySqlSingleton.getInstance().executarUpdate(
                sql, 
                usuario.getNome(), 
                usuario.getEmail(), 
                usuario.getSenha()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean emailExiste(String email) {
        String sql = "SELECT count(*) AS total FROM usuarios WHERE email = ?";
        try (ResultSet rs = MySqlSingleton.getInstance().executar(sql, email)) {
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public Usuario buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (ResultSet rs = MySqlSingleton.getInstance().executar(sql, email, senha)) {
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}