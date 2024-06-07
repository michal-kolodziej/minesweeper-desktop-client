package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.component.particle.effect.SizeEffect;
import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.Builder;
import lombok.Singular;
import processing.core.PApplet;

import java.util.List;

@Builder
public class TextParticle implements Particle {
    private final LocationEffect locationEffect;
    private final SizeEffect sizeEffect;
    @Singular
    private final List<Particle> appearanceEffects;

    private final String text;

    @Override
    public void draw(PApplet drawingArea) {
        update();
        appearanceEffects.forEach(e -> e.draw(drawingArea));
        drawingArea.textSize(sizeEffect.getSize());
        drawingArea.text(text, locationEffect.getX(), locationEffect.getY());
    }

    private void update() {
        locationEffect.update();
        sizeEffect.update();
    }

    @Override
    public boolean isAlive() {
        return locationEffect.keepParticleAlive() &&
                sizeEffect.keepParticleAlive() &&
                appearanceEffects.stream().allMatch(Particle::isAlive);
    }
}
