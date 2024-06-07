package com.gomora.typeclicker.component.graphics.sprite;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteLoader {

    private final PApplet drawingArea;

    public SpriteLoader(PApplet drawingArea) {
        this.drawingArea = drawingArea;
    }

    public void loadImages(){
        MINESWEEPER_0 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_0.png");

        MINESWEEPER_1 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_1.png");

        MINESWEEPER_2 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_2.png");

        MINESWEEPER_3 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_3.png");

        MINESWEEPER_4 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_4.png");

        MINESWEEPER_5 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_5.png");

        MINESWEEPER_6 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_6.png");

        MINESWEEPER_7 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_7.png");

        MINESWEEPER_8 = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_8.png");

        MINESWEEPER_C = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_C.png");

        MINESWEEPER_F = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_F.png");

        MINESWEEPER_M = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_M.png");

        MINESWEEPER_X = drawingArea.loadImage("sprites/minesweeper_default/MINESWEEPER_X.png");

        final int resizeW = 48;
        final int resizeH = 48;
        MINESWEEPER_0.resize(resizeW,resizeH);
        MINESWEEPER_1.resize(resizeW,resizeH);
        MINESWEEPER_2.resize(resizeW,resizeH);
        MINESWEEPER_3.resize(resizeW,resizeH);
        MINESWEEPER_4.resize(resizeW,resizeH);
        MINESWEEPER_5.resize(resizeW,resizeH);
        MINESWEEPER_6.resize(resizeW,resizeH);
        MINESWEEPER_7.resize(resizeW,resizeH);
        MINESWEEPER_8.resize(resizeW,resizeH);
        MINESWEEPER_C.resize(resizeW,resizeH);
        MINESWEEPER_F.resize(resizeW,resizeH);
        MINESWEEPER_M.resize(resizeW,resizeH);
        MINESWEEPER_X.resize(resizeW,resizeH);
    }

    public PImage MINESWEEPER_0;
    public PImage MINESWEEPER_1;
    public PImage MINESWEEPER_2;
    public PImage MINESWEEPER_3;
    public PImage MINESWEEPER_4;
    public PImage MINESWEEPER_5;
    public PImage MINESWEEPER_6;
    public PImage MINESWEEPER_7;
    public PImage MINESWEEPER_8;
    public PImage MINESWEEPER_C;
    public PImage MINESWEEPER_F;
    public PImage MINESWEEPER_M;
    public PImage MINESWEEPER_X;
}
