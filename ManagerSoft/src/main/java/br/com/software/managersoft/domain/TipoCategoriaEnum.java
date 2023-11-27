package br.com.software.managersoft.domain;

public enum TipoCategoriaEnum {
    R("Receita"), D("Despesa");

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    TipoCategoriaEnum(String tipo) {
        this.tipo = tipo;
    }
}
