package com.gomora.typeclicker.component;

import com.gomora.typeclicker.GamePApplet;
import processing.event.MouseEvent;

import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;

public abstract class Button extends DrawableComponent {
    protected final int width;
    protected final int height;
    protected final Runnable leftClickButtonAction;
    protected final Runnable rightClickButtonAction;

    Button(int x, int y, GamePApplet drawingArea, int width, int height, Runnable leftClickButtonAction, Runnable rightClickButtonAction) {
        super(x, y, drawingArea);
        this.width = width;
        this.height = height;
        this.leftClickButtonAction = leftClickButtonAction;
        this.rightClickButtonAction = rightClickButtonAction;
    }

    public abstract void draw();

    public void maybeClick(MouseEvent e) {
        if (isInsideButton(e.getX(), e.getY())) {
            if (e.getButton() == LEFT) {
                leftClickButtonAction.run();
            } else if (e.getButton() == RIGHT) {
                rightClickButtonAction.run();
            }
        }
    }

    protected boolean isInsideButton(int mouseX, int mouseY) {
        return mouseY > this.y && mouseY < this.y + height
                && mouseX > this.x && mouseX < this.x + width;
    }
}
