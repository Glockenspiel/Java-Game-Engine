package levels;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import levelloading.ObjConstructor;
import loaders.XmlLoader;
import scripts.StressScript;
import demo.Player;
import framework.Game;
import framework.GameObject;
import framework.Level;

public class Level3 extends Level {

	@Override
	public void init() {
		String filename = "level1.xml";
		String path = "Resources/Levels/";
		NodeList nodeList = XmlLoader.load(path+filename, "Level");
		
		if(nodeList==null){
			return;
		}
		
		Element elements = (Element) nodeList.item(0);
		
		NodeList objNodes = elements.getElementsByTagName("object");
		Game.print("obj count:" + objNodes.getLength());
		
		//load each game object
		for(int i=0; i<objNodes.getLength(); i++){
			
				//get the parameters
				Element objElement = (Element) objNodes.item(i);
				NodeList paramNodes = objElement.getElementsByTagName("param");
				
				String [] objArgs = new String[paramNodes.getLength()];
				
				for(int q=0; q<paramNodes.getLength(); q++)
					objArgs[q] = paramNodes.item(q).getTextContent();
				
				//get the class name
				NodeList objclassNodes = objElement.getElementsByTagName("obj_class");
				String obj_class_name = objclassNodes.item(0).getTextContent();
				
				//create instance
				Class<?> objClass = null;
				try {
					objClass = Class.forName(obj_class_name);
				} catch (ClassNotFoundException e) {e.printStackTrace();} 
				
				GameObject obj = null;
				try {
					obj = (GameObject) objClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				//construct game object
				obj.construct(objArgs);
				
				
				//load each addable object
				NodeList addables = objElement.getElementsByTagName("add");
				for(int a=0; a<addables.getLength(); a++){
					//get parameters
					Element addableEl = (Element) addables.item(a);
					NodeList valNodes = addableEl.getElementsByTagName("val");
					
					String [] vals = new String[valNodes.getLength()];
					for(int v=0; v<valNodes.getLength(); v++){
						vals[v]=valNodes.item(v).getTextContent();
					}
					
					
					//get class name
					NodeList classNode = addableEl.getElementsByTagName("type");
					String className = classNode.item(0).getTextContent();
					
					//construct
					ObjConstructor.loadAddableObject(vals, className, obj);
				}
				
				
				
				//add to game
				Game.addGameObject(obj);
		}
	}
	
	private String getContent(Element tile, String TagName){
		NodeList nList = tile.getElementsByTagName(TagName);
		
		if(nList.item(0)==null)
			return "-1";
		
		
		return nList.item(0).getTextContent();
	}
}
