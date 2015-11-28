package levelloading;

import java.util.ArrayList;
import java.util.HashMap;

import loaders.XmlLoader;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import framework.Game;
import framework.GameObject;

public class LevelConstructor {
	
	private static HashMap<Integer,Constructable> sharedObjs = new HashMap<Integer,Constructable>();
	
	public static void construct(String fileName){
		sharedObjs.clear();
		String path = "Resources/Levels/";
		NodeList nodeList = XmlLoader.load(path+fileName, "Level");
		
		if(nodeList==null){
			return;
		}
		
		Element elements = (Element) nodeList.item(0);
		
		NodeList sharedObjNodes = elements.getElementsByTagName("sharedObj");
		for(int q=0; q<sharedObjNodes.getLength(); q++){
			Element sharedObjElement = (Element) sharedObjNodes.item(q);
			
			//get params
			NodeList paramNodes = sharedObjElement.getElementsByTagName("param");
			
			String [] sharedObjArgs = new String[paramNodes.getLength()];
			
			for(int t=0; t<paramNodes.getLength(); t++)
				sharedObjArgs[t] = paramNodes.item(t).getTextContent();
			
			//get the class name
			NodeList sharedObjclassNodes = sharedObjElement.getElementsByTagName("obj_class");
			String sharedObj_class_name = sharedObjclassNodes.item(0).getTextContent();
			
			NodeList idNodes = sharedObjElement.getElementsByTagName("id");
			int sharedObjID = -1;
			if(idNodes.item(0)!=null){
				sharedObjID = Cast.toInt(idNodes.item(0).getTextContent());
			}
			
			sharedObjs.put(sharedObjID,ObjConstructor.loadObject(sharedObjArgs, sharedObj_class_name));
		}
		
		
		
		NodeList objNodes = elements.getElementsByTagName("object");
		
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
					
					//using a shared object
					NodeList objIDNodes = addableEl.getElementsByTagName("objID");
					for(int idNode=0; idNode<objIDNodes.getLength(); idNode++){
						int objID = Cast.toInt(objIDNodes.item(idNode).getTextContent());
					}
					
					
					NodeList valNodes = addableEl.getElementsByTagName("val");
					
					//valNodes to string array
					String [] vals = new String[valNodes.getLength()];
					for(int v=0; v<valNodes.getLength(); v++){
						vals[v]=valNodes.item(v).getTextContent();
					}
					
					
					//get class name
					NodeList classNode = addableEl.getElementsByTagName("type");
					
					
					//construct
					if(classNode.item(0)!=null){
						String className = classNode.item(0).getTextContent();
						ObjConstructor.loadAddableObject(vals, className, obj);
					}
				}
				
				
				
				//add to game
				Game.addGameObject(obj);
		}
	}
	
	public static Constructable getSharedObj(int id){
		return sharedObjs.get(id);
	}
}
