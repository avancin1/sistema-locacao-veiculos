package controller;

import conexion.ConexaoBanco;
import model.Locacao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ControllerLocacao {

    /** JÁ IMPLEMENTADO: modelo do padrão. */
    public int contar() {
        String sql = "SELECT COUNT(1) total_locacoes FROM locacoes";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total_locacoes") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar locações: " + e.getMessage());
            return 0;
        }
    }

   
   
    public boolean existe(int idLocacao) {
    	    String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_locacao = " + idLocacao;

    	    try (Connection con = ConexaoBanco.getConexao();
    	         Statement st = con.createStatement();
    	         ResultSet rs = st.executeQuery(sql)) {

    	        return rs.next() && rs.getInt("total") > 0;

    	    } catch (SQLException e) {
    	        System.out.println("Erro ao verificar locação: " + e.getMessage());
    	        return false;
    	    }
    	}
    public void inserir(Locacao l) {
    	String sql = "INSERT INTO locacoes "
                + "(id_cliente, id_veiculo, data_retirada, data_devolucao_prevista, data_devolucao, valor_total) "
                + "VALUES ("
                + l.getCliente().getIdCliente() + ", "
                + l.getVeiculo().getIdVeiculo() + ", '"
                + l.getDataRetirada() + "', '"
                + l.getDataDevolucaoPrevista() + "', "
                + (l.getDataDevolucao() == null ? "NULL" : "'" + l.getDataDevolucao() + "'") + ", "
                + (l.getValorTotal() == null ? "NULL" : l.getValorTotal()) + ")";

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Locação inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir locação: " + e.getMessage());
        }
    }

    public void atualizar(Locacao l) {
        String sql = "UPDATE locacoes SET "
                + "id_cliente = " + l.getCliente().getIdCliente() + ", "
                + "id_veiculo = " + l.getVeiculo().getIdVeiculo() + ", "
                + "data_retirada = '" + l.getDataRetirada() + "', "
                + "data_devolucao_prevista = '" + l.getDataDevolucaoPrevista() + "' "
                + "WHERE id_locacao = " + l.getIdLocacao();

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Locação atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar locação: " + e.getMessage());
        }
    }

    public void remover(int idLocacao) {
        String sql = "DELETE FROM locacoes WHERE id_locacao = " + idLocacao;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Locação removida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover locação: " + e.getMessage());
        }
    }
    public List<Locacao> listar() {
        List<Locacao> lista = new java.util.ArrayList<>();

        String sql = "SELECT "
                + "id_locacao, "
                + "id_cliente, "
                + "id_veiculo, "
                + "data_retirada, "
                + "data_devolucao_prevista, "
                + "data_devolucao, "
                + "valor_total "
                + "FROM locacoes "
                + "ORDER BY id_locacao";

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            controller.ControllerCliente cc = new controller.ControllerCliente();
            controller.ControllerVeiculo cv = new controller.ControllerVeiculo();

            while (rs.next()) {
                Locacao l = new Locacao();

                l.setIdLocacao(rs.getInt("id_locacao"));

                l.setCliente(
                        cc.buscarPorId(rs.getInt("id_cliente"))
                );

                l.setVeiculo(
                        cv.buscarPorId(rs.getInt("id_veiculo"))
                );

                l.setDataRetirada(
                        rs.getDate("data_retirada").toLocalDate()
                );

                l.setDataDevolucaoPrevista(
                        rs.getDate("data_devolucao_prevista").toLocalDate()
                );

                if (rs.getDate("data_devolucao") != null) {
                    l.setDataDevolucao(
                            rs.getDate("data_devolucao").toLocalDate()
                    );
                }

                if (rs.getObject("valor_total") != null) {
                    l.setValorTotal(
                            rs.getDouble("valor_total")
                    );
                }

                lista.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar locações: " + e.getMessage());
        }

        return lista;
    }

    public Locacao buscarPorId(int idLocacao) {
        String sql = "SELECT "
                + "l.id_locacao, "
                + "l.id_cliente, "
                + "l.id_veiculo, "
                + "l.data_retirada, "
                + "l.data_devolucao_prevista, "
                + "l.data_devolucao, "
                + "l.valor_total "
                + "FROM locacoes l "
                + "WHERE l.id_locacao = " + idLocacao;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                Locacao l = new Locacao();

                l.setIdLocacao(rs.getInt("id_locacao"));

                controller.ControllerCliente cc = new controller.ControllerCliente();
                controller.ControllerVeiculo cv = new controller.ControllerVeiculo();

                l.setCliente(cc.buscarPorId(rs.getInt("id_cliente")));
                l.setVeiculo(cv.buscarPorId(rs.getInt("id_veiculo")));

                l.setDataRetirada(rs.getDate("data_retirada").toLocalDate());
                l.setDataDevolucaoPrevista(
                        rs.getDate("data_devolucao_prevista").toLocalDate()
                );

                if (rs.getDate("data_devolucao") != null) {
                    l.setDataDevolucao(
                            rs.getDate("data_devolucao").toLocalDate()
                    );
                }

                if (rs.getObject("valor_total") != null) {
                    l.setValorTotal(rs.getDouble("valor_total"));
                }

                return l;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar locação: " + e.getMessage());
        }

        return null;
    }
    
    public int contarPorCliente(int idCliente) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_cliente = " + idCliente;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return rs.next() ? rs.getInt("total") : 0;

        } catch (SQLException e) {
            System.out.println("Erro ao contar locações do cliente: " + e.getMessage());
            return 0;
        }
    }

    public int contarPorVeiculo(int idVeiculo) {
        String sql = "SELECT COUNT(1) total FROM locacoes WHERE id_veiculo = " + idVeiculo;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            return rs.next() ? rs.getInt("total") : 0;

        } catch (SQLException e) {
            System.out.println("Erro ao contar locações do veículo: " + e.getMessage());
            return 0;
        }
    }

    public void removerPorCliente(int idCliente) {
        String sql = "DELETE FROM locacoes WHERE id_cliente = " + idCliente;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao remover locações do cliente: " + e.getMessage());
        }
    }

    public void removerPorVeiculo(int idVeiculo) {
        String sql = "DELETE FROM locacoes WHERE id_veiculo = " + idVeiculo;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao remover locações do veículo: " + e.getMessage());
        }
    }
}
