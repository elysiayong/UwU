package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import main.ID;

public class Horizontalenemy extends GameObject{

	private Handler handler;
	
	private BufferedImage enemy_image; 
	
	
	public Horizontalenemy(int x, int y, int velX, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.velX = velX;
				
		enemy_image = Game.SPRITESHEET.grabImage(2, 1, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
		
	}

	public void tick() {
		x += velX; 
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.15f, handler));
		
		if(velX > 0 && x >= Game.WIDTH) {
			handler.removeObject(this);
		}else if(velX < 0 && x <= 0) {
			handler.removeObject(this);
		}
	}


	public void render(Graphics g) {
		g.drawImage(enemy_image, (int) x, (int) y, null);
	}

}
