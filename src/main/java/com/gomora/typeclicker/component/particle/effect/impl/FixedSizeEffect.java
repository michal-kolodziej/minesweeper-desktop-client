package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.SizeEffect;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedSizeEffect implements SizeEffect {
    private final float size;

    @Override
    public void update() {}

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public float getSize() {
        return size;
    }
}
