package com.gomora.typeclicker.component;

import com.gomora.typeclicker.GamePApplet;
import processing.event.MouseEvent;

public abstract class DrawableComponent extends PositionedComponent {
    protected final GamePApplet drawingArea;

    public DrawableComponent(int x, int y, GamePApplet drawingArea) {
        super(x,y);
        this.drawingArea = drawingArea;
    }

    public abstract void draw();

    // to be overriden. does nothing by default
    public void mouseReleased(MouseEvent mouseEvent){}
}
