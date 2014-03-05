package game;

import game.GameSystem.STATE;
import game.Menu.MENUSTATE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuChar implements GeneralMenu{
		private BufferedImage mdSelectOn,mdSelectOff,hoSelectOn,hoSelectOff,saSelectOn,saSelectOff,kySelectOn,kySelectOff,maSelectOn,maSelectOff;
		private BufferedImage mdBg,kyBg,saBg,maBg,hoBg,charSelectBg;
		private BufferedImage mdName,kyName,saName,maName,hoName;
		
		private BufferedImage mdStats,hoStats,saStats,maStats,kyStats;
		private BufferedImage statsSelectionCombinedOff, statsSelectionHpOn,statsSelectionMpOn,statsSelectionSpdOn,statsSelectionSoulOn,statsSelectionDamageOn,statsSelectionRangeOn;
		
		private int cSelectIndex;
		private int cSelectHeight,cSelectWidth;
		double yShift;
		private boolean shiftingDown;
		private BufferedImageLoader loader;
		
		private int height = 350;
		private int width = 115;
		
		private static int statsX=280;
		private static int statsY=140;
		public static int statsShift=28;
		
		public Game game;
		
		public AttributeHandler handler;
		
		public static enum CHARACTER{
			MADOKA,
			HOMURA,
			SAYAKA,
			MAMI,
			KYOUKO,
		};
		
		public static enum CHAR_MENU_STATE{
			IS_CHOOSING,
			DISPLAYING_STATUS,
		}
		
		public static enum SELECTED_ATTRIBUTE{
			HP,
			MP,
			SOUL,
			SPEED,
			DAMAGE,
			RANGE,
		}
		
		public static CHARACTER cSelected = CHARACTER.MADOKA;
		public static CHAR_MENU_STATE cState = CHAR_MENU_STATE.IS_CHOOSING;
		public static SELECTED_ATTRIBUTE cAttribute = SELECTED_ATTRIBUTE.HP;
		
		public MenuChar(Game game){
			this.game = game;
			loader = new BufferedImageLoader();
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
			
			mdStats=loader.loadImage("/image/madoka_Stats.png");
			hoStats=loader.loadImage("/image/homura_Stats.png");
			saStats=loader.loadImage("/image/sayaka_Stats.png");
			maStats=loader.loadImage("/image/mami_Stats.png");
			kyStats=loader.loadImage("/image/kyouko_Stats.png");
			
			
			statsSelectionCombinedOff=loader.loadImage("/image/statusSelectionCombinedOff.png");
			statsSelectionHpOn=loader.loadImage("/image/statsSelectionHpOn.png");
			statsSelectionMpOn=loader.loadImage("/image/statsSelectionMpOn.png");
			statsSelectionSoulOn=loader.loadImage("/image/statsSelectionSoulOn.png");
			statsSelectionSpdOn=loader.loadImage("/image/statsSelectionSpeedOn.png");
			statsSelectionDamageOn=loader.loadImage("/image/statsSelectionDamageOn.png");
			statsSelectionRangeOn=loader.loadImage("/image/statsSelectionRangeOn.png");
			
			
			charSelectBg = loader.loadImage("/image/charSelectBg.png");
			
			
			cSelectIndex=(GameSystem.ABSWIDTH-5*mdSelectOn.getWidth())/6;
			cSelectWidth=mdSelectOn.getWidth();
			cSelectHeight = mdSelectOn.getHeight();
			yShift=1;
			shiftingDown=true;
			
			handler = new AttributeHandler(game);
		}
		
		public void tick(){
			shiftDown();
		}
		
		public void render(Graphics g){
			if(isChooseChar()){
				g.drawImage(charSelectBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
				g.drawImage(mdSelectOff,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
				g.drawImage(hoSelectOff,2*cSelectIndex+cSelectWidth+8,(int) yShift+32,null);
				g.drawImage(saSelectOff,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
				g.drawImage(maSelectOff,4*cSelectIndex+3*cSelectWidth+8,(int)yShift+32,null);
				g.drawImage(kySelectOff,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
			}
			else if(isDisplayStatus()){
				if(cSelected == CHARACTER.MADOKA){
					g.drawImage(mdStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.HOMURA){
					g.drawImage(hoStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.SAYAKA){
					g.drawImage(saStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.MAMI){
					g.drawImage(maStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.KYOUKO){
					g.drawImage(kyStats, 0,0,null);
				}
				g.drawImage(statsSelectionCombinedOff,statsX,statsY,null);
			}
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
			if(isChooseChar()){
				if(cSelected == CHARACTER.MADOKA){
					//g.drawImage(mdBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
					g.drawImage(mdSelectOn,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
					g.drawImage(mdName,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
				}
				else if(cSelected == CHARACTER.HOMURA){
					//g.drawImage(hoBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
					g.drawImage(hoSelectOn,2*cSelectIndex+cSelectWidth+8,(int)yShift+32,null);
					g.drawImage(hoName,2*cSelectIndex+cSelectWidth+8,(int)yShift-16,null);
				}
				else if(cSelected == CHARACTER.SAYAKA){
					//g.drawImage(saBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
					g.drawImage(saSelectOn,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
					g.drawImage(saName,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
					
				}
				else if(cSelected == CHARACTER.MAMI){
					g.drawImage(maSelectOn,4*cSelectIndex+3*cSelectWidth+8,(int)yShift+32,null);
					g.drawImage(maName,4*cSelectIndex+3*cSelectWidth+8,(int)yShift-16,null);
				}
				else if(cSelected == CHARACTER.KYOUKO){
					g.drawImage(kySelectOn,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
					g.drawImage(kyName,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
				}
			}
			else if(isDisplayStatus()){
				if(cAttribute == SELECTED_ATTRIBUTE.HP){
					g.drawImage(statsSelectionHpOn, statsX-12, statsY+27+statsShift*0, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.MP){
					g.drawImage(statsSelectionMpOn, statsX-12, statsY+27+statsShift*1, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
					g.drawImage(statsSelectionSoulOn, statsX-12, statsY+27+statsShift*2, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
					g.drawImage(statsSelectionSpdOn, statsX-12, statsY+27+statsShift*3, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
					g.drawImage(statsSelectionDamageOn, statsX-12, statsY+27+statsShift*4, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
					g.drawImage(statsSelectionRangeOn, statsX-12, statsY+27+statsShift*5, null);
				}
				//handler will render the attribute values in form of images
				handler.render(g);
			}
		}

		public void keyPressed(int key) {
			if(this.isChooseChar()){
				if(key==KeyEvent.VK_X){
					yShift=1;
					GameSystem.playCancel();
					Menu.mState=Menu.MENUSTATE.MAIN;
				}
				else if(key==KeyEvent.VK_RIGHT){				
					if(cSelected == CHARACTER.MADOKA){
							cSelected=CHARACTER.HOMURA;
					}
					else if(cSelected == CHARACTER.HOMURA){
						cSelected=CHARACTER.SAYAKA;
					}
					else if(cSelected == CHARACTER.SAYAKA){
						cSelected=CHARACTER.MAMI;
					}
					else if(cSelected == CHARACTER.MAMI){
						cSelected=CHARACTER.KYOUKO;
					}
					else if(cSelected == CHARACTER.KYOUKO){
						cSelected=CHARACTER.MADOKA;
					}
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_LEFT){
					if(cSelected == CHARACTER.HOMURA){
						cSelected=CHARACTER.MADOKA;
					}
					else if(cSelected == CHARACTER.SAYAKA){
						cSelected=CHARACTER.HOMURA;
					}
					else if(cSelected == CHARACTER.MAMI){
						cSelected=CHARACTER.SAYAKA;
					}
					else if(cSelected == CHARACTER.KYOUKO){
						cSelected=CHARACTER.MAMI;
					}
					else if(cSelected == CHARACTER.MADOKA){
						cSelected=CHARACTER.KYOUKO;
					}
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_Z){
					handler.refreshAll();
					this.setDisplayStatus();
					GameSystem.playConfirm();
				}
				
			}
			
			
			
			else if(this.isDisplayStatus()){
				 if(key==KeyEvent.VK_Z){
					if(cSelected == CHARACTER.MADOKA){
						Game.cChosen=Game.CHARACTER.MADOKA;
					}
					else if(cSelected == CHARACTER.HOMURA){
						Game.cChosen=Game.CHARACTER.HOMURA;
					}
					else if(cSelected == CHARACTER.SAYAKA){
						Game.cChosen=Game.CHARACTER.SAYAKA;
					}
					else if(cSelected == CHARACTER.MAMI){
						Game.cChosen=Game.CHARACTER.MAMI;
					}
					else if(cSelected == CHARACTER.KYOUKO){
						Game.cChosen=Game.CHARACTER.KYOUKO;
					}
					handler.setNewValues();
					GameSystem.playConfirm();
					playSelectionSound();
					Menu.toGameMode();
				}
				else if(key==KeyEvent.VK_X){
					yShift=1;
					handler.restoreOriginalValue();
					GameSystem.playCancel();
					setChooseChar();
				}
				else if(key==KeyEvent.VK_DOWN){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						cAttribute = SELECTED_ATTRIBUTE.MP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						cAttribute = SELECTED_ATTRIBUTE.SOUL;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						cAttribute = SELECTED_ATTRIBUTE.SPEED;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						cAttribute = SELECTED_ATTRIBUTE.DAMAGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						cAttribute = SELECTED_ATTRIBUTE.RANGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						cAttribute = SELECTED_ATTRIBUTE.HP;
					}
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_UP){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						cAttribute = SELECTED_ATTRIBUTE.RANGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						cAttribute = SELECTED_ATTRIBUTE.HP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						cAttribute = SELECTED_ATTRIBUTE.MP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						cAttribute = SELECTED_ATTRIBUTE.SOUL;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						cAttribute = SELECTED_ATTRIBUTE.SPEED;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						cAttribute = SELECTED_ATTRIBUTE.DAMAGE;
					}
					GameSystem.playSwitch();
				}
				
				//increase stats if you have enough pt
				else if(key==KeyEvent.VK_RIGHT){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						if(handler.BPCur>=handler.hpCost){
							GameSystem.playConfirm();
							handler.BPCur=handler.BPCur-handler.hpCost;
							handler.hpCur=handler.hpCur+handler.hpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						if(handler.BPCur>=handler.mpCost){
							GameSystem.playConfirm();
							handler.BPCur=handler.BPCur-handler.mpCost;
							handler.mpCur+=handler.mpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						if(handler.BPCur>=handler.soulCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.soulCost;
							handler.soulCur+=handler.soulValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						if(handler.BPCur>=handler.spdCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.spdCost;
							handler.spdCur+=handler.spdValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						if(handler.BPCur>=handler.bombStrengthCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.bombStrengthCost;
							handler.bombStrengthCur+=handler.bombStrengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						if(handler.BPCur>=handler.bombLengthCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.bombLengthCost;
							handler.bombLengthCur+=handler.bombLengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
				}
				//reduces stats if conditions meet
				else if(key==KeyEvent.VK_LEFT){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						if(handler.BPCur+handler.hpCost<=handler.BPOriginal&&handler.hpCur-handler.hpValue>=handler.hpOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.hpCost;
							handler.hpCur-=handler.hpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						if(handler.BPCur+handler.mpCost<=handler.BPOriginal&&handler.mpCur-handler.mpValue>=handler.mpOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.mpCost;
							handler.mpCur-=handler.mpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						if(handler.BPCur+handler.soulCost<=handler.BPOriginal&&handler.soulCur-handler.soulValue>=handler.soulOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.soulCost;
							handler.soulCur-=handler.soulValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						if(handler.BPCur+handler.spdCost<=handler.BPOriginal&&handler.spdCur-handler.spdValue>=handler.spdOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.spdCost;
							handler.spdCur-=handler.spdValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						if(handler.BPCur+handler.bombStrengthCost<=handler.BPOriginal&&handler.bombStrengthCur-handler.bombStrengthValue>=handler.bombStrengthOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.bombStrengthCost;
							handler.bombStrengthCur-=handler.bombStrengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						if(handler.BPCur+handler.bombLengthCost<=handler.BPOriginal&&handler.bombLengthCur-handler.bombLengthValue>=handler.bombLengthOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.bombLengthCost;
							handler.bombLengthCur-=handler.bombLengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playCancel();
						}
					}
				}
			}
		}
		public void playSelectionSound(){
			if(cSelected == CHARACTER.MADOKA){
				GameSystem.musicPlayer.playVoice("/sound/mdSelect.wav");
			}
			else if(cSelected == CHARACTER.HOMURA){
				GameSystem.musicPlayer.playVoice("/sound/hoSelect.wav");
			}
			else if(cSelected == CHARACTER.SAYAKA){
				GameSystem.musicPlayer.playVoice("/sound/saSelect.wav");
			}
			else if(cSelected == CHARACTER.MAMI){
				GameSystem.musicPlayer.playVoice("/sound/maSelect.wav");
			}
			else if(cSelected == CHARACTER.KYOUKO){
				GameSystem.musicPlayer.playVoice("/sound/kySelect.wav");
			}
		}
		
		
		public boolean isChooseChar(){
			if(cState == CHAR_MENU_STATE.IS_CHOOSING){
				return true;
			}
			return false;
		}
		
		public void setChooseChar(){
			cState = CHAR_MENU_STATE.IS_CHOOSING;
		}
		
		public boolean isDisplayStatus(){
			if(cState == CHAR_MENU_STATE.DISPLAYING_STATUS){
				return true;
			}
			return false;
		}
		
		public void setDisplayStatus(){
			cState = CHAR_MENU_STATE.DISPLAYING_STATUS;
		}
	
}
