package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConexaoBanco {

    private static final Dotenv DOTENV = Dotenv.configure()
            .directory("./")
            .ignoreIfMissing()
            .load();

    private static final String URL = DOTENV.get("DB_URL", "jdbc:postgresql://localhost:5432/locadora");
    private static final String USUARIO = DOTENV.get("DB_USER", "postgres");
    private static final String SENHA = DOTENV.get("DB_PASSWORD");

    public static Connection getConexao() throws SQLException {
        if (SENHA == null || SENHA.isBlank()) {
            throw new SQLException(
                    "Senha do banco não encontrada. Defina DB_PASSWORD no arquivo .env na raiz do projeto.");
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
