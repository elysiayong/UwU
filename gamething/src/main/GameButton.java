package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameButton extends MouseAdapter{
	private int x, y, width, height;
	private String text;
	private Font font; private Color color;
	
	public GameButton(String text, int xPos, int yPos, int width, int height, Font font, Color color) {
		this.x = xPos; 
		this.y = yPos;
		this.width = width;
		this.height = height;
		this.text = text;
		this.font = font;
		this.color = color;
	}
	
	public void createButton(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawRect(x, y, width, height);
		g.drawString(text, (x + (width/2 - g.getFontMetrics().stringWidth(text)/2)), 
				(y + (height - g.getFontMetrics().getHeight()/2)));	
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isHover(MouseEvent e) {
		if((x < e.getX()) && (e.getX() < x + width)){
			if((y < e.getY()) && (e.getY() < y + height)){
				AudioPlayer.getSound(0).play(); 
				return true;
			}
		}
		return false;
	}
	
}
