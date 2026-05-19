// Caminho: src/main/mvc/model/Cliente.java
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente implements Mapeavel {
    
    private int idCliente;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;

    public Cliente() {}

    public Cliente(String nome, String cpfCnpj, String email, String telefone) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public void preencher(ResultSet rs) throws SQLException {
        this.idCliente = rs.getInt("id");
        this.nome = rs.getString("nome");
        this.cpfCnpj = rs.getString("cpf_cnpj");
        this.email = rs.getString("email");
        this.telefone = rs.getString("telefone");
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}