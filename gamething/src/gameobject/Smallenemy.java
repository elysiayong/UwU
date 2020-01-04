package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.ID;

public class Smallenemy extends GameObject{

	private Handler handler;
	private GameObject player; 
	
	public Smallenemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.getObject().size(); i++) {
			if(handler.getObject().get(i).getId() == ID.Player) player = handler.getObject().get(i); 
		}
	
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
		
	}

	public void tick() {
		x += velX; 
		y += velY;
		
		float diffX = x - player.getX(); 
		float diffY = y - player.getY(); 
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
		
		velX = ((-1/ distance) * diffX);
		velY = ((-1/ distance) * diffY);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 8, 8, 0.2f, handler));
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 8, 8);
		
	}

}
