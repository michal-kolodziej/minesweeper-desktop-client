package com.gomora.typeclicker.component;

import com.gomora.typeclicker.GamePApplet;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class TextButton extends Button {

    private final String text;

    public TextButton(int x, int y, GamePApplet drawingArea, int width, int height, Runnable leftClickButtonAction, Runnable rightClickButtonAction, String text) {
        super(x, y, drawingArea, width, height, leftClickButtonAction, rightClickButtonAction);
        this.text = text;
    }

    public void draw() {
        drawingArea.pushStyle();
        setButtonBgColor(drawingArea);
        drawingArea.rect(x, y, width, height);

        drawingArea.textSize(32);
        drawingArea.fill(0);
        drawingArea.textAlign(CENTER, CENTER);
        //width >> 1 is division by 2, but SAFER(probably)
        drawingArea.text(text, x + (width >> 1), y + (height >> 1));
        drawingArea.popStyle();
    }

    private void setButtonBgColor(PApplet drawingArea) {
        if (!isInsideButton(drawingArea.mouseX, drawingArea.mouseY)) {
            drawingArea.fill(200);
        } else {
            drawingArea.fill(255);
        }
    }
}