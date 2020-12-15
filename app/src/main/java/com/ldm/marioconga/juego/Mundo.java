package com.ldm.marioconga.juego;

import java.util.Random;

public class Mundo {
    static final int MUNDO_ANCHO = 10;
    static final int MUNDO_ALTO = 13;
    static final int PUNTUACION_MINIMA = 5;
    static final int PUNTUACION_MEDIA = 10;
    static final int PUNTUACION_MAXIMA = 15;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public Personaje personaje;
    public Premio premio;
    public Enemigo enemigo;
    public boolean finalJuego = false;
    public int puntuacion = 0;
    public int numeroEnemigos;

    boolean campos[][] = new boolean [MUNDO_ANCHO][MUNDO_ALTO];
    boolean camposEnemigo[][]=new boolean [MUNDO_ANCHO][MUNDO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public Mundo() {
        personaje = new Personaje();
        colocarPremio();
        colocarEnemigo();
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

    private void colocarEnemigo() {
        System.out.println("entro");
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

        int enemigox = random.nextInt(MUNDO_ANCHO);
        int enemigoy = random.nextInt(MUNDO_ALTO);
        while (true) {
            if (campos[enemigox][enemigoy] == false)
                break;
            enemigox += 1;
            if (enemigox >= MUNDO_ANCHO) {
                enemigox = 0;
                enemigoy += 1;
                if (enemigoy >= MUNDO_ALTO) {
                    enemigoy = 0;
                }
            }
        }
        enemigo = new Enemigo(enemigox, enemigoy, random.nextInt(3));
        if(enemigo.getX()==premio.getX() && enemigo.getY()==premio.getY()){
            System.out.println("entro12");
            colocarEnemigo();
        }

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
                if (premio.getTipo() == 0){
                    puntuacion += PUNTUACION_MINIMA;
                }else if (premio.getTipo() == 1){
                    puntuacion += PUNTUACION_MEDIA;
                }else {
                    puntuacion += PUNTUACION_MAXIMA;
                }

                personaje.conga();
                if (personaje.partes.size() == MUNDO_ANCHO * MUNDO_ALTO) {
                    finalJuego = true;
                    return;
                } else {
                    colocarPremio();
                    if(personaje.partes.size()%5==0){
                        /*
                        * Aqui se añade un enemigo si la cola es multiplo de 5
                        * se pretente implementar que el bucle for llame colocar enemigo varias veces para colocar un numero aleatorio de enemigos.
                        * */

                        System.out.println(numeroEnemigos);

                        colocarEnemigo();

                    }
                }


                if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                    tick -= TICK_DECREMENTO;
                }
            }
            if(head.x==enemigo.x && head.y==enemigo.y){
                finalJuego=true;
            }

        }
    }
}

