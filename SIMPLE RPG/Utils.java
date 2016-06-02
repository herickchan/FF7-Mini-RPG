import hsa.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import  sun.audio.*;

public class Utils{
	//Loading Map from CSV
	public static String[][] loadMap(String strFileName){
		String strData[][];
		String strReadLine;
		int intRows = 0;
		int intCounter;
		int intLength;
		String strLine = null;
		String strsub[];
	
		BufferedReader infile = new BufferedReader(new InputStreamReader(Utils.class.getResourceAsStream(strFileName)));
		
		strData = new String[20][20];
		try{
			strLine = infile.readLine();
			}catch(IOException e){}
		
		while(strLine != null){
			intLength = strLine.length();
			strsub = strLine.split(",");
			for(intCounter = 0; intCounter < strsub.length; intCounter++){
				strData[intRows][intCounter] = strsub[intCounter];
			}
			intRows++;
			try{
			strLine = infile.readLine();
			}catch(IOException e){}
		}
		try{
		infile.close();
		}catch(IOException e){}
		return strData;
	}

	//Load Images
	public static void loadImage(String strImage, int intX, int intY,  hsa.Console con){
			BufferedImage img = null;
			try {
		      		   img = ImageIO.read(Utils.class.getResource(strImage));
		       		} catch (IOException e) {
		       		}	
		    con.drawImage(img, intX, intY,null);
	}
	
