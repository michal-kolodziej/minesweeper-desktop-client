package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.ColorEffect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Random;

@RequiredArgsConstructor
@Getter
public class RainbowColorEffect implements ColorEffect {
    private float currentHue = new Random().nextFloat();
    private Color color = Color.getHSBColor(currentHue, 1, 1);

    @Override
    public void update() {
        updateCurrentHue();
        this.color = Color.getHSBColor(currentHue, 1, 1);
    }

    private void updateCurrentHue() {
        if (currentHue <= 1) {
            this.currentHue += 0.02;
        } else {
            this.currentHue = 0;
        }
    }

    @Override
    public int getR() {
        return color.getRed();
    }

    @Override
    public int getG() {
        return color.getGreen();
    }

    @Override
    public int getB() {
        return color.getBlue();
    }

    @Override
    public boolean keepParticleAlive() {
        return true;
    }
}
