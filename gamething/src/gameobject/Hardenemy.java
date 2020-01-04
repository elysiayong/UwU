package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import main.ID;

public class Hardenemy extends GameObject{

	private Handler handler;
	private Random r = new Random();
	private BufferedImage enemy_image;
	
	public Hardenemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velX = 5;
		velY = 5; 
				
		enemy_image = Game.SPRITESHEET.grabImage(4, 1, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
		
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		if(y <= -5 || y >= Game.HEIGHT - 55) {
			if(y <= 0) velY = r.nextInt(7) + 1; 
			else velY = (r.nextInt(8) + 1) * -1;
		} 
		if(x <= 0 || x >= Game.WIDTH - 20) {
			if(x <= 0) velX = r.nextInt(7) + 1 ; 
			else velX = (r.nextInt(8) + 1 ) * -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.2f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemy_image, (int) x, (int) y, null);
	}

}
