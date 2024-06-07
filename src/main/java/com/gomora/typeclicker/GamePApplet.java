package com.gomora.typeclicker;

import com.gomora.typeclicker.component.game.Game;
import com.gomora.typeclicker.component.graphics.sprite.SpriteLoader;
import com.gomora.typeclicker.component.particle.GifParticle;
import com.gomora.typeclicker.component.particle.ParticleManagerComponent;
import com.gomora.typeclicker.connection.Client;
import com.gomora.typeclicker.connection.IncomingMessageParser;
import com.gomora.typeclicker.connection.OutgoingMessageFactory;
import com.gomora.typeclicker.connection.PlayerCursorPositionManager;
import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class GamePApplet extends PApplet {

    private ParticleManagerComponent particleManagerComponent;
    private Client client;
    private Game game;
    private Supplier<Object> sendMouseMessageMemoized;
    private final PlayerCursorPositionManager playerCursorPositionManager = new PlayerCursorPositionManager();
    private SpriteLoader spriteLoader;
    private boolean gameOver = false;

    GamePApplet() {

    }

    private void onServerMessageReceived(String message) {
        System.out.println("server: " + message);
        handleMessage(message);
    }

    private void handleMessage(String message) {
        if (message.startsWith("GAMEUPDATE")) {
            String[][] board = IncomingMessageParser.boardUpdate(message);
            if (!gameOver) {
                game.setBoard(board);
            }
        } else if (message.startsWith("MOUSEPOS")) {
            var coordPair = IncomingMessageParser.mousePos(message);
            playerCursorPositionManager.updateCoordsForClient(coordPair.getRight(), new ImmutablePair<>(coordPair.getLeft(), coordPair.getMiddle()));
        } else if (message.startsWith("GAMEOVER")) {
            List<Pair<Integer, Integer>> mines = IncomingMessageParser.gameOver(message);
            game.setMines(mines);
            particleManagerComponent.submitParticle(new GifParticle("sprites/przegranko.gif"));
            this.gameOver = true;
        }
    }

    @Override
    public void settings() {
        size(1205, 645);
    }

    @Override
    public void setup() {
        frameRate(60);
        spriteLoader = new SpriteLoader(this);
        particleManagerComponent = new ParticleManagerComponent(0, 0, this);
        client = new Client("localhost", 2137, this::onServerMessageReceived);
        game = new Game(0, 0, this, particleManagerComponent, client);
        sendMouseMessageMemoized = Suppliers.memoizeWithExpiration(() -> {
            client.sendMessageToServer(OutgoingMessageFactory.mousePos(mouseX, mouseY));
            return null;
        }, 10, TimeUnit.MILLISECONDS);

        spriteLoader.loadImages();
        try {
            client.startConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void draw() {
        background(20);
        game.draw();
        playerCursorPositionManager.clientIdToMousePositionCoordMap.forEach((key1, coords) -> rect(coords.getLeft(), coords.getRight(), 10, 10));
        particleManagerComponent.draw();
    }


    @Override
    public void mouseMoved(MouseEvent event) {
        sendMouseMessageMemoized.get();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        gameOver = false;
        game.mouseReleased(event);
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    public SpriteLoader getSpriteLoader() {
        return spriteLoader;
    }
}
