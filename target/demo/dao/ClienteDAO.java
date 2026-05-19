// Caminho: src/main/mvc/dao/ClienteDAO.java
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO extends MysqlDAO {

    public ClienteDAO() {
        super();
    }

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf_cnpj, email, telefone) VALUES (?, ?, ?, ?)";
        try {
            super.executarUpdate(sql, cliente.getNome(), cliente.getCpfCnpj(), cliente.getEmail(), cliente.getTelefone());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY nome";
        try (ResultSet rs = super.executar(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.preencher(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
        return clientes;
    }

    public boolean cpfCnpjExiste(String cpfCnpj) {
        String sql = "SELECT count(*) AS total FROM clientes WHERE cpf_cnpj = ?";
        try (ResultSet rs = super.executar(sql, cpfCnpj)) {
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao checar CPF/CNPJ", e);
        }
        return false;
    }

    public void deletar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            super.executarUpdate(sql, idCliente);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }
}