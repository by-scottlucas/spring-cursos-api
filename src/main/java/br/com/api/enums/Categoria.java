package br.com.api.enums;

public enum Categoria {
    BACKEND("Back-End"),
    FRONTEND("Front-End");

    private String value;

    private Categoria(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
