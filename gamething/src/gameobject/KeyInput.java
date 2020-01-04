package gameobject;

//rewrite this class using Key Binding

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game;
import main.ID;
import main.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	Game game;

	private boolean[] pKey = new boolean[4];

	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		pKey[0] = false;
		pKey[1] = false;
		pKey[2] = false;
		pKey[3] = false;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyChar();
		
		for(int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);
				
			if(tempObject.getId() == ID.Player) {
				
				if(key == 'w') { pKey[0] = true; tempObject.setVelY(-handler.plyrspd); }
				if(key == 's') { pKey[1] = true; tempObject.setVelY(handler.plyrspd); }
				if(key == 'd') { pKey[2] = true; tempObject.setVelX(handler.plyrspd); }
				if(key == 'a') { pKey[3] = true; tempObject.setVelX(-handler.plyrspd); }
				
				if(key == 'W') { pKey[0] = true; tempObject.setVelY(-(handler.plyrspd - (handler.plyrspd/2 + 1))); }
				if(key == 'S') { pKey[1] = true; tempObject.setVelY(handler.plyrspd - (handler.plyrspd/2 + 1)); }
				if(key == 'D') { pKey[2] = true; tempObject.setVelX(handler.plyrspd - (handler.plyrspd/2 + 1)); }
				if(key == 'A') { pKey[3] = true; tempObject.setVelX(-(handler.plyrspd - (handler.plyrspd/2 + 1))); }
				
				}
			}
		
			if(key == 'p' || key == 'P') {
				if(Game.gameState == STATE.Game) { 
					Game.paused = !Game.paused; 
					}
			}
			
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			
			if(key == KeyEvent.VK_SPACE) {
				if(Game.gameState == STATE.Game) {
					Game.gameState = STATE.Shop;
				}else if(Game.gameState == STATE.Shop) {
					Game.gameState = STATE.Game;
				}
			}
		}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyChar();
		
		for(int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == 'w') {
					pKey[0] = false;
					if(pKey[1]) {
						tempObject.setVelY(handler.plyrspd);
					}else tempObject.setVelY(0);
				}

				if(key == 's') {
					pKey[1] = false; 		
					if(pKey[0]) {
						tempObject.setVelY(-handler.plyrspd);
					}
				}
				
				if(key == 'd') {
					pKey[2] = false;
					if(pKey[3]) {
						tempObject.setVelX(-handler.plyrspd);
					}
				}
				if(key == 'a') {
					pKey[3] = false;
					if(pKey[2]) {
						tempObject.setVelX(handler.plyrspd);
					}
				}
				
				if(key == 'W') {
					pKey[0] = false;
					if(pKey[1]) {
						tempObject.setVelY((handler.plyrspd - (handler.plyrspd/2 + 1)));
					}
				}
				
				if(key == 'S') {
					pKey[1] = false; 		
					if(pKey[0]) {
						tempObject.setVelY(-(handler.plyrspd - (handler.plyrspd/2 + 1)));
					}
				}
				
				if(key == 'D') {
					pKey[2] = false;
					if(pKey[3]) {
						tempObject.setVelX(-(handler.plyrspd - (handler.plyrspd/2 + 1)));
					}
				}
				if(key == 'A') {
					pKey[3] = false;
					if(pKey[2]) {
						tempObject.setVelX(handler.plyrspd - (handler.plyrspd/2 + 1));
					}
				}
				
				if(!pKey[0] && !pKey[1]) tempObject.setVelY(0);
				if(!pKey[2] && !pKey[3]) tempObject.setVelX(0);
			}
		}
	}
}
