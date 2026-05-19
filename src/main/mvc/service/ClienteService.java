package service;

import java.util.List;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public void salvar(Cliente cliente) {
        if (cliente == null || cliente.getCpfCnpj() == null || cliente.getCpfCnpj().isBlank()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório.");
        }
        if (clienteDAO.cpfCnpjExiste(cliente.getCpfCnpj())) {
            throw new IllegalArgumentException("CPF/CNPJ já cadastrado.");
        }
        this.clienteDAO.salvar(cliente);
    }

    public List<Cliente> listarTodos() {
        return this.clienteDAO.listarTodos();
    }

    public void deletar(int id) {
        this.clienteDAO.deletar(id);
    }
}