// Caminho: src/main/mvc/dao/FaturaDAO.java
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fatura;

public class FaturaDAO extends MysqlDAO {

    public FaturaDAO() {
        super();
    }

    public void salvar(Fatura fatura) {
        String sql = "INSERT INTO faturas (id_cliente, descricao, valor, data_vencimento, status) VALUES (?, ?, ?, ?, ?)";
        try {
            super.executarUpdate(sql, fatura.getIdCliente(), fatura.getDescricao(), fatura.getValor(), fatura.getDataVencimento(), "Pendente");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fatura", e);
        }
    }

    public List<Fatura> listarTodas() {
        List<Fatura> faturas = new ArrayList<>();
        String sql = "SELECT * FROM faturas ORDER BY data_vencimento";
        try (ResultSet rs = super.executar(sql)) {
            while (rs.next()) {
                Fatura fatura = new Fatura();
                fatura.setIdFatura(rs.getInt("id"));
                fatura.setIdCliente(rs.getInt("id_cliente"));
                fatura.setDescricao(rs.getString("descricao"));
                fatura.setValor(rs.getDouble("valor"));
                fatura.setDataVencimento(rs.getString("data_vencimento"));
                fatura.setStatus(rs.getString("status"));
                faturas.add(fatura);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar faturas", e);
        }
        return faturas;
    }

    public List<Fatura> listarPorCliente(int idCliente) {
        List<Fatura> faturas = new ArrayList<>();
        String sql = "SELECT * FROM faturas WHERE id_cliente = ? ORDER BY data_vencimento";
        try (ResultSet rs = super.executar(sql, idCliente)) {
            while (rs.next()) {
                Fatura fatura = new Fatura();
                fatura.setIdFatura(rs.getInt("id"));
                fatura.setIdCliente(rs.getInt("id_cliente"));
                fatura.setDescricao(rs.getString("descricao"));
                fatura.setValor(rs.getDouble("valor"));
                fatura.setDataVencimento(rs.getString("data_vencimento"));
                fatura.setStatus(rs.getString("status"));
                faturas.add(fatura);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar faturas do cliente", e);
        }
        return faturas;
    }

    public void marcarComoPago(int idFatura) {
        String sql = "UPDATE faturas SET status = ? WHERE id = ?";
        try {
            super.executarUpdate(sql, "Pago", idFatura);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da fatura", e);
        }
    }

    public void deletar(int idFatura) {
        String sql = "DELETE FROM faturas WHERE id = ?";
        try {
            super.executarUpdate(sql, idFatura);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fatura", e);
        }
    }
}