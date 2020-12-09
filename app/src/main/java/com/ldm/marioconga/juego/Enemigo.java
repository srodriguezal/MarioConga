package com.ldm.marioconga.juego;

public class Enemigo {
    public static final int TIPO_1 = 0;
    public static final int TIPO_2 = 1;
    public static final int TIPO_3 = 2;
    public int x, y;
    public int tipo;

    public Enemigo(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

