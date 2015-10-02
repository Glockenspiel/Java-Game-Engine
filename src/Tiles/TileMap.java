package Tiles;

import java.util.ArrayList;

import framework.Component;
import framework.GameObject;
import framework.Vector;
import Components.SpriteSheet;
import Display.Drawer;
import Loaders.TileFactory;

public class TileMap extends Component {

	ArrayList<Tile> tiles;
	
	public TileMap(SpriteSheet sheet){
		tiles = TileFactory.loadAndGetTiles();
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

}
