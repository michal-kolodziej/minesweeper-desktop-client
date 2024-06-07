package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LinearFallingLocationEffect implements LocationEffect {
    private final int initialVerticalPosition;
    private final int fallDownFactor;
    private int verticalOffset;

    public static LocationEffect create(int initialPosition, int fallDownFactor){
        return new LinearFallingLocationEffect(initialPosition, fallDownFactor);
    }

    @Override
    public void update() {
        verticalOffset += fallDownFactor;
    }

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public int getValue() {
        return initialVerticalPosition + verticalOffset;
    }
}
