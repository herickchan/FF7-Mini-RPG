import hsa.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import  sun.audio.*;

	public class RPGHerick{
		public static void main(String [] args){
			hsa.Console con2 = new hsa.Console(17, 57, "Menu");
			char chrMenu = 0;
			//MUSIC Variables - Creates the Player
				AudioStream Player = null;
				AudioStream Speech = null;
				AudioStream Effects = null;
				
			//VARIABLES INSIDE WHILE LOOP - to save space
			while(chrMenu != 3){
				Utils.stopMusic(Player);
				Player = Utils.startMusic("Boss3.wav"); // loads up same song as boss 3 - EPIC MUSIC...
				
				//Basic Variables
				String strMap[][]; // The map array
				String strPos = "";
				char chrHeroMoves = 0;
				char chrBack = 0; // Used for the "press any key to continue" situations.
				char chrBattle = 0; // Used to choose action in battle
				char chrSkillMenu = 0; // Used to choose skill in battle
				char chrSkillMagic = 0; // Skill magic menu
				char chrSkillSword = 0; // skill sword menu
				char chrFinalMenu = 0; // For last boss cinematic
				char chrFinalSkillMenu = 0; // For last boss skill cinematic
				int intbuilding[] = new int[4]; // for each building
				boolean blnchest[] = new boolean[4];// for each chest
				boolean blnallchest = false; // if all chests are found this will be true
				boolean blnboss[] = new boolean[4]; // for each boss
				boolean blnallboss = false; // if all bosses are defeated, this wil be true
				boolean blnchestopened[] = new boolean [4]; //Used to determine how much life to heal after increasing max health
				boolean blnHiddenItem[] = new boolean [9]; // There are 9 hidden items throughout the map - First set = 's' key if command, Second Set = 'w' key, Third Set = 'd' key.
				boolean blnSpecialItem = false; // SPECIAL item. only 1 in game
				int intDiebyW = 0; // Used to determine if the player drowned or not
				int intCounter = 0; // Counters used for initializing, etc
				int intCounter2 = 0;
				int intPlayerTurn = 0; // Used to determine the player's turn in a battle - these two variables are always turned back to 0 after both turns.
				int intEnemyTurn = 0; // Used to determine the enemy's turn in a battle 
				int intWin = 0; // When player reaches vincent, then win.
				
				//Player Variables
				int intCurrentRow = 18; // Used to locate hero's current Row
				int intCurrentCol = 17; // used to locate hero's current Column
				int intX = 340; // X and Y coordinates of the hero (Used for printing and putting restrictions - ex. trees + water)
				int intY = 360;
				int intHP = 50; // life at 50
				int intATT = 15; // Attack points - 15
				int intDEF = 10; // Defense points - 10
				int intMAG = 15; // Magic points - 15
				int intGUARD = 0;
				int intDMG = 0; // Initializing DMG and Guard for now.... will randomize later
				int intMP = 50; // MP at 50
				int intCure = 0;
				int intFira = 0;
				int intShock = 0;
				int intThorn = 0;
				int intSlam = 0;
				String strHP = "";
				String strATT = "";
				String strDEF = "";
				String strMAG = "";
				String strMP = "";
				int intSword = 0;  //Hidden items throughout the game.
				int intShield = 0;
				int intWand = 0;
				String strSword = "";
				String strShield = "";
				String strWand = "";
				
				//Enemy Variables
				//Boss1
				int intBoss1HP = 100;
				int intBoss1MP = 0;
				String strBoss1HP = "";
				String strBoss1MP = "";
				int intBoss1ATT = 0;
				//Boss2
				int intBoss2HP = 200;
				int intBoss2MP = 0;
				String strBoss2HP = "";
				String strBoss2MP = "";
				int intBoss2ATT = 0;
				//Boss3
				int intBoss3HP = 750;
				int intBoss3MP = 200;
				String strBoss3HP = "";
				String strBoss3MP = "";
				int intBoss3ATT = 0;
				//Boss4
				int intBoss4HP = 2000;
				int intBoss4MP = 1000;
				String strBoss4HP = "";
				String strBoss4MP = "";
				int intBoss4ATT = 0;
				
				//Used to initialized the array variables - to save space
				for(intCounter = 0;intCounter < 4;intCounter++){ // Faster way of initializing array variables
					intbuilding[intCounter] = 0;
					blnchest[intCounter] = false;
					blnboss[intCounter] = false;
				}
				
				for(intCounter = 0;intCounter < 9;intCounter++){ // Faster way of initializing array variables
					blnHiddenItem[intCounter] = false;
				}
			
				//Title page image
				Utils.loadImage("Title.png",0,0,con2);
				chrMenu = con2.getChar();
				
				//IF PLAYER CHOOSES 1, GAME STARTS
				if(chrMenu == '1'){
				con2.clear(); // clears current menu screen
				
			//BACKROUND STORY
				Utils.stopMusic(Player);
				Player = Utils.startMusic("Instructions.wav");
				Utils.loadImage("story.png",0,0,con2);
				chrBack = con2.getChar();
				
				//Loads Menu screen
				Utils.loadImage("menu.png",0,0,con2);
				//Creates the Game console after menu
				hsa.Console con = new hsa.Console(17, 57, "Game");
				//Loads the "map" into the array
				strMap = Utils.loadMap("map.csv");
				//Prints all the images matching the corresponding letter into the console
				Utils.gameload(con);
				//Loads Hero at spawn point
				Utils.loadImage("herof.png",intX,intY,con);
				//Loads Hero Health
				Utils.loadImage("health.png",25,50,con2);
				Utils.loadImage("health.png",60,50,con2);
				Utils.loadImage("health.png",95,50,con2);
				Utils.loadImage("health.png",130,50,con2);
				Utils.loadImage("health.png",165,50,con2);
				//Loads Objectives
				//Bosses
				Utils.loadImage("bossicon1.png",15,250,con2);
				Utils.loadImage("bossicon2.png",55,250,con2);
				Utils.loadImage("bossicon3.png",95,250,con2);
				Utils.loadImage("bossicon4.png",135,250,con2);
				//Chests
				Utils.loadImage("chest1.png",15,320,con2);
				Utils.loadImage("chest2.png",55,320,con2);
				Utils.loadImage("chest3.png",95,320,con2);
				Utils.loadImage("chest4.png",135,320,con2);
				//Stops Previous Song to start new song
				Utils.stopMusic(Player);
				Speech = Utils.startMusic("CloudTalk1.wav");
				Player = Utils.startMusic("Game.wav"); // NEW SONG
				con2.setFont(new Font("Verdana", 50, 15)); 
		//Game Start
			//Game will continue as long as hero's life is greater than 0
			while(intHP > 0 && intWin == 0){
			Utils.loadImage("healthmenu.png",0,0,con2); // Refreshes the top part of the con2 menu - the health + mana stats.
			con2.setColor(Color.black); // Erases the old data in order to put in the new ones
			con2.drawString(strATT,290,233);
			con2.drawString(strDEF,290,253);
			con2.drawString(strMAG,290,273);
			con2.drawString(strHP,150,30);
			con2.drawString(strSword,255,370);
			con2.drawString(strShield,220,370);
			con2.drawString(strWand,290,370);
			con2.drawString(strMP,285,30);
			
			strHP = intHP + ""; // Re-converts the stats to str to draw on the console
			strATT = intATT + "";
			strDEF = intDEF + "";
			strMAG = intMAG + "";
			strSword = intSword + "";
			strShield = intShield + "";
			strWand = intWand + "";
			strMP = intMP + "";
			con2.setColor(Color.white); // Mew Data
			con2.drawString(strATT,290,233);
			con2.drawString(strDEF,290,253);
			con2.drawString(strMAG,290,273);
			con2.drawString(strHP,150,30);
			con2.drawString(strSword,255,370);
			con2.drawString(strShield,220,370);
			con2.drawString(strWand,290,370);
			con2.drawString(strMP,285,30);
			
			chrHeroMoves = 0;
			chrHeroMoves = con.getChar();
		
		//Menu.. aka console 2	
			//Health Bar depletion in menu 
			//Depends on chests that were opened and prints out the empty health cartridges accordingly
			if(intHP <= 190 && intHP > 180){
				for(intCounter2 = 340;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
			}else if(intHP <= 180 && intHP > 170){
				for(intCounter2 = 305;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
			}else if(intHP <= 170 && intHP > 160){
					for(intCounter2 = 270;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
			}else if(intHP <= 160 && intHP > 150){
					for(intCounter2 = 235;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
			}else if(intHP <= 150 && intHP > 140){
				if(blnchest[2] == true){ // makes sure that the player has received chest #3 before printing out empty hp image
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 140 && intHP > 130){
				if(blnchest[2] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
					for(intCounter2 = 165;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}		
				}else{
					for(intCounter2 = 165;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 130 && intHP > 120){
				if(blnchest[2] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
					for(intCounter2 = 130;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}else{
					for(intCounter2 = 130;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 120 && intHP > 110){
				if(blnchest[2] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 95;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
				}else{
					for(intCounter2 = 95;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 110 && intHP > 100){
				if(blnchest[2] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
					for(intCounter2 = 60;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}else{
					for(intCounter2 = 60;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 100 && intHP > 90){
				if(blnchest[2] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}
			}else if(intHP <= 90 && intHP > 80){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 340;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
					for(intCounter2 = 340;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 340;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 80 && intHP > 70){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 305;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 305;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else{
					for(intCounter2 = 305;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 70 && intHP > 60){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 270;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 270;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 270;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 60 && intHP > 50){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 235;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}
					for(intCounter2 = 235;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 235;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 50 && intHP > 40){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[0] == true){
					for(intCounter2 = 200;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 40 && intHP > 30){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 165;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 165;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[0] == true){
					for(intCounter2 = 165;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 165;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 30 && intHP > 20){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 130;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 130;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[0] == true){
					for(intCounter2 = 130;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 130;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 20 && intHP > 10){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 95;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 95;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[0] == true){
					for(intCounter2 = 95;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 95;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}else if(intHP <= 10 && intHP > 0){
				if(blnchest[2] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 60;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
					for(intCounter2 = 60;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
				}else if(blnchest[0] == true){
					for(intCounter2 = 60;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 60;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}
		//Main Game aka Console 1
			//Hero moves forwards
			if(chrHeroMoves == ('w')){
				strPos = "";
				strPos = "forwards";
				intY = intY - 20;
				intCurrentRow = intCurrentRow - 1; // Counts where hero is in array
				//Player can't hit Tree
				if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("t")){
					intY = intY + 20; //Restricts character from going into tree by making image print back to position before
					intCurrentRow = intCurrentRow + 1;
				//Player can't go in Water if they do, game over
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w1") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w2") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w3") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w4") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w5") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w6")){
					intY = intY + 20;
					intCurrentRow = intCurrentRow + 1;
					intHP = 0;
					intDiebyW = intDiebyW + 1;
				//Player can only run into each building once and each time, their hp will return to max
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b1") && intbuilding[0] <= 1){
					intbuilding[0] = intbuilding[0] + 1;
					intHP = 50;
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b2") && intbuilding[1] == 0){
					intbuilding[1] = intbuilding[1] + 1;
					intHP = 100; // Max health is increased by 50 since it is after 1st boss fight which already gives player an extra 50 life there fore max life = 100
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b3") && intbuilding[2] == 0){
					intbuilding[2] = intbuilding[2] + 1;
					intHP = 150;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b4") && intbuilding[3] == 0){
					intbuilding[3] = intbuilding[3] + 1;
					intHP = 200;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
					
				//When player runs into enemy, player loses 10 life
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e1")){
					intHP = intHP - 10;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e2")){
					intHP = intHP - 20;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e3")){
					intHP = intHP - 30;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e4")){
					intHP = intHP - 40;
					
				//For chests 1-4 (From 1-3 gives 50 to the max health) Chest 4 gives special item - ONE CHEST FOR EACH DIRECTION. WASD
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("c1") && blnchest[0] == false){
					Utils.loadImage("cog1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("cog2.png",0,0,con);
					Utils.loadImage("cog3.png",0,0,con);
					Effects = Utils.startMusic("chestopen.wav");
					Utils.pause(1000);
					Utils.loadImage("chestitemshield.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("chestitembuster.wav");
					chrBack = con.getChar();
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					intHP = 100;
					intMP = 70;
					intDEF = intDEF + 10;
					blnchest[0] = true;
					Utils.loadImage("chest1r.png",15,320,con2);
					Utils.loadImage("guardian.png",320,320,con2);
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
						
				//When player reaches Vincent then win
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("v")){
					intWin = intWin + 1;
				//If player reaches a hidden item.... 
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sw2")){
					if(blnHiddenItem[1] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemsword.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar(); //Used to get back to world map
					intSword = intSword + 1;
					intATT = intATT + 2;
					blnHiddenItem[1] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
					}	
				
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sh2")){
					if(blnHiddenItem[4] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemshield.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intShield = intShield + 1;
					intDEF = intDEF + 5;
					blnHiddenItem[4] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
					}
				
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("wa2")){
					if(blnHiddenItem[7] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemwand.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intWand = intWand + 1;
					intMAG = intMAG + 2;
					blnHiddenItem[7] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
					}
				
					
	//LAST BOSS BATTLE SYSTEM!!!! (only appears in "w" key since the boss is at the front of the hero only - no point in wasting more space)
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo4") && blnboss[3] == false){
					intY = intY + 20;
					intCurrentRow = intCurrentRow + 1;
					Utils.stopMusic(Player);
					Utils.loadImage("bossicon4.png",140,120,con); // BOSS APPEAR on map
					Utils.loadImage("boss4appear1.png",0,0,con);
					Utils.pause(500);
					Effects = Utils.startMusic("found.wav");
					Utils.loadImage("boss4appear2.png",0,0,con);
					Utils.pause(1000);
					con.clear();
					Utils.stopMusic(Player);
					Utils.stopMusic(Speech);
					Speech = Utils.startMusic("CloudTalk5.wav");
					Player = Utils.startMusic("Boss4.wav");
					Utils.loadImage("boss4battle.png",0,0,con);
					Utils.pause(100);
					Effects = Utils.startMusic("bustersword.wav");
					Utils.loadImage("boss4battle2.png",0,0,con);
					Utils.pause(100);
					con.setFont(new Font("Verdana", 50, 15));
					while(intBoss4HP > 0 && intHP > 0){ // while boss isn't defeated
					chrBattle = 0;
					intPlayerTurn = 0;
					intEnemyTurn = 0;
					Utils.loadImage("boss4battleM.png",0,0,con);

						//BEFORE BATTLE
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString("+ "+intCure, 325, 230);
							con.drawString("- "+intFira, 20, 230);
							con.drawString("- "+intThorn, 20, 230);
							con.drawString("- "+intSlam, 13, 230);
							con.drawString("- "+intShock, 20, 230);
							con.drawString("- "+intBoss4ATT, 325, 230);
							con.drawString("- "+intDMG, 20, 230);
							con.drawString("Not Enough MP",257,50);
							//Initializes the string variables to make them into the int values
							strHP = intHP + "";
							strMP = intMP + "";
							con.drawString("????",80,75);
							if(intBoss4HP < 1000){
							con.drawString(strBoss4HP,80,75);
							strBoss4HP = intBoss4HP + "";
							con.drawString(strMP,310,100);
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss4HP,80,75);
							con.drawString("????",80,100);
							}else{
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString("????",80,75);
							con.drawString("????",80,100);
							}
							intGUARD = (int)(Math.random()*(intDEF*3) + 5); // Used for Battle - Randomizes from 5 - Def pt x 3 
							intDMG = (int)(Math.random()*(intATT*3) + 5); // Used for Battle - Randomizes from 5 - Attack pt x3
							intBoss4ATT = (int)(Math.random()*50 + 1); // Boss ATTACK
							intCure = (int)(Math.random()*(intMAG*3) + 20); // Cure Skill randomizes from 20 - magic pt x 3 - BUFFED UP FOR LAST BOSS
							intFira = (int)(Math.random()*(intMAG*3) + 20); // Fire SKill randomizes from 20 - magic pt x3 - BUFFED UP FOR LAST BOSS
							intSlam = (int)(Math.random()*(intMAG*6) + 40); // SLAM skill randomizes from 40 - magic pt x6
							intThorn = (int)(Math.random()*(intMAG*4) + 30);// Thorn skill randomizes from 20 - magic pt x 4 - BUFFED UP FOR LAST BOSS
							intShock = (int)(Math.random()*(intMAG*5) + 30); // SHOCK skill randomizes from 30 - magic pt x5
							
						//BATTLE TIME!
							//PLAYER TURN
							while(intPlayerTurn == 0){
								Utils.loadImage("attackmenu.png",0,299,con); // Main battle menu
								chrBattle = con.getChar(); // reads player command
								if(chrBattle == '1'){ // Attack Option
									Utils.loadImage("boss4sword.png",2,114,con);
									Utils.loadImage("boss4sword1.png",2,114,con);
									Effects = Utils.startMusic("dash.wav");
									Utils.pause(500);
									Utils.loadImage("boss4sword2.png",2,114,con);
									Utils.loadImage("boss4sword3.png",2,114,con);
									Effects = Utils.startMusic("slash.wav");
									intBoss4HP = intBoss4HP - intDMG;
									con.drawString("- "+intDMG, 20, 230);
									Utils.pause(500);
									Utils.loadImage("boss4sword4.png",2,114,con);
									Utils.pause(200);
									Utils.loadImage("boss4attacks1.png",2,114,con); // original position
									Utils.pause(500);
									intPlayerTurn = 1;
								
								}else if(chrBattle == '2'){ // Skill Option
									Utils.loadImage("Skill1.png",0,299,con); // SWORD SKILLS
									chrSkillMenu = 0; 
									chrSkillMenu = con.getChar();
									if(chrSkillMenu == '1'){
											Utils.loadImage("SkillSword.png",0,299,con);
											chrSkillSword = con.getChar();
										if(intMP >= 50){
											if(chrSkillSword == '1'){ // SLAM
												Utils.loadImage("boss4slam1.png",2,114,con);
												Utils.loadImage("boss4slam2.png",2,114,con);
												Utils.loadImage("boss4slam3.png",2,114,con);
												Speech = Utils.startMusic("CloudTalkSlam.wav");
												Utils.pause(2000);
												Utils.loadImage("boss4slam4.png",2,114,con);
												Utils.loadImage("boss4slam5.png",2,114,con);
												Utils.loadImage("boss4slam6.png",2,114,con);
												Utils.loadImage("boss4slam7.png",2,114,con);
												Utils.loadImage("boss4slam8.png",2,114,con);
												Effects = Utils.startMusic("Slam.wav");
												Effects = Utils.startMusic("explosion.wav");
												intMP = intMP - 50;
												intBoss4HP = intBoss4HP - intSlam;
												con.setColor(Color.white);
												con.drawString("- "+intSlam, 13, 230);
												Utils.pause(1000);
												con.setColor(Color.black);
												con.drawString("- "+intSlam, 13, 230);
												con.setColor(Color.white); // Sets font back to white after erasing
													intPlayerTurn = 1;
												}
											}else if(chrSkillMagic == '6'){
											
											}else{
												con.drawString("Not Enough MP",257,50);	
												}
									}else if(chrSkillMenu == '2'){
										Utils.loadImage("Skill.png",0,299,con); // MAGIC SKILLS
										chrSkillMagic = con.getChar();
										if(chrSkillMagic== '1'){ // FIRA skill only if there is enough MP
											if(intMP >= 20){
											Utils.loadImage("boss4cure1.png",2,114,con); // uses same animation for CURE therefore same pic name
											Utils.pause(100);
											Utils.loadImage("boss4cure2.png",2,114,con);
											Utils.pause(100);
											Utils.loadImage("boss4cure3.png",2,114,con);
											Speech = Utils.startMusic("CloudFira.wav");
											Utils.pause(500);
											Utils.loadImage("boss4fire1.png",2,114,con);
											Utils.loadImage("boss4fire2.png",2,114,con);
											Utils.loadImage("boss4fire3.png",2,114,con);
											Utils.loadImage("boss4fire4.png",2,114,con);
											Utils.loadImage("boss4fire5.png",2,114,con);
											intMP = intMP - 20; // USES 20 MP for spell
											Effects = Utils.startMusic("fira.wav");
											intBoss4HP = intBoss4HP - intFira;
											con.setColor(Color.white);
											con.drawString("- "+intFira, 20, 230);
											Utils.pause(1000);
											con.setColor(Color.black);
											con.drawString("- "+intFira, 20, 230);
											Utils.loadImage("boss4cure3.png",2,114,con);
											Utils.loadImage("boss4cure2.png",2,114,con);
											Utils.pause(100);
											Utils.loadImage("boss4cure1.png",2,114,con);
											Utils.pause(500);
											con.setColor(Color.white);
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '2'){ // CURE skill
											if(intMP > 0){ // ONLY IF THERE IS ENOUGH MP TO USE
											Speech = Utils.startMusic("CloudCure.wav");
											Utils.pause(500);
											Utils.loadImage("boss4cure1.png",2,114,con);
											Utils.pause(100);
											Utils.loadImage("boss4cure2.png",2,114,con);
											Utils.pause(100);
											Utils.loadImage("boss4cure3.png",2,114,con);
											Utils.pause(500);
											Utils.loadImage("boss4cure4.png",2,114,con);
											Utils.pause(100);
											Utils.loadImage("boss4cure5.png",2,114,con);
											Effects = Utils.startMusic("Cure.wav");
											intMP = intMP - 10;
											intHP = intHP + intCure;
											if(intHP > 200){
											intHP = 200;	
											}
											con.drawString("+ "+intCure, 320, 230);
											Utils.pause(500);
											con.setColor(Color.black);
											con.drawString("+ "+intCure, 320, 230);
											Utils.pause(500);
											Utils.loadImage("boss4cure3.png",2,114,con);
											Utils.loadImage("boss4cure1.png",2,114,con);
											Utils.pause(500);
											con.setColor(Color.white); // Sets font back to white after erasing
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '3'){ // Next page
											chrSkillMagic = 0; //RESET VARIABLE
											Utils.loadImage("Skill3.png",0,299,con);
											chrSkillMagic = con.getChar();
										if(intMP >= 30){
											if(chrSkillMagic == '4'){ // THORN
												Utils.loadImage("boss4cure1.png",2,114,con); // uses same animation for CURE therefore same pic name
												Utils.pause(100);
												Utils.loadImage("boss4cure2.png",2,114,con);
												Utils.pause(100);
												Utils.loadImage("boss4cure3.png",2,114,con);
												Speech = Utils.startMusic("CloudTalkThorn.wav");
												Utils.pause(500);
												Utils.loadImage("boss4thorn1.png",2,114,con);
												Utils.loadImage("boss4thorn2.png",2,114,con);
												Utils.loadImage("boss4thorn3.png",2,114,con);
												Utils.loadImage("boss4thorn4.png",2,114,con);
												Effects = Utils.startMusic("Thorn.wav");
												intMP = intMP - 30;
												intBoss4HP = intBoss4HP - intThorn;
												con.setColor(Color.white);
												con.drawString("- "+intThorn, 15, 230);
												Utils.pause(1000);
												con.setColor(Color.black);
												con.drawString("- "+intThorn, 15, 230);
												Utils.loadImage("boss4cure3.png",2,114,con);
												Utils.loadImage("boss4cure2.png",2,114,con);
												Utils.pause(100);
												Utils.loadImage("boss4cure1.png",2,114,con);
												Utils.pause(500);
												con.setColor(Color.white); // Sets font back to white after erasing
												intPlayerTurn = 1;
													}
												}else if(chrSkillMagic == '6'){
													
												}else{
														con.setColor(Color.white); 
														con.drawString("Not Enough MP",257,50);	
													}
											if(chrSkillMagic == '5'){ //SHOCK
												if(intMP >= 40){
													Utils.loadImage("boss4cure1.png",2,114,con); // uses same animation for CURE therefore same pic name
													Utils.pause(100);
													Utils.loadImage("boss4cure2.png",2,114,con);
													Utils.pause(100);
													Utils.loadImage("boss4cure3.png",2,114,con);
													Speech = Utils.startMusic("CloudTalkShock.wav");
													Utils.pause(100);
													Effects = Utils.startMusic("Shockcharge.wav");
													Utils.loadImage("boss4shock1.png",2,114,con);
													Utils.pause(100);
													Utils.loadImage("boss4shock2.png",2,114,con);
													Effects = Utils.startMusic("Shockcharge.wav");
													Utils.loadImage("boss4shock1.png",2,114,con);
													Utils.pause(100);
													Utils.loadImage("boss4shock2.png",2,114,con);
													Utils.pause(500);
													Effects = Utils.startMusic("thunder.wav");
													Utils.loadImage("boss4shock3.png",2,114,con);
													Utils.loadImage("boss4shock4.png",2,114,con);
													intMP = intMP - 40;
													intBoss4HP = intBoss4HP - intShock;
													con.setColor(Color.white);
													con.drawString("- "+intShock, 15, 230);
													Utils.pause(1000);
													con.setColor(Color.black);
													con.drawString("- "+intShock, 15, 230);
													Utils.loadImage("boss4cure3.png",2,114,con);
													Utils.loadImage("boss4cure2.png",2,114,con);
													Utils.pause(100);
													Utils.loadImage("boss4cure1.png",2,114,con);
													Utils.pause(500);
													con.setColor(Color.white); // Sets font back to white after erasing
													intPlayerTurn = 1;
													}else if(chrSkillMagic == '6'){
													
													}else{
														con.setColor(Color.white); 
														con.drawString("Not Enough MP",257,50);	
													}
											}
										}		
									}
								}else if(chrBattle == '3'){ // Defend Option
									intBoss4ATT = intBoss4ATT + 30;
									intBoss4ATT = intBoss4ATT - intGUARD; // Subtracts boss att with guard
									Utils.loadImage("boss4def1.png",2,114,con);
									Utils.pause(500);
									intPlayerTurn = 1;
								}
							}
							// Updates after attacking
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							strHP = intHP + "";
							strMP = intMP + "";
							con.drawString("????",80,75);
							con.drawString("Not Enough MP",257,50);
							if(intBoss4HP < 1000){
							con.drawString(strBoss4HP,80,75);
							strBoss4HP = intBoss4HP + "";
							con.setColor(Color.white);	
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss4HP,80,75);
							con.drawString("????",80,100); // BOSS MP is still ???? always
							}else{
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString("????",80,75);
							}
							
							if(intBoss4HP > 0){ // Only if the boss isn't dead then run his turn
							//ENEMY TURN
							while(intEnemyTurn == 0){		
								//Boss Attacks	
								if(chrBattle == '3'){ // If player is defending do this
									if(intBoss4ATT < 0){
									intBoss4ATT = 0;	
									}
									Utils.loadImage("boss4def1.png",2,114,con);
									Utils.pause(200);
									Utils.loadImage("boss4def2.png",2,114,con);
									Utils.pause(400);
									Utils.loadImage("boss4def3.png",2,114,con);
									Utils.loadImage("boss4def4.png",2,114,con);
									Effects = Utils.startMusic("Block.wav");
									intHP = intHP - intBoss4ATT;
									con.drawString("- "+intBoss4ATT, 325, 230);
									intMP = intMP + 10;
									if(intMP > 110){
										intMP = 110;	
									}
									Utils.pause(500);
									intEnemyTurn = 1;
								}else{ // any other option do this
								if(intBoss4HP > 1000){
									Utils.loadImage("boss4attacks1.png",2,114,con); // As long as boss's health is over 1000, it'll continue doing this attack
									Utils.loadImage("boss4attacks2.png",2,114,con);
									Utils.loadImage("boss4attacks3.png",2,114,con);
									Utils.pause(500);
									Utils.loadImage("boss4attacks4.png",2,114,con);
									Utils.loadImage("boss4attacks5.png",2,114,con);
									Effects = Utils.startMusic("boss4attack.wav");
									intHP = intHP - intBoss4ATT;
									con.drawString("- "+intBoss4ATT, 325, 230);
									Utils.pause(500);
									intEnemyTurn = 1;
								}else if(intBoss4HP <= 1000 && intBoss4HP >= 500){ // When hp is in between 1000 and 500, then he will do this attack
									Utils.loadImage("boss4mpattacks1.png",2,114,con);
									Utils.loadImage("boss4mpattacks2.png",2,114,con);
									Utils.pause(500);
									Utils.loadImage("boss4mpattacks3.png",2,114,con);
									Utils.loadImage("boss4mpattacks4.png",2,114,con);
									Utils.loadImage("boss4mpattacks5.png",2,114,con);
									Effects = Utils.startMusic("boss4attack.wav");
									intBoss4ATT = (int)(Math.random()*(intBoss4ATT + 20) + 10);
									intHP = intHP - intBoss4ATT;
									con.drawString("- "+intBoss4ATT, 325, 230);
									Utils.pause(500);
									intEnemyTurn = 1;
								}else if(intBoss4HP < 500){ // When boss hp is less than 500, he uses his final attacks
									Utils.loadImage("boss4mp2attacks1.png",2,114,con);
									Utils.loadImage("boss4mp2attacks2.png",2,114,con);
									Utils.pause(500);
									Utils.loadImage("boss4mp2attacks3.png",2,114,con);
									Utils.loadImage("boss4mp2attacks4.png",2,114,con);
									Utils.loadImage("boss4mp2attacks5.png",2,114,con);
									Effects = Utils.startMusic("boss4attack.wav");
									intBoss4ATT = (int)(Math.random()*(intBoss4ATT + 30) + 10);
									intHP = intHP - intBoss4ATT;
									con.drawString("- "+intBoss4ATT, 325, 230);
									Utils.pause(500);
									intEnemyTurn = 1;
								}
								}
							}
						}
					}
				//ENDING CINEMATIC WITH LAST BOSS
					if(intBoss4HP <= 0){
						Utils.loadImage("boss4die1.png",0,0,con); 
						Utils.pause(500);
						Utils.loadImage("boss4die2.png",0,0,con);
						Effects = Utils.startMusic("boss4attack.wav");
						Utils.pause(500);
						Utils.loadImage("boss4die3.png",0,0,con);
						Effects = Utils.startMusic("boss4attack.wav");
						Utils.pause(500);
						Utils.loadImage("boss4die4.png",0,0,con);
						Effects = Utils.startMusic("boss4attack.wav");
						Utils.pause(200);
						Utils.loadImage("boss4die5.png",0,0,con);
						Effects = Utils.startMusic("beam.wav");
						Utils.pause(200);
						Utils.loadImage("boss4die6.png",0,0,con);
						Effects = Utils.startMusic("beam.wav");
						Utils.pause(200);
						Utils.loadImage("boss4die7.png",0,0,con);
						Effects = Utils.startMusic("beam.wav");
						Effects = Utils.startMusic("wallhit.wav");
						Utils.pause(200);
						Utils.loadImage("boss4die8.png",0,0,con);
						Utils.pause(1500);
						Effects = Utils.startMusic("dash.wav");
						Utils.loadImage("boss4die9.png",0,0,con);
						Utils.pause(100);
						Utils.loadImage("boss4die10.png",0,0,con);
						Utils.loadImage("attackmenufinal.png",0,299,con); // Final battle menu
						
					while(blnboss[3] == false){ // While boss isn't dead, the menu will continue looping.
						chrFinalMenu = 0;
						chrFinalSkillMenu = 0;
						chrFinalMenu = con.getChar();
						
						if(chrFinalMenu == '1'){
							Utils.loadImage("attackmenuitem.png",0,299,con);
							chrFinalSkillMenu = con.getChar();
								if(chrFinalSkillMenu == '1'){
									Utils.loadImage("boss4die10.png",0,0,con);
									Speech = Utils.startMusic("CloudTalkBoss3.wav");
									Utils.pause(1000);
									Utils.loadImage("boss4die11.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die12.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die13.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die14.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die15.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die16.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die11.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die12.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die13.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die14.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die15.png",0,0,con);
									Effects = Utils.startMusic("omnislash.wav");
									Utils.loadImage("boss4die16.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die17.png",0,0,con);
									Utils.pause(200);
									Utils.loadImage("boss4die18.png",0,0,con);
									Utils.pause(200);
									Effects = Utils.startMusic("boss3die.wav");
									Utils.stopMusic(Player);
									Speech = Utils.startMusic("CloudTalkFinal.wav");
									Utils.pause(2500);
									Effects = Utils.startMusic("explosion.wav");
									Utils.loadImage("boss4die19.png",0,0,con);
									Utils.loadImage("boss4die20.png",0,0,con);
									Utils.loadImage("boss4die19.png",0,0,con);
									Utils.loadImage("boss4die20.png",0,0,con);
									Utils.loadImage("boss4die19.png",0,0,con);
									Utils.loadImage("boss4die20.png",0,0,con);
									Utils.loadImage("boss4die21.png",0,0,con);
									Utils.pause(3000);
									blnboss[3] = true;
								}
							}
						}
						intMP = 0; // uses all mp for last attack
						intHP = 1; // got hit to critical levels.
						Utils.loadImage("boss4win.png",0,0,con);
						Utils.stopMusic(Player);
						Player = Utils.startMusic("Win.wav");
						chrBack = con.getChar();
						Utils.loadImage("bossicon4d.png",135,250,con2); // makes boss gray in the HUD - boss defeated
						Utils.stopMusic(Player);
						Player = Utils.startMusic("Game2.wav");
						//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
						//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
					}
					
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo4") && blnboss[3] == true){
					
				}
					
			 	Utils.loadImage("herof.png",intX, intY,con); // walking forwards
				Utils.prevImage(strPos, strMap, intCurrentRow, intCurrentCol, con, intX, intY);
			}
			//Hero moves backwards (parts repeat from here!)
			if(chrHeroMoves == ('s')){
				strPos = "";
				strPos = "backwards";
				intY = intY + 20;
				intCurrentRow = intCurrentRow + 1;
				if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("t")){
					intY = intY - 20;
					intCurrentRow = intCurrentRow - 1;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w1") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w2") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w3") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w4") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w5") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w6")){
					intY = intY - 20;
					intCurrentRow = intCurrentRow - 1;
					intHP = 0;
					intDiebyW = intDiebyW + 1;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b1") && intbuilding[0] == 0 && intbuilding[0] <= 1){
					intbuilding[0] = intbuilding[0] + 1;
					intHP = 50;
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b2") && intbuilding[1] == 0){
					intbuilding[1] = intbuilding[1] + 1;
					intHP = 100;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b3") && intbuilding[2] == 0){
					intbuilding[2] = intbuilding[2] + 1;
					intHP = 150;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b4") && intbuilding[3] == 0){
					intbuilding[3] = intbuilding[3] + 1;
					intHP = 200;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e1")){
					intHP = intHP - 10;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e2")){
					intHP = intHP - 20;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e3")){
					intHP = intHP - 30;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e4")){
					intHP = intHP - 40;
				//For chests 1-4 (From 1-3 gives 50 to the max health) Chest 4 gives special item (NoTE: ONE CHEST IN EACH DIRECTION. Player can only enter each chest from one side.) - saves space
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("c3") && blnchest[2] == false){
					Utils.loadImage("cod1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("cod2.png",0,0,con);
					Utils.loadImage("cod3.png",0,0,con);
					Effects = Utils.startMusic("chestopen.wav");
					Utils.pause(1000);
					Utils.loadImage("chestitemsword.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("chestitembuster.wav");
					chrBack = con.getChar();
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
					intHP = 200;
					intMP = 110;
					intATT = intATT + 10;
					blnchest[2] = true;
					Utils.loadImage("chest3r.png",95,320,con2);
					Utils.loadImage("divine.png",320,340,con2);
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("v")){
					intWin = intWin + 1;
					
				//HiddenItems
				//All the first section's hidden chests are in the "down" direction therefore, it only appears in this if command
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sw1")){
					if(blnHiddenItem[0] == false){ // Each hidden item takes on place in the bln array
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemsword.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar(); //Used to get back to world map
					intSword = intSword + 1;
					intATT = intATT + 2;
					blnHiddenItem[0] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("wa1")){
					if(blnHiddenItem[6] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemwand.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intWand = intWand + 1;
					intMAG = intMAG + 2;
					blnHiddenItem[6] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sh1")){
					if(blnHiddenItem[3] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemshield.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intShield = intShield + 1;
					intDEF = intDEF + 5;
					blnHiddenItem[3] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game.wav");
					}
		//SPECIAL HIDDEN ITEM
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sp")){
					if(blnSpecialItem == false){
					Utils.stopMusic(Player);
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenspecialitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("specialitem.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("chestitembuster.wav");
					chrBack = con.getChar();
					intATT = intATT + 10;
					intMAG = intMAG + 10;
					blnSpecialItem = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
					}
					
	//SECOND BOSS BATTLE SYSTEM!!!! (only appears in "s" key since the boss is on the down side only - no point in wasting more space)
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo2") && blnboss[1] == false){
					intY = intY - 20;
					intCurrentRow = intCurrentRow - 1;
					Utils.stopMusic(Player);
					Utils.loadImage("bossicon2.png",20,120,con);
					Utils.loadImage("boss2appear1.png",0,0,con);
					Utils.pause(500);
					Effects = Utils.startMusic("boss2die.wav");
					Utils.loadImage("boss2appear2.png",0,0,con);
					Utils.pause(1000);
					con.clear();
					Utils.stopMusic(Player);
					Utils.stopMusic(Speech);
					Speech = Utils.startMusic("CloudTalk3.wav");
					Player = Utils.startMusic("Boss2.wav");
					Utils.loadImage("boss2battle.png",0,0,con);
					Utils.pause(100);
					Utils.loadImage("boss2battle2.png",0,0,con);
					Effects = Utils.startMusic("bustersword.wav");
					Utils.pause(100);
					con.setFont(new Font("Verdana", 50, 15));
					while(intBoss2HP > 0 && intHP > 0){ // while boss isn't defeated
					chrBattle = 0;
					intPlayerTurn = 0;
					intEnemyTurn = 0;
					Utils.loadImage("boss2battleM.png",0,0,con);
						//BEFORE BATTLE
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss2HP,80,75);
							con.drawString(strBoss2MP,80,100);
							con.drawString("+ "+intCure, 325, 230);
							con.drawString("- "+intFira, 20, 230);
							con.drawString("- "+intBoss2ATT, 325, 230);
							con.drawString("- "+intDMG, 20, 230);
							con.drawString("Not Enough MP",257,50);
							//Initializes the string variables to make them into the int values
							strHP = intHP + "";
							strMP = intMP + "";
							strBoss2HP = intBoss2HP + "";
							strBoss2MP = intBoss2MP + "";
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss2HP,80,75);
							con.drawString(strBoss2MP,80,100);
							intGUARD = (int)(Math.random()*(intDEF*3) + 5); // Used for Battle - Randomizes from 5 - Def pt x 3 
							intDMG = (int)(Math.random()*(intATT*3) + 5); // Used for Battle - Randomizes from 5 - Attack pt x3
							intBoss2ATT = (int)(Math.random()*30 + 1); // Boss ATTACK
							intCure = (int)(Math.random()*(intMAG*3) + 10); // Cure Skill randomizes from 10 - magic pt x 3
							intFira = (int)(Math.random()*(intMAG*3) + 10); // Fire SKill randomizes from 10 - magic pt x3
						//BATTLE TIME!
							//PLAYER TURN
							while(intPlayerTurn == 0){
								Utils.loadImage("attackmenu.png",0,299,con);
								chrBattle = con.getChar(); // reads player command
								if(chrBattle == '1'){ // Attack Option
									Utils.loadImage("boss2sword.png",1,115,con);
									Utils.loadImage("boss2sword1.png",1,115,con);
									Effects = Utils.startMusic("dash.wav");
									Utils.pause(500);
									Utils.loadImage("boss2sword2.png",1,115,con);
									Utils.loadImage("boss2sword3.png",1,115,con);
									Effects = Utils.startMusic("slash.wav");
									intBoss2HP = intBoss2HP - intDMG;
									con.drawString("- "+intDMG, 20, 230);
									Utils.pause(500);
									Utils.loadImage("boss2sword4.png",1,115,con);
									Utils.pause(200);
									Utils.loadImage("boss2attacks1.png",1,115,con); // original position
									Utils.pause(500);
									intPlayerTurn = 1;
								
								}else if(chrBattle == '2'){ // Skill Option
									Utils.loadImage("Skill1.png",0,299,con);
									chrSkillMenu = 0;
									chrSkillMenu = con.getChar();
									if(chrSkillMenu == '1'){
										Utils.loadImage("noSkill.png",0,299,con);	
										chrSkillSword = con.getChar();
									}else if(chrSkillMenu == '2'){
										Utils.loadImage("Skill.png",0,299,con);
										chrSkillMagic = con.getChar();
										if(chrSkillMagic == '1'){ // FIRA skill only if there is enough MP
											if(intMP >= 20){
											Utils.loadImage("boss2cure1.png",1,115,con); // uses same animation for CURE therefore same pic name
											Utils.pause(100);
											Utils.loadImage("boss2cure2.png",1,115,con);
											Utils.pause(100);
											Utils.loadImage("boss2cure3.png",1,115,con);
											Speech = Utils.startMusic("CloudFira.wav");
											Utils.pause(500);
											Utils.loadImage("boss2fire1.png",1,115,con);
											Utils.loadImage("boss2fire2.png",1,115,con);
											Utils.loadImage("boss2fire3.png",1,115,con);
											Utils.loadImage("boss2fire4.png",1,115,con);
											Utils.loadImage("boss2fire5.png",1,115,con);
											intMP = intMP - 20; // USES 20 MP for spell
											Effects = Utils.startMusic("fira.wav");
											intBoss2HP = intBoss2HP - intFira;
											con.setColor(Color.white);
											con.drawString("- "+intFira, 20, 230);
											Utils.pause(1000);
											con.setColor(Color.black);
											con.drawString("- "+intFira, 20, 230);
											Utils.loadImage("boss2cure3.png",1,115,con);
											Utils.loadImage("boss2cure2.png",1,115,con);
											Utils.pause(100);
											Utils.loadImage("boss2cure1.png",1,115,con);
											Utils.pause(500);
											con.setColor(Color.white);
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '2'){ // CURE skill
											if(intMP > 0){ // ONLY IF THERE IS ENOUGH MP TO USE
											Speech = Utils.startMusic("CloudCure.wav");
											Utils.loadImage("boss2cure1.png",1,115,con);
											Utils.pause(100);
											Utils.loadImage("boss2cure2.png",1,115,con);
											Utils.pause(100);
											Utils.loadImage("boss2cure3.png",1,115,con);
											Utils.pause(500);
											Utils.loadImage("boss2cure4.png",1,115,con);
											Utils.pause(100);
											Utils.loadImage("boss2cure5.png",1,115,con);
											Effects = Utils.startMusic("Cure.wav");
											intMP = intMP - 10;
											intHP = intHP + intCure;
											if(intHP > 100){
											intHP = 100;	
											}
											con.drawString("+ "+intCure, 325, 230);
											Utils.pause(500);
											con.setColor(Color.black);
											con.drawString("+ "+intCure, 325, 230);
											Utils.pause(500);
											Utils.loadImage("boss2cure3.png",1,115,con);
											Utils.loadImage("boss2cure1.png",1,115,con);
											Utils.pause(500);
											con.setColor(Color.white); // Sets font back to white after erasing
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '3'){ // NEXT PAGE 
											Utils.loadImage("noSkill.png",0,299,con);
											chrBack = con.getChar();
										}
									}
								}else if(chrBattle == '3'){ // Defend Option
									intBoss2ATT = intBoss2ATT + 10;
									intBoss2ATT = intBoss2ATT - intGUARD; // Subtracts boss att with guard
									Utils.loadImage("boss2def1.png",1,115,con);
									Utils.pause(500);
									intPlayerTurn = 1;
								}
							}
							// Updates after attacking
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss2HP,80,75);
							con.drawString(strBoss2MP,80,100);
							strHP = intHP + "";
							strMP = intMP + "";
							if(intBoss2HP < 0){
								intBoss2HP = 0;	
							}
							strBoss2HP = intBoss2HP + "";
							strBoss2MP = intBoss2MP + "";
							con.drawString("Not Enough MP",257,50);
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss2HP,80,75);
							con.drawString(strBoss2MP,80,100);
							
							if(intBoss2HP > 0){ // Only if the boss isn't dead then run his turn
							//ENEMY TURN
							while(intEnemyTurn == 0){		
								//Boss Attacks	
								if(chrBattle == '3'){ // If player is defending do this
									if(intBoss2ATT < 0){
									intBoss2ATT = 0;	
									}
									Utils.loadImage("boss2def1.png",1,115,con);
									Utils.loadImage("boss2def2.png",1,115,con);
									Utils.loadImage("boss2def3.png",1,115,con);
									Utils.loadImage("boss2def4.png",1,115,con);
									Effects = Utils.startMusic("Block.wav");
									intHP = intHP - intBoss2ATT;
									con.drawString("- "+intBoss2ATT, 325, 230);
									intMP = intMP + 10;
									if(intMP > 70){
										intMP = 70;	
									}
									Utils.pause(500);
									intEnemyTurn = 1;
								}else{ // any other option do this
									Utils.loadImage("boss2attacks1.png",1,115,con);
									Utils.loadImage("boss2attacks2.png",1,115,con);
									Utils.loadImage("boss2attacks3.png",1,115,con);
									Utils.loadImage("boss2attacks4.png",1,115,con);
									Effects = Utils.startMusic("hit.wav");
									intHP = intHP - intBoss2ATT;
									con.drawString("- "+intBoss2ATT, 325, 230);
									Utils.pause(500);
									intEnemyTurn = 1;
								}
							}
						}
					}
					if(intBoss2HP <= 0){
						Utils.stopMusic(Player);
						Utils.loadImage("boss2die1.png",0,0,con);
						Utils.loadImage("boss2die2.png",0,0,con);
						Utils.loadImage("boss2die3.png",0,0,con);
						Effects = Utils.startMusic("boss2die.wav");
						Utils.pause(500);
						Effects = Utils.startMusic("bossfade.wav");
						Utils.loadImage("boss2die4.png",0,0,con);
						Utils.pause(200);
						Utils.loadImage("boss2die5.png",0,0,con);
						Utils.pause(200);
						Utils.loadImage("boss2die6.png",0,0,con);
						Utils.pause(200);
						Utils.loadImage("boss2die7.png",0,0,con);
						Utils.pause(500);
						Utils.loadImage("boss2win.png",0,0,con);
						Utils.stopMusic(Player);
						Player = Utils.startMusic("BattleWin.wav");
						chrBack = con.getChar();
						blnboss[1] = true;
						Utils.loadImage("bossicon2d.png",55,250,con2); // makes boss gray in the HUD - boss defeated
						Utils.stopMusic(Player);
						Utils.loadImage("newskillthorn.png",0,0,con);
						Effects = Utils.startMusic("newskill.wav"); // GAIN NEW SKILLL!!!!!
						chrBack = con.getChar();
						Player = Utils.startMusic("Game2.wav");
						//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
						//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
					}
					
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo2") && blnboss[1] == true){
					
				}
				
				Utils.loadImage("herob.png",intX, intY,con);
				Utils.prevImage(strPos, strMap, intCurrentRow, intCurrentCol, con, intX, intY);
			}
			//Hero moves to the left
			if(chrHeroMoves == ('a')){
				strPos = "";
				strPos = "left";
				intX = intX - 20;
				intCurrentCol = intCurrentCol - 1;
				if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("t")){
					intX = intX + 20;
					intCurrentCol = intCurrentCol + 1;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w1") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w2") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w3") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w4") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w5") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w6")){
					intX = intX + 20;
					intCurrentRow = intCurrentRow + 1;
					intHP = 0;
					intDiebyW = intDiebyW + 1;
					
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b1") && intbuilding[0] == 0 && intbuilding[0] <= 1){
					intbuilding[0] = intbuilding[0] + 1;
					intHP = 50;
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b2") && intbuilding[1] == 0){
					intbuilding[1] = intbuilding[1] + 1;
					intHP = 100;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b3") && intbuilding[2] == 0){
					intbuilding[2] = intbuilding[2] + 1;
					intHP = 150;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b4") && intbuilding[3] == 0){
					intbuilding[3] = intbuilding[3] + 1;
					intHP = 200;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e1")){
					intHP = intHP - 10;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e2")){
					intHP = intHP - 20;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e3")){
					intHP = intHP - 30;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e4")){
					intHP = intHP - 40;
				//For chests 1-4 (From 1-3 gives 50 to the max health) Chest 4 gives special item
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("c4") && blnchest[3] == false){
					Utils.loadImage("cou1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("cou2.png",0,0,con);
					Utils.loadImage("cou3.png",0,0,con);
					Effects = Utils.startMusic("chestopen.wav");
					Utils.pause(1000);
					Utils.loadImage("chestitembuster.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("chestitembuster.wav");
					chrBack = con.getChar();
					blnchest[3] = true;
					blnallchest = true;
					Utils.loadImage("chest4r.png",135,320,con2);
					Utils.loadImage("buster.png",343,343,con2);
					Utils.loadImage("newskillslam.png",0,0,con);
					Effects = Utils.startMusic("newskill.wav"); // GAIN NEW SKILLL!!!!!
					chrBack = con.getChar();
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
					
	//FIRST BOSS BATTLE SYSTEM!!!! (only appears in "a" key since the boss is on the left side only - no point in wasting more space)
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("bo1") && blnboss[0] == false){
					intX = intX + 20;
					intCurrentCol = intCurrentCol + 1;
					Utils.stopMusic(Player);
					Utils.loadImage("boss1.png",300,60,con);
					Utils.loadImage("boss1appear1.png",0,0,con);
					Utils.pause(500);
					Effects = Utils.startMusic("found.wav");
					Utils.loadImage("boss1appear2.png",0,0,con);
					Utils.pause(1000);
					con.clear();
					Utils.stopMusic(Player);
					Utils.stopMusic(Speech);
					Speech = Utils.startMusic("CloudTalk2.wav");
					Player = Utils.startMusic("Boss1.wav");
					Utils.loadImage("boss1battle.png",0,0,con);
					Utils.pause(100);
					Effects = Utils.startMusic("bustersword.wav");
					Utils.loadImage("boss1battle2.png",0,0,con);
					Utils.pause(100);
					con.setFont(new Font("Verdana", 50, 15));
					while(intBoss1HP > 0 && intHP > 0){ // while boss isn't defeated
					chrBattle = 0;
					intPlayerTurn = 0; //Resets the turns each time
					intEnemyTurn = 0;
					Utils.loadImage("boss1battleM.png",0,0,con);
						//BEFORE BATTLE
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss1HP,80,75);
							con.drawString(strBoss1MP,80,100);
							con.drawString("+ "+intCure, 290, 240);
							con.drawString("- "+intFira, 35, 240);
							con.drawString("- "+intBoss1ATT, 290, 240);
							con.drawString("- "+intDMG, 35, 240);
							con.drawString("Not Enough MP",257,50);
							//Initializes the string variables to make them into the int values
							strHP = intHP + "";
							strMP = intMP + "";
							strBoss1HP = intBoss1HP + "";
							strBoss1MP = intBoss1MP + "";
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss1HP,80,75);
							con.drawString(strBoss1MP,80,100);
							
							intGUARD = (int)(Math.random()*(intDEF*3) + 5); // Used for Battle - Randomizes from 5 - Def pt x 3 
							intDMG = (int)(Math.random()*(intATT*3) + 5); // Used for Battle - Randomizes from 5 - Attack pt x3
							intBoss1ATT = (int)(Math.random()*10 + 1); // Boss ATTACK
							intCure = (int)(Math.random()*(intMAG*3) + 10); // Cure Skill randomizes from 10 - magic pt x 3
							intFira = (int)(Math.random()*(intMAG*3) + 10); // Fire SKill randomizes from 10 - magic pt x3
						//BATTLE TIME!
							//PLAYER TURN
							while(intPlayerTurn == 0){
								Utils.loadImage("attackmenu.png",0,299,con);
								chrBattle = con.getChar(); // reads player command
								if(chrBattle == '1'){ // Attack Option
									Utils.loadImage("boss1sword.png",2,127,con);
									Utils.loadImage("boss1sword1.png",2,127,con);
									Effects = Utils.startMusic("dash.wav");
									Utils.pause(500);
									Utils.loadImage("boss1sword2.png",2,127,con);
									Utils.loadImage("boss1sword3.png",2,127,con);
									Effects = Utils.startMusic("slash.wav");
									intBoss1HP = intBoss1HP - intDMG;
									con.drawString("- "+intDMG, 35, 240);
									Utils.pause(500);
									Utils.loadImage("boss1sword4.png",2,127,con);
									Utils.pause(200);
									Utils.loadImage("boss1attacks1.png",2,127,con); // original position
									Utils.pause(500);
									intPlayerTurn = 1;
									
								}else if(chrBattle == '2'){ // Skill Option
									Utils.loadImage("Skill1.png",0,299,con); // MAIN MENU FOR SKILLS
									chrSkillMenu = 0;
									chrSkillMenu = con.getChar();
									if(chrSkillMenu == '1'){ // SWORD MENU
										Utils.loadImage("noSkill.png",0,299,con);
										chrSkillSword = con.getChar();
									}else if(chrSkillMenu == '2'){ // MAGIC MENU
										Utils.loadImage("Skill.png",0,299,con);
										chrSkillMagic = con.getChar();
										if(chrSkillMagic == '1'){ // FIRA skill only if there is enough MP
											if(intMP >= 20){
											Utils.loadImage("boss1cure1.png",2,127,con); // uses same animation for CURE therefore same pic name
											Utils.pause(100);
											Utils.loadImage("boss1cure2.png",2,127,con);
											Utils.pause(100);
											Utils.loadImage("boss1cure3.png",2,127,con);
											Speech = Utils.startMusic("CloudFira.wav");
											Utils.pause(500);
											Utils.loadImage("boss1fire1.png",2,127,con);
											Utils.loadImage("boss1fire2.png",2,127,con);
											Utils.loadImage("boss1fire3.png",2,127,con);
											Utils.loadImage("boss1fire4.png",2,127,con);
											Utils.loadImage("boss1fire5.png",2,127,con);
											intMP = intMP - 20; // USES 20 MP for spell
											Effects = Utils.startMusic("fira.wav");
											intBoss1HP = intBoss1HP - intFira;
											con.setColor(Color.white);
											con.drawString("- "+intFira, 35, 240);
											Utils.pause(1000);
											con.setColor(Color.black);
											con.drawString("- "+intFira, 35, 240);
											Utils.loadImage("boss1cure3.png",2,127,con);
											Utils.loadImage("boss1cure2.png",2,127,con);
											Utils.pause(100);
											Utils.loadImage("boss1cure1.png",2,127,con);
											Utils.pause(500);
											con.setColor(Color.white);
											intPlayerTurn = 1;
											}else{
												con.drawString("Not Enough MP",257,50);
											}
										}else if(chrSkillMagic == '2'){ // CURE skill
											if(intMP > 0){ // ONLY IF THERE IS ENOUGH MP TO USE
											Speech = Utils.startMusic("CloudCure.wav");
											Utils.loadImage("boss1cure1.png",2,127,con);
											Utils.pause(100);
											Utils.loadImage("boss1cure2.png",2,127,con);
											Utils.pause(100);
											Utils.loadImage("boss1cure3.png",2,127,con);
											Utils.pause(500);
											Utils.loadImage("boss1cure4.png",2,127,con);
											Utils.pause(100);
											Utils.loadImage("boss1cure5.png",2,127,con);
											Effects = Utils.startMusic("Cure.wav");
											intMP = intMP - 10;
											intHP = intHP + intCure;
											if(intHP > 50){ // Makes it so that the player does not heal over 50 - max health during this boss
											intHP = 50;	
											}
											con.drawString("+ "+intCure, 290, 240);
											Utils.pause(500);
											con.setColor(Color.black);
											con.drawString("+ "+intCure, 290, 240);
											Utils.pause(500);
											Utils.loadImage("boss1cure3.png",2,127,con);
											Utils.loadImage("boss1cure1.png",2,127,con);
											Utils.pause(500);
											con.setColor(Color.white); // Sets font back to white after erasing
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50); // IF THERE ISN"T ENOUGH MP
											}
										}else if(chrSkillMagic == '3'){ // Go to empty next page then menu
											Utils.loadImage("noSkill.png",0,299,con);
											chrBack = con.getChar();
										}
									}
								}else if(chrBattle == '3'){ // Defend Option
									intBoss1ATT = intBoss1ATT - intGUARD; // Subtracts boss att with guard
									Utils.loadImage("boss1def1.png",2,127,con);
									Utils.pause(500);
									intPlayerTurn = 1;
								}
							}
							// Updates after attacking
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss1HP,80,75);
							con.drawString(strBoss1MP,80,100);
							strHP = intHP + "";
							strMP = intMP + "";
							if(intBoss1HP < 0){ //So that boss's hp doesn't go negative
								intBoss1HP = 0;	
							}
							strBoss1HP = intBoss1HP + "";
							strBoss1MP = intBoss1MP + "";
							con.drawString("Not Enough MP",257,50);
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss1HP,80,75);
							con.drawString(strBoss1MP,80,100);
							
							if(intBoss1HP > 0){ // Only if the boss isn't dead then run his turn
							//ENEMY TURN
							while(intEnemyTurn == 0){		
								//Boss Attacks	
								if(chrBattle == '3'){ // If player is defending do this
									if(intBoss1ATT < 0){
									intBoss1ATT = 0;	
									}
								Utils.loadImage("boss1def1.png",2,127,con);
									Utils.pause(200);
									Utils.loadImage("boss1def2.png",2,127,con);
									Utils.pause(400);
									Utils.loadImage("boss1def3.png",2,127,con);
									Utils.loadImage("boss1def4.png",2,127,con);
									Effects = Utils.startMusic("Block.wav");
									intHP = intHP - intBoss1ATT;
									con.drawString("- "+intBoss1ATT, 290, 240);
									intMP = intMP + 10;
									if(intMP > 50){
									intMP = 50;	
									}
									Utils.pause(500);
									intEnemyTurn = 1;
								}else{ // any other option do this
									Utils.loadImage("boss1attacks1.png",2,127,con);
									Utils.pause(200);
									Utils.loadImage("boss1attacks2.png",2,127,con);
									Utils.pause(200);
									Utils.loadImage("boss1attacks3.png",2,127,con);
									Effects = Utils.startMusic("hit.wav");
									intHP = intHP - intBoss1ATT;
									con.drawString("- "+intBoss1ATT, 290, 240);
									Utils.pause(500);
									intEnemyTurn = 1;
								}
							}
						}
					}
					if(intBoss1HP <= 0){
						Utils.stopMusic(Player);
						Utils.loadImage("boss1die1.png",0,0,con);
						Utils.loadImage("boss1die2.png",0,0,con);
						Utils.pause(1000);
						Effects = Utils.startMusic("bossfade.wav");
						Utils.loadImage("boss1die3.png",0,0,con);
						Utils.loadImage("boss1die4.png",0,0,con);
						Utils.loadImage("boss1die5.png",0,0,con);
						Utils.loadImage("boss1die6.png",0,0,con);
						Utils.pause(500);
						Speech = Utils.startMusic("CloudTalkBoss1.wav");
						Utils.pause(1500);
						Utils.loadImage("boss1win.png",0,0,con);
						Player = Utils.startMusic("BattleWin.wav");
						chrBack = con.getChar();
						blnboss[0] = true;
						Utils.loadImage("bossicon1d.png",15,250,con2); // makes boss gray in the HUD - boss defeated
						Utils.stopMusic(Player);
						Player = Utils.startMusic("Game.wav");
						//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
						//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
					}
					
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("bo1") && blnboss[0] == true){
					
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("v")){
					intWin = intWin + 1;
				}
					
				Utils.loadImage("herol.png",intX, intY,con);
				Utils.prevImage(strPos, strMap, intCurrentRow, intCurrentCol, con, intX, intY);
			}
			//Hero moves to the right
			if(chrHeroMoves == ('d')){
				strPos = "";
				strPos = "right";
				intX = intX + 20;
				intCurrentCol = intCurrentCol + 1;
				if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("t")){
					intX = intX - 20;
					intCurrentCol = intCurrentCol - 1;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w1") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w2") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w3") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w4") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w5") || strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("w6")){
					intX = intX - 20;
					intCurrentCol = intCurrentCol - 1;
					intHP = 0;
					intDiebyW = intDiebyW + 1;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b1") && intbuilding[0] == 0 && intbuilding[0] <= 1){
					intbuilding[0] = intbuilding[0] + 1;
					intHP = 50;
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b2") && intbuilding[1] == 0){
					intbuilding[1] = intbuilding[1] + 1;
					intHP = 100;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b3") && intbuilding[2] == 0){
					intbuilding[2] = intbuilding[2] + 1;
					intHP = 150;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b4") && intbuilding[3] == 0){
					intbuilding[3] = intbuilding[3] + 1;
					intHP = 200;
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e1")){
					intHP = intHP - 10;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e2")){
					intHP = intHP - 20;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e3")){
					intHP = intHP - 30;
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("e4")){
					intHP = intHP - 40;
				//Final border that keeps players from reaching vincent
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b") && blnallchest == true){
					
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("b") && blnallchest == false){
					intX = intX - 20;
					intCurrentCol = intCurrentCol - 1;	
				//For chests 1-4 (From 1-3 gives 50 to the max health) Chest 4 gives special item
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("c2") && blnchest[1] == false){
					Utils.loadImage("coi1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("coi2.png",0,0,con);
					Utils.loadImage("coi3.png",0,0,con);
					Effects = Utils.startMusic("chestopen.wav");
					Utils.pause(1000);
					Utils.loadImage("chestitemwand.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("chestitembuster.wav");
					chrBack = con.getChar();	
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("health.png",intCounter2,100,con2);
					}
					intHP = 150;
					intMP = 90;
					intMAG = intMAG + 10;
					blnchest[1] = true;
					Utils.loadImage("chest2r.png",55,320,con2);
					Utils.loadImage("chaos.png",340,320,con2);
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("v")){
					intWin = intWin + 1;
				
				}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sw3")){
					if(blnHiddenItem[2] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemsword.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar(); //Used to get back to world map
					intSword = intSword + 1;
					intATT = intATT + 2;
					blnHiddenItem[2] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
					}		
			}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("sh3")){
					if(blnHiddenItem[5] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemshield.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intShield = intShield + 1;
					intDEF = intDEF + 5;
					blnHiddenItem[5] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
					}
			}else if(strMap[intCurrentRow][intCurrentCol].equalsIgnoreCase("wa3")){
					if(blnHiddenItem[8] == false){
					Utils.loadImage("hiddenitem1a1.png",0,0,con);
					Utils.pause(500);
					Utils.loadImage("hiddenitem1a2.png",0,0,con);
					Utils.loadImage("hiddenitem1a3.png",0,0,con);
					Effects = Utils.startMusic("hiddenitem.wav");
					Utils.pause(1000);
					Utils.loadImage("hiddenitemwand.png",0,0,con);
					Utils.stopMusic(Player);
					Effects = Utils.startMusic("item.wav");
					chrBack = con.getChar();
					intWand = intWand + 1;
					intMAG = intMAG + 2;
					blnHiddenItem[8] = true;
					//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
					//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
						Player = Utils.startMusic("Game2.wav");
					}
					
	//THIRD BOSS BATTLE SYSTEM!!!! (only appears in "d" key since the boss is on the right side only - no point in wasting more space)
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo3") && blnboss[2] == false){
					intX = intX - 20;
					intCurrentCol = intCurrentCol - 1;
					Utils.stopMusic(Player);
					Utils.loadImage("bossicon3.png",120,320,con);
					Utils.loadImage("boss3appear1.png",0,0,con);
					Utils.pause(500);
					Effects = Utils.startMusic("bustersword.wav");
					Utils.loadImage("boss3appear2.png",0,0,con);
					Utils.pause(1000);
					con.clear();
					Utils.stopMusic(Player);
					Utils.stopMusic(Speech);
					Speech = Utils.startMusic("CloudTalk4.wav");
					Player = Utils.startMusic("Boss3.wav");
					Utils.loadImage("boss3battle.png",0,0,con);
					Utils.pause(100);
					Effects = Utils.startMusic("bustersword.wav");
					Utils.loadImage("boss3battle2.png",0,0,con);
					Utils.pause(100);
					con.setFont(new Font("Verdana", 50, 15));
					while(intBoss3HP > 0 && intHP > 0){ // while boss isn't defeated
					chrBattle = 0;
					intPlayerTurn = 0;
					intEnemyTurn = 0;
					Utils.loadImage("boss3battleM.png",0,0,con);
					//Utils.loadImage("boss3attacks1.png",0,117,con);
						//BEFORE BATTLE
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss3HP,80,75);
							con.drawString(strBoss3MP,80,100);
							con.drawString("+ "+intCure, 325, 230);
							con.drawString("- "+intFira, 60, 230);
							con.drawString("- "+intThorn, 60, 230);
							con.drawString("- "+intBoss3ATT, 325, 230);
							con.drawString("- "+intDMG, 60, 230);
							con.drawString("Not Enough MP",257,50);
							//Initializes the string variables to make them into the int values
							strHP = intHP + "";
							strMP = intMP + "";
							strBoss3HP = intBoss3HP + "";
							strBoss3MP = intBoss3MP + "";
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss3HP,80,75);
							con.drawString(strBoss3MP,80,100);
							intGUARD = (int)(Math.random()*(intDEF*3) + 5); // Used for Battle - Randomizes from 5 - Def pt x 3 
							intDMG = (int)(Math.random()*(intATT*3) + 5); // Used for Battle - Randomizes from 5 - Attack pt x3
							intBoss3ATT = (int)(Math.random()*40 + 1); // Boss ATTACK
							intCure = (int)(Math.random()*(intMAG*3) + 10); // Cure Skill randomizes from 10 - magic pt x 3
							intFira = (int)(Math.random()*(intMAG*3) + 10); // Fire SKill randomizes from 10 - magic pt x3
							intThorn = (int)(Math.random()*(intMAG*4) + 20);// Thorn skill randomizes from 20 - magic pt x 4
						//BATTLE TIME!
							//PLAYER TURN
							while(intPlayerTurn == 0){
								Utils.loadImage("attackmenu.png",0,299,con);
								chrBattle = con.getChar(); // reads player command
								if(chrBattle == '1'){ // Attack Option
									Utils.loadImage("boss3sword.png",0,117,con);
									Utils.loadImage("boss3sword1.png",0,117,con);
									Effects = Utils.startMusic("dash.wav");
									Utils.pause(500);
									Utils.loadImage("boss3sword2.png",0,117,con);
									Utils.loadImage("boss3sword3.png",0,117,con);
									Effects = Utils.startMusic("slash.wav");
									intBoss3HP = intBoss3HP - intDMG;
									con.drawString("- "+intDMG, 60, 230);
									Utils.pause(500);
									Utils.loadImage("boss3sword4.png",0,117,con);
									Utils.pause(200);
									Utils.loadImage("boss3attacks1.png",0,117,con); // original position
									Utils.pause(500);
									intPlayerTurn = 1;
			
								}else if(chrBattle == '2'){ // Skill Option
									Utils.loadImage("Skill1.png",0,299,con);
									chrSkillMenu = 0;
									chrSkillMenu = con.getChar();
									if(chrSkillMenu == '1'){
										Utils.loadImage("noSkill.png",0,299,con);
										chrSkillSword = con.getChar();
									}else if(chrSkillMenu == '2'){
											Utils.loadImage("Skill.png",0,299,con);
											chrSkillMagic = con.getChar();
										if(chrSkillMagic == '1'){ // FIRA skill only if there is enough MP
											if(intMP >= 20){
											Utils.loadImage("boss3cure1.png",0,117,con); // uses same animation for CURE therefore same pic name
											Utils.pause(100);
											Utils.loadImage("boss3cure2.png",0,117,con);
											Utils.pause(100);
											Utils.loadImage("boss3cure3.png",0,117,con);
											Speech = Utils.startMusic("CloudFira.wav");
											Utils.pause(500);
											Utils.loadImage("boss3fire1.png",0,117,con);
											Utils.loadImage("boss3fire2.png",0,117,con);
											Utils.loadImage("boss3fire3.png",0,117,con);
											Utils.loadImage("boss3fire4.png",0,117,con);
											Utils.loadImage("boss3fire5.png",0,117,con);
											intMP = intMP - 20; // USES 20 MP for spell
											Effects = Utils.startMusic("fira.wav");
											intBoss3HP = intBoss3HP - intFira;
											con.setColor(Color.white);
											con.drawString("- "+intFira, 60, 230);
											Utils.pause(500);
											con.setColor(Color.black);
											con.drawString("- "+intFira, 60, 230);
											Utils.loadImage("boss3cure3.png",0,117,con);
											Utils.loadImage("boss3cure2.png",0,117,con);
											Utils.pause(100);
											Utils.loadImage("boss3cure1.png",0,117,con);
											Utils.pause(500);
											con.setColor(Color.white);
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '2'){ // CURE skill
											if(intMP > 0){ // ONLY IF THERE IS ENOUGH MP TO USE
											Speech = Utils.startMusic("CloudCure.wav");
											Utils.loadImage("boss3cure1.png",0,117,con);
											Utils.pause(100);
											Utils.loadImage("boss3cure2.png",0,117,con);
											Utils.pause(100);
											Utils.loadImage("boss3cure3.png",0,117,con);
											Utils.pause(500);
											Utils.loadImage("boss3cure4.png",0,117,con);
											Utils.pause(100);
											Utils.loadImage("boss3cure5.png",0,117,con);
											Effects = Utils.startMusic("Cure.wav");
											intMP = intMP - 10;
											intHP = intHP + intCure;
											if(intHP > 150){
											intHP = 150;	
											}
											con.drawString("+ "+intCure, 325, 230);
											Utils.pause(500);
											con.setColor(Color.black);
											con.drawString("+ "+intCure, 325, 230);
											Utils.pause(500);
											Utils.loadImage("boss3cure3.png",0,117,con);
											Utils.loadImage("boss3cure1.png",0,117,con);
											Utils.pause(500);
											con.setColor(Color.white); // Sets font back to white after erasing
											intPlayerTurn = 1;
											}else{
											con.drawString("Not Enough MP",257,50);	
											}
										}else if(chrSkillMagic == '3'){ //NEXT PAGE
											chrSkillMagic = 0; // Reset variable
											Utils.loadImage("Skill2.png",0,299,con);
											chrSkillMagic = con.getChar();
											if(intMP >= 30){
											if(chrSkillMagic == '4'){ // THORNN
												Utils.loadImage("boss3cure1.png",0,117,con); // uses same animation for CURE therefore same pic name
												Utils.pause(100);
												Utils.loadImage("boss3cure2.png",0,117,con);
												Utils.pause(100);
												Utils.loadImage("boss3cure3.png",0,117,con);
												Speech = Utils.startMusic("CloudTalkThorn.wav");
												Utils.pause(500);
												Utils.loadImage("boss3thorn1.png",0,117,con);
												Utils.loadImage("boss3thorn2.png",0,117,con);
												Utils.loadImage("boss3thorn3.png",0,117,con);
												Utils.loadImage("boss3thorn4.png",0,117,con);
												Effects = Utils.startMusic("Thorn.wav");
												intMP = intMP - 30;
												intBoss3HP = intBoss3HP - intThorn;
												con.setColor(Color.white);
												con.drawString("- "+intThorn, 60, 230);
												Utils.pause(1000);
												con.setColor(Color.black);
												con.drawString("- "+intThorn, 60, 230);
												Utils.loadImage("boss3cure3.png",0,117,con);
												Utils.loadImage("boss3cure2.png",0,117,con);
												Utils.pause(100);
												Utils.loadImage("boss3cure1.png",0,117,con);
												Utils.pause(500);
												con.setColor(Color.white); // Sets font back to white after erasing
												intPlayerTurn = 1;
												}
												}else if(chrSkillMagic == '6'){
													
												}else{
													con.setColor(Color.white); 
													con.drawString("Not Enough MP",257,50);	
													}
												}
											}
								}else if(chrBattle == '3'){ // Defend Option
									intBoss3ATT = intBoss3ATT + 10;
									intBoss3ATT = intBoss3ATT - intGUARD; // Subtracts boss att with guard
									Utils.loadImage("boss3def1.png",0,117,con);
									Utils.pause(500);
									intPlayerTurn = 1;
								}
							}
							// Updates after attacking
							con.setColor(Color.black); // Erases old numbers
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss3HP,80,75);
							con.drawString(strBoss3MP,80,100);
							strHP = intHP + "";
							strMP = intMP + "";
							if(intBoss3HP < 0){
								intBoss3HP = 0;	
							}
							strBoss3HP = intBoss3HP + "";
							strBoss3MP = intBoss3MP + "";
							con.drawString("Not Enough MP",257,50);
							con.setColor(Color.white); // Puts in the new stats onto the screen
							con.drawString(strHP,310,75);
							con.drawString(strMP,310,100);
							con.drawString(strBoss3HP,80,75);
							con.drawString(strBoss3MP,80,100);
							
							if(intBoss3HP > 0){ // Only if the boss isn't dead then run his turn
							//ENEMY TURN
							while(intEnemyTurn == 0){		
								//Boss Attacks	
								if(chrBattle == '3'){ // If player is defending do this
									if(intBoss3ATT < 0){
									intBoss3ATT = 0;	
									}
									Utils.loadImage("boss3def1.png",0,117,con);
									Utils.loadImage("boss3def2.png",0,117,con);
									Utils.loadImage("boss3def3.png",0,117,con);
									Utils.loadImage("boss3def4.png",0,117,con);
									Effects = Utils.startMusic("Block.wav");
									intHP = intHP - intBoss3ATT;
									con.drawString("- "+intBoss3ATT, 325, 230);
									intMP = intMP + 10;
									if(intMP > 90){
										intMP = 90;	
									}
									Utils.pause(500);
									intEnemyTurn = 1;
								}else if(intBoss3HP < 400){ //When boss reaches less than 500 hp
									if(intBoss3MP > 0){ // as long as boss still has mp it'll keep using skill
										Utils.loadImage("boss3mana1.png",0,117,con);
										Utils.loadImage("boss3mana2.png",0,117,con);
										Utils.loadImage("boss3mana3.png",0,117,con);// CHARGING FOR BIG ATTACK
										Utils.pause(400);
										Effects = Utils.startMusic("thunder.wav");
										Utils.loadImage("boss3mana4.png",0,117,con);
										Utils.loadImage("boss3mana5.png",0,117,con);
										intBoss3MP = intBoss3MP - 20;
										intBoss3ATT = (int)(Math.random()*(intBoss3ATT + 20) + 20);
										intHP = intHP - intBoss3ATT;
										con.drawString("- "+intBoss3ATT, 325, 230);
										Utils.pause(500);
										intEnemyTurn = 1;
									}else{ // any other option do this
										Utils.loadImage("boss3attacks1.png",0,117,con);
										Utils.loadImage("boss3attacks2.png",0,117,con);
										Utils.pause(200);
										Utils.loadImage("boss3attacks3.png",0,117,con);
										Effects = Utils.startMusic("hit.wav");
										intHP = intHP - intBoss3ATT;
										con.drawString("- "+intBoss3ATT, 325, 230);
										Utils.pause(500);
										intEnemyTurn = 1;
									}
								
								}else{ // any other option do this
									Utils.loadImage("boss3attacks1.png",0,117,con);
									Utils.loadImage("boss3attacks2.png",0,117,con);
									Utils.pause(200);
									Utils.loadImage("boss3attacks3.png",0,117,con);
									Effects = Utils.startMusic("hit.wav");
									intHP = intHP - intBoss3ATT;
									con.drawString("- "+intBoss3ATT, 325, 230);
									Utils.pause(500);
									intEnemyTurn = 1;
								}
							}
						}
					}
					if(intBoss3HP <= 0){
						Utils.stopMusic(Player);
						Effects = Utils.startMusic("bossfade.wav");
						Utils.loadImage("boss3die1.png",0,0,con);
						Utils.pause(500);
						Utils.loadImage("boss3die2.png",0,0,con);
						Utils.pause(200);
						Utils.loadImage("boss3die3.png",0,0,con);
						Speech = Utils.startMusic("CloudTalkBoss3.wav");
						Utils.pause(1000);
						Utils.loadImage("boss3die4.png",0,0,con);
						Utils.pause(200);
						Effects = Utils.startMusic("hit.wav");
						Utils.loadImage("boss3die5.png",0,0,con);
						Utils.pause(500);
						Effects = Utils.startMusic("boss3die.wav");
						Utils.loadImage("boss3die6.png",0,0,con);
						Utils.loadImage("boss3die7.png",0,0,con);
						Utils.pause(500);
						Utils.loadImage("boss3win.png",0,0,con);
						Utils.stopMusic(Player);
						Player = Utils.startMusic("BattleWin.wav");
						chrBack = con.getChar();
						blnboss[2] = true;
						Utils.loadImage("bossicon3d.png",95,250,con2); // makes boss gray in the HUD - boss defeated
						Utils.stopMusic(Player);
						Utils.loadImage("newskillshock.png",0,0,con);
						Effects = Utils.startMusic("newskill.wav"); // GAIN NEW SKILLL!!!!!
						chrBack = con.getChar();
						Player = Utils.startMusic("Game2.wav");
						//Prints all the images matching the corresponding letter into the console
						Utils.gameload(con);
						//Loads Hero at spawn point
						Utils.loadImage("herof.png",intX,intY,con);
					}
					
				}else if(strMap[intCurrentRow][intCurrentCol].startsWith("bo3") && blnboss[2] == true){
					
				}
						
				Utils.loadImage("heror.png",intX, intY,con);
				Utils.prevImage(strPos, strMap, intCurrentRow, intCurrentCol, con, intX, intY);
			}
			if(chrHeroMoves == (' ')){ // Press h FOR INSTRUCTIONS PAGE
					Utils.stopMusic(Player);
					Player = Utils.startMusic("Instructions.wav");
					Utils.loadImage("instructions.png",0,0,con);
					chrBack = con.getChar();
					Utils.loadImage("instructions2.png",0,0,con);
					chrBack = con.getChar();
					//Prints all the images matching the corresponding letter into the console
					Utils.gameload(con);
					//Loads Hero at spawn point
					Utils.loadImage("herof.png",intX,intY,con);
					Utils.stopMusic(Player);
					if(blnboss[1] == false){
					Player = Utils.startMusic("Game.wav");
					}else if(blnboss[1] == true){
					Player = Utils.startMusic("Game2.wav");	
					}
			}
		}
		//Will show health bar being empty	
			if(intHP <= 0){
				if(blnchest[2] == true){ //Restrictions for health depletion so the screen won't show more empty bars of health then the max health of players
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else if(blnchest[1] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,100,con2);
					}	
				}else if(blnchest[0] == true){
					for(intCounter2 = 25;intCounter2 <= 340;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}else{
					for(intCounter2 = 25;intCounter2 <= 165;intCounter2 = intCounter2 + 35){
					Utils.loadImage("healthe.png",intCounter2,50,con2);
					}	
				}
			}
		//WIN SCREEN
		if(intWin == 1){
			Utils.loadImage("vincenttalk.png",0, 0,con);
			chrBack = con.getChar();
			Utils.stopMusic(Player);
			con.clear();
			Utils.loadImage("win.png",0, 0,con);
			Player = Utils.startMusic("Win.wav");
			chrBack = con.getChar();
			con.close();
		}
		
		//For drowned screen
			con2.setColor(Color.black);
			con2.drawString(strHP,150,30);
			strHP = intHP + "";
			con2.setColor(Color.white);
			con2.drawString(strHP,150,30);
		//DEAD screen
			if(intHP <= 0){
			con.clear();
			Utils.stopMusic(Player);
			Player = Utils.startMusic("Lose.wav");
				if(intDiebyW >= 1){
					Utils.loadImage("drowned.png",0, 0,con); // loads the drowned picture
					Utils.pause(5000);
					con.close();
				}else{
					Utils.loadImage("killed.png",0, 0,con); // loads the killed picture
					Utils.pause(5000);//closes the window so that everything can restart
					con.close();
				}
			}
				}else if(chrMenu == '2'){
					Utils.stopMusic(Player);
					Player = Utils.startMusic("Instructions.wav");
					Utils.loadImage("instructions.png",0,0,con2);
					chrBack = con2.getChar();
					Utils.loadImage("instructions2.png",0,0,con2);
					chrBack = con2.getChar();
					
				}else if(chrMenu == '3'){
					Utils.stopMusic(Player);
					Utils.stopMusic(Effects);
					Utils.stopMusic(Speech);
					System.exit(0); // Closes the program
				}
			}			
		}
	}