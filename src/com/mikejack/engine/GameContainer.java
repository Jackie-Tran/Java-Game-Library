package com.mikejack.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;


//Game container class "contains the game"
//Game loop = a while loop that loops the game until the game is over
//Game engine = engine starts up game loop and loads all resources and controls user inputs
public class GameContainer extends Canvas implements Runnable {

    private Window window;
    private AbstractGame game;

    private Thread gameThread;
    private boolean running = false;
    // window variables
    private int width = 320, height = 240;
    private int scale = 3;
    private String title = "MiJakEngine v0.1";
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private Screen screen;

    public GameContainer(AbstractGame game) {
	this.game = game;
	window = new Window(title, width, height, scale, this);
	screen = new Screen(this);
	
    }

    public void init() {
    }

    public synchronized void start() {
	if (running)
	    return;
	running = true;
	gameThread = new Thread(this);
	gameThread.start();
	
	
    }

    public synchronized void stop() {
	running = false;
	if (gameThread != null) {
	    try {
		gameThread.join();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    public void run() {
	long lastTime = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;

	init();
	while (running) {
	    long now = System.nanoTime();
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    while (delta >= 1) {
		update();
		updates++;
		delta--;
	    }
	    render();
	    frames++;

	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		System.out.println("UPDATES: " + updates + "\t FPS: " + frames);
		frames = 0;
		updates = 0;
	    }
	}
    }

    public void update() {
    	
    }

    public void render() {
	BufferStrategy bs = getBufferStrategy();
	if (bs == null) {
	    createBufferStrategy(3);
	    return;
	}

	Graphics g = bs.getDrawGraphics();
	screen.clear();
	g.drawImage(image, 0, 0, width * scale, height * scale, null);
	g.dispose();
	bs.show();

    }
    
    public Window getWindow() {
        return window;
    }

    public int getScale() {
        return scale;
    }

    public BufferedImage getImage() {
	return image;
    }

    public Screen getScreen() {
	return screen;
    }

}
