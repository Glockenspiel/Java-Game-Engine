package Tiles;

import framework.Vector;
import framework.GameObject;
import Components.Animation;
import Components.SpriteSheet;
import Display.Drawer;

public class AnimatedTile implements Tile {
	
	private Vector position;
	private Animation anim;
	private boolean hasCollision;
	
	public AnimatedTile(SpriteSheet spriteSheet, int width, int height, int x, int y, int frameSpeed, boolean hasCollision){
		position = new Vector(x,y);
		anim = new Animation(spriteSheet, width, height, 0,0, frameSpeed);
		this.hasCollision=hasCollision;
	}

	//update and draw tile
	@Override
	public void draw(Drawer g, int x, int y) {
		anim.update(new GameObject("null"));
		anim.draw(g, position);
	}

	@Override
	public boolean hasCollision() {
		return hasCollision;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
