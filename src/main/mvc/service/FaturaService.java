package service;

import java.util.List;

import dao.FaturaDAO;
import model.Fatura;

public class FaturaService {
    private final FaturaDAO faturaDAO;

    public FaturaService() {
        this.faturaDAO = new FaturaDAO();
    }

    public void salvar(Fatura fatura) {
        if (fatura == null || fatura.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Cliente inválido para a fatura.");
        }
        this.faturaDAO.salvar(fatura);
    }

    public List<Fatura> listarTodas() {
        return this.faturaDAO.listarTodas();
    }

    public List<Fatura> listarPorCliente(int idCliente) {
        return this.faturaDAO.listarPorCliente(idCliente);
    }

    public void marcarComoPago(int idFatura) {
        this.faturaDAO.marcarComoPago(idFatura);
    }

    public void deletar(int idFatura) {
        this.faturaDAO.deletar(idFatura);
    }
}