package com.example.cincuentazoproyecto.model;

import java.util.ArrayList;

public class Juego {

    private Mazo mazo;
    private Mesa mesa;
    private ArrayList<Jugador> jugadores;

    private int turnoActual;

    public Juego() {

        mazo = new Mazo();
        mesa = new Mesa();
        jugadores = new ArrayList<>();

        turnoActual = 0;
    }

    //========================
    // GETTERS
    //========================

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    //========================
    // JUGADORES
    //========================

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void siguienteTurno() {

        turnoActual++;

        if (turnoActual >= jugadores.size()) {
            turnoActual = 0;
        }

    }

    //========================
    // INICIO DEL JUEGO
    //========================

    public void repartirManosIniciales() {

        for (int i = 0; i < 4; i++) {

            for (Jugador jugador : jugadores) {

                Carta carta = mazo.sacarCarta();

                if (carta != null) {
                    jugador.recibirCarta(carta);
                }

            }

        }

    }

    public void iniciarMesa() {

        Carta carta = mazo.sacarCarta();

        if (carta != null) {
            mesa.ponerCarta(carta);
        }

    }

    //========================
    // REGLAS
    //========================

    public boolean puedeJugar(Carta carta) {

        return mesa.puedeJugar(carta);

    }

    //========================
    // JUGAR UNA CARTA (HUMANO)
    //========================

    public boolean jugarCarta(Jugador jugador, int posicion) {

        Carta carta = jugador.getCarta(posicion);

        if (carta == null) {
            return false;
        }

        if (!puedeJugar(carta)) {
            return false;
        }

        jugador.eliminarCarta(posicion);

        mesa.ponerCarta(carta);

        Carta nueva = mazo.sacarCarta();

        if (nueva != null) {
            jugador.recibirCarta(nueva);
        }

        return true;

    }

    //========================
    // TURNO CPU
    //========================

    public Carta jugarTurnoCPU(Jugador jugador) {

        for (int i = 0; i < jugador.getMano().size(); i++) {

            Carta carta = jugador.getCarta(i);

            if (puedeJugar(carta)) {

                jugarCarta(jugador, i);

                return carta;

            }

        }

        return null;

    }

    //========================
    // VALIDAR JUGADAS
    //========================

    public boolean jugadorTieneJugada(Jugador jugador) {

        for (Carta carta : jugador.getMano()) {

            if (puedeJugar(carta)) {
                return true;
            }

        }

        return false;

    }

    //========================
    // ELIMINAR JUGADORES
    //========================

    public void eliminarJugadoresSinJugada() {

        for (Jugador jugador : jugadores) {

            if (!jugador.estaEliminado()
                    && !jugadorTieneJugada(jugador)) {

                jugador.eliminar();

            }

        }

    }

    //========================
    // FIN DEL JUEGO
    //========================

    public boolean juegoTerminado() {

        int vivos = 0;

        for (Jugador jugador : jugadores) {

            if (!jugador.estaEliminado()) {
                vivos++;
            }

        }

        return vivos == 1;

    }

    public Jugador obtenerGanador() {

        for (Jugador jugador : jugadores) {

            if (!jugador.estaEliminado()) {
                return jugador;
            }

        }

        return null;

    }

    public boolean jugadorSigueEnJuego(Jugador jugador) {

        return !jugador.estaEliminado();

    }

}