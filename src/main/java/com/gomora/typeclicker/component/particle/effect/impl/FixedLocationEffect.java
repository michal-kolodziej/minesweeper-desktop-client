package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedLocationEffect implements LocationEffect {
    private final int value;

    @Override
    public void update() {}

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public int getValue() {
        return value;
    }
}
