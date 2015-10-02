package Loaders;

import java.util.ArrayList;

import Components.SpriteSheet;
import Tiles.Tile;

public class TileFactory {


	public static ArrayList<Tile> loadAndGetTiles(SpriteSheet sheet, String filename) {
		String format = Format.getFormat(filename);
		//todo: check if valid format and which way to load files
		
		//todo: read file and decide on which tiles to use
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		//add tiles here
		return tiles;
	}
}
