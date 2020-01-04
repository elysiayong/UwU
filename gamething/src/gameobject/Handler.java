package gameobject;

import java.awt.Graphics;
import java.util.ArrayList;

import main.ID;

public class Handler{
	
	private ObjectFactory fac;
	private ArrayList<GameObject> object = new ArrayList<GameObject>();
	ArrayList<GameObject> particle = new ArrayList<GameObject>();
	
	public int plyrspd = 5;
	
	public void tick() {
		for(int i = 0; i < getObject().size(); i++) {
			GameObject tempObject = getObject().get(i);
			if(tempObject.isActive()) {
				tempObject.tick();
			}
		}
		for(int i = 0; i < particle.size(); i++) {
			GameObject tempObject = particle.get(i);
			if(tempObject.isActive()) {
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < getObject().size(); i++) {
			GameObject tempObject = getObject().get(i);
			if(tempObject.isActive()) {
				tempObject.render(g);
			}
		}
		for(int i = 0; i < particle.size(); i++) {
			GameObject tempObject = particle.get(i);
			if(tempObject.isActive()) {
				tempObject.render(g);
			}
		}
	}
	
	public void addParticle(GameObject particle) {
		this.particle.add(particle);
	}
	
	public void removeParticle(GameObject particle) {
		this.particle.remove(particle);
	}
	
	public void addObject(GameObject object) {
		this.getObject().add(object);
	}
	
	public void removeObject(GameObject object) {
		this.getObject().remove(object);
	}
	
	public void clearEnemies() {
		for(int i = 0; i < getObject().size(); i++) {
			GameObject tempObject = getObject().get(i);
			
			if(tempObject.getId() != ID.Player && tempObject.getId() != ID.healthblob) {
				removeObject(tempObject);
				i--;
			}
		}
		
	}
	public void clearAll() {
		for(int i = 0; i < getObject().size(); i++) {			
				removeObject(getObject().get(i));
				i--;
		}
		for(int i = 0; i < particle.size(); i++) {
			removeParticle(particle.get(i));
			i--;
		}
	}

	public ArrayList<GameObject> getObject() {
		return object;
	}

	public void setObject(ArrayList<GameObject> object) {
		this.object = object;
	}

	
	
}
