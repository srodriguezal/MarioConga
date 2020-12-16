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
        Assets.luigi_arriba = g.newPixmap("luigi_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.luigi_izquierda = g.newPixmap("luigi_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.luigi_abajo = g.newPixmap("luigi_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.luigi_derecha = g.newPixmap("luigi_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.luigi_bebe = g.newPixmap("bebe_luigi.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_arriba = g.newPixmap("peach_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_izquierda = g.newPixmap("peach_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_abajo = g.newPixmap("peach_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_derecha = g.newPixmap("peach_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.peach_bebe = g.newPixmap("bebe_peach.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_arriba = g.newPixmap("estela_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_izquierda = g.newPixmap("estela_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_abajo = g.newPixmap("estela_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.estela_derecha = g.newPixmap("estela_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.destello = g.newPixmap("destello.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bowser_arriba = g.newPixmap("bowser_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bowser_izquierda = g.newPixmap("bowser_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bowser_abajo = g.newPixmap("bowser_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bowser_derecha = g.newPixmap("bowser_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bowser_jr = g.newPixmap("bowser_jr.png", Graficos.PixmapFormat.ARGB4444);
        Assets.yoshi_arriba = g.newPixmap("yoshi_arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.yoshi_izquierda = g.newPixmap("yoshi_izquierda.png", Graficos.PixmapFormat.ARGB4444);
        Assets.yoshi_abajo = g.newPixmap("yoshi_abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.yoshi_derecha = g.newPixmap("yoshi_derecha.png", Graficos.PixmapFormat.ARGB4444);
        Assets.huevos = g.newPixmap("huevo.png", Graficos.PixmapFormat.ARGB4444);

        Assets.premio1 = g.newPixmap("seta1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio2 = g.newPixmap("seta2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio3 = g.newPixmap("seta3.png", Graficos.PixmapFormat.ARGB4444);
        Assets.boo=g.newPixmap("boo.png",Graficos.PixmapFormat.ARGB4444);
        Assets.goomba=g.newPixmap("goomba.png",Graficos.PixmapFormat.ARGB4444);
        Assets.bootler=g.newPixmap("bootler.png",Graficos.PixmapFormat.ARGB4444);
        Assets.melocoton=g.newPixmap("melocoton.png",Graficos.PixmapFormat.ARGB4444);
        Assets.melocoton_azul=g.newPixmap("melocoton_azul.png",Graficos.PixmapFormat.ARGB4444);
        Assets.melocoton_dorado=g.newPixmap("melocoton_dorado.png",Graficos.PixmapFormat.ARGB4444);
        Assets.caparazon=g.newPixmap("caparazon.png",Graficos.PixmapFormat.ARGB4444);
        Assets.planta=g.newPixmap("planta.png",Graficos.PixmapFormat.ARGB4444);
        Assets.corazon=g.newPixmap("corazon.png",Graficos.PixmapFormat.ARGB4444);
        Assets.corazon_azul=g.newPixmap("corazon_azul.png",Graficos.PixmapFormat.ARGB4444);
        Assets.corazon_dorado=g.newPixmap("corazon_dorado.png",Graficos.PixmapFormat.ARGB4444);
        Assets.huevo_amarillo=g.newPixmap("huevo_amarillo.png",Graficos.PixmapFormat.ARGB4444);
        Assets.huevo_azul=g.newPixmap("huevo_azul.png",Graficos.PixmapFormat.ARGB4444);
        Assets.huevo_rosa=g.newPixmap("huevo_rosa.png",Graficos.PixmapFormat.ARGB4444);
        Assets.rey_goomba=g.newPixmap("rey_goomba.png",Graficos.PixmapFormat.ARGB4444);
        Assets.lady_bow=g.newPixmap("lady_bow.png",Graficos.PixmapFormat.ARGB4444);
        Assets.bala=g.newPixmap("bala.png",Graficos.PixmapFormat.ARGB4444);
        Assets.dark_boo_rey=g.newPixmap("dark_boo_rey.png",Graficos.PixmapFormat.ARGB4444);
        Assets.dark_boo=g.newPixmap("dark_boo.png",Graficos.PixmapFormat.ARGB4444);
        Assets.boo_rey1=g.newPixmap("boo_rey1.png",Graficos.PixmapFormat.ARGB4444);
        Assets.boo_rey2=g.newPixmap("boo_rey2.png",Graficos.PixmapFormat.ARGB4444);









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