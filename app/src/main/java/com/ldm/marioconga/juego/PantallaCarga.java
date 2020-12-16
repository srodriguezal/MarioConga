package com.ldm.marioconga.juego;

import com.ldm.marioconga.Graficos;
import com.ldm.marioconga.Input;
import com.ldm.marioconga.Juego;
import com.ldm.marioconga.Pantalla;

import java.util.List;

public class PantallaCarga extends Pantalla {
    int tipoPersonaje;

    public PantallaCarga(Juego juego, int tipoPersonaje){
        super(juego);
        this.tipoPersonaje = tipoPersonaje;

    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x > 256 && event.y > 416 ) {
                    juego.setScreen(new PantallaJuego(juego, tipoPersonaje));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.start.play(1);
                    return;
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 416) {
                    if (Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    juego.setScreen(new PantallaPersonaje(juego));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.fondo, 0, 0);

        if(tipoPersonaje== 1){ // Mario
            g.drawPixmap(Assets.pantalla_mario, 0, 0);

        } else if(tipoPersonaje== 2){ // Luigi
            g.drawPixmap(Assets.pantalla_luigi, 0, 0);

        } else if(tipoPersonaje== 3){ // Peach
            g.drawPixmap(Assets.pantalla_peach, 0, 0);

        } else if(tipoPersonaje== 4){ // Estela
            g.drawPixmap(Assets.pantalla_estela, 0, 0);

        } else if(tipoPersonaje== 5){ // Bowser
            g.drawPixmap(Assets.pantalla_bowser, 0, 0);

        } else if(tipoPersonaje== 6){ // Yoshi
            g.drawPixmap(Assets.pantalla_yoshi, 0, 0);

        }
        g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);

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
