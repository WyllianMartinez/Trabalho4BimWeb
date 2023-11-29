package br.com.software.managersoft.domain;

public class SaldoDTO {

    private double entradas;
    private double saidas;
    private double saldo;

    public SaldoDTO() {
    }

    public SaldoDTO(double entradas, double saidas, double saldo) {
        this.entradas = entradas;
        this.saidas = saidas;
        this.saldo = saldo;
    }

    public double getEntradas() {
        return entradas;
    }

    public void setEntradas(double entradas) {
        this.entradas = entradas;
    }

    public double getSaidas() {
        return saidas;
    }

    public void setSaidas(double saidas) {
        this.saidas = saidas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
