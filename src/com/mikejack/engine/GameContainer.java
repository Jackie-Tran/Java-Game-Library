package com.mikejack.engine;

//Game container class "contains the game"
//Game loop = a while loop that loops the game until the game is over
//Game engine = engine starts up game loop and loads all resources and controls user inputs
public class GameContainer implements Runnable{

	private Thread thread;
	private Window window;
	private AbstractGame game;
	
	
	
	private boolean running = false;
	private final double UPDATE_CAP = 1.0/60.0;
	//window variables
	private int width = 320, height = 240;
	private float scale = 3f;
	private String title = "MiJakEngine v1.0";
	
	
	public GameContainer(AbstractGame game) {
		this.game = game;
	}
	
	public void start() {
		window = new Window(this);
		
		thread = new Thread(this);
		thread.run();
	}
	
	public void run() {
		running = true;
		
		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while (running) {
			render = false;
			firstTime = System.nanoTime()/1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				
				game.update(this, (float)UPDATE_CAP);
				
				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}
			
			if(render) {
				game.render(this, renderer);
				window.update();
				frames++;
			}else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		dispose();
	}
	
	private void dispose() {
		
	}
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String ittle) {
		this.title = ittle;
	}

}
