package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo() {

        cartas = new ArrayList<>();

        for (Palo palo : Palo.values()) {

            for (Valor valor : Valor.values()) {

                cartas.add(new Carta(palo, valor));

            }

        }

        barajar();

    }

    public void barajar() {

        Collections.shuffle(cartas);

    }

    public Carta sacarCarta() {

        if (estaVacio()) {
            return null;
        }

        return cartas.remove(0);

    }

    public void agregarCarta(Carta carta) {

        cartas.add(carta);

    }

    public void agregarCartas(ArrayList<Carta> nuevasCartas) {

        cartas.addAll(nuevasCartas);

    }

    public boolean estaVacio() {

        return cartas.isEmpty();

    }

    public int cantidadCartas() {

        return cartas.size();

    }

    public ArrayList<Carta> getCartas() {

        return cartas;

    }

}