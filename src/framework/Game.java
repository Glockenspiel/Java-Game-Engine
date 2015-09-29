package framework;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game {
	
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	
	public Game(){
		//display window
		JFrame f = new JFrame();
		DrawPanel panel = new DrawPanel();
		f.add(panel);
		f.setBounds(100,50,500,300);
		f.setVisible(true);
	}
	
	public void test(){
		/*
		 * this is example usage of the framework
		 * use this area for testing
		*/
		
		//create new GameObjects (Entities)
		objs.add(new GameObject("ground"));
		objs.add(new GameObject("enemy"));
				
		GameObject p = new GameObject("Player");
		p.addComponent(new ExampleComponent()); //add component
		objs.add(p);
				
		//display IDs
		System.out.println("GameObject:ID");
		for(GameObject g : objs){
			System.out.println(g.getTag() + ":" + g.getID());
		}
		System.out.println(); //spacing
			
		
		
		//call init() method in all gameObjects
		for(GameObject g : objs)
			g.init();
				
		//main loop
		boolean flag=true;
		while(flag){
			//get the component and modify the value
			ExampleComponent ex = (ExampleComponent)p.getComponentByType("Example"); 
			ex.setNum(69); 
					
					
			//get all the components of the same time
			//uncomment this to see behaviour of getting a single component above
			ArrayList<Component> comps = new ArrayList<Component>();
			p.getAllComponentsByType("Example", comps);
			for(Component c : comps){
				ExampleComponent a = (ExampleComponent)c;
				a.setNum(100);
			}
					
			for(GameObject g : objs){
				g.update();
			}
					
			//only loop once for testing
			flag=false;
		}
	}
	
	//return a shallow copy of all the GameObjects
	public static ArrayList<GameObject> copyOfGameObjects(){
		return new ArrayList<GameObject>(objs);
	}
}
