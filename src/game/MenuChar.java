package game;

import game.GameSystem.STATE;
import game.Menu.MENUSTATE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuChar {
		private BufferedImage mdSelectOn,mdSelectOff,hoSelectOn,hoSelectOff,saSelectOn,saSelectOff,kySelectOn,kySelectOff,maSelectOn,maSelectOff;
		private int cSelectIndex;
		private int cSelectHeight,cSelectWidth;
		double yShift;
		private boolean shiftingDown;
		private BufferedImageLoader loader;
		private Music musicPlayer;
		
		public static enum CHARACTER{
			MADOKA,
			HOMURA
		};
		
		public static CHARACTER cSelected = CHARACTER.MADOKA;
		
		public MenuChar(){
			loader = new BufferedImageLoader();
			musicPlayer = new Music();
			mdSelectOn = loader.loadImage("/image/mdSelectOn.png");
			mdSelectOff = loader.loadImage("/image/mdSelectOff.png");
			hoSelectOn = loader.loadImage("/image/hoSelectOn.png");
			hoSelectOff = loader.loadImage("/image/hoSelectOff.png");
			maSelectOn = loader.loadImage("/image/maSelectOn.png");
			maSelectOff = loader.loadImage("/image/maSelectOff.png");
			saSelectOn = loader.loadImage("/image/saSelectOn.png");
			saSelectOff = loader.loadImage("/image/saSelectOff.png");
			kySelectOn = loader.loadImage("/image/kySelectOn.png");
			kySelectOff = loader.loadImage("/image/kySelectOff.png");
			cSelectIndex=(GameSystem.ABSWIDTH-5*mdSelectOn.getWidth())/6;
			cSelectWidth=mdSelectOn.getWidth();
			cSelectHeight = mdSelectOn.getHeight();
			yShift=1;
			shiftingDown=true;
		}
		
		public void tick(){
			shiftDown();
		}
		
		public void render(Graphics g){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GameSystem.ABSWIDTH+8, GameSystem.ABSHEIGHT+10);
			g.drawImage(mdSelectOff,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
			g.drawImage(hoSelectOff,2*cSelectIndex+cSelectWidth+8,(int) yShift,null);
			g.drawImage(saSelectOff,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
			g.drawImage(maSelectOff,4*cSelectIndex+3*cSelectWidth+8,(int)yShift,null);
			g.drawImage(kySelectOff,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
			renderSelected(g);
		}
		
		public void shiftDown(){
			if(yShift>=(GameSystem.ABSHEIGHT-cSelectHeight)/2){
				yShift=(GameSystem.ABSHEIGHT-cSelectHeight)/2;
				return;
			}
			yShift=yShift*1.23;
		
		}
		
		public void renderSelected(Graphics g){
			if(cSelected == CHARACTER.MADOKA){
				g.drawImage(mdSelectOn,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
			}
			else if(cSelected == CHARACTER.HOMURA){
				g.drawImage(hoSelectOn,2*cSelectIndex+cSelectWidth+8,(int)yShift,null);
			}
		}

		public void keyPressed(int key) {
			if(key==KeyEvent.VK_X){
				yShift=1;
				playCancel();
				Menu.mState=Menu.MENUSTATE.MAIN;
			}
			else if(key==KeyEvent.VK_RIGHT){				
				if(cSelected == CHARACTER.MADOKA){
						playSwitch();
						cSelected=CHARACTER.HOMURA;
				}
			}
			else if(key==KeyEvent.VK_LEFT){
				if(cSelected == CHARACTER.HOMURA){
					playSwitch();
					cSelected=CHARACTER.MADOKA;
				}
			}
			else if(key==KeyEvent.VK_Z){
				if(cSelected == CHARACTER.MADOKA){
					playConfirm();
					Game.cChosen=Game.CHARACTER.MADOKA;
				}
				else if(cSelected == CHARACTER.HOMURA){
					playConfirm();
					Game.cChosen=Game.CHARACTER.HOMURA;
				}
				toGameMode();
			}
			
		}
		public void playSwitch(){
			musicPlayer.playVoice("/sound/switch1.wav");
		}
		public void playConfirm(){
			musicPlayer.playVoice("/sound/choice2.wav");
		}
		public void playCancel(){
			musicPlayer.playVoice("/sound/cancel2.wav");
		}
		private void toGameMode() {
			Menu.turnOffBgm();
			GameSystem.state=STATE.GAME;
		}
		
	
}
