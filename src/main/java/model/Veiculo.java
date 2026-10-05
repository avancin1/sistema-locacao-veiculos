package model;

public class Veiculo {
    private int idVeiculo;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double valorDiaria;
    // No banco é CHAR(1): 'S' ou 'N'. O controller converte de/para boolean.
    private boolean disponivel;

    public Veiculo() { }

    public Veiculo(int idVeiculo, String placa, String modelo, String marca,
                   int ano, double valorDiaria, boolean disponivel) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
    }

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public double getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(double valorDiaria) { this.valorDiaria = valorDiaria; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "Veiculo [id=" + idVeiculo + ", placa=" + placa + ", " + marca + " " + modelo
                + ", ano=" + ano + ", diaria=" + valorDiaria + ", disponivel=" + disponivel + "]";
    }
}
