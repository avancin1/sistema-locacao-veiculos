package reports;

import conexion.ConexaoBanco;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Relatorios {
    // Caminhos relativos à raiz do projeto (rode o programa a partir da raiz)
    public static final String LOCACOES_POR_MODELO = "sql/relatorios/relatorio_locacoes_por_modelo.sql";
    public static final String LOCACOES_DETALHADAS = "sql/relatorios/relatorio_locacoes_detalhadas.sql";

    /** Lê o arquivo .sql, executa e imprime o resultado em colunas. */
    public void executar(String caminhoSql) {
        try {
            String sql = Files.readString(Path.of(caminhoSql));
            try (Connection con = ConexaoBanco.getConexao();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                ResultSetMetaData md = rs.getMetaData();
                int colunas = md.getColumnCount();

                StringBuilder cabecalho = new StringBuilder();
                for (int i = 1; i <= colunas; i++) {
                    cabecalho.append(String.format("%-24s", md.getColumnLabel(i)));
                }
                System.out.println("\n" + cabecalho);
                System.out.println("-".repeat(cabecalho.length()));

                boolean vazio = true;
                while (rs.next()) {
                    vazio = false;
                    StringBuilder linha = new StringBuilder();
                    for (int i = 1; i <= colunas; i++) {
                        Object valor = rs.getObject(i);
                        linha.append(String.format("%-24s", valor == null ? "-" : valor.toString()));
                    }
                    System.out.println(linha);
                }
                if (vazio) System.out.println("(nenhum registro)");
            }
        } catch (java.io.IOException e) {
            System.out.println("Não consegui ler o arquivo " + caminhoSql + ": " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao executar o relatório: " + e.getMessage());
        }
    }
}
