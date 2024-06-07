package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.AlphaEffect;

public class AlphaFadingEffect implements AlphaEffect {
    private final int fadingFactor;
    private int alpha;

    public AlphaFadingEffect(int startingAlpha, int fadingFactor) {
        this.alpha = startingAlpha;
        this.fadingFactor = fadingFactor;
    }

    public AlphaFadingEffect(int fadingFactor) {
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
        return alpha;
    }
}
