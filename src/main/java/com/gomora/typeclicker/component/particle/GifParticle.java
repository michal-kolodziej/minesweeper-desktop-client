package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.GamePApplet;
import com.gomora.typeclicker.component.DrawableComponent;
import com.gomora.typeclicker.component.graphics.gif.Gif;
import processing.core.PApplet;

public class GifParticle extends DrawableComponent implements Particle {
    private final String filename;
    int tint = 255;
    private Gif gif = null;

    public GifParticle(int x, int y, GamePApplet drawingArea, String filename) {
        super(x, y, drawingArea);
        this.filename = filename;
    }

    @Override
    public void draw(PApplet drawingArea) {
        drawInternal();
    }

    private void drawInternal(){
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


    @Override
    public void draw() {
        drawInternal();
    }
}
