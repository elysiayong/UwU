package main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	private Game game;
	public HUD(Game game) {
		this.game = game;
	}
		
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + game.getBound(), 32);
		g.setColor(Color.getHSBColor((1f * Game.HEALTH)/360, 1f, 1f));
		g.fillRect(15, 15, (int) (Game.HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + game.getBound(), 32);
		
		g.drawString("Score: " + game.getScore(), 15, 64);
		g.drawString("Level: " + game.getLevel(), 15, 80);
		
	}
}
