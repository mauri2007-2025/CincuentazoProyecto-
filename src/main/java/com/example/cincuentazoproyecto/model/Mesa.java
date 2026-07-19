package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Carta> cartasMesa;

    public Mesa() {
        cartasMesa = new ArrayList<>();
    }

    public void ponerCarta(Carta carta) {
        cartasMesa.add(carta);
    }

    public ArrayList<Carta> getCartasMesa() {
        return cartasMesa;
    }

    public void limpiarMesa() {
        cartasMesa.clear();
    }

}