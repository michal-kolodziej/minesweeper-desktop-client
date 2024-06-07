package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinearMoveLocationEffect implements LocationEffect {
    private final int initialHorizontalPosition;
    private final float horizontalFalldownFactor;
    private final int initialVerticalPosition;
    private final float verticalFalldownFactor;
    private float verticalOffset;
    private float horizontalOffset;

    @Override
    public void update() {
        horizontalOffset += horizontalFalldownFactor;
        verticalOffset += verticalFalldownFactor;
    }

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public int getX() {
        return (int)(initialHorizontalPosition + horizontalOffset);
    }

    @Override
    public int getY() {
        return (int)(initialVerticalPosition + verticalOffset);
    }
}
