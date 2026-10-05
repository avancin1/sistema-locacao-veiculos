package controller;

import conexion.ConexaoBanco;
import model.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ControllerCliente {

    public int contar() {
        String sql = "SELECT COUNT(1) total_clientes FROM clientes";
        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total_clientes") : 0;
        } catch (SQLException e) {
            System.out.println("Erro ao contar clientes: " + e.getMessage());
            return 0;
        }
    }

    public boolean existe(int idCliente) {
    	 String sql = "SELECT COUNT(1) total FROM clientes WHERE id_cliente = " + idCliente;

    	    try (Connection con = ConexaoBanco.getConexao();
    	         Statement st = con.createStatement();
    	         ResultSet rs = st.executeQuery(sql)) {

    	        return rs.next() && rs.getInt("total") > 0;

    	    } catch (SQLException e) {
    	        System.out.println("Erro ao verificar cliente: " + e.getMessage());
    	        return false;
    	    }
    	}

    public void inserir(Cliente c) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, cnh) VALUES ('"
                + c.getNome() + "', '"
                + c.getCpf() + "', '"
                + c.getTelefone() + "', '"
                + c.getCnh() + "')";

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Cliente inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public void atualizar(Cliente c) {
    	String sql = "UPDATE clientes SET "
                + "nome = '" + c.getNome() + "', "
                + "cpf = '" + c.getCpf() + "', "
                + "telefone = '" + c.getTelefone() + "', "
                + "cnh = '" + c.getCnh() + "' "
                + "WHERE id_cliente = " + c.getIdCliente();

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Cliente atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void remover(int idCliente) {
    	String sql = "DELETE FROM clientes WHERE id_cliente = " + idCliente;

        try (Connection con = ConexaoBanco.getConexao();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Cliente removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listar() {
    	  List<Cliente> clientes = new java.util.ArrayList<>();

    	    String sql = "SELECT id_cliente, nome, cpf, telefone, cnh "
    	               + "FROM clientes ORDER BY id_cliente";

    	    try (Connection con = ConexaoBanco.getConexao();
    	         Statement st = con.createStatement();
    	         ResultSet rs = st.executeQuery(sql)) {

    	        while (rs.next()) {
    	            Cliente c = new Cliente();

    	            c.setIdCliente(rs.getInt("id_cliente"));
    	            c.setNome(rs.getString("nome"));
    	            c.setCpf(rs.getString("cpf"));
    	            c.setTelefone(rs.getString("telefone"));
    	            c.setCnh(rs.getString("cnh"));

    	            clientes.add(c);
    	        }

    	    } catch (SQLException e) {
    	        System.out.println("Erro ao listar clientes: " + e.getMessage());
    	    }

    	    return clientes;
    	}

    public Cliente buscarPorId(int idCliente) {
    	  String sql = "SELECT id_cliente, nome, cpf, telefone, cnh "
                  + "FROM clientes WHERE id_cliente = " + idCliente;

       try (Connection con = ConexaoBanco.getConexao();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

           if (rs.next()) {
               Cliente c = new Cliente();

               c.setIdCliente(rs.getInt("id_cliente"));
               c.setNome(rs.getString("nome"));
               c.setCpf(rs.getString("cpf"));
               c.setTelefone(rs.getString("telefone"));
               c.setCnh(rs.getString("cnh"));

               return c;
           }

       } catch (SQLException e) {
           System.out.println("Erro ao buscar cliente: " + e.getMessage());
       }

       return null;
   }
}
