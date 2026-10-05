import java.time.LocalDate;
import controller.ControllerCliente;
import controller.ControllerLocacao;
import controller.ControllerVeiculo;
import model.Cliente;
import model.Locacao;
import model.Veiculo;
import reports.Relatorios;
import utils.Entrada;
import utils.Menus;
import utils.SplashScreen;

public class Principal {
    private static final ControllerCliente controllerCliente = new ControllerCliente();
    private static final ControllerVeiculo controllerVeiculo = new ControllerVeiculo();
    private static final ControllerLocacao controllerLocacao = new ControllerLocacao();
    private static final Relatorios relatorios = new Relatorios();

    public static void main(String[] args) {
        SplashScreen.exibir(controllerCliente.contar(), controllerVeiculo.contar(), controllerLocacao.contar());

        int opcao;
        do {
            Menus.principal();
            opcao = Entrada.lerInt("Opção: ");
            switch (opcao) {
                case 1 -> menuRelatorios();
                case 2 -> menuInserir();
                case 3 -> menuRemover();
                case 4 -> menuAtualizar();
                case 5 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void menuRelatorios() {
        int opcao;
        do {
            Menus.relatorios();
            opcao = Entrada.lerInt("Opção: ");
            switch (opcao) {
                case 1 -> relatorios.executar(Relatorios.LOCACOES_POR_MODELO);
                case 2 -> relatorios.executar(Relatorios.LOCACOES_DETALHADAS);
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Padrão dos três menus abaixo (edital 6.b, 6.c, 6.d):
    // repetir { escolher entidade -> fluxo da entidade -> "deseja continuar?" } até o usuário voltar (0).

    private static void menuInserir() {   int opcao;

    do {
        Menus.entidades("Inserir");
        opcao = Entrada.lerInt("Opção: ");

        switch (opcao) {
            case 1 -> {
            	String nome = Entrada.lerTexto("Nome: ");
            	String cpf = Entrada.lerTexto("CPF: ");
            	String telefone = Entrada.lerTexto("Telefone: ");
            	String cnh = Entrada.lerTexto("CNH: ");

                Cliente cliente = new Cliente(0, nome, cpf, telefone, cnh);

                controllerCliente.inserir(cliente);
                
                boolean continuar = Entrada.lerSimNao("Deseja inserir outro cliente?");
                if (!continuar) {
                    opcao = 0;
                }
            }
            case 2 -> {
     
                String placa = Entrada.lerTexto("Placa: ");
                String modelo = Entrada.lerTexto("Modelo: ");
                String marca = Entrada.lerTexto("Marca: ");
                int ano = Entrada.lerInt("Ano: ");
                double valorDiaria = Entrada.lerDouble("Valor da diária: ");
                boolean disponivel = Entrada.lerSimNao("O veículo está disponível?");

                Veiculo veiculo = new Veiculo(
                    0,
                    placa,
                    modelo,
                    marca,
                    ano,
                    valorDiaria,
                    disponivel
                );

                controllerVeiculo.inserir(veiculo);

                boolean continuar = Entrada.lerSimNao("Deseja inserir outro veículo?");
                if (!continuar) {
                    opcao = 0;
                }
            }
            case 3 -> {
                int idCliente = Entrada.lerInt("ID do cliente: ");
                int idVeiculo = Entrada.lerInt("ID do veículo: ");

                if (!controllerCliente.existe(idCliente)) {
                    System.out.println("Cliente não encontrado.");
                    break;
                }

                if (!controllerVeiculo.existe(idVeiculo)) {
                    System.out.println("Veículo não encontrado.");
                    break;
                }

                LocalDate dataRetirada = Entrada.lerData("Data de retirada");
                LocalDate dataDevolucaoPrevista = Entrada.lerData("Data de devolução prevista");

                Locacao locacao = new Locacao();

                locacao.setCliente(controllerCliente.buscarPorId(idCliente));
                locacao.setVeiculo(controllerVeiculo.buscarPorId(idVeiculo));
                locacao.setDataRetirada(dataRetirada);
                locacao.setDataDevolucaoPrevista(dataDevolucaoPrevista);

                controllerLocacao.inserir(locacao);
                
                boolean continuar = Entrada.lerSimNao("Deseja inserir outra locação?");
                if (!continuar) {
                    opcao = 0;
                }
            }
            case 0 -> { }
            default -> System.out.println("Opção inválida.");
        }

    } while (opcao != 0);}

    private static void menuRemover() {
    	int opcao;

    	do {
    	    Menus.entidades("Remover");
    	    opcao = Entrada.lerInt("Opção: ");

    	    switch (opcao) {
    	    
    	    case 1 -> {
    	        int idCliente = Entrada.lerInt("ID do cliente: ");

    	        if (!controllerCliente.existe(idCliente)) {
    	            System.out.println("Cliente não encontrado.");
    	            break;
    	        }

    	        int totalLocacoes = controllerLocacao.contarPorCliente(idCliente);

    	        if (totalLocacoes > 0) {
    	            System.out.println("Este cliente possui " + totalLocacoes + " locação(ões).");

    	            boolean removerLocacoes = Entrada.lerSimNao(
    	                    "Deseja remover também as locações desse cliente?"
    	            );

    	            if (!removerLocacoes) {
    	                System.out.println("Cliente não removido.");
    	                break;
    	            }

    	            controllerLocacao.removerPorCliente(idCliente);
    	        }

    	        controllerCliente.remover(idCliente);
    	    }

    	    case 2 -> {
    	        int idVeiculo = Entrada.lerInt("ID do veículo: ");

    	        if (!controllerVeiculo.existe(idVeiculo)) {
    	            System.out.println("Veículo não encontrado.");
    	            break;
    	        }

    	        int totalLocacoes = controllerLocacao.contarPorVeiculo(idVeiculo);

    	        if (totalLocacoes > 0) {
    	            System.out.println("Este veículo possui " + totalLocacoes + " locação(ões).");

    	            boolean removerLocacoes = Entrada.lerSimNao(
    	                    "Deseja remover também as locações desse veículo?"
    	            );

    	            if (!removerLocacoes) {
    	                System.out.println("Veículo não removido.");
    	                break;
    	            }

    	            controllerLocacao.removerPorVeiculo(idVeiculo);
    	        }

    	        controllerVeiculo.remover(idVeiculo);
    	    }

    	        case 3 -> {
    	            int idLocacao = Entrada.lerInt("ID da locação: ");

    	            if (!controllerLocacao.existe(idLocacao)) {
    	                System.out.println("Locação não encontrada.");
    	                break;
    	            }

    	            controllerLocacao.remover(idLocacao);
    	        }

    	        case 0 -> {
    	        }

    	        default -> System.out.println("Opção inválida.");
    	    }

    	} while (opcao != 0);
    }
    private static void menuAtualizar() {
        int opcao;

        do {
            Menus.entidades("Atualizar");
            opcao = Entrada.lerInt("Opção: ");

            switch (opcao) {
                case 1 -> {
                    int idCliente = Entrada.lerInt("ID do cliente: ");

                    Cliente cliente = controllerCliente.buscarPorId(idCliente);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    cliente.setNome(Entrada.lerTexto("Nome: "));
                    cliente.setCpf(Entrada.lerTexto("CPF: "));
                    cliente.setTelefone(Entrada.lerTexto("Telefone: "));
                    cliente.setCnh(Entrada.lerTexto("CNH: "));

                    controllerCliente.atualizar(cliente);
                }

                case 2 -> {
                    int idVeiculo = Entrada.lerInt("ID do veículo: ");

                    Veiculo veiculo = controllerVeiculo.buscarPorId(idVeiculo);

                    if (veiculo == null) {
                        System.out.println("Veículo não encontrado.");
                        break;
                    }

                    veiculo.setPlaca(Entrada.lerTexto("Placa: "));
                    veiculo.setModelo(Entrada.lerTexto("Modelo: "));
                    veiculo.setMarca(Entrada.lerTexto("Marca: "));
                    veiculo.setAno(Entrada.lerInt("Ano: "));
                    veiculo.setValorDiaria(Entrada.lerDouble("Valor da diária: "));
                    veiculo.setDisponivel(Entrada.lerSimNao("O veículo está disponível?"));

                    controllerVeiculo.atualizar(veiculo);
                }

                case 3 -> {
                    int idLocacao = Entrada.lerInt("ID da locação: ");

                    Locacao locacao = controllerLocacao.buscarPorId(idLocacao);

                    if (locacao == null) {
                        System.out.println("Locação não encontrada.");
                        break;
                    }

                    LocalDate dataRetirada = Entrada.lerData("Nova data de retirada");
                    LocalDate dataDevolucaoPrevista =
                            Entrada.lerData("Nova data de devolução prevista");

                    locacao.setDataRetirada(dataRetirada);
                    locacao.setDataDevolucaoPrevista(dataDevolucaoPrevista);

                    controllerLocacao.atualizar(locacao);
                }

                case 0 -> {
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
