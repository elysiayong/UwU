package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import gameobject.GameObject;
import gameobject.Handler;
import gameobject.KeyInput;
import gameobject.Player;
import gameobject.ShootyEnemy;
import gameobject.SpriteSheet;
import gameobject.Wavyenemy;

/**
 * 'UwU' is a really small bullet-hell game that utilizes multithreading to render 
 * object graphics in real-time.  
 * This project is mostly just to practice using design patterns and experiment with 
 * multithreading. Lag spikes will occur due to the lack of implementing Object Pool. 
 * @author elysiay 
 *
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -9138514732886014137L;

	public static final int WIDTH = 640, HEIGHT = 480;
	
	//Core variables to run the game
	private Thread thread; 
	private boolean running = false;
	public static boolean paused = false;
	
	//Game variables
	private int bound = 0;
	private int score = 0;
	private int level = 1;
	private int reward = 1000;
	private int stage = 1;
	public static float HEALTH = 100;
	
	//Core classes used to generate the game
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	private AudioPlayer audio;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End,
		Complete,
		Shop;
	};
	
	public static STATE gameState = STATE.Menu; 
	public static SpriteSheet SPRITESHEET;
	
	public Game() {
		//Core game variables
		BuffImageLoader loader = new BuffImageLoader();
		SPRITESHEET = new SpriteSheet(loader.loadImage("res/spritesheet/spritesheet.png"));

		r = new Random();
		
		//Initialize Menus
		handler = new Handler();
		hud = new HUD(this);
		shop = new Shop(this, handler);
		menu = new Menu(this, handler, shop);
		spawner = new Spawn(handler, this);		
		audio = new AudioPlayer();
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();
		AudioPlayer.getMusic(0).loop();
		
		new Window(WIDTH, HEIGHT, "UwU", this);
		
	
		//for debugging/testing purposes, change the gameState to Game
		//and spawn GameObjects upon instantiation
		if(gameState == STATE.Game) {
			//spawn characters:
			handler.addObject(new Player(WIDTH/2-32 - 50, HEIGHT/2-32 - 40, ID.Player, handler));
			
		}else if(gameState == STATE.Menu) {
			menuEffect();
		}
	}
	
	private void menuEffect(){
		for(int i = 0; i <= 8; i++){
			handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
		}
	}
	
	public int getScore() {return this.score;}
	public int getLevel() {return this.level;}
	public int getReward() {return this.reward;}
	public int getStage() {return this.stage;}
	public int getBound() {return this.bound;}
	
	public void setScore(int score) {this.score = score;}
	public void setLevel(int level) {this.level = level;}
	public void setReward(int reward) {this.reward = reward;}
	public void setStage(int stage) {this.stage = stage;}
	public void setBound(int bound) {this.bound = bound;}

	
	public void resetVariables() {
		level = 1;
		score = 0;
		reward = 0;
		stage = 0;
		HEALTH = 100;
		bound = 0;
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
	    long lastTime = System.nanoTime();
	    double amountOfTicks = 60.0;
	    double ns = 1000000000 / amountOfTicks;
	    double delta = 0;
	    long timer = System.currentTimeMillis();
	    while(running)
	    {
	        long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	        lastTime = now;
	        while(delta >=1)
	                {
	                    tick();
	                    delta--;
	                }
	                if(running)
	                    render();
	                
	                if(System.currentTimeMillis() - timer > 1000)
	                {
	                    timer += 1000;
	                }
	    		}
	            stop();
		    }
	
	//Game frame-by-frame processing method 
	private void tick() {
		if(gameState == STATE.Game) {
			if(!paused) {
				spawner.tick();
				handler.tick(); 
				score++;
				HEALTH = Game.clamp(HEALTH, 0, 100 + (bound/2));

				if(HEALTH <= 0) {
					gameState = STATE.End;
					handler.clearAll();
					menuEffect();
				}else if(stage == 29) {
					gameState = STATE.Complete;
					handler.clearAll();
					menuEffect();
				}	
				
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.Complete) {
			handler.tick();
			menu.tick();
		}
	}
	
	//Game frame-by-frame object rendering method
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			bs = this.getBufferStrategy();
		} 
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if(gameState == STATE.Game) {
			handler.render(g);
			
			if(paused) {
				g.setColor(Color.white);
				g.drawString("PAUSED", 300, 235);
			}
			
			for (int i = 0; i < handler.getObject().size(); i++) {
		        GameObject tempObject = handler.getObject().get(i);
		        
		        if (tempObject.getId() != ID.Trail){
		            tempObject.render(g);
		        }
		    }
			hud.render(g);
		}
		
		else if(gameState == STATE.Shop) {
			shop.render(g);
		}
		
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Complete) {
			handler.render(g);
			for (int i = 0; i < handler.getObject().size(); i++) {
		        GameObject tempObject = handler.getObject().get(i);

		        if (tempObject.getId() != ID.Trail){
		            tempObject.render(g);
		        }
		    }
			menu.render(g);
		}	
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float x, float min, float max) {
		if(x >= max) 
			return x = max; 
		else if(x <= min) 
			return x = min;
		else
			return x;
	}
	
	public static void main(String args[]) {
	    System.setProperty("sun.java2d.opengl", "true");
		new Game();
	}
	
}
