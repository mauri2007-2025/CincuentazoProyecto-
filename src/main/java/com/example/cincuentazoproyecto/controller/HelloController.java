package com.example.cincuentazoproyecto.controller;

import com.example.cincuentazoproyecto.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class HelloController {
    @FXML
    private ImageView imgCPU1;

    @FXML
    private ImageView imgCPU2;

    @FXML
    private ImageView imgCPU3;

    @FXML
    private ImageView imgJugador;

    @FXML
    private Label lblPuntosCPU1;

    @FXML
    private Label lblPuntosCPU2;

    @FXML
    private Label lblPuntosCPU3;

    @FXML
    private Label lblPuntosJugador;

    @FXML
    private Label lblRonda;

    @FXML
    private TextArea txtSalida;

    @FXML
    private Button btnJugar;

    @FXML
    private Button btnReiniciar;


    private Juego juego;

    @FXML
    public void initialize() {

        juego = new Juego();

        juego.agregarJugador(new JugadorHumano("TÚ"));

        juego.agregarJugador(new JugadorMaquina("CPU 1"));

        juego.agregarJugador(new JugadorMaquina("CPU 2"));

        juego.agregarJugador(new JugadorMaquina("CPU 3"));


    }

    private String mostrarCarta(Carta carta) {

        String palo = "";

        switch (carta.getPalo()) {

            case CORAZONES:
                palo = "♥";
                break;

            case DIAMANTES:
                palo = "♦";
                break;

            case TREBOLES:
                palo = "♣";
                break;

            case PICAS:
                palo = "♠";
                break;
        }

        return obtenerValor(carta) + palo;
    }
    private String obtenerValor(Carta carta){

        switch (carta.getValor()){

            case AS:
                return "A";

            case DOS:
                return "2";

            case TRES:
                return "3";

            case CUATRO:
                return "4";

            case CINCO:
                return "5";

            case SEIS:
                return "6";

            case SIETE:
                return "7";

            case OCHO:
                return "8";

            case NUEVE:
                return "9";

            case DIEZ:
                return "10";

            case J:
                return "J";

            case Q:
                return "Q";

            case K:
                return "K";
        }

        return "";
    }

    @FXML
    public void jugarRonda() {

        txtSalida.appendText("\n========== NUEVA RONDA ==========\n");

        for (Jugador jugador : juego.getJugadores()) {

            Carta carta = juego.repartirCarta(jugador);

            if (carta != null) {

                txtSalida.appendText(
                        jugador.getNombre()
                                + " recibió "
                                + carta
                                + " | Total: "
                                + jugador.calcularPuntos()
                                + "\n"
                );

                if (jugador.getNombre().equals("TÚ")) {

                    imgJugador.setImage(carta.getImagen());
                    lblPuntosJugador.setText("Puntos: " + jugador.calcularPuntos());

                } else if (jugador.getNombre().equals("CPU 1")) {

                    imgCPU1.setImage(carta.getImagen());
                    lblPuntosCPU1.setText("Puntos: " + jugador.calcularPuntos());

                } else if (jugador.getNombre().equals("CPU 2")) {

                    imgCPU2.setImage(carta.getImagen());
                    lblPuntosCPU2.setText("Puntos: " + jugador.calcularPuntos());

                } else if (jugador.getNombre().equals("CPU 3")) {

                    imgCPU3.setImage(carta.getImagen());
                    lblPuntosCPU3.setText("Puntos: " + jugador.calcularPuntos());

                }

                }

            }

        }

    }
