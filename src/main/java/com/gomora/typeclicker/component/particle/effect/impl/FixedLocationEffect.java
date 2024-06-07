package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedLocationEffect implements LocationEffect {
    private final int x;
    private final int y;

    @Override
    public void update() {}

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
