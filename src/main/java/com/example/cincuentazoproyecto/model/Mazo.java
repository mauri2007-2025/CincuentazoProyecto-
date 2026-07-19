package com.example.cincuentazoproyecto.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo() {

        cartas = new ArrayList<>();

        for (Palo palo : Palo.values()) {

            for (Valor valor : Valor.values()) {

                cartas.add(new Carta(
                        palo,
                        valor,
                        cargarImagen(palo, valor)
                ));

            }

        }

        Collections.shuffle(cartas);

    }

    private Image cargarImagen(Palo palo, Valor valor) {

        String nombreValor = "";
        String nombrePalo = "";

        switch (valor) {

            case AS:
                nombreValor = "ace";
                break;

            case DOS:
                nombreValor = "2";
                break;

            case TRES:
                nombreValor = "3";
                break;

            case CUATRO:
                nombreValor = "4";
                break;

            case CINCO:
                nombreValor = "5";
                break;

            case SEIS:
                nombreValor = "6";
                break;

            case SIETE:
                nombreValor = "7";
                break;

            case OCHO:
                nombreValor = "8";
                break;

            case NUEVE:
                nombreValor = "9";
                break;

            case DIEZ:
                nombreValor = "10";
                break;

            case J:
                nombreValor = "jack";
                break;

            case Q:
                nombreValor = "queen";
                break;

            case K:
                nombreValor = "king";
                break;
        }

        switch (palo) {

            case TREBOLES:
                nombrePalo = "clubs";
                break;

            case DIAMANTES:
                nombrePalo = "diamonds";
                break;

            case CORAZONES:
                nombrePalo = "hearts";
                break;

            case PICAS:
                nombrePalo = "spades";
                break;
        }

        String ruta = "/com/example/cincuentazoproyecto/cartas/"
                + nombreValor
                + "_of_"
                + nombrePalo
                + ".png";

        return new Image(getClass().getResourceAsStream(ruta));
    }

    public Carta sacarCarta() {

        if (cartas.isEmpty()) {
            return null;
        }

        return cartas.remove(0);

    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int cantidadCartas() {
        return cartas.size();
    }

}