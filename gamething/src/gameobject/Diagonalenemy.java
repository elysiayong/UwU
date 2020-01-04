package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import main.ID;

public class Diagonalenemy extends GameObject{

	private Handler handler;
	
	private BufferedImage enemy_image; 
	
	
	public Diagonalenemy(int x, int y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.velX = velX;
		this.velY = velY; 
		
		enemy_image = Game.SPRITESHEET.grabImage(2, 1, 16, 16);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
		
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		if((velX > 0 && x > Game.WIDTH) && (velY > 0 && y >= Game.HEIGHT)) {
			handler.removeObject(this);
		}else if((velX < 0 && x < 0) && (velY < 0 && y <= 0)) {
			handler.removeObject(this);
		}else if((velX < 0 && x < 0) && (velY > 0 && y >= Game.HEIGHT)) {
			handler.removeObject(this);
		}else if((velX > 0 && x > Game.WIDTH) && (velY < 0 && y <= 0)) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}


	public void render(Graphics g) {
		g.drawImage(enemy_image, (int) x, (int) y, null);
	}

}
