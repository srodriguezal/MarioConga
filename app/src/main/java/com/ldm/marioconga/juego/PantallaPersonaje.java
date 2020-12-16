package com.ldm.marioconga.juego;

import com.ldm.marioconga.Graficos;
import com.ldm.marioconga.Input;
import com.ldm.marioconga.Juego;
import com.ldm.marioconga.Pantalla;

import java.util.List;

public class PantallaPersonaje extends Pantalla {
    public PantallaPersonaje(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 416) {
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    juego.setScreen(new MainMenuScreen(juego));
                    return;
                }
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Configuraciones.sonidoHabilitado = !Configuraciones.sonidoHabilitado;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                }
                // Seleccionar el personaje

                if(inBounds(event, 30, 115, 70, 80) ){ // Mario
                    if(Configuraciones.sonidoHabilitado)
                        Assets.start.play(1);
                    juego.setScreen(new PantallaJuego(juego, 1));

                } else if(inBounds(event, 205, 115, 70, 80) ){ // Luigi
                    if(Configuraciones.sonidoHabilitado)
                        Assets.start.play(1);
                    juego.setScreen(new PantallaJuego(juego, 2));

                } else if(inBounds(event, 30, 220, 70, 70) ){ // Peach
                        if(Configuraciones.sonidoHabilitado)
                            Assets.start.play(1);
                        juego.setScreen(new PantallaJuego(juego, 3));

                } else if(inBounds(event, 205, 220, 70, 70) ){ // Estela
                        if(Configuraciones.sonidoHabilitado)
                            Assets.start.play(1);
                        juego.setScreen(new PantallaJuego(juego, 4));

                } else if(inBounds(event, 30, 325, 70, 60) ){ // Bowser
                    if(Configuraciones.sonidoHabilitado)
                        Assets.start.play(1);
                    juego.setScreen(new PantallaJuego(juego, 5));

                } else if(inBounds(event, 205, 320, 70, 55) ){ // Yoshi
                if(Configuraciones.sonidoHabilitado)
                    Assets.start.play(1);
                juego.setScreen(new PantallaJuego(juego, 6));

            }
                    return;


            }

        }

    }
    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.fondo, 0, 0);
        g.drawPixmap(Assets.pantalla_personajes, 0, 0);
        g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);

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
