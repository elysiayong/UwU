package main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class AudioPlayer {
	
	private static Map<Integer, Sound> soundMap = new HashMap<Integer, Sound>();
	private static Map<Integer, Music> musicMap = new HashMap<Integer, Music>();
	
	public static void load() {
		File soundFolder = new File("gamething/res/sfx");
		File musicFolder = new File("gamething/res/music");
		
		File[] soundList = soundFolder.listFiles();
		File[] musicList = musicFolder.listFiles();
		
		System.out.println(soundList.length); 


		try {
			for(int i = 0; i < soundList.length; i++) soundMap.put(i, new Sound(soundList[i].getCanonicalPath()));
			for(int i = 0; i < musicList.length; i++) musicMap.put(i, new Music(musicList[i].getCanonicalPath()));
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(Integer key) {
		return musicMap.get(key);
	}
	public static Sound getSound(Integer key) {
		return soundMap.get(key);
	}
}