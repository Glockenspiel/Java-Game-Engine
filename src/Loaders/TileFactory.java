package Loaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.Game;
import Components.SpriteSheet;
import Tiles.BasicTile;
import Tiles.Tile;

public class TileFactory {

	private static  String [] formats = new String[]{"xml"};

	public static ArrayList<Tile> loadAndGetTiles(SpriteSheet sheet, String filename) {
		String format = Format.getFormat(filename);
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		if(checkValidFormat(format)){
			//read file
			//first iteration:read gird
			//2nd iteration interface for reading 
		
		}
		//todo: read file and decide on which tiles to use
		
		//tiles.add(new BasicTile(image, 0,0,64,64,false));
		//add tiles here
		
		
		return tiles;
	}
	
	private static boolean checkValidFormat(String format){
		for(int i=0; i<formats.length; i++)
			if(formats[i].equalsIgnoreCase(format))
				return true;
		
		return false;
	}
}