	//Printing images
	public static void gameload(hsa.Console con){
		String strMaps[][];
		String strName;
		int intCounterR; //Counter for Rows
		int intCounterC; //Counter for Columns
		int intX;
		int intY;

			BufferedImage treeimage = null;
			BufferedImage grassimage = null;
			BufferedImage grass2image = null;
			BufferedImage borderimage = null;
			BufferedImage buildingimage = null;
			BufferedImage water1image = null;
			BufferedImage water2image = null;
			BufferedImage water3image = null;
			BufferedImage water4image = null;
			BufferedImage water5image = null;
			BufferedImage water6image = null;
			BufferedImage spawnimage = null;
			BufferedImage treasure1image = null;
			BufferedImage treasure2image = null;
			BufferedImage treasure3image = null;
			BufferedImage treasure4image = null;
			BufferedImage bo1image = null;
			BufferedImage bo21image = null;
			BufferedImage bo22image = null;
			BufferedImage bo23image = null;
			BufferedImage bo24image = null;
			BufferedImage bo31image = null;
			BufferedImage bo32image = null;
			BufferedImage bo33image = null;
			BufferedImage bo34image = null;
			BufferedImage bo41image = null;
			BufferedImage bo42image = null;
			BufferedImage bo43image = null;
			BufferedImage bo44image = null;
			BufferedImage vincentimage = null;
			BufferedImage swordimage = null;
			BufferedImage shieldimage = null;
			BufferedImage wandimage = null;
			
			try {
		      		   treeimage = ImageIO.read(Utils.class.getResource("trees.png"));
		      		   grassimage = ImageIO.read(Utils.class.getResource("grass.png"));
		      		   grass2image = ImageIO.read(Utils.class.getResource("grass2.png"));
		      		   borderimage = ImageIO.read(Utils.class.getResource("border.png"));
		      		   buildingimage = ImageIO.read(Utils.class.getResource("building.png"));
		      		   water1image = ImageIO.read(Utils.class.getResource("water1.png"));
		      		   water2image = ImageIO.read(Utils.class.getResource("water2.png"));
		      		   water3image = ImageIO.read(Utils.class.getResource("water3.png"));
		      		   water4image = ImageIO.read(Utils.class.getResource("water4.png"));
		      		   water5image = ImageIO.read(Utils.class.getResource("water5.png"));
		      		   water6image = ImageIO.read(Utils.class.getResource("water6.png"));
		      		   spawnimage = ImageIO.read(Utils.class.getResource("spawn.png"));
		      		   treasure1image = ImageIO.read(Utils.class.getResource("treasure1.png"));
		      		   treasure2image = ImageIO.read(Utils.class.getResource("treasure2.png"));
		      		   treasure3image = ImageIO.read(Utils.class.getResource("treasure3.png"));
		      		   treasure4image = ImageIO.read(Utils.class.getResource("treasure4.png"));
		      		   bo1image = ImageIO.read(Utils.class.getResource("boss1.png"));
		      		   bo21image = ImageIO.read(Utils.class.getResource("bo21.png"));
		      		   bo22image = ImageIO.read(Utils.class.getResource("bo22.png"));
		      		   bo23image = ImageIO.read(Utils.class.getResource("bo23.png"));
		      		   bo24image = ImageIO.read(Utils.class.getResource("bo24.png"));
		      		   bo31image = ImageIO.read(Utils.class.getResource("bo31.png"));
		      		   bo32image = ImageIO.read(Utils.class.getResource("bo32.png"));
		      		   bo33image = ImageIO.read(Utils.class.getResource("bo33.png"));
		      		   bo34image = ImageIO.read(Utils.class.getResource("bo34.png"));
		      		   bo41image = ImageIO.read(Utils.class.getResource("bo41.png"));
		      		   bo42image = ImageIO.read(Utils.class.getResource("bo42.png"));
		      		   bo43image = ImageIO.read(Utils.class.getResource("bo43.png"));
		      		   bo44image = ImageIO.read(Utils.class.getResource("bo44.png"));
		      		   vincentimage = ImageIO.read(Utils.class.getResource("vincent.png"));
		       		} catch (IOException e) {
		       		}	
		
		strName = "";
		strMaps = loadMap("map.csv");
		intX = 0;
		intY = 0;
		
		for(intCounterR = 0;intCounterR < strMaps.length;intCounterR++){ //Counts upwards for rows
			for(intCounterC = 0;intCounterC < strMaps[0].length;intCounterC++){ //Counts upwards for columns
				intX = intCounterC*20;
				if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("t")){
					con.drawImage(treeimage, intX, intY,null);
					//loadImage("trees.png", intX, intY, con); // makes the variable a certain name each time to print onto the map
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("g")){
					con.drawImage(grassimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("g2")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("b")){
					con.drawImage(borderimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("b1")){
					con.drawImage(buildingimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("b2")){
					con.drawImage(buildingimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("b3")){
					con.drawImage(buildingimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("b4")){
					con.drawImage(buildingimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w1")){
					con.drawImage(water1image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w2")){
					con.drawImage(water2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w3")){
					con.drawImage(water3image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w4")){
					con.drawImage(water4image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w5")){
					con.drawImage(water5image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("w6")){
					con.drawImage(water6image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("e1")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("e2")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("e3")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("e4")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("s")){
					con.drawImage(spawnimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("c1")){
					con.drawImage(treasure1image, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("c2")){
					con.drawImage(treasure2image, intX, intY,null);			
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("c3")){
					con.drawImage(treasure3image, intX, intY,null);			
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("c4")){
					con.drawImage(treasure4image, intX, intY,null);			
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo1")){
					con.drawImage(bo1image, intX, intY,null);		
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo21")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo22")){
					con.drawImage(grass2image, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo23")){
					con.drawImage(grass2image, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo24")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo31")){
					con.drawImage(grass2image, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo32")){
					con.drawImage(grass2image, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo33")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo34")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo41")){
					con.drawImage(grassimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo42")){
					con.drawImage(grassimage, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo43")){
					con.drawImage(grassimage, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("bo44")){
					con.drawImage(grassimage, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].equalsIgnoreCase("v")){
					con.drawImage(vincentimage, intX, intY,null);	
				}else if(strMaps[intCounterR][intCounterC].startsWith("sw")){ // startsWith Command Makes it so that anything starting with this will be printed with grass image - sasves space + time
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].startsWith("sh")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].startsWith("wa")){
					con.drawImage(grass2image, intX, intY,null);
				}else if(strMaps[intCounterR][intCounterC].startsWith("sp")){
					con.drawImage(grass2image, intX, intY,null);		
			}
		}	
		intY = intY + 20;
	}
}
	//Prints previoius images on map to erase the image of the hero left behind
		public static void prevImage(String strPos, String strMap[][], int intRow, int intCol, hsa.Console con, int intX, int intY){
		String strName = "";
			if(strPos.equalsIgnoreCase("forwards")){ // Sees what direction character is going to determine which way the image should print
			intRow = intRow+ 1;
			}else if(strPos.equalsIgnoreCase("backwards")){
			intRow = intRow- 1;
			}else if(strPos.equalsIgnoreCase("left")){
			intCol = intCol+ 1;
			}else if(strPos.equalsIgnoreCase("right")){
			intCol = intCol- 1;
			}
			if(strMap[intRow][intCol].equalsIgnoreCase("t")){
					strName = "trees.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("g")){
					strName = "grass.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("g2")){
					strName = "grass2.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("b")){
					strName = "borderpassed.png";		
			}else if(strMap[intRow][intCol].equalsIgnoreCase("b1")){
					strName = "buildingused.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("b2")){
					strName = "buildingused.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("b3")){
					strName = "buildingused.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("b4")){
					strName = "buildingused.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w1")){
					strName = "water1.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w2")){
					strName = "water2.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w3")){
					strName = "water3.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w4")){
					strName = "water4.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w5")){
					strName = "water5.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("w6")){
					strName = "water6.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("e1")){
					strName = "enemy1.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("e2")){
					strName = "enemy2.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("e3")){
					strName = "enemy3.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("e4")){
					strName = "enemy4.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("s")){
					strName = "spawn.png";
			}else if(strMap[intRow][intCol].equalsIgnoreCase("c1")){
					strName = "treasureO1.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("c2")){
					strName = "treasureO2.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("c3")){
					strName = "treasureO3.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("c4")){
					strName = "treasureO4.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("v")){
					strName = "vincent.png";	
			}else if(strMap[intRow][intCol].equalsIgnoreCase("bo1")){
					strName = "grass.png";
			}else if(strMap[intRow][intCol].startsWith("bo2")){
					strName = "grass.png";
			}else if(strMap[intRow][intCol].startsWith("bo3")){
					strName = "grass.png";
			}else if(strMap[intRow][intCol].startsWith("bo4")){
					strName = "grass.png";
			}else if(strMap[intRow][intCol].startsWith("sw")){
					strName = "treasureO.png";
			}else if(strMap[intRow][intCol].startsWith("sh")){
					strName = "treasureO.png";
			}else if(strMap[intRow][intCol].startsWith("wa")){
					strName = "treasureO.png";
			}else if(strMap[intRow][intCol].startsWith("sp")){
					strName = "treasureO.png";		
			}
			
			if(strPos.equalsIgnoreCase("forwards")){
			intCol = intCol- 1;
			loadImage(strName, intX, intY + 20, con);
			}else if(strPos.equalsIgnoreCase("backwards")){
			intCol = intCol+ 1;
			loadImage(strName, intX, intY - 20, con);
			}else if(strPos.equalsIgnoreCase("left")){
			intRow = intRow- 1;
			loadImage(strName, intX + 20, intY, con);
			}else if(strPos.equalsIgnoreCase("right")){
			intRow = intRow+ 1;
			loadImage(strName, intX - 20, intY, con);
			}	
					
	}
		
	//pause method
	public static void pause(int intMS){
		try{
			Thread.sleep(intMS);
		}catch(InterruptedException e){}	
	}
	
	//Start Music
	public static AudioStream startMusic(String strFileName){
		InputStream Music = null;
		AudioStream Player = null;
		
		try{
		Music = Utils.class.getResourceAsStream(strFileName);
		Player = new AudioStream(Music);
		} catch (IOException e){
		}
		
		AudioPlayer.player.start(Player);
		return Player;
	}
	
	//Stop Music
	public static void stopMusic(AudioStream Player){
		AudioPlayer.player.stop(Player);
	}
	
	//Start Continuous Music
	public static ContinuousAudioDataStream startCMusic(String strFileName){
		InputStream Music = null;
		AudioStream Player = null;
		//AudioData data = as.getData();
		AudioData data = null;
		//ContinuousAudioDataStream cas = new ContinuousAudioDataStream (data);
		ContinuousAudioDataStream cas = null;


		
		try{
		Music = new FileInputStream(strFileName);
		Player = new AudioStream(Music);
		} catch (IOException e){
		}
		
		//AudioPlayer.player.start(Player);
		// CADAWAS Change... instead of starting... will try to put in continous container
		try{
			data = Player.getData();
		}catch(IOException e){
		}
		cas = new ContinuousAudioDataStream (data);
		AudioPlayer.player.start(cas);
		
		return cas;
	}
	
	//Stop ConinuousMusic
	public static void stopCMusic(ContinuousAudioDataStream cas){
		
		AudioPlayer.player.stop(cas);
	}
}
