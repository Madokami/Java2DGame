package gameObject;

import java.util.Random;

import system.GameSystem;


public class SaVoice implements PlayerVoice{
	private Random rand;
	public SaVoice(){
		rand=new Random();
	}
	public void playDeathSound(){
		int x =rand.nextInt(2);
		String url = "/sound/saDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		
		String url = "/sound/saUlt.wav";
		System.out.println(url);
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/saLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/saItem.wav");
	}
	public void playDamagedMediumSound(){
		GameSystem.musicPlayer.playVoice("/sound/saDamage0.wav");
	}
	public void playDamagedHeavySound(){
		GameSystem.musicPlayer.playVoice("/sound/saDamage1.wav");
	}
	public void playSoulGemDarkSound(){
		GameSystem.musicPlayer.playVoice("/sound/saSoul1.wav");
	}
	

}
