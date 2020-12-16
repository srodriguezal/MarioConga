package com.ldm.marioconga.juego;

import java.util.List;

import android.graphics.Color;

import com.ldm.marioconga.Juego;
import com.ldm.marioconga.Graficos;
import com.ldm.marioconga.Pixmap;
import com.ldm.marioconga.Pantalla;
import com.ldm.marioconga.Input;

public class PantallaJuego extends Pantalla {
    enum EstadoJuego {
        Preparado,
        Ejecutandose,
        Pausado,
        FinJuego
    }

    EstadoJuego estado = EstadoJuego.Preparado;
    Mundo mundo;
    int antiguaPuntuacion = 0;
    String puntuacion = "0";

    public PantallaJuego(Juego juego, int tipoPersonaje) {
        super(juego);
        mundo = new Mundo(tipoPersonaje);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        if(estado == EstadoJuego.Preparado)
            updateReady(touchEvents);
        if(estado == EstadoJuego.Ejecutandose)
            updateRunning(touchEvents, deltaTime);
        if(estado == EstadoJuego.Pausado)
            updatePaused(touchEvents);
        if(estado == EstadoJuego.FinJuego)
            updateGameOver(touchEvents);

    }

    private void updateReady(List<Input.TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            estado = EstadoJuego.Ejecutandose;
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if((event.x > 185 && event.x <240) && event.y > 416) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pausa.play(1);
                    estado = EstadoJuego.Pausado;
                    return;
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                    mundo.personaje.girarIzquierda();
                }
                if(event.x > 256 && event.y > 416) {
                    mundo.personaje.girarDerecha();
                }
            }
        }

        mundo.update(deltaTime);
        if(mundo.finalJuego) {
            if(Configuraciones.sonidoHabilitado)
                Assets.derrota.play(1);
            estado = EstadoJuego.FinJuego;
        }
        if(antiguaPuntuacion != mundo.puntuacion) {
            antiguaPuntuacion = mundo.puntuacion;
            puntuacion = "" + antiguaPuntuacion;
            if(Configuraciones.sonidoHabilitado)
                Assets.sonido_seta.play(1);
        }
    }

    private void updatePaused(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Configuraciones.sonidoHabilitado)
                            Assets.pulsar.play(1);
                        estado = EstadoJuego.Ejecutandose;
                        return;
                    }
                    if(event.y > 148 && event.y < 196) {
                        if(Configuraciones.sonidoHabilitado)
                            Assets.pulsar.play(1);
                        juego.setScreen(new MainMenuScreen(juego));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    juego.setScreen(new MainMenuScreen(juego));
                    return;
                }
            }
        }
    }


    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.fondo, 0, 0);
        drawWorld(mundo);
        if(estado == EstadoJuego.Preparado)
            drawReadyUI();

        if(estado == EstadoJuego.Ejecutandose){
            if(Configuraciones.sonidoHabilitado){
                Assets.partida.play();
                Assets.partida.setLooping(true);

            }

            drawRunningUI();
        }

        if(estado == EstadoJuego.Pausado){
            if(Configuraciones.sonidoHabilitado)
                Assets.partida.pause();
            drawPausedUI();
        }

        if(estado == EstadoJuego.FinJuego) {
            if(Configuraciones.sonidoHabilitado)
                Assets.partida.stop();
            drawGameOverUI();
        }


        drawText(g, puntuacion, g.getWidth() / 2 - puntuacion.length()*20 / 2, g.getHeight() - 42);
    }

    private void drawWorld(Mundo mundo) {
        Graficos g = juego.getGraphics();
        Personaje personaje = mundo.personaje;
        Seguidores head = personaje.partes.get(0);
        Premio premio = mundo.premio;
        Enemigo enemigo=mundo.enemigo;







        Pixmap headPixmap = null;
       if(personaje.tipo== Personaje.TIPO_1) { // Mario
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.premio1;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.premio2;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.premio3;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);

           int len = personaje.partes.size();
           for(int i = 1; i < len; i++) {
               Seguidores part = personaje.partes.get(i);
               x = part.x * 32;
               y = part.y * 32;
               g.drawPixmap(Assets.mario_bebe, x, y);
           }
           Pixmap enmigoPixmap=null;
           if(enemigo.tipo==Enemigo.TIPO_1)
               enmigoPixmap=Assets.boo_rey2;
           if (enemigo.tipo==Enemigo.TIPO_2)
               enmigoPixmap=Assets.bootler;
           if(enemigo.tipo==Enemigo.TIPO_3)
               enmigoPixmap=Assets.goomba;
           int xE= enemigo.x *32;
           int yE=enemigo.y*32;
           g.drawPixmap(enmigoPixmap,xE,yE);
           if (personaje.direccion == Personaje.ARRIBA)
               headPixmap = Assets.mario_arriba;
           if (personaje.direccion == Personaje.IZQUIERDA)
               headPixmap = Assets.mario_izquierda;
           if (personaje.direccion == Personaje.ABAJO)
               headPixmap = Assets.mario_abajo;
           if (personaje.direccion == Personaje.DERECHA)
               headPixmap = Assets.mario_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);

       }else if(personaje.tipo== Personaje.TIPO_2) { // Luigi
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.premio1;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.premio2;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.premio3;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);
            int len = personaje.partes.size();
            for(int i = 1; i < len; i++) {
                Seguidores part = personaje.partes.get(i);
                x = part.x * 32;
                y = part.y * 32;
                g.drawPixmap(Assets.luigi_bebe, x, y);
            }
            Pixmap enmigoPixmap=null;
            if(enemigo.tipo==Enemigo.TIPO_1)
                enmigoPixmap=Assets.goomba;
            if (enemigo.tipo==Enemigo.TIPO_2)
                enmigoPixmap=Assets.bootler;
            if(enemigo.tipo==Enemigo.TIPO_3)
                enmigoPixmap=Assets.boo_rey1;
            int xE= enemigo.x *32;
            int yE=enemigo.y*32;
            g.drawPixmap(enmigoPixmap,xE,yE);
            if (personaje.direccion == Personaje.ARRIBA)
                headPixmap = Assets.luigi_arriba;
            if (personaje.direccion == Personaje.IZQUIERDA)
                headPixmap = Assets.luigi_izquierda;
            if (personaje.direccion == Personaje.ABAJO)
                headPixmap = Assets.luigi_abajo;
            if (personaje.direccion == Personaje.DERECHA)
                headPixmap = Assets.luigi_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);

        }else if(personaje.tipo== Personaje.TIPO_3) { // Peach
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.melocoton;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.melocoton_azul;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.melocoton_dorado;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);
           int len = personaje.partes.size();
           for(int i = 1; i < len; i++) {
               Seguidores part = personaje.partes.get(i);
               x = part.x * 32;
               y = part.y * 32;
               g.drawPixmap(Assets.peach_bebe, x, y);
           }
           Pixmap enmigoPixmap=null;
           if(enemigo.tipo==Enemigo.TIPO_1)
               enmigoPixmap=Assets.lady_bow;
           if (enemigo.tipo==Enemigo.TIPO_2)
               enmigoPixmap=Assets.rey_goomba;
           if(enemigo.tipo==Enemigo.TIPO_3)
               enmigoPixmap=Assets.dark_boo_rey;
           int xE= enemigo.x *32;
           int yE=enemigo.y*32;
           g.drawPixmap(enmigoPixmap,xE,yE);
           if (personaje.direccion == Personaje.ARRIBA)
               headPixmap = Assets.peach_arriba;
           if (personaje.direccion == Personaje.IZQUIERDA)
               headPixmap = Assets.peach_izquierda;
           if (personaje.direccion == Personaje.ABAJO)
               headPixmap = Assets.peach_abajo;
           if (personaje.direccion == Personaje.DERECHA)
               headPixmap = Assets.peach_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);

       } else if(personaje.tipo== Personaje.TIPO_4) { // Estela
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.corazon;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.corazon_azul;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.corazon_dorado;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);
           int len = personaje.partes.size();
           for(int i = 1; i < len; i++) {
               Seguidores part = personaje.partes.get(i);
               x = part.x * 32;
               y = part.y * 32;
               g.drawPixmap(Assets.destello, x, y);
           }
           Pixmap enmigoPixmap=null;
           if(enemigo.tipo==Enemigo.TIPO_1)
               enmigoPixmap=Assets.rey_goomba;
           if (enemigo.tipo==Enemigo.TIPO_2)
               enmigoPixmap=Assets.lady_bow;
           if(enemigo.tipo==Enemigo.TIPO_3)
               enmigoPixmap=Assets.dark_boo_rey;
           int xE= enemigo.x *32;
           int yE=enemigo.y*32;
           g.drawPixmap(enmigoPixmap,xE,yE);
           if (personaje.direccion == Personaje.ARRIBA)
               headPixmap = Assets.estela_arriba;
           if (personaje.direccion == Personaje.IZQUIERDA)
               headPixmap = Assets.estela_izquierda;
           if (personaje.direccion == Personaje.ABAJO)
               headPixmap = Assets.estela_abajo;
           if (personaje.direccion == Personaje.DERECHA)
               headPixmap = Assets.estela_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);

       } else if(personaje.tipo== Personaje.TIPO_5) { // Bowser
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.planta;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.caparazon;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.bala;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);
            int len = personaje.partes.size();
            for(int i = 1; i < len; i++) {
                Seguidores part = personaje.partes.get(i);
                x = part.x * 32;
                y = part.y * 32;
                g.drawPixmap(Assets.bowser_jr, x, y);
            }
            Pixmap enmigoPixmap=null;
            if(enemigo.tipo==Enemigo.TIPO_1)
                enmigoPixmap=Assets.mario_izquierda;
            if (enemigo.tipo==Enemigo.TIPO_2)
                enmigoPixmap=Assets.luigi_derecha;
            if(enemigo.tipo==Enemigo.TIPO_3)
                enmigoPixmap=Assets.yoshi_izquierda;
            int xE= enemigo.x *32;
            int yE=enemigo.y*32;
            g.drawPixmap(enmigoPixmap,xE,yE);
            if (personaje.direccion == Personaje.ARRIBA)
                headPixmap = Assets.bowser_arriba;
            if (personaje.direccion == Personaje.IZQUIERDA)
                headPixmap = Assets.bowser_izquierda;
            if (personaje.direccion == Personaje.ABAJO)
                headPixmap = Assets.bowser_abajo;
            if (personaje.direccion == Personaje.DERECHA)
                headPixmap = Assets.bowser_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);

        }else if(personaje.tipo== Personaje.TIPO_6) { // Yoshi
           Pixmap stainPixmap = null;
           if(premio.tipo== Premio.TIPO_1)
               stainPixmap = Assets.huevo_rosa;
           if(premio.tipo == Premio.TIPO_2)
               stainPixmap = Assets.huevo_azul;
           if(premio.tipo == Premio.TIPO_3)
               stainPixmap = Assets.huevo_amarillo;
           int x = premio.x * 32;
           int y = premio.y * 32;
           g.drawPixmap(stainPixmap, x, y);
           int len = personaje.partes.size();
           for(int i = 1; i < len; i++) {
               Seguidores part = personaje.partes.get(i);
               x = part.x * 32;
               y = part.y * 32;
               g.drawPixmap(Assets.huevos, x, y);
           }
           Pixmap enmigoPixmap=null;
           if(enemigo.tipo==Enemigo.TIPO_1)
               enmigoPixmap=Assets.goomba;
           if (enemigo.tipo==Enemigo.TIPO_2)
               enmigoPixmap=Assets.boo;
           if(enemigo.tipo==Enemigo.TIPO_3)
               enmigoPixmap=Assets.dark_boo;
           int xE= enemigo.x *32;
           int yE=enemigo.y*32;
           g.drawPixmap(enmigoPixmap,xE,yE);
           if (personaje.direccion == Personaje.ARRIBA)
               headPixmap = Assets.yoshi_arriba;
           if (personaje.direccion == Personaje.IZQUIERDA)
               headPixmap = Assets.yoshi_izquierda;
           if (personaje.direccion == Personaje.ABAJO)
               headPixmap = Assets.yoshi_abajo;
           if (personaje.direccion == Personaje.DERECHA)
               headPixmap = Assets.yoshi_derecha;

           x = head.x * 32 + 16;
           y = head.y * 32 + 16;
           g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
       }

    }

    private void drawReadyUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.preparado, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.botones, 190, 416, 64, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
        g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);
    }

    private void drawPausedUI() {
        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.menupausa, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.finjuego, 62, 100);
        g.drawPixmap(Assets.botones, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    public void drawText(Graficos g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if(estado == EstadoJuego.Ejecutandose)
            estado = EstadoJuego.Pausado;

        if(mundo.finalJuego) {
            Configuraciones.addScore(mundo.puntuacion);
            Configuraciones.save(juego.getFileIO());
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}