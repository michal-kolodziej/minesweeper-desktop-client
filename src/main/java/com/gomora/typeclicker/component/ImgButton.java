package com.gomora.typeclicker.component;

import com.gomora.typeclicker.GamePApplet;
import processing.core.PImage;

public class ImgButton extends Button {

    private final PImage image;

    public ImgButton(int x, int y, GamePApplet drawingArea, int width, int height, Runnable leftClickButtonAction, Runnable rightClickButtonAction, PImage image) {
        super(x, y, drawingArea, width, height, leftClickButtonAction, rightClickButtonAction);
        this.image = image;
    }


    @Override
    public void draw() {
        drawingArea.image(image, x,y);
    }
}
