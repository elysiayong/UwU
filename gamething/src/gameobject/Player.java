package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import main.ID;

public class Player extends GameObject{

	Handler handler; 
	private boolean isInv = false;
	private final int INVTIME = 5;
	private int timer = 5;
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
				
		player_image = Game.SPRITESHEET.grabImage(1, 1, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	public void tick() {
		x += velX; 
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 39); 
		y = Game.clamp(y, 0, Game.HEIGHT - 68);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.3f, handler));

		if(!isInv) {
			collision();
		}else {
			timer--;
			if(timer <= 0) {
				isInv = false;
				timer = INVTIME;
			}
		}
	}

	private void collision() {
		for(int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);
			
			float diffX = tempObject.getX() - x; 
			float diffY = tempObject.getY() - y; 
			float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
			
			if(distance <= 50) {
				if(tempObject.getId() == ID.basicenemy || tempObject.getId() == ID.shootyenemy || tempObject.getId() == ID.wavyenemy || tempObject.getId() == ID.fastenemy || tempObject.getId() == ID.smallenemy) {
					if(getBounds().intersects((tempObject.getBounds()))) {
						Game.HEALTH -= 2;	
						isInv = true;
					}
				}
				if(tempObject.getId() == ID.diagonalenemy) {
					if(getBounds().intersects((tempObject.getBounds()))) {
						Game.HEALTH -= 10;
						isInv = true;
					}
				}
				if(tempObject.getId() == ID.healthblob) {
					if(getBounds().intersects((tempObject.getBounds()))) {
						Game.HEALTH += 20;
						handler.removeObject(tempObject);
						isInv = true;
					}
				}
				
				if(tempObject.getId() == ID.Boss || tempObject.getId() == ID.verticalenemy || tempObject.getId() == ID.horizontalenemy) {
					if(getBounds().intersects((tempObject.getBounds()))) {
						Game.HEALTH -= 3;
						isInv = true;
					}
				}
			}
		}
		
		for(int i = 0; i < handler.particle.size(); i++) {
			GameObject tempParticle = handler.particle.get(i);
			
			float diffX = tempParticle.getX() - x; 
			float diffY = tempParticle.getY() - y; 
			float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
			
			if(distance <= 20) {
				if(tempParticle.getId() == ID.bullet) {
					if(getBounds().intersects((tempParticle.getBounds()))) {
						Game.HEALTH -= 1;
						tempParticle.derender();
						isInv = true;
					}
				}
			}
		}
	}


	public void render(Graphics g) {
		g.drawImage(player_image, (int) x, (int) y, null);
	}

}
