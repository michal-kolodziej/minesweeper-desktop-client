package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.component.particle.effect.SizeEffect;
import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.Builder;
import lombok.Singular;
import processing.core.PApplet;

import java.util.List;

@Builder
public class TextParticle implements Particle {
    private final LocationEffect xCoordLocationEffect;
    private final LocationEffect yCoordLocationEffect;
    private final SizeEffect sizeEffect;
    @Singular
    private final List<Particle> appearanceEffects;

    private final String text;

    @Override
    public void draw(PApplet drawingArea) {
        update();
        appearanceEffects.forEach(e -> e.draw(drawingArea));
        drawingArea.textSize(sizeEffect.getSize());
        drawingArea.text(text, xCoordLocationEffect.getValue(), yCoordLocationEffect.getValue());
    }

    private void update() {
        yCoordLocationEffect.update();
        xCoordLocationEffect.update();
        sizeEffect.update();
    }

    @Override
    public boolean isAlive() {
        return xCoordLocationEffect.keepParticleAlive() &&
                yCoordLocationEffect.keepParticleAlive() &&
                sizeEffect.keepParticleAlive() &&
                appearanceEffects.stream().allMatch(Particle::isAlive);
    }
}
