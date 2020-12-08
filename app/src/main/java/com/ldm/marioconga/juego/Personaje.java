package com.ldm.marioconga.juego;

import java.util.ArrayList;
import java.util.List;

public class Personaje {
    public static final int ARRIBA = 0;
    public static final int IZQUIERDA= 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;

    public List<Seguidores> partes = new ArrayList<Seguidores>();
    public int direccion;

    public Personaje() {
        direccion = ARRIBA;
        partes.add(new Seguidores(5, 6));
        partes.add(new Seguidores(5, 7));
        partes.add(new Seguidores(5, 8));
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

    public void abordaje() {
        Seguidores end = partes.get(partes.size()-1);
        partes.add(new Seguidores(end.x, end.y));
    }

    public void avance() {
        Seguidores barco = partes.get(0);

        int len = partes.size() - 1;
        for(int i = len; i > 0; i--) {
            Seguidores antes = partes.get(i-1);
            Seguidores parte = partes.get(i);
            parte.x = antes.x;
            parte.y = antes.y;
        }

        if(direccion == ARRIBA)
            barco.y -= 1;
        if(direccion == IZQUIERDA)
            barco.x -= 1;
        if(direccion == ABAJO)
            barco.y += 1;
        if(direccion == DERECHA)
            barco.x += 1;

        if(barco.x < 0)
            barco.x = 9;
        if(barco.x > 9)
            barco.x = 0;
        if(barco.y < 0)
            barco.y = 12;
        if(barco.y > 12)
            barco.y = 0;
    }

    public boolean comprobarChoque() {
        int len = partes.size();
        Seguidores barco = partes.get(0);
        for(int i = 1; i < len; i++) {
            Seguidores parte = partes.get(i);
            if(parte.x == barco.x && parte.y == barco.y)
                return true;
        }
        return false;
    }
}
