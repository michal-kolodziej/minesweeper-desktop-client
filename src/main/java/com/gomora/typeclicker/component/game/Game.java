package com.gomora.typeclicker.component.game;

import com.gomora.typeclicker.GamePApplet;
import com.gomora.typeclicker.component.DrawableComponent;
import com.gomora.typeclicker.component.TextButton;
import com.gomora.typeclicker.component.graphics.sprite.SpriteLoader;
import com.gomora.typeclicker.component.particle.ParticleManager;
import com.gomora.typeclicker.connection.Client;
import com.gomora.typeclicker.connection.OutgoingMessageFactory;
import org.apache.commons.lang3.tuple.Pair;
import processing.core.PImage;
import processing.event.MouseEvent;

import java.util.List;

public class Game extends DrawableComponent {

    private final Client client;
    private Cell[][] board;

    private boolean isInitialized = false;

    public Game(int x, int y, GamePApplet drawingArea, ParticleManager particleManager, Client client) {
        super(x, y, drawingArea);
        this.client = client;
    }

    @Override
    public void draw() {
        if (!isInitialized) return;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].getButton().draw();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (!isInitialized) return;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].getButton().maybeClick(mouseEvent);
            }
        }
    }

    public void setBoard(String[][] board) {
        this.board = convertToCells(board);
        this.isInitialized = true;
    }

    private Cell[][] convertToCells(String[][] board) {
        Cell[][] cells = new Cell[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int roww = row;
                int coll = col;
                String cellText = board[row][col];
                cells[row][col] = new Cell(cellText,
//                        new ImgButton(x + (col * 40) + 5, y + (row * 40) + 5, drawingArea, 32, 32,
//                                () -> client.sendMessageToServer(OutgoingMessageFactory.click(roww, coll)),
//                                () -> client.sendMessageToServer(OutgoingMessageFactory.flag(roww, coll)),
//                                pickImgBasedOnText(drawingArea.getSpriteLoader(), cellText))
                        new TextButton(x + (col * 40) + 5, y + (row * 40) + 5, drawingArea, 32, 32,
                                () -> client.sendMessageToServer(OutgoingMessageFactory.click(roww, coll)),
                                () -> client.sendMessageToServer(OutgoingMessageFactory.flag(roww, coll)), convertCellText(cellText))
                );
            }
        }
        return cells;
    }

    private PImage pickImgBasedOnText(SpriteLoader spriteLoader, String text) {
        switch (text) {
            case "F":
                return spriteLoader.MINESWEEPER_F;
            case "M":
                return spriteLoader.MINESWEEPER_M;
            case "E":
                return spriteLoader.MINESWEEPER_0;
            case "1":
                return spriteLoader.MINESWEEPER_1;
            case "2":
                return spriteLoader.MINESWEEPER_2;
            case "3":
                return spriteLoader.MINESWEEPER_3;
            case "4":
                return spriteLoader.MINESWEEPER_4;
            case "5":
                return spriteLoader.MINESWEEPER_5;
            case "6":
                return spriteLoader.MINESWEEPER_6;
            case "7":
                return spriteLoader.MINESWEEPER_7;
            case "8":
                return spriteLoader.MINESWEEPER_8;
            default:
                throw new RuntimeException("Cannot load sprite: " + text + ", it's not defined in spriteLoader");
        }
    }

    public void setMines(List<Pair<Integer, Integer>> mines) {
        mines.forEach(
                mine -> {
                    final int row = mine.getLeft();
                    final int col = mine.getRight();
                    board[mine.getLeft()][mine.getRight()] = new Cell("M",
//                            new ImgButton(x + (col * 40) + 5, y + (row * 40) + 5, drawingArea, 32, 32, () -> {
//                            }, () -> {
//                            }, pickImgBasedOnText(drawingArea.getSpriteLoader(), "M"))
                            new TextButton(x + (col * 40) + 5, y + (row * 40) + 5, drawingArea, 32, 32, () -> {
                            }, () -> {
                            }, "M")
                    );
                });


    }

    // here we define conversion between what we get as field text from server and what's actually displayed in the cell,
    // so for example 'E' means empty cell which should not be presented.
    private String convertCellText(String s) {
        if ("E".equals(s)) {
            return "";
        } else {
            return s;
        }
    }


}
