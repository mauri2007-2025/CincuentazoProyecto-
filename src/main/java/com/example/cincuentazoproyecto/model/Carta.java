package com.example.cincuentazoproyecto.model;

import javafx.scene.image.Image;

public class Carta {

    private Palo palo;
    private Valor valor;
    private Image imagen;

    public Carta(Palo palo, Valor valor, Image imagen) {

        this.palo = palo;
        this.valor = valor;
        this.imagen = imagen;

    }

    public Palo getPalo() {
        return palo;
    }

    public Valor getValor() {
        return valor;
    }

    public Image getImagen() {
        return imagen;
    }

    public int getPuntos() {
        return valor.getPuntos();
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}