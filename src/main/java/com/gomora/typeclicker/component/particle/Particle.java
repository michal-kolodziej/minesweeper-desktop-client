package com.gomora.typeclicker.component.particle;

import processing.core.PApplet;

public interface Particle {
    void draw(PApplet drawingArea);
    boolean isAlive();
}
