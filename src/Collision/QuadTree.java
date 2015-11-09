package collision;

import collision.Rectangle;

import java.awt.Color;
import java.util.ArrayList;

import display.Drawer;
import framework.GameObject;


public class QuadTree {
 
	 private static int MAX_OBJECTS = 5;
	 private static int MAX_LEVELS = 6;
		 
	 private int level;
	 private ArrayList<GameObject> objects;
	 private RectangleI bounds;
	 private QuadTree[] nodes;
		 
	//constructor
	public QuadTree(int level, RectangleI bounds) {
		this.level = level;
		objects = new ArrayList<GameObject>();
		this.bounds = bounds;
		nodes = new QuadTree[4];
	}
	
	//Clears the QuadTree
	public void clear() {
		objects.clear();
	 
	 	for (int i = 0; i < nodes.length; i++) {
	 		if(nodes[i] != null) {
	 			nodes[i].clear();
	 			nodes[i] = null;
	 		}
	 	}
	 }
	
	//insert an ArrayList of GameObjects in to the tree
	public void insertAll(ArrayList<GameObject> objs){
		for(GameObject o : objs)
			insert(o);
	}
	
	//insert a GameObject to the tree
	public void insert(GameObject obj) {
		//return if the obj has no CollisionShapes
		RectangleI rect = obj.getCollisionBounds();
		if(rect==null) 
			return;
		 
		if (nodes[0] != null) {
			int index = getIndex(rect);
		     
			//keep obj in parent node(this node) because it doesn't fit into any of the leave nodes
			if (index != -1) {
				nodes[index].insert(obj);
				return;
			}
		}
		 
		objects.add(obj);
		 
		//check if we exceed the maximum objects and don't exceed the maximum levels
		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			//split if not already split
			if (nodes[0] == null) { 
		    	split(); 
		    }
		 
		    int i = 0;
		    while (i < objects.size()) {
		    	int index = getIndex(objects.get(i).getCollisionBounds());
		    	if (index != -1) {
		    		nodes[index].insert(objects.remove(i));
		    	}
		    	else {
		    		i++;
		    	}
		    }
		}
	}
	
	//split the current node into 4 nodes
	private void split() {
		int subWidth = (int)(bounds.getWidth() / 2);
		int subHeight = (int)(bounds.getHeight() / 2);
		int x = (int)bounds.getX();
		int y = (int)bounds.getY();
		 
		nodes[0] = new QuadTree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new QuadTree(level+1, new Rectangle(x, y, subWidth, subHeight));
		nodes[2] = new QuadTree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new QuadTree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	}
	
	
	/*
	 * Determine which node the object belongs to. -1 means
	 * object cannot completely fit within a child node and is part
	 * of the parent node
	 */
	private int getIndex(RectangleI pRect) {
		if(pRect==null) return -1;
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
	 
		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
	 
		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			}
			else if (bottomQuadrant) {
				index = 2;
			}
	    }
	    // Object can completely fit within the right quadrants
	    else if (pRect.getX() > verticalMidpoint) {
	    	if (topQuadrant) {
	    		index = 0;
	    	}
	    	else if (bottomQuadrant) {
	    		index = 3;
	    	}
	   }
	 
	   return index;
	 }
	
	 
	// Return all objects that could collide with the given object
	public ArrayList<GameObject> retrieve(ArrayList<GameObject> returnObjects, RectangleI objRect) {
		  int index = getIndex(objRect);
		  if (index != -1 && nodes[0] != null) {
			  nodes[index].retrieve(returnObjects, objRect);
		  }
	  
		  returnObjects.addAll(objects);
	  
		  return returnObjects;
	 }

	public void drawBounds(Drawer g) {
		g.drawBox(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), Color.GRAY);

		if(nodes[0]==null)
			return;
		
		for(QuadTree tree : nodes){
			tree.drawBounds(g);
		}
	}
}

