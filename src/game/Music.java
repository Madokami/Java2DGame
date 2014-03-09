package game;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;



public class Music{

	AudioInputStream in;
    Clip music;
    Clip voice;
    Clip sound;
    Clip explosion;
    Clip sisPuellaMagica;
    
    public static int musicVolume,soundVolume,voiceVolume;
    
    AudioLoader loader;
	public Music(){
		//musicFile=new File("/bgm1.wav");
		loader = new AudioLoader();
		try {
			music = AudioSystem.getClip();
			sound = AudioSystem.getClip();
			sisPuellaMagica = AudioSystem.getClip();
			explosion = loader.newClip("/sound/expl1.wav");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader.loadAudio("/sound/expl1.wav", sound);
		
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
		sound = loader.newClip("/sound/soundEffect1.wav");
		sound.start();
	}
	public synchronized void playMusic(String url){
		music.stop();
		music = loader.newClip(url);
		setMusicVolume(musicVolume);
		music.loop(music.LOOP_CONTINUOUSLY);
	}
	public void playVoice(String url){
		if(voice!=null) voice.stop();
		voice = loader.newClip(url);
		voice.start();
	}
	public void playSound(String url){
		sound=loader.newClip(url);
		sound.start();
	}
	public void reloadExplosion(){
		explosion = loader.newClip("/sound/expl1.wav");
	}
	public boolean musicIsPlaying(){
		if(music.isActive())
			return true;
		return false;
	}
	
	public void setMusicVolume(int value){
		FloatControl volume = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(value);
	}
}
