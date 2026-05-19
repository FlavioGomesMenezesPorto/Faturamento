package Config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlSingleton {
    private static final String URL = buildDatabaseUrl();
    private static MySqlSingleton instance;
    private Connection conexao;

    private MySqlSingleton() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver SQLite nao encontrado no projeto.", e);
        }
    }

    public static synchronized MySqlSingleton getInstance() {
        if (instance == null) {
            instance = new MySqlSingleton();
        }
        return instance;
    }

    private Connection obterConexao() throws SQLException {
        if (this.conexao == null || this.conexao.isClosed()) {
            this.conexao = DriverManager.getConnection(URL);
        }
        return this.conexao;
    }

    private static String buildDatabaseUrl() {
        String dbDir = System.getProperty("catalina.base");
        if (dbDir == null || dbDir.isBlank()) {
            dbDir = System.getProperty("user.dir");
        }
        Path path = Paths.get(dbDir, "faturamento.db");
        return "jdbc:sqlite:" + path.toAbsolutePath();
    }

    private void criarTabelaClientesSeNecessario(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "email TEXT NOT NULL UNIQUE, "
                + "senha TEXT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS clientes ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "cpf_cnpj TEXT NOT NULL UNIQUE, "
                + "email TEXT, "
                + "telefone TEXT)");
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS faturas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "id_cliente INTEGER NOT NULL, "
                + "descricao TEXT NOT NULL, "
                + "valor REAL NOT NULL, "
                + "data_vencimento TEXT, "
                + "status TEXT DEFAULT 'Pendente', "
                + "FOREIGN KEY (id_cliente) REFERENCES clientes(id))");
        }
    }

    public ResultSet executar(String sql, Object... parametros) throws SQLException {
        Connection conn = this.obterConexao();
        criarTabelaClientesSeNecessario(conn);
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }
        
        // CORREÇÃO AQUI: Garante que o SQLite liberta o "lock" quando terminar a leitura
        ps.closeOnCompletion();
        
        return ps.executeQuery();
    }

    public int executarUpdate(String sql, Object... parametros) throws SQLException {
        Connection conn = this.obterConexao();
        criarTabelaClientesSeNecessario(conn);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }
            return ps.executeUpdate();
        }
    }
}