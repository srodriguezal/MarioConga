package com.ldm.marioconga.juego;

import com.ldm.marioconga.Juego;
import com.ldm.marioconga.Graficos;
import com.ldm.marioconga.Pantalla;

public class LoadingScreen extends Pantalla {
    public LoadingScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        Assets.fondo = g.newPixmap("cesped.png", Graficos.PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.menuprincipal = g.newPixmap("menuprincipal.png", Graficos.PixmapFormat.ARGB4444);
        Assets.pantalla_personajes = g.newPixmap("pantalla_personajes.png", Graficos.PixmapFormat.ARGB4444);
        Assets.botones = g.newPixmap("botones.png", Graficos.PixmapFormat.ARGB4444);
        Assets.ayuda1 = g.newPixmap("ayuda1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.ayuda2 = g.newPixmap("ayuda2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.ayuda3 = g.newPixmap("ayuda3.png", Graficos.PixmapFormat.ARGB4444);
        Assets.numeros = g.newPixmap("numeros.png", Graficos.PixmapFormat.ARGB4444);
        Assets.preparado = g.newPixmap("preparado.png", Graficos.PixmapFormat.ARGB4444);
        Assets.menupausa = g.newPixmap("menupausa.png", Graficos.PixmapFormat.ARGB4444);
        Assets.finjuego = g.newPixmap("finjuego.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_arriba = g.newPixmap("mario_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_izquierda = g.newPixmap("mario_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_abajo = g.newPixmap("mario_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_derecha = g.newPixmap("mario_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_bebe = g.newPixmap("bebe_mario.png", Graficos.PixmapFormat.ARGB4444);
        /*Assets.mario_arriba = g.newPixmap("luigi_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_izquierda = g.newPixmap("luigi_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_abajo = g.newPixmap("luigi_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_derecha = g.newPixmap("luigi_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.luigi_bebe = g.newPixmap("bebe_luigi.png", Graficos.PixmapFormat.ARGB4444);*/
        /*Assets.mario_arriba = g.newPixmap("peach_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_izquierda = g.newPixmap("peach_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_abajo = g.newPixmap("peach_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_derecha = g.newPixmap("peach_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_bebe = g.newPixmap("bebe_peach.png", Graficos.PixmapFormat.ARGB4444);*/

        Assets.estela_arriba = g.newPixmap("estela_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_izquierda = g.newPixmap("estela_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_abajo = g.newPixmap("estela_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_derecha = g.newPixmap("estela_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.destello = g.newPixmap("destello.png", Graficos.PixmapFormat.ARGB4444);

        /*Assets.mario_arriba = g.newPixmap("mario_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_izquierda = g.newPixmap("mario_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_abajo = g.newPixmap("mario_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_derecha = g.newPixmap("mario_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_bebe = g.newPixmap("bebe_mario.png", Graficos.PixmapFormat.ARGB4444);

        Assets.mario_arriba = g.newPixmap("mario_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_izquierda = g.newPixmap("mario_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_abajo = g.newPixmap("mario_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_derecha = g.newPixmap("mario_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.mario_bebe = g.newPixmap("bebe_mario.png", Graficos.PixmapFormat.ARGB4444);*/

        Assets.premio1 = g.newPixmap("seta1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio2 = g.newPixmap("seta2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio3 = g.newPixmap("seta3.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boo=g.newPixmap("boo.png",Graficos.PixmapFormat.ARGB4444);
        Assets.goomba=g.newPixmap("goomba.png",Graficos.PixmapFormat.ARGB4444);
        Assets.bootler=g.newPixmap("bootler.png",Graficos.PixmapFormat.ARGB4444);

        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.start = juego.getAudio().nuevoSonido("start.ogg");
        Assets.pausa = juego.getAudio().nuevoSonido("pausa.ogg");
        Assets.sonido_seta = juego.getAudio().nuevoSonido("sonido_seta.ogg");
        Assets.derrota = juego.getAudio().nuevoSonido("derrota.ogg");


        Configuraciones.cargar(juego.getFileIO());
        juego.setScreen(new MainMenuScreen(juego));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}