package com.ldm.marioconga.juego;

import com.ldm.marioconga.Pantalla;
import com.ldm.marioconga.androidimpl.AndroidJuego;

public class MarioConga extends AndroidJuego {
    @Override
    public Pantalla getStartScreen() {
        return new LoadingScreen(this);
    }
}
