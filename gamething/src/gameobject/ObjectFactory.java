package gameobject;

import main.ID;

public class ObjectFactory {
	public GameObject create(int xPos, int yPos, int velX, int velY, ID id, int timer, Handler handler) {
		if(id.equals(ID.basicenemy)) handler.addObject(new Basicenemy(xPos, yPos, id, timer, handler));
		
		if(id.equals(ID.fastenemy)) handler.addObject(new Fastenemy(xPos, yPos, id, handler));
		
		if(id.equals(ID.smallenemy)) handler.addObject(new Smallenemy(xPos, yPos, id, handler));

		if(id.equals(ID.verticalenemy)) handler.addObject(new Verticalenemy(xPos, yPos, velY, id, handler));
		
		if(id.equals(ID.horizontalenemy)) handler.addObject(new Horizontalenemy(xPos, yPos, velX, id, handler));
		
		if(id.equals(ID.diagonalenemy)) handler.addObject(new Diagonalenemy(xPos, yPos, velX, velY, id, handler));
		
		if(id.equals(ID.wavyenemy)) handler.addObject(new Wavyenemy(xPos, yPos, id, handler));
		
		if(id.equals(ID.shootyenemy)) handler.addObject(new ShootyEnemy(xPos, yPos, id, handler));
		
		if(id.equals(ID.healthblob)) handler.addObject(new Healthblob(xPos, yPos, id));
		
		return null;		
	}
}
