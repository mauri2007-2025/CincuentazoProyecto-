package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Carta> cartasMesa;
    private int sumaMesa;

    public Mesa() {

        cartasMesa = new ArrayList<>();
        sumaMesa = 0;

    }

    public void ponerCarta(Carta carta) {

        cartasMesa.add(carta);
        sumaMesa += carta.getPuntos(sumaMesa);

    }

    public int getSumaMesa() {

        return sumaMesa;

    }

    public ArrayList<Carta> getCartasMesa() {

        return cartasMesa;

    }

    public Carta getUltimaCarta() {

        if (cartasMesa.isEmpty()) {
            return null;
        }

        return cartasMesa.get(cartasMesa.size() - 1);

    }

    public void limpiarMesa() {

        cartasMesa.clear();
        sumaMesa = 0;

    }

    public boolean puedeJugar(Carta carta) {

        return sumaMesa + carta.getPuntos() <= 50;

    }

}