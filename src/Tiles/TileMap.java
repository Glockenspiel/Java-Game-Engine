package Tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import framework.Component;
import framework.Game;
import framework.GameObject;
import framework.Vector;
import Components.SpriteSheet;
import Display.Drawer;
import Loaders.ImageLoader;

public class TileMap extends Component {

	private ArrayList<Tile_I> tiles;
	private SpriteSheet sheet;
	private Vector tileSize;
	private ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();

	private static String tileMapPath= "Resources/TileMaps/";
	
	public TileMap(SpriteSheet sheet, String filename, Vector tileSize){
		//read indexes from file
		this.sheet=sheet;
		this.tileSize=tileSize;
		
		tiles= new ArrayList<Tile_I>();
		loadTiles(filename);
	}
	
	//xml loading
	//todo: add comments to this
	private void loadTiles(String filename) {
		File xmlFile = new File(tileMapPath+filename);
		
		if(!xmlFile.exists()){
			return;
		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(xmlFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		NodeList nList = doc.getElementsByTagName("Legend");
		Element legend = (Element) nList.item(0);
		System.out.println(legend.getNodeName());
		
		NodeList tiles = legend.getChildNodes();
		int id, sheetX, sheetY;
		String name;
		boolean collision;
		
		for(int i=0; i<tiles.getLength(); i++){
			//read tiles
			if(tiles.item(i).getNodeName().equals("Tile")){
				System.out.println(tiles.item(i).getNodeName());
				Element tile = (Element) tiles.item(i);
				
				name = getContent(tile,"name");
				id = Integer.parseInt(getContent(tile, "id"));
				sheetX = Integer.parseInt(getContent(tile, "sheetX"));
				sheetY = Integer.parseInt(getContent(tile, "sheetY"));
				collision = getContent(tile, "collision").equalsIgnoreCase("true");
				
				this.tiles.add(new Tile(sheet.getFrame(sheetX, sheetY),tileSize.intX(), tileSize.intY(), id, name, collision));
			}
		
			//read indexes
			else if(tiles.item(i).getNodeName().equalsIgnoreCase("Map")){
				System.out.println("map");
				Element data = (Element) tiles.item(i);
				NodeList rows = data.getElementsByTagName("row");
				
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

	@Override
	public void draw(Drawer g, Vector objPos) {
		Tile_I t;
		for(int y=0; y<indexes.size(); y++){
			for(int x=0;x<indexes.get(y).size(); x++){
				t=getTileByID(indexes.get(y).get(x));
				if(t!=null)
					t.draw(g, x*tileSize.intX(), y*tileSize.intY());
			}
		}
	}
	
	private Tile_I getTileByID(int id){
		for(Tile_I t : tiles){
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
