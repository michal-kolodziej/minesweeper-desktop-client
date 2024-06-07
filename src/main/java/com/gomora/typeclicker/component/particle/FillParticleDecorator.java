package com.gomora.typeclicker.component.particle;

import com.gomora.typeclicker.component.particle.effect.AlphaEffect;
import com.gomora.typeclicker.component.particle.effect.ColorEffect;
import lombok.Builder;
import processing.core.PApplet;

@Builder
public class FillParticleDecorator implements Particle {
    private final ColorEffect colorEffect;
    private final AlphaEffect alphaEffect;

    @Override
    public void draw(PApplet drawingArea) {
        update();
        drawingArea.fill(colorEffect.getR(), colorEffect.getG(), colorEffect.getB(), alphaEffect.getAlpha());
    }

    private void update() {
        colorEffect.update();
        alphaEffect.update();
    }

    @Override
    public boolean isAlive() {
        return alphaEffect.keepParticleAlive() && colorEffect.keepParticleAlive();
    }
}
