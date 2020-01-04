package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import main.ID;

public class Fastenemy extends GameObject{

	private Handler handler;
	private BufferedImage enemy_image;
	private int timer = 800;
	
	public Fastenemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velX = 6;
		velY = 9; 
				
		enemy_image = Game.SPRITESHEET.grabImage(3, 1, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 12, 12);
		
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		if(timer >= 0) {
			if(y <= 0 || y >= Game.HEIGHT - 55) velY *= -1;
			if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
		}
		
		if(timer < 0) {
			if(y < 0 || y > Game.HEIGHT || x < 0 || x > Game.WIDTH) handler.removeObject(this);
		}
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.25f, handler));
	}


	public void render(Graphics g) {
		g.drawImage(enemy_image, (int) x, (int) y, null);
		
	}

}
