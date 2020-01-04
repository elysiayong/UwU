package gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.ID;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id; 
	protected float velX, velY;
	protected boolean active;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y; 
		this.id = id;
		this.active = true;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	//getter and setter methods
	public void setX(int x) {
		this.x = x;	
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void derender() {
		x = -200;
		y = -200;
	}
	
}
