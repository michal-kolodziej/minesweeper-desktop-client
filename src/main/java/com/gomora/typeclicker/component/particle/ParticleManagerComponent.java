package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.GamePApplet;
import com.gomora.typeclicker.component.DrawableComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
public class ParticleManagerComponent extends DrawableComponent implements ParticleManager {
    private final List<Particle> particlesBeingDrawn;

    public ParticleManagerComponent(int x, int y, GamePApplet drawingArea) {
        super(x, y, drawingArea);
        this.particlesBeingDrawn = new ArrayList<>();
    }

    public void submitParticle(Particle particle) {
        particlesBeingDrawn.add(particle);
        log.trace("particle submitted, total particles: " + particlesBeingDrawn.size());
    }

    @Override
    public void draw() {
        ListIterator<Particle> particleListIterator = particlesBeingDrawn.listIterator();
        while (particleListIterator.hasNext()) {
            Particle particle = particleListIterator.next();
            if (particle.isAlive()) {
                particle.draw(drawingArea);
            } else {
                log.trace("removing inactive particle");
                particleListIterator.remove();
            }
        }
    }
}
