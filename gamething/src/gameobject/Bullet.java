package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.Game;
import main.ID;

public class Bullet extends GameObject{

	private Handler handler;
	private GameObject player;
	Random r = new Random(); 
	int sizex, sizey;
	boolean random;
	
	
	public Bullet(int x, int y, int sizex, int sizey, boolean random, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.setActive(false);
		this.sizex = sizex; 
		this.sizey = sizey;
		
		if(random) {
			velX = (r.nextInt(5 - -5) + -5);
			velY = 5; 
		}else {
			velX = 0; 
			velY = 0;
		}
	}
	
	public void changeAngle() {
		for(int i = 0; i < handler.getObject().size(); i++) {
			if(handler.getObject().get(i).getId() == ID.Player) player = handler.getObject().get(i); 
		
		float vel = 8.0f;
		float angle = (float) Math.atan2( (player.getX() - x), (player.getY() - y) );
		
		velX = (float) (vel * -Math.cos(angle + Math.PI/2));
		velY = (float) (vel * Math.sin(angle + Math.PI/2));
		
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.sizex, this.sizey);	
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		
		if(y >= Game.HEIGHT || y < 0 || x < 0 || x >= Game.WIDTH) {
			this.setActive(false);
			this.derender();
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, 12, 12);
	}

}
