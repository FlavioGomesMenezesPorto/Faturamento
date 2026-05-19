package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import Config.MySqlSingleton;

public abstract class MysqlDAO {
    protected final MySqlSingleton banco;

    public MysqlDAO() {
        this.banco = MySqlSingleton.getInstance();
    }

    protected ResultSet executar(String sql, Object... parametros) throws SQLException {
        return this.banco.executar(sql, parametros);
    }

    protected int executarUpdate(String sql, Object... parametros) throws SQLException {
        return this.banco.executarUpdate(sql, parametros);
    }
}