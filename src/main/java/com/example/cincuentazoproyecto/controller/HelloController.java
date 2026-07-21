package com.example.cincuentazoproyecto.controller;

import com.example.cincuentazoproyecto.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    //=================== MESA ===================
    @FXML
    private void jugarCarta1(MouseEvent event) {

        jugarCarta(0);

    }

    @FXML
    private void jugarCarta2(MouseEvent event) {

        jugarCarta(1);

    }

    @FXML
    private void jugarCarta3(MouseEvent event) {

        jugarCarta(2);

    }

    @FXML
    private void jugarCarta4(MouseEvent event) {

        jugarCarta(3);

    }
    private void verificarGanador() {

        if (juego.getMesa().getSumaMesa() == 50) {

            txtSalida.appendText("\n¡CINCUENTAZO!\n");

            btnJugar.setDisable(true);
            btnReiniciar.setDisable(false);

            return;
        }

        boolean todosSinCartas = true;

        for (Jugador jugador : juego.getJugadores()) {

            if (jugador.tieneCartas()) {
                todosSinCartas = false;
                break;
            }

        }

        if (juego.getMazo().estaVacio() && todosSinCartas) {

            txtSalida.appendText("\nLa partida terminó.\n");

            btnJugar.setDisable(true);
            btnReiniciar.setDisable(false);

        }

    }
    private boolean verificarCincuentazo() {

        if (juego.getMesa().getSumaMesa() == 50) {

            txtSalida.appendText("\n¡¡CINCUENTAZO!!\n");

            return true;
        }

        return false;
    }

    private void jugarCarta(int posicion) {

        // Verificar que la carta exista
        if (posicion >= jugadorHumano.getMano().size()) {
            return;
        }

        // Guardamos la carta solo para mostrarla en el TextArea
        Carta cartaJugador = jugadorHumano.getCarta(posicion);

        // ================== TURNO DEL JUGADOR ==================

        if (juego.jugarCarta(jugadorHumano, posicion)) {

            txtSalida.appendText(
                    jugadorHumano.getNombre()
                            + " jugó "
                            + mostrarCarta(cartaJugador)
                            + "\n"
            );

        } else {

            txtSalida.appendText(
                    jugadorHumano.getNombre()
                            + " no puede jugar "
                            + mostrarCarta(cartaJugador)
                            + " porque supera 50.\n"
            );

        }

        // ================== TURNO DE LAS CPU ==================

        for (int i = 1; i < juego.getJugadores().size(); i++) {

            Jugador cpu = juego.getJugadores().get(i);

            Carta cartaCPU = juego.jugarTurnoCPU(cpu);

            if (cartaCPU != null) {

                txtSalida.appendText(
                        cpu.getNombre()
                                + " jugó "
                                + mostrarCarta(cartaCPU)
                                + "\n"
                );

            } else {

                txtSalida.appendText(
                        cpu.getNombre()
                                + " no pudo jugar.\n"
                );

            }

        }

        // ================== ELIMINAR JUGADORES ==================

        juego.eliminarJugadoresSinJugada();
        actualizarInterfaz();

        if (!juego.jugadorSigueEnJuego(jugadorHumano)) {

            txtSalida.appendText(
                    "\n❌ Has sido eliminado.\n"
            );

        }

        // ================== GANADOR ==================

        if (juego.juegoTerminado()) {

            actualizarInterfaz();

            Jugador ganador = juego.obtenerGanador();

            txtSalida.appendText(
                    "\n=====================\n" +
                            "🏆 GANADOR: " + ganador.getNombre() +
                            "\n=====================\n"
            );

            btnJugar.setDisable(true);
            btnReiniciar.setDisable(false);

            return;

        }
        // ================== SIGUIENTE RONDA ==================

        ronda++;
        lblRonda.setText("Ronda " + ronda);

        actualizarInterfaz();



    }
    @FXML
    private ImageView imgMesa;

    @FXML
    private Label lblSumaMesa;

    //=================== JUGADOR ===================

    @FXML
    private ImageView imgJugador1;

    @FXML
    private ImageView imgJugador2;

    @FXML
    private ImageView imgJugador3;

    @FXML
    private ImageView imgJugador4;

    //=================== CPU 1 ===================

    @FXML
    private ImageView imgCPU11;

    @FXML
    private ImageView imgCPU12;

    @FXML
    private ImageView imgCPU13;

    @FXML
    private ImageView imgCPU14;

    //=================== CPU 2 ===================

    @FXML
    private ImageView imgCPU21;

    @FXML
    private ImageView imgCPU22;

    @FXML
    private ImageView imgCPU23;

    @FXML
    private ImageView imgCPU24;

    //=================== CPU 3 ===================

    @FXML
    private ImageView imgCPU31;

    @FXML
    private ImageView imgCPU32;

    @FXML
    private ImageView imgCPU33;

    @FXML
    private ImageView imgCPU34;

    //=================== CONTROLES ===================

    @FXML
    private Label lblRonda;

    @FXML
    private TextArea txtSalida;

    @FXML
    private Button btnJugar;

    @FXML
    private Button btnReiniciar;

    //=================== MODELO ===================

    private Juego juego;

    private JugadorHumano jugadorHumano;

    private int ronda;

    private ImageView[] cartasJugador;

    private ImageView[][] cartasCPU;

    private Image imagenReverso;

    //=================== INITIALIZE ===================

    @FXML
    public void initialize() {

        cartasJugador = new ImageView[]{

                imgJugador1,
                imgJugador2,
                imgJugador3,
                imgJugador4

        };

        cartasCPU = new ImageView[][]{

                {imgCPU11, imgCPU12, imgCPU13, imgCPU14},

                {imgCPU21, imgCPU22, imgCPU23, imgCPU24},

                {imgCPU31, imgCPU32, imgCPU33, imgCPU34}

        };

        imagenReverso = new Image(

                getClass().getResourceAsStream(

                        "/com/example/cincuentazoproyecto/cartas/back.png"

                )

        );

        iniciarJuego();   // ← ESTA LÍNEA ES NUEVA
    }

    //=================== INICIAR JUEGO ===================

    private void iniciarJuego() {

        juego = new Juego();

        jugadorHumano = new JugadorHumano("TÚ");

        juego.agregarJugador(jugadorHumano);
        juego.agregarJugador(new JugadorMaquina("CPU 1"));
        juego.agregarJugador(new JugadorMaquina("CPU 2"));
        juego.agregarJugador(new JugadorMaquina("CPU 3"));

        juego.repartirManosIniciales();

        juego.iniciarMesa();

        ronda = 1;

        lblRonda.setText("Ronda " + ronda);

        txtSalida.clear();

        btnJugar.setDisable(false);
        btnReiniciar.setDisable(true);

        actualizarInterfaz();

    }

