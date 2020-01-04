package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import main.Game;
import main.ID;

public class Boss extends GameObject{

	private Handler handler;
	private Game game;
	
	Random r = new Random();
	
	private int timer = 1000;
	private boolean isfight = false;
	
	public Boss(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		velX = 0;
		velY = 2; 
		
		for(int i = 0; i <= 20; i++) {
			handler.addParticle(new Bullet(-200, -200, 12, 12, true, ID.bullet, handler));
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
		
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		//boss enters
		if(y + 96 >= 120 && isfight == false && timer > 0) {
			velY = 0;
			isfight = true;
		}
		
		//bossfight begins
		timer--;
		if(isfight == true) {
			if(timer > 0 && timer <= 920) {
				if(velX == 0) velX = 2;	
				if(velX < 0) velX -= 0.01f;
					else velX += 0.01f;
				int spawnind = r.nextInt(handler.particle.size());
				
				
				GameObject tempParticle = handler.particle.get(spawnind);
				
				if(!tempParticle.isActive()) {
					tempParticle.setActive(true);
					tempParticle.setX((int) x + 45);
					tempParticle.setY((int) y + 90);
				}
				
			}else if(timer < 0) {
					game.setScore(game.getScore() + game.getReward());
					game.setReward(1000);
					isfight = false;
					timer = 0;
			}	
		//bossfight ends
		}else if(isfight == false) {
			if(game.getStage() == 0) {
				game.setStage(game.getStage() + 1);
			}
			if(velX != 0 && timer <= -93) velX = 0;
			
			if(timer <= -130) velY = -2;
			
			if(y <= -96) {
				this.setActive(false);
				handler.clearEnemies();
			}
		}
		
		if(x <= 0 || x >= Game.WIDTH - 102) velX *= -1;

	}
		


	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	

}
