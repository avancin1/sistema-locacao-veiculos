package utils;

public class SplashScreen {
    
    private static final String NOME_SISTEMA = "SISTEMA DE LOCAÇÃO DE VEÍCULOS";
    private static final String CRIADO_POR = "ANA JULIA, DAYANE";
    private static final String DISCIPLINA = "BANCO DE DADOS";
    private static final String SEMESTRE = "2026/2";
    private static final String PROFESSOR = "HOWARD ROATTI";

    public static void exibir(int totalClientes, int totalVeiculos, int totalLocacoes) {
        String borda = "#".repeat(50);
        System.out.println(borda);
        linha(NOME_SISTEMA);
        linha("");
        linha("TOTAL DE REGISTROS EXISTENTES");
        linha("  1 - CLIENTES:  " + totalClientes);
        linha("  2 - VEÍCULOS:  " + totalVeiculos);
        linha("  3 - LOCAÇÕES:  " + totalLocacoes);
        linha("");
        linha("CRIADO POR: " + CRIADO_POR);
        linha("");
        linha("DISCIPLINA: " + DISCIPLINA);
        linha("            " + SEMESTRE);
        linha("PROFESSOR: " + PROFESSOR);
        System.out.println(borda);
    }

    private static void linha(String texto) {
        System.out.printf("# %-46s #%n", texto);
    }
}
