package game;

import game.GameSystem.STATE;
import game.Menu.MENUSTATE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuChar{
		private BufferedImage mdSelectOn,mdSelectOff,hoSelectOn,hoSelectOff,saSelectOn,saSelectOff,kySelectOn,kySelectOff,maSelectOn,maSelectOff;
		private BufferedImage mdBg,kyBg,saBg,maBg,hoBg,charSelectBg;
		private BufferedImage mdName,kyName,saName,maName,hoName;
		private int cSelectIndex;
		private int cSelectHeight,cSelectWidth;
		double yShift;
		private boolean shiftingDown;
		private BufferedImageLoader loader;
		private Music musicPlayer;
		
		public static enum CHARACTER{
			MADOKA,
			HOMURA,
			SAYAKA,
			MAMI,
			KYOUKO,
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
			
			mdBg=loader.loadImage("/image/mdBgTrans.png");
			maBg=loader.loadImage("/image/maBg.png");
			saBg=loader.loadImage("/image/saBgTrans.png");
			kyBg=loader.loadImage("/image/kyBg.png");
			hoBg=loader.loadImage("/image/hoBgTrans.png");
			
			hoName=loader.loadImage("/image/hoName.png");
			saName=loader.loadImage("/image/saName.png");
			kyName=loader.loadImage("/image/kyName.png");
			maName=loader.loadImage("/image/maName.png");
			mdName=loader.loadImage("/image/mdName.png");
			
			charSelectBg = loader.loadImage("/image/charSelectBg.png");
			
			
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
			//if(cSelected == CHARACTER.MADOKA){
			//	g.drawImage(mdBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
			//}
			g.drawImage(charSelectBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
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
				//g.drawImage(mdBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
				g.drawImage(mdSelectOn,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
				g.drawImage(mdName,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-64,null);
			}
			else if(cSelected == CHARACTER.HOMURA){
				//g.drawImage(hoBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
				g.drawImage(hoSelectOn,2*cSelectIndex+cSelectWidth+8,(int)yShift,null);
				g.drawImage(hoName,2*cSelectIndex+cSelectWidth+8,(int)yShift-64,null);
			}
			else if(cSelected == CHARACTER.SAYAKA){
				//g.drawImage(saBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
				g.drawImage(saSelectOn,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
				g.drawImage(saName,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-64,null);
				
			}
			else if(cSelected == CHARACTER.MAMI){
				g.drawImage(maSelectOn,4*cSelectIndex+3*cSelectWidth+8,(int)yShift,null);
				g.drawImage(maName,4*cSelectIndex+3*cSelectWidth+8,(int)yShift-64,null);
			}
			else if(cSelected == CHARACTER.KYOUKO){
				g.drawImage(kySelectOn,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift,null);
				g.drawImage(kyName,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-64,null);
			}
		}

		public void keyPressed(int key) {
			if(key==KeyEvent.VK_X){
				yShift=1;
				GameSystem.playCancel();
				Menu.mState=Menu.MENUSTATE.MAIN;
			}
			else if(key==KeyEvent.VK_RIGHT){				
				if(cSelected == CHARACTER.MADOKA){
					GameSystem.playSwitch();
						cSelected=CHARACTER.HOMURA;
				}
				else if(cSelected == CHARACTER.HOMURA){
					GameSystem.playSwitch();
					cSelected=CHARACTER.SAYAKA;
				}
				else if(cSelected == CHARACTER.SAYAKA){
					GameSystem.playSwitch();
					cSelected=CHARACTER.MAMI;
				}
				else if(cSelected == CHARACTER.MAMI){
					GameSystem.playSwitch();
					cSelected=CHARACTER.KYOUKO;
				}
				else if(cSelected == CHARACTER.KYOUKO){
					GameSystem.playSwitch();
					cSelected=CHARACTER.MADOKA;
				}
				
			}
			else if(key==KeyEvent.VK_LEFT){
				if(cSelected == CHARACTER.HOMURA){
					GameSystem.playSwitch();
					cSelected=CHARACTER.MADOKA;
				}
				else if(cSelected == CHARACTER.SAYAKA){
					GameSystem.playSwitch();
					cSelected=CHARACTER.HOMURA;
				}
				else if(cSelected == CHARACTER.MAMI){
					GameSystem.playSwitch();
					cSelected=CHARACTER.SAYAKA;
				}
				else if(cSelected == CHARACTER.KYOUKO){
					GameSystem.playSwitch();
					cSelected=CHARACTER.MAMI;
				}
				else if(cSelected == CHARACTER.MADOKA){
					GameSystem.playSwitch();
					cSelected=CHARACTER.KYOUKO;
				}
			}
			else if(key==KeyEvent.VK_Z){
				if(cSelected == CHARACTER.MADOKA){
					GameSystem.playConfirm();
					Game.cChosen=Game.CHARACTER.MADOKA;
				}
				else if(cSelected == CHARACTER.HOMURA){
					GameSystem.playConfirm();
					Game.cChosen=Game.CHARACTER.HOMURA;
				}
				else if(cSelected == CHARACTER.SAYAKA){
					GameSystem.playConfirm();
					Game.cChosen=Game.CHARACTER.SAYAKA;
				}
				else if(cSelected == CHARACTER.MAMI){
					GameSystem.playConfirm();
					Game.cChosen=Game.CHARACTER.MAMI;
				}
				else if(cSelected == CHARACTER.KYOUKO){
					GameSystem.playConfirm();
					Game.cChosen=Game.CHARACTER.KYOUKO;
				}
				Menu.toGameMode();
			}
			
		}
		
	
}
