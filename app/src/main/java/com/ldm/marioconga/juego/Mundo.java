package com.ldm.marioconga.juego;

import java.util.Random;

public class Mundo {
    static final int MUNDO_ANCHO = 10;
    static final int MUNDO_ALTO = 13;
    static final int INCREMENTO_PUNTUACION = 10;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public Personaje personaje;
    public Premio premio;
    public boolean finalJuego = false;
    public int puntuacion = 0;

    boolean campos[][] = new boolean[MUNDO_ANCHO][MUNDO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public Mundo() {
        personaje = new Personaje();
        colocarPremio();
    }

    private void colocarPremio() {
        for (int x = 0; x < MUNDO_ANCHO; x++) {
            for (int y = 0; y < MUNDO_ALTO; y++) {
                campos[x][y] = false;
            }
        }

        int len = personaje.partes.size();
        for (int i = 0; i < len; i++) {
            Seguidores parte = personaje.partes.get(i);
            campos[parte.x][parte.y] = true;
        }

        int premioX = random.nextInt(MUNDO_ANCHO);
        int premioY = random.nextInt(MUNDO_ALTO);
        while (true) {
            if (campos[premioX][premioY] == false)
                break;
            premioX += 1;
            if (premioX >= MUNDO_ANCHO) {
                premioX = 0;
                premioY += 1;
                if (premioY >= MUNDO_ALTO) {
                    premioY = 0;
                }
            }
        }
        premio = new Premio(premioX, premioY, random.nextInt(3));
    }

    public void update(float deltaTime) {
        if (finalJuego)

            return;

        tiempoTick += deltaTime;

        while (tiempoTick > tick) {
            tiempoTick -= tick;
            personaje.avance();
            if (personaje.comprobarChoque()) {
                finalJuego = true;
                return;
            }

            Seguidores head = personaje.partes.get(0);
            if (head.x == premio.x && head.y == premio.y) {
                puntuacion += INCREMENTO_PUNTUACION;
                personaje.conga();
                if (personaje.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
                    finalJuego = true;
                    return;
                } else {
                    colocarPremio();
                }

                if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                    tick -= TICK_DECREMENTO;
                }
            }
        }
    }
}

