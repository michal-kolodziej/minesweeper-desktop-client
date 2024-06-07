package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EasingFallingLocationEffect implements LocationEffect {
    private final int initialVerticalPosition;
    private final int targetVerticalPosition;
    private int verticalOffset = 1;

    public static LocationEffect create(int initialPosition, int targetPosition){
        return new EasingFallingLocationEffect(initialPosition, targetPosition);
    }

    @Override
    public void update() {
        float progress =  (verticalOffset * 1.0f) / (targetVerticalPosition - initialVerticalPosition);
        verticalOffset += progress * progress;
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
