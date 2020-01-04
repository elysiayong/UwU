package main;

import java.util.Random;

import gameobject.Boss;
import gameobject.Handler;
import gameobject.ObjectFactory;

public class Spawn {
	private Handler handler; 
	private Game game;
	private ObjectFactory eFac;
	private Random r = new Random();
	
	private int Keepscore = 0;
	
	
	public Spawn(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		this.eFac = new ObjectFactory();
	}
	
	public void tick(){
		Keepscore ++;
		
		//setting level climb
		if(Keepscore >= 250) {
			Keepscore = 0;
			game.setLevel(game.getLevel() + 1);
			if(game.getStage() == 0) { 
				if(game.getLevel()%2 == 0 && game.getLevel()%5 != 0 && game.getLevel() <= 10) { 
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.basicenemy, 1200, handler);
				}
				if(game.getLevel()%3 == 0 && game.getLevel() < 10) {
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.fastenemy, 0, handler);	
				}
				if(game.getLevel()%4 == 0) {
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.healthblob, 0, handler);
				}
			
				if(game.getLevel() == 5 || game.getLevel() == 9) {
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.smallenemy, 0, handler);
				}
				if(game.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new Boss((Game.WIDTH/2) - 48, -96, ID.Boss, handler, game));
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.smallenemy, 0, handler);
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.smallenemy, 0, handler);
				}	
			}
			
			if(game.getStage() == 1) { //level 15 onwards
				if(game.getLevel() == 15) {
					eFac.create(25, 25, 0, 0, ID.basicenemy, 800, handler);
					eFac.create(Game.WIDTH - 25, 25, 0, 0, ID.basicenemy, 800, handler);

					for(int i = 0; i <= 8; i++) {
						eFac.create(-50, (i * 60), 7, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 64), -750, 0, 7, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 16) {

					for(int i = 0; i <= 8; i++) {
						eFac.create(-50, (i * 60), 7, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 64), -50, 0, 7, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 17) {
					for(int i = 0; i <= 8; i++) {
						eFac.create(Game.WIDTH+50, (i * 60), -8, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 64), Game.HEIGHT+50, 0, -8, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 18) {

					for(int i = 0; i <= 10; i++) {
						eFac.create(Game.WIDTH+50+(i*25), (i * 55), -8, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 11; i++) {
						eFac.create((i * 55), -50-(i*25), 0, 8, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create(-900, (i * 55 - 25), 8, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 11; i++) {
						eFac.create((i * 55 - 25), Game.HEIGHT+750+(i*25), 0, -10, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create(Game.WIDTH+1550+(i*25), (i * 55 + 25), -10, 0, ID.horizontalenemy, 0, handler);	
					}
					for(int i = 0; i <= 11; i++) {
						eFac.create((i * 55 + 25), Game.HEIGHT+1550+(i*25), 0, -8, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 19) {

					//claws is super high dmg type
					for(int i = 0; i < 5; i++) { 
						eFac.create((i * 30) - 150, -(i * 30) - 30, 15, 15, ID.diagonalenemy, 0, handler);	
						eFac.create(Game.WIDTH+(i * 30), (i * 30) - 150, -15, 15, ID.diagonalenemy, 0, handler);	
					}
					for(int i = 0; i <= 9; i++) {
						eFac.create(Game.WIDTH + 24 - (i * 35), Game.HEIGHT + 550, 0, -11, ID.verticalenemy, 0, handler);	
						eFac.create((i * 35) - 32, Game.HEIGHT + 550, 0, -11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create(Game.WIDTH - (i * 35), Game.HEIGHT + 1200, 0, -11, ID.verticalenemy, 0, handler);	
						eFac.create((i * 35) - 128, Game.HEIGHT + 1200, 0, -11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create(Game.WIDTH + 104 - (i * 35), Game.HEIGHT + 1750, 0, -11, ID.verticalenemy, 0, handler);	
						eFac.create((i * 35) - 24, Game.HEIGHT + 1750, 0, -11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create(Game.WIDTH + 62 - (i * 35), Game.HEIGHT + 2300, 0, -11, ID.verticalenemy, 0, handler);	
						eFac.create((i * 35) - 64, Game.HEIGHT + 2300, 0, -11, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 20) {

					for(int i = 0; i < 5; i++) { 
						eFac.create((i * 30) - 350, Game.HEIGHT + (i * 30) + 200, 15, -15, ID.diagonalenemy, 0, handler);	
						eFac.create(Game.WIDTH + (i * 30) + 200, -((i * 30) - Game.HEIGHT - 320), -15, -15, ID.diagonalenemy, 0, handler);	
					}
					for(int i = 0; i <= 9; i++) {
						eFac.create((i * 35) - 52, -800, 0, 11, ID.verticalenemy, 0, handler);	
						eFac.create(Game.WIDTH - (i * 35), -800, 0, 11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 35) - 144, -1300, 0, 11, ID.verticalenemy, 0, handler);	
						eFac.create(Game.WIDTH - (i * 35) - 16, -1300, 0, 11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 35) - 32, -1700, 0, 11, ID.verticalenemy, 0, handler);	
						eFac.create(Game.WIDTH - (i * 35) + 96, -1700, 0, 11, ID.verticalenemy, 0, handler);	
					}
					for(int i = 0; i <= 10; i++) {
						eFac.create((i * 35) - 176, -2100, 0, 11, ID.verticalenemy, 0, handler);	
						eFac.create(Game.WIDTH - (i * 35) - 48, -2100, 0, 11, ID.verticalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 21) {
					game.setReward(5000);
					eFac.create(Game.WIDTH - 50, Game.HEIGHT - 50, 0, 0, ID.smallenemy, 0, handler);
					eFac.create(50, 50, 0, 0, ID.smallenemy, 0, handler);

					for(int i = 0; i < 13; i++) { 
						eFac.create((i * 50) - 650, Game.HEIGHT + (i * 50) + 250, 8, -8, ID.diagonalenemy, 0, handler);	
						eFac.create(Game.WIDTH + (i * 50) + 1300, -((i * 50) - Game.HEIGHT - 1820), -8, -8, ID.diagonalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 22) {
					for(int i = 0; i < 13; i++) { 
						eFac.create((i * 50 - 550), -(i * 50 - 50), 8, 8, ID.diagonalenemy, 0, handler);	
						eFac.create(Game.WIDTH + (i * 50) + 1200, (i * 50) - 1800, -8, 8, ID.diagonalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 23) {
					for(int i = 0; i < 13; i++) { 
						eFac.create((i * 50 - 550), -(i * 50 - 50), 8, 8, ID.diagonalenemy, 0, handler);	
						eFac.create(Game.WIDTH + (i * 50) + 1200, (i * 50) - 1800, -8, 8, ID.diagonalenemy, 0, handler);	
					}
				}
				if(game.getLevel() == 24) {
					eFac.create(50, 50, 0, 0, ID.wavyenemy, 0, handler);	
					eFac.create(Game.WIDTH - 50, 50, 0, 0, ID.wavyenemy, 0, handler);	
				}
				if(game.getLevel() == 26) {
					eFac.create(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 0, 0, ID.shootyenemy, 0, handler);	
				}
				if(game.getLevel() == 27) {
					eFac.create(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 0, 0, ID.shootyenemy, 0, handler);	
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.healthblob, 0, handler);
				}
				
				if(game.getLevel()%4 == 0) {
					eFac.create(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 65), 0, 0, ID.healthblob, 0, handler);
				}	
			}
		}		
	}
}
