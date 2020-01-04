package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import main.ID;

public class Wavyenemy extends GameObject{

	private float alpha = 2f;
	
	private Handler handler;
	private BufferedImage enemy_image; 
	
	public Wavyenemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velX = 3;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16); 	
		
	}

	public void tick() {
		alpha += 0.1f; 
		velY = (float) (Math.sin(alpha) * 20); 
		
		if(y + velY <= 0 || y + velY >= Game.HEIGHT - 55) velY *= -1;
		if(x + velX <= 0 || x + velX>= Game.WIDTH - 20) velX *= -1;
		
		
		x += velX; 
		y += velY;
		
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.08f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(enemy_image, (int) x, (int) y, null);
	}

}