//=================== ACTUALIZAR TODA LA INTERFAZ ===================

    private void actualizarInterfaz() {

        actualizarMesa();

        actualizarJugador();

        actualizarCPU();

    }

//=================== ACTUALIZAR JUGADOR ===================

    private void actualizarJugador() {
        if (jugadorHumano.estaEliminado()) {

            for (ImageView carta : cartasJugador) {
                carta.setImage(null);
            }

            return;
        }

        for (ImageView imageView : cartasJugador) {

            imageView.setImage(null);

        }

        if (!juego.jugadorSigueEnJuego(jugadorHumano)) {

            return;

        }

        for (int i = 0;
             i < jugadorHumano.getMano().size() && i < cartasJugador.length;
             i++) {

            cartasJugador[i].setImage(

                    jugadorHumano.getMano().get(i).getImagen()

            );

        }

    }

//=================== ACTUALIZAR CPU ===================

    private void actualizarCPU() {

        // Limpiar todas las imágenes
        for (int cpu = 0; cpu < cartasCPU.length; cpu++) {

            for (int carta = 0; carta < cartasCPU[cpu].length; carta++) {

                cartasCPU[cpu][carta].setImage(null);

            }

        }

        int posicionCPU = 0;

        for (Jugador jugador : juego.getJugadores()) {

            // Saltar al jugador humano
            if (jugador == jugadorHumano) {
                continue;
            }

            // Saltar CPUs eliminadas
            if (jugador.estaEliminado()) {
                posicionCPU++;
                continue;
            }

            if (posicionCPU >= cartasCPU.length) {
                break;
            }

            // Mostrar el reverso de las cartas de la CPU
            for (int i = 0;
                 i < jugador.getMano().size() && i < cartasCPU[posicionCPU].length;
                 i++) {

                cartasCPU[posicionCPU][i].setImage(imagenReverso);

            }

            posicionCPU++;

        }

    }
    private void actualizarMesa() {

        Carta ultimaCarta = juego.getMesa().getUltimaCarta();

        if (ultimaCarta != null) {

            imgMesa.setImage(ultimaCarta.getImagen());

        } else {

            imgMesa.setImage(null);

        }

        lblSumaMesa.setText(
                "Suma: " + juego.getMesa().getSumaMesa()
        );

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

    private String obtenerValor(Carta carta) {

        switch (carta.getValor()) {

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

        txtSalida.appendText("Botón presionado.\n");

    }

    @FXML
    public void reiniciarJuego() {

        iniciarJuego();

    }



}