package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.SizeEffect;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LinearGrowingSizeEffect implements SizeEffect {
    private float currentSize;
    private final float growFactor;

    @Override
    public void update() {
        currentSize += growFactor;
    }

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public float getSize() {
        return currentSize;
    }
}
