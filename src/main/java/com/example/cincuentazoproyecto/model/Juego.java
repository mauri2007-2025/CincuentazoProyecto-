package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public class Juego {

    private Mazo mazo;
    private Mesa mesa;
    private ArrayList<Jugador> jugadores;

    public Juego() {

        mazo = new Mazo();

        mesa = new Mesa();

        jugadores = new ArrayList<>();

    }

    public void agregarJugador(Jugador jugador){

        jugadores.add(jugador);

    }

    public ArrayList<Jugador> getJugadores(){

        return jugadores;

    }

    public Mazo getMazo(){

        return mazo;

    }

    public Mesa getMesa(){

        return mesa;

    }
    public Carta repartirCarta(Jugador jugador){

        if(mazo.estaVacio()){
            return null;
        }

        Carta carta = mazo.sacarCarta();

        jugador.recibirCarta(carta);

        return carta;
    }

    public void repartirATodos(){

        for(Jugador jugador : jugadores){

            repartirCarta(jugador);

        }

    }

    public void eliminarJugadores(){

        jugadores.removeIf(Jugador::estaEliminado);

    }

    public boolean juegoTerminado(){

        return jugadores.size() == 1;

    }

    public Jugador obtenerGanador(){

        if(juegoTerminado()){

            return jugadores.get(0);

        }

        return null;

    }

}