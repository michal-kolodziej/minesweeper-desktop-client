package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.LocationEffect;

public class EasingMoveLocationEffect implements LocationEffect {
    private final int targetHorizontalPosition;
    private int currentHorizontalPosition;
    private final int targetVerticalPosition;
    private int currentVerticalPosition;
    final float easing = 0.05f;

    public EasingMoveLocationEffect(int xFrom, int xTo, int yFrom, int yTo) {
        this.currentHorizontalPosition = xFrom;
        this.targetHorizontalPosition = xTo;
        this.currentVerticalPosition = yFrom;
        this.targetVerticalPosition = yTo;
    }

    @Override
    public void update() {
        float dx = targetHorizontalPosition - currentHorizontalPosition;
        currentHorizontalPosition += dx * easing;


        float dy = targetVerticalPosition - currentVerticalPosition;
        currentVerticalPosition += dy * easing;
    }

    @Override
    public boolean keepParticleAlive() {
        return true;
    }

    @Override
    public int getX() {
        return currentHorizontalPosition;
    }

    @Override
    public int getY() {
        return currentVerticalPosition;
    }
}
