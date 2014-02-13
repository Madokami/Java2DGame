package game;
import sun.audio.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Music{
	AudioPlayer ap;
	AudioStream bgm;
	AudioData data;
	AudioInputStream in;
    Clip clip;
	ContinuousAudioDataStream loop =null;
	public Music(){
		//musicFile=new File("/bgm1.wav");
		ap=AudioPlayer.player;
		try {
			in = AudioSystem.getAudioInputStream(getClass().getResource("/bgm1.wav"));
			clip = AudioSystem.getClip();
			clip.open(in);
			clip.start();
			//bgm = new AudioStream(new FileInputStream("C:\\Users\\Attack on Majou\\workspace\\Java2DGame\\res\\bgm1.wav"));
			//data=bgm.getData();
			//loop=new ContinuousAudioDataStream(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void playMusic(){
		
	}
}
