package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.ID;

public class Healthblob extends GameObject{	
	public Healthblob(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 24, 24);
	}

	public void tick() {}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 24, 24);
	}

}
