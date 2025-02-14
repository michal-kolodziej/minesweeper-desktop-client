package com.gomora.typeclicker;

import com.gomora.typeclicker.component.game.Game;
import com.gomora.typeclicker.component.graphics.sprite.SpriteLoader;
import com.gomora.typeclicker.component.particle.*;
import com.gomora.typeclicker.component.particle.effect.impl.AlphaFadingEffect;
import com.gomora.typeclicker.component.particle.effect.impl.FixedSizeEffect;
import com.gomora.typeclicker.component.particle.effect.impl.LinearMoveLocationEffect;
import com.gomora.typeclicker.component.particle.effect.impl.RainbowColorEffect;
import com.gomora.typeclicker.connection.*;
import com.google.common.base.Suppliers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class GamePApplet extends PApplet {

    private final PlayerCursorPositionManager playerCursorPositionManager = new PlayerCursorPositionManager();
    private ParticleManagerComponent particleManagerComponent;
    private final Client client;
    private Game game;
    private Supplier<Object> sendMouseMessageMemoized;
    private SpriteLoader spriteLoader;
    private boolean gameOver = false;

    GamePApplet(Config config) {
        client = new Client(config.getServerIp(), config.getServerPort(), this::onServerMessageReceived);
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
            particleManagerComponent.submitParticle(new GifParticle(300, 300, this, "sprites/przegranko.gif"));
            this.gameOver = true;
        } else if (message.startsWith("GAMEWON")) {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                Particle gameWonParticle = TextParticle.builder()
                        .locationEffect(new LinearMoveLocationEffect(
                                mouseX + (random.nextBoolean() ? random.nextInt(50) : -random.nextInt(50)),
                                random.nextBoolean() ? random.nextFloat() * 2 : -random.nextFloat() * 2,
                                mouseY + (random.nextBoolean() ? random.nextInt(50) : -random.nextInt(50)),
                                random.nextBoolean() ? random.nextFloat() * 2 : -random.nextFloat() * 2))
                        .sizeEffect(new FixedSizeEffect(72))
                        .appearanceEffects(Collections.singletonList(FillParticleDecorator.builder()
                                .colorEffect(new RainbowColorEffect())
                                .alphaEffect(new AlphaFadingEffect(0.5f))
                                .build()))
                        .text("ZWYCIĘSTWO!!!!!111111")
                        .build();
                particleManagerComponent.submitParticle(gameWonParticle);
            }

            this.gameOver = true;
        }
    }

    @Override
    public void settings() {
        size(1440 + 10, 768 + 10);
    }


    @Override
    public void setup() {
        frame.setTitle("Minesweeper");
        frameRate(60);
        spriteLoader = new SpriteLoader(this);
        particleManagerComponent = new ParticleManagerComponent(0, 0, this);
        game = new Game(0, 0, this, particleManagerComponent, client);
        sendMouseMessageMemoized = Suppliers.memoizeWithExpiration(() -> {
            client.sendMessageToServer(OutgoingMessageFactory.mousePos(mouseX, mouseY));
            return null;
        }, 20, TimeUnit.MILLISECONDS);

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
        playerCursorPositionManager.clientIdToMousePositionCoordMap.forEach(
                (key1, coords) -> rect(coords.getX(), coords.getY(), 10, 10));
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
