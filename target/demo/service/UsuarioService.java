package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void salvar(Usuario usuario) {
        if (usuario == null || usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        
        if (usuarioDAO.emailExiste(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        this.usuarioDAO.salvar(usuario);
    }
    
    public Usuario autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return null;
        }
        return this.usuarioDAO.buscarPorEmailSenha(email, senha);
    }
}