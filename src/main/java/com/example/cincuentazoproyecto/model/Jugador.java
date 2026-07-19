package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public abstract class Jugador {

    protected String nombre;
    protected ArrayList<Carta> mano;

    public Jugador(String nombre){
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    public void recibirCarta(Carta carta){
        mano.add(carta);
    }

    public ArrayList<Carta> getMano(){
        return mano;
    }

    public String getNombre(){
        return nombre;
    }

    public int calcularPuntos(){

        int suma = 0;

        for(Carta carta : mano){
            suma += carta.getPuntos();
        }

        return suma;
    }

    public boolean estaEliminado(){
        return calcularPuntos() > 50;
    }

}