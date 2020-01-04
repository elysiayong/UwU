package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.Game;
import main.ID;

public class ShootyEnemy extends GameObject{
	
	private Handler handler;
	private int timer;
	
	Random r = new Random();
	
	public ShootyEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i <= 10; i++) {
			handler.addParticle(new Bullet(-200, -200, 8, 8, false, ID.bullet, handler));
		}
		
		timer = 6;
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
		
	}

	public void tick() {
		timer--;
		
		for(GameObject a: handler.particle) {
			if(a.id == ID.bullet) {
				Bullet p = (Bullet) a;
				if(!p.isActive() && timer < 0) {
					p.setX((int) x + 15);
					p.setY((int) y + 15);
					p.setActive(true);
					p.changeAngle();
	
					timer = 8;
					break;
				}
			}
		}
		x += velX; 
		y += velY;	
		
		if(x >= Game.WIDTH - 32 || x <= 0) velX *= -1;
		if(y >= Game.HEIGHT - 64 || y <= 0) velY *= -1;
	}
		
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	

}
