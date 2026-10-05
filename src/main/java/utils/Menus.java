package utils;

/** Só imprime menus. Quem trata a opção escolhida é a classe Principal. */
public class Menus {
    public static void principal() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1 - Relatórios");
        System.out.println("2 - Inserir registros");
        System.out.println("3 - Remover registros");
        System.out.println("4 - Atualizar registros");
        System.out.println("5 - Sair");
    }

    public static void relatorios() {
        System.out.println("\n----- RELATÓRIOS -----");
        System.out.println("1 - Locações por modelo (total e duração média)");
        System.out.println("2 - Locações detalhadas (cliente + veículo)");
        System.out.println("0 - Voltar");
    }

    public static void entidades(String acao) {
        System.out.println("\n----- " + acao.toUpperCase() + ": ESCOLHA A ENTIDADE -----");
        System.out.println("1 - Clientes");
        System.out.println("2 - Veículos");
        System.out.println("3 - Locações");
        System.out.println("0 - Voltar");
    }
}
