package Tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;
import Components.SpriteSheet;
import Display.Drawer;
import Loaders.ImageLoader;
import Loaders.TileFactory;

public class TileMap extends Component {

	private ArrayList<Tile> tiles;
	private SpriteSheet sheet;
	private Vector mapSize;
	private Vector tileSize;
	private int [][] indexes = new int[][]{	{0,1,0,0},
											{0,0,1,0},
											{0,3,0,2},
											{0,0,0,2}};
	
	public TileMap(SpriteSheet sheet, String filename, Vector tileSize){
		//read indexes from file
		this.sheet=sheet;
		this.tileSize=tileSize;
		
		tiles= new ArrayList<Tile>();
		
		//turn each image in sprite sheet to a tile
		int id;
		for(int y=0; y<sheet.gridHeight(); y++)
		for(int x=0; x<sheet.gridWidth(); x++){
			id = y*sheet.gridWidth() + x;
			tiles.add(new Tile2(sheet.getFrame(x, y), tileSize.intX(),tileSize.intY(), id, false));
		}
	}
	
	@Override
	public String getType() {
		return "TileMap";
	}

	@Override
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {
		Tile t;
		for(int y=0; y<indexes.length; y++){
			for(int x=0; x<indexes[y].length; x++){
				t = getTileByID(indexes[y][x]);
				if(t!=null)
					t.draw(g, x*tileSize.intX(), y*tileSize.intY());
			}
		}
	}
	
	private Tile getTileByID(int id){
		for(Tile t : tiles){
			if(t.getID()==id)
				return t;
		}
		Game.print().log("Warning tile map id not found: " + id);
		return null;
	}
	
	public BufferedImage getSpriteFromSheet(int x, int y){
		return sheet.getFrame(x, y);
	}

}
