package com.ldm.marioconga.juego;

import java.util.ArrayList;
import java.util.List;

public class Personaje {
    public static final int ARRIBA = 0;
    public static final int IZQUIERDA= 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;
    public static final int TIPO_1 = 1;
    public static final int TIPO_2 = 2;
    public static final int TIPO_3 = 3;
    public static final int TIPO_4 = 4;
    public static final int TIPO_5 = 5;
    public static final int TIPO_6 = 6;
    public int tipo;

    public List<Seguidores> partes = new ArrayList<Seguidores>();
    public int direccion;

    public Personaje(int tipo) {
        direccion = ARRIBA;
        partes.add(new Seguidores(5, 6));
        partes.add(new Seguidores(5, 7));
        partes.add(new Seguidores(5, 8));
        this.tipo = tipo;
    }

    public void girarIzquierda() {
        direccion += 1;
        if(direccion > DERECHA)
            direccion = ARRIBA;
    }

    public void girarDerecha() {
        direccion -= 1;
        if(direccion < ARRIBA)
            direccion = DERECHA;
    }

    public void conga() {
        Seguidores end = partes.get(partes.size()-1);
        partes.add(new Seguidores(end.x, end.y));
    }

    public void avance() {
        Seguidores cabeza = partes.get(0);

        int len = partes.size() - 1;
        for(int i = len; i > 0; i--) {
            Seguidores antes = partes.get(i-1);
            Seguidores parte = partes.get(i);
            parte.x = antes.x;
            parte.y = antes.y;
        }

        if(direccion == ARRIBA)
            cabeza.y -= 1;
        if(direccion == IZQUIERDA)
            cabeza.x -= 1;
        if(direccion == ABAJO)
            cabeza.y += 1;
        if(direccion == DERECHA)
            cabeza.x += 1;

        if(cabeza.x < 0)
            cabeza.x = 9;
        if(cabeza.x > 9)
            cabeza.x = 0;
        if(cabeza.y < 0)
            cabeza.y = 12;
        if(cabeza.y > 12)
            cabeza.y = 0;
    }

    public boolean comprobarChoque() {
        int len = partes.size();
        Seguidores cabeza = partes.get(0);
        for(int i = 1; i < len; i++) {
            Seguidores parte = partes.get(i);
            if(parte.x == cabeza.x && parte.y == cabeza.y)
                return true;
        }
        return false;
    }
}

