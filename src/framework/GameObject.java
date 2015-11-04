package framework;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import collision.CollisionListener;
import collision.CollisionShape;
import components.HUDItem;
import display.Drawer;
import display.SwingDrawer;

public class GameObject implements Debug{ // also known as an Entity
	
	//-1 equals invalid ID
	private int id=-1;
	private String tag="";
	private ArrayList<Component> components = new ArrayList<Component>();
	private ArrayList<CollisionShape> collisionShapes = new ArrayList<CollisionShape>();
	private ArrayList<Script> scripts = new ArrayList<Script>();
	private Vector position = new Vector(0,0);
	private boolean isGlobal=false;
	private int drawLayer=0;
	private long deleteOnTime=0;
	
	//constructor
	public GameObject(String tag){
		this.tag=tag;
	}
	
	//get unique id of this gameObject
	public int getID(){
		//if id hasn't been generated yet, generate it
		if(id < 0) 
			id = IdGenerator.generateID();
		
		return id;
	}
	
	//returns false if this this object hasn't generated an id yet
	public boolean hasID(){
		return id>=0;
	}
	
	//adds a Component to the GameObject
	public void add(Component c){
		components.add(c);
	}
	
	//adds a CollisionShape to the GameObject
	public void add(CollisionShape shape){
		collisionShapes.add(shape);
	}
	
	//adds a Script to the GameObject
	public void add(Script s){
		scripts.add(s);
	}
	
	//removes all CollisionShapes with a matching tag
	public void removeCollisionShapesByTag(String tag){
		for(int i=0; i<collisionShapes.size(); i++){
			if(collisionShapes.get(i).getTag().equalsIgnoreCase(tag))
				collisionShapes.remove(i);
		}
	}
	
	//returns first found component with matching type
	public Component getComponentByType(Class cls){
		String className = cls.getName();
		for(Component c : components)
			if(c.getClass().getName().equals(className))
				return c;
		
		return null;
	}
	
	//returns all components with matching type
	public void getAllComponentsByType(Class cls, ArrayList<Component> comps){
		String className = cls.getName();
		for(Component c: components)
			if(c.getClass().getName().equals(className))
				comps.add(c);
	}
	
	//returns all CollisionShapes
	public ArrayList<CollisionShape> getCollisionShapes(){
		return collisionShapes;
	}

	//update all components
	public void update(){

		for(Script s: scripts)
			s.execute(this);
		
		for(Component c : components)
			c.update(this);
		
		if(deleteOnTime>0 && Time.getTime()>deleteOnTime){
			delete();
		}
	}
	
	//draw all components
	public void draw(Drawer g){
		for(Component c : components)
			c.draw(g, this.position);
	}
	
	//return a copy of position to avoid breaking encapsulation
	public Vector getPosition(){
		return new Vector(position.getX(), position.getY());
	}
	
	//moves this GameObject by a given amount
	public void moveBy(Vector amount){
		position.moveBy(amount);
	}
	
	//moves this GameObject to the position given
	public void moveTo(Vector position){
		this.position.moveTo(position);
	}
	
	//get tag of this GameObject
	public String getTag(){
		return tag;
	}
	
	//deletes this game object
	public void delete(){
		Game.deleteObjByID(id);
	}
	
	//deletes this object in a given amount of time (milliseconds)
	public void delete(long milliseconds){
		deleteOnTime=Time.getTime()+milliseconds;
	}

	//draws the debug for this GameObject
	@Override
	public void debugDraw(Drawer g, Vector objPos) {
		//draw origin of game object
		Color lineColor = Color.GREEN;
		int lineLength=20;
		g.drawLine(position.intX()-lineLength/2, position.intY(), lineLength, 0, lineColor);
		g.drawLine(position.intX(), position.intY()-lineLength/2, 0, lineLength, lineColor);
		
		g.drawText(getPosition().toString(), position.intX()+2, position.intY()-5, lineColor);
		g.drawText(getTag(), position.intX()+2, position.intY()-18, lineColor);
		
		//draw debug of all components that are instances of debug
		for(Component c : components)
		if(c instanceof Debug){
            ((Debug) c).debugDraw(g, getPosition());
		}
		
		//draw the debugging for all the CollisionShapes
		for(CollisionShape s : collisionShapes){
			if(s instanceof Debug){
				((Debug) s).debugDraw(g, getPosition());
			}
		}
	}

	//finds which scripts implement HUDItem and draw the HUD
	public void drawHUDItems(SwingDrawer drawer) {
		for(Script s : scripts){
			if(s instanceof HUDItem){
				((HUDItem) s).drawGUI(drawer);
			}
		}
	}

	//updates the CollisionShapes in this GameObject
	public void updateCollisionShapes() {
		for(CollisionShape cs : collisionShapes){
			cs.update(getPosition());
		}
	}

	//notifies any CollisionListeners of CollisionOverlaps this GameObject has had
	public void collisionOverlap(String tag, int id) {
		for(Script s : scripts){
			if(s instanceof CollisionListener){
				CollisionListener cl = (CollisionListener) s;
				cl.onTrigger(tag, id);
			}
		}
	}

	//returns all the Scripts
	public ArrayList<Script> getScripts(){
		return scripts;
	}
	
	//set this GameObjects Global status. global true means this object passes from level to level and doesnt get deleted when the level changes
	public void setIsGlobal(boolean isGlobal){
		this.isGlobal=isGlobal;
	}
	
	//returns true if this GameObject is global
	public boolean getIsGlobal() {
		return isGlobal;
	}
	
	//returns the draw layer
	public int getDrawLayer(){
		return drawLayer;
	}
	
	//sets the draw layer
	public void setDrawLayer(int drawLayer){
		this.drawLayer=drawLayer;
	}

	//returns the all the scripts of event type
	public ArrayList<Event> getEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		for(Script s: scripts){
			if(s instanceof Event){
				events.add((Event)s);
			}
		}
		return events;
	}
}
