package tiles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import loaders.XmlLoader;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import components.SpriteSheet;

import display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;

public class TileMap extends Component {

	private ArrayList<Tile_I> tiles;
	private SpriteSheet sheet;
	private Vector tileSize;
	private ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();

	private static String tileMapPath= "Resources/TileMaps/";
	
	public TileMap(String filename, int tileW, int tileH){
		tileSize=new Vector(tileW, tileH);
		tiles= new ArrayList<Tile_I>();
		loadTiles(filename);
	}

	private void loadTiles(String filename) {
		NodeList nList = XmlLoader.load(tileMapPath+filename, "Legend");
		
		if(nList==null){
			return;
		}
		
		Element legend = (Element) nList.item(0);
		
		NodeList tiles = legend.getChildNodes();
		int id, sheetX, sheetY;
		String name;
		boolean collision;
		
		for(int i=0; i<tiles.getLength(); i++){
			
			if(tiles.item(i).getNodeName().equals("SpriteSheet")){
				Element e = (Element) tiles.item(i);
				String imgSrc = getContent(e,"image");
				int frameW = Integer.parseInt(getContent(e, "width"));
				int frameH = Integer.parseInt(getContent(e, "height"));
				
				sheet = new SpriteSheet(imgSrc, frameW, frameH);
			}
			
			//read tiles
			if(tiles.item(i).getNodeName().equals("Tile")){
				Element e = (Element) tiles.item(i);
				name = getContent(e,"name");
				id = Integer.parseInt(getContent(e, "id"));
				sheetX = Integer.parseInt(getContent(e, "sheetX"));
				sheetY = Integer.parseInt(getContent(e, "sheetY"));
				collision = getContent(e, "collision").equalsIgnoreCase("true");
				
				this.tiles.add(new Tile(sheet.getFrame(sheetX, sheetY), id, name, collision));
			}

			//read indexes
			else if(tiles.item(i).getNodeName().equalsIgnoreCase("Map")){
				Element e = (Element) tiles.item(i);
				NodeList rows = e.getElementsByTagName("row");
				
				//read row and convert to int
				for(int y=0; y<rows.getLength(); y++){
					String row = rows.item(y).getTextContent();
					String stringIndexes[] = row.split(",");
					
					ArrayList<Integer> newRow = new ArrayList<Integer>();
					for(String s : stringIndexes){
						newRow.add(Integer.parseInt(s));
					}
					this.indexes.add(newRow);
				}
			}
		}
	}
	
	//used for xml loading
	private String getContent(Element tile, String TagName){
		NodeList nList = tile.getElementsByTagName(TagName);
		
		if(nList.item(0)==null)
			return "-1";
		
		
		return nList.item(0).getTextContent();
	}

	@Override
	public String getType() {
		return "TileMap";
	}

	@Override
	public void update(GameObject obj) {}

	//draw all the tiles
	@Override
	public void draw(Drawer g, Vector objPos) {
		Tile_I t;
		for(int y=0; y<indexes.size(); y++){
			for(int x=0;x<indexes.get(y).size(); x++){
				t=getTileByID(indexes.get(y).get(x));
				if(t!=null)
					t.draw(g, objPos.intX()+x*tileSize.intX(), objPos.intY()+y*tileSize.intY(), tileSize.intX(), tileSize.intY());
			}
		}
	}
	
	//get a certain tile by its id
	private Tile_I getTileByID(int id){
		for(Tile_I t : tiles){
			if(t.getID()==id)
				return t;
		}
		Game.print().log("Warning tile map id not found: " + id);
		return null;
	}
	
	//returns a BufferedImage from the spriteSheet at a given grid coordinate
	public BufferedImage getSpriteFromSheet(int x, int y){
		return sheet.getFrame(x, y);
	}

	@Override
	public void interruptThreads() {}

}
