package game;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Music{

	AudioInputStream in;
    Clip music;
    Clip voice;
    Clip effect;
    Clip explosion;
    Clip sisPuellaMagica;
    AudioLoader loader;
	public Music(){
		//musicFile=new File("/bgm1.wav");
		loader = new AudioLoader();
		try {
			music = AudioSystem.getClip();
			effect = AudioSystem.getClip();
			sisPuellaMagica = AudioSystem.getClip();
			explosion = loader.newClip("/sound/expl1.wav");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader.loadAudio("/sound/expl1.wav", effect);
		
	}
	public void playBgm(){
		
		music=loader.newClip("/sound/bgm1.wav");
		music.loop(music.LOOP_CONTINUOUSLY);
	}
	public void playExplosion(){
		explosion.start();
	}
	public void playBattleMusic(){
		music.stop();
		music.flush();
		music = loader.newClip("/sound/Delusio_summa.wav");
		music.start();
	}
	public void stopMusic(){
		music.stop();
	}
	public void resumeMusic(){
		music.start();
	}
	public void pause(){
		try {
			music.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void playSwoosh() {
		effect = loader.newClip("/sound/soundEffect1.wav");
		effect.start();
	}
	public synchronized void playMusic(String url){
		music.stop();
		music = loader.newClip(url);
		music.loop(music.LOOP_CONTINUOUSLY);
	}
	public void playVoice(String url){
		voice = loader.newClip(url);
		voice.start();
	}
	public void reloadExplosion(){
		explosion = loader.newClip("/sound/expl1.wav");
	}
	public boolean musicIsPlaying(){
		if(music.isActive())
			return true;
		return false;
	}
}
