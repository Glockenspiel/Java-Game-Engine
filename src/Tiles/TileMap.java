package Tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import framework.Component;
import framework.GameObject;
import framework.Vector;
import Components.SpriteSheet;
import Display.Drawer;
import Loaders.TileFactory;

public class TileMap extends Component {

	ArrayList<Tile> tiles;
	SpriteSheet sheet;
	
	public TileMap(SpriteSheet sheet, String filename){
		//read 
		this.sheet=sheet;
		tiles = TileFactory.loadAndGetTiles(sheet, filename);
	}
	
	public void addTile(Tile t){
		tiles.add(t);
	}
	
	@Override
	public String getType() {
		return "TileMap";
	}

	@Override
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {
		for (Tile t : tiles)
			t.draw(g);
	}
	
	public BufferedImage getSpriteFromSheet(int x, int y){
		return sheet.getFrame(x, y);
	}

}
