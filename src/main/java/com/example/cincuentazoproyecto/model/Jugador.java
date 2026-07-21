package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public abstract class Jugador {

    protected String nombre;
    protected ArrayList<Carta> mano;

    // Nuevo atributo
    private boolean eliminado;

    public Jugador(String nombre) {

        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.eliminado = false;

    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public void jugarCarta(Carta carta) {
        mano.remove(carta);
    }

    public int calcularPuntos() {

        int suma = 0;

        for (Carta carta : mano) {
            suma += carta.getPuntos();
        }

        return suma;
    }

    public boolean tieneCartas() {
        return !mano.isEmpty();
    }

    // =======================
    // NUEVOS MÉTODOS
    // =======================

    public boolean estaEliminado() {
        return eliminado;
    }

    public void eliminar() {
        eliminado = true;
    }

    public Carta getCarta(int posicion) {

        if (posicion < 0 || posicion >= mano.size()) {
            return null;
        }

        return mano.get(posicion);
    }

    public void eliminarCarta(int posicion) {

        if (posicion >= 0 && posicion < mano.size()) {
            mano.remove(posicion);
        }

    }

}