package gameObject;

public class Event {
	private int duration;
	private int durationTimer;
	private int value;
	private boolean on;
	
	public void tick(){
		durationTimer++;
		if(durationTimer>duration){
			stop();
		}
	}
	public boolean isOn(){
		return on;
	}
	public void start(int duration){
		this.duration=duration;
		this.durationTimer=0;
		this.on=true;
	}
	public void start(int duration,int value){
		this.duration=duration;
		this.durationTimer=0;
		this.on=true;
	}
	public void stop(){
		if(!on) return;
		on=false;
	}
	public int getValue(){
		return value;
	}
}
