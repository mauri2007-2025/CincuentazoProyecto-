package com.example.cincuentazoproyecto.model;

public enum Valor {

    AS(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(0),
    DIEZ(10),
    J(-10),
    Q(-10),
    K(-10);

    private final int puntos;

    Valor(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

}