package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.component.graphics.gif.Gif;
import processing.core.PApplet;

public class GifParticle implements Particle {
    private final String filename;
    int tint = 255;
    private Gif gif = null;

    public GifParticle(String filename) {
        this.filename = filename;
    }


    @Override
    public void draw(PApplet drawingArea) {
        if (gif == null) {
            gif = new Gif(drawingArea, filename);
            gif.loop();
        } else {
            drawingArea.pushStyle();
            drawingArea.tint(255, tint--);
            drawingArea.image(gif, 300, 300);
            drawingArea.popStyle();
        }

    }

    @Override
    public boolean isAlive() {
        return tint > 0;
    }


}
