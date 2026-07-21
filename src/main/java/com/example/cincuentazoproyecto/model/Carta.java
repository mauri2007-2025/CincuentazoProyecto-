package com.example.cincuentazoproyecto.model;

import javafx.scene.image.Image;

public class Carta {

    private Palo palo;
    private Valor valor;

    public Carta(Palo palo, Valor valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public Valor getValor() {
        return valor;
    }

    public int getPuntos() {
        return valor.getPuntos();
    }

    public int getPuntos(int sumaActualMesa) {

        if (valor == Valor.AS) {

            if (sumaActualMesa + 10 <= 50) {
                return 10;
            }

            return 1;

        }

        return valor.getPuntos();

    }

    public Image getImagen() {

        String nombreValor = "";

        switch (valor) {
            case AS -> nombreValor = "ace";
            case DOS -> nombreValor = "2";
            case TRES -> nombreValor = "3";
            case CUATRO -> nombreValor = "4";
            case CINCO -> nombreValor = "5";
            case SEIS -> nombreValor = "6";
            case SIETE -> nombreValor = "7";
            case OCHO -> nombreValor = "8";
            case NUEVE -> nombreValor = "9";
            case DIEZ -> nombreValor = "10";
            case J -> nombreValor = "jack";
            case Q -> nombreValor = "queen";
            case K -> nombreValor = "king";
        }

        String nombrePalo = "";

        switch (palo) {
            case TREBOLES -> nombrePalo = "clubs";
            case DIAMANTES -> nombrePalo = "diamonds";
            case CORAZONES -> nombrePalo = "hearts";
            case PICAS -> nombrePalo = "spades";
        }

        String ruta = "/com/example/cincuentazoproyecto/cartas/"
                + nombreValor
                + "_of_"
                + nombrePalo
                + ".png";

        return new Image(getClass().getResourceAsStream(ruta));
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }

}