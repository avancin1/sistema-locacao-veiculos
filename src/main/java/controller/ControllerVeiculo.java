package controller;

import conexion.ConexaoBanco;
import model.Veiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerVeiculo {

    public int contar() {
        String sql = "SELECT COUNT(1) total_veiculos FROM veiculos";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total_veiculos") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar veículos: " + e.getMessage());
            return 0;
        }
    }

    public boolean existe(int idVeiculo) {
        String sql = "SELECT COUNT(1) total FROM veiculos WHERE id_veiculo = " + idVeiculo;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() && rs.getInt("total") > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar veículo: " + e.getMessage());
            return false;
        }
    }

    public void inserir(Veiculo v) {
        String sql = "INSERT INTO veiculos (placa, modelo, marca, ano, valor_diaria, disponivel) VALUES ('"
                + v.getPlaca() + "', '" + v.getModelo() + "', '" + v.getMarca() + "', "
                + v.getAno() + ", " + v.getValorDiaria() + ", '"
                + (v.isDisponivel() ? "S" : "N") + "')";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Veículo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir veículo: " + e.getMessage());
        }
    }

    public void atualizar(Veiculo v) {
        String sql = "UPDATE veiculos SET placa = '" + v.getPlaca()
                + "', modelo = '" + v.getModelo()
                + "', marca = '" + v.getMarca()
                + "', ano = " + v.getAno()
                + ", valor_diaria = " + v.getValorDiaria()
                + ", disponivel = '" + (v.isDisponivel() ? "S" : "N") + "'"
                + " WHERE id_veiculo = " + v.getIdVeiculo();
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Veículo atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    public void remover(int idVeiculo) {
        String sql = "DELETE FROM veiculos WHERE id_veiculo = " + idVeiculo;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Veículo removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
        }
    }

    public Veiculo buscarPorId(int idVeiculo) {
        String sql = "SELECT * FROM veiculos WHERE id_veiculo = " + idVeiculo;
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return montarVeiculo(rs);
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
            return null;
        }
    }

    public List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculos ORDER BY id_veiculo";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(montarVeiculo(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        }
        return lista;
    }

    private Veiculo montarVeiculo(ResultSet rs) throws SQLException {
        Veiculo v = new Veiculo();
        v.setIdVeiculo(rs.getInt("id_veiculo"));
        v.setPlaca(rs.getString("placa"));
        v.setModelo(rs.getString("modelo"));
        v.setMarca(rs.getString("marca"));
        v.setAno(rs.getInt("ano"));
        v.setValorDiaria(rs.getDouble("valor_diaria"));
        v.setDisponivel(rs.getString("disponivel").equals("S"));
        return v;
    }
}
