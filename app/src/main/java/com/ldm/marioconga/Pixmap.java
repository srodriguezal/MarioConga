package com.ldm.marioconga;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graficos.PixmapFormat getFormat();

    public void dispose();
}

