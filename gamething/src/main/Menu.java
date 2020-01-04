package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import gameobject.Handler;
import gameobject.Player;
import gameobject.Basicenemy;
import main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Handler handler;
	private Game game;
	private Shop shop;
	private Random r = new Random();
	private ArrayList<GameButton> buttons;
	private Font fnt1, fnt2, fnt3, fnt0;
	
	public Menu(Game game, Handler handler, Shop shop) {
		this.game = game;
		this.handler = handler;
		this.shop = shop;
		
		fnt0 = new Font("Comic Sans MS", Font.PLAIN, 50);
		fnt1 = new Font("Comic Sans MS", Font.PLAIN, 30);
		fnt2 = new Font("Comic Sans MS", Font.PLAIN, 25);
		fnt3 = new Font("Comic Sans MS", Font.PLAIN, 20);
		
		buttons = new ArrayList<GameButton>() {
			{
				add(new GameButton("start", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 64, fnt1, Color.white));
				add(new GameButton("help", Game.WIDTH/2 - 100, Game.HEIGHT/2, 200, 64, fnt1, Color.white));
				add(new GameButton("quit", Game.WIDTH/2 - 100, Game.HEIGHT/2 + 100, 200, 64, fnt1, Color.white));
				add(new GameButton("back", Game.WIDTH - 100, Game.HEIGHT - 80, 75, 35, fnt3, Color.white));
			}
		};
	}
	
	public void mousePressed(MouseEvent e) {
		if(Game.gameState == STATE.Menu) {
			//start button
			if(buttons.get(0).isHover(e)) {
				Game.gameState = STATE.Game;
				handler.clearAll();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new Basicenemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), ID.basicenemy, 1200, handler));			}
			
			//help button
			if(buttons.get(1).isHover(e)) {
				Game.gameState = STATE.Help;
			}	
			
			//quit button
			if(buttons.get(2).isHover(e)) {
				System.exit(0);
			}
		}

		else if(Game.gameState == STATE.Help || Game.gameState == STATE.End || Game.gameState == STATE.Complete) {
			//back button 
			if(buttons.get(3).isHover(e)) {
				game.resetVariables();
				shop.resetShop();
				handler.plyrspd = 5;
				Game.gameState = STATE.Menu;
			}
		}
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		
			if(Game.gameState == STATE.Menu) {
				//Title
				g.setColor(Color.RED);
				g.setFont(fnt0);
				g.drawString("UwU", Game.WIDTH/2 - g.getFontMetrics().stringWidth("UwU")/2, 75);
				
				//Buttons
				this.buttons.get(0).createButton(g);
				this.buttons.get(1).createButton(g);
				this.buttons.get(2).createButton(g);
			}
			else if(Game.gameState == STATE.Help) {
				g.setColor(Color.white);
				
				g.setFont(fnt0);
				g.drawString("help", Game.WIDTH/2 - 55, 75);
				g.drawString("GOOD LUCK!", 145, 400);
				
				this.buttons.get(3).createButton(g);
				
				g.setFont(fnt2);
				g.drawString("there are 25 levels; manage your health to survive", 30, 120);
				g.drawString("WASD to move around and dodge enemies", 30, 170);
				g.drawString("levels increment every 250 points", 30, 220);
				g.drawString("press Space to open shop", 30, 270);
				g.drawString("health upgrades restore to full health; use wisely", 30, 320);
				
			}else if(Game.gameState == STATE.End) {
				g.setColor(Color.white);
				
				g.setFont(fnt0);
				g.drawString("onoes you dieded xd", Game.WIDTH/2 - 240, 75);
				
				g.setFont(fnt1);
				g.drawString("you have: " + game.getScore() + " points!!!", 50, 240);
				g.drawString("you made it to level: " + game.getLevel(), 50, 275);
	
				g.setFont(fnt3);
				g.drawString("try again uwu", Game.WIDTH/2 - 95, 320);
				
				this.buttons.get(3).createButton(g);
				
			}else if(Game.gameState == STATE.Complete) {
				g.setColor(Color.YELLOW);
				
				g.setFont(fnt1);
				g.drawString("Congrats! you survived somehow", 95, 75);
				
				g.setColor(Color.white);
				g.setFont(fnt1);
				g.drawString("you have: " + game.getScore() + " points!!!", 195, 200);
				g.drawString("you made it to level: " + game.getLevel(), 173, 250);
	
				g.setFont(fnt3);
				g.drawString("play again uwu", 250, 335);
				
				this.buttons.get(3).createButton(g);
			}
	}
}
