package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.AlphaEffect;

public class AlphaFadingEffect implements AlphaEffect {
    private final float fadingFactor;
    private float alpha;

    public AlphaFadingEffect(int startingAlpha, float fadingFactor) {
        this.alpha = startingAlpha;
        this.fadingFactor = fadingFactor;
    }

    public AlphaFadingEffect(float fadingFactor) {
        this(255, fadingFactor);
    }


    public void update() {
        alpha -= fadingFactor;
    }

    public boolean keepParticleAlive() {
        return alpha > 0;
    }

    @Override
    public int getAlpha() {
        return (int) alpha;
    }
}
