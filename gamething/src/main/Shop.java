package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameobject.Handler;
import main.Game.STATE;

public class Shop extends MouseAdapter{
	
	Handler handler;
	Game game;
	
	private int B1 = 1000;
	private int B2 = 1000;
	private int B3 = 1000;
	
	public Shop(Game game, Handler handler) {
		this.handler = handler; 
		this.game = game;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2 - 65, 50);
		
		//item 1 HEALTH UPGRADE
		g.setFont(new Font("Comic Sans MS", 0, 22));
		g.drawString("Upgwade Hweath", 235, 133);
		g.drawString(Integer.toString(B1), 295, 170);
		g.drawRect(Game.WIDTH/2 - 105, 100, 215, 85);
		
		//item 2 SPEED UPGRADE
		g.drawString("Upgwade Spweed", 235, 248);
		g.drawString(Integer.toString(B2), 295, 285);
		g.drawRect(Game.WIDTH/2 - 105, 215, 215, 85);
		
		//item 3 RESTORE HEALTH
		g.drawString("Full Restwore", 255, 363);
		g.drawString(Integer.toString(B3), 295, 400);
		g.drawRect(Game.WIDTH/2 - 105, 330, 215, 85);
			
		g.drawString("SCORE: " + game.getScore(), Game.WIDTH/2 - 45, 85);
		g.setFont(new Font("Comic Sans MS", 0, 16));
		g.drawString("Press Space to Return", 460, 430);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Shop) {
		
			//item 1 +HEALTH
			if(mx >= Game.WIDTH/2 - 105 && mx <= Game.WIDTH/2 + 110) {
				if(my >= 100 && my <= 185 && game.getScore() >= B1) {
						game.setScore(game.getScore() - B1);
						B1 += 500;
						game.setBound(game.getBound() + 20); 
						Game.HEALTH = 100 + game.getBound()/2;
				}
			}
			//item 2 +SPEED
			if(mx >= Game.WIDTH/2 - 105 && mx <= Game.WIDTH/2 + 110) {
				if(my >= 215 && my <= 300 && game.getScore() >= B2) {
					game.setScore(game.getScore() - B2);
					B2 += 500;
					handler.plyrspd++;
				}
			}
			//item 3 FULL HEALTH
			if(mx >= Game.WIDTH/2 - 105 && mx <= Game.WIDTH/2 + 110) {
				if(my >= 330 && my <= 415 && game.getScore() >= B3) {
					game.setScore(game.getScore() - B3);
					Game.HEALTH = 100 + game.getBound()/2;
				}
			}
		}
	}
	
	public void resetShop() {
		B1 = 1000;
		B2 = 1000;
		B3 = 1000;
		
	}
}
