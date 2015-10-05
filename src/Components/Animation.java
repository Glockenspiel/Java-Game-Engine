package Components;

import java.awt.image.BufferedImage;

import Display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class Animation extends Component {

	private SpriteSheet spriteSheet;
	private Vector offset;
	private int frameX = 0, frameY=0;
	private int frameSpeed; //number of game frames until change to next image in animation
	private int frameCount=0;
	/*
	 * width and height are display sizes
	 * offsetX and offsetY are offsets to append to the GameObject origin
	 */
	public Animation(SpriteSheet spriteSheet, int displayW, int displayH, Vector offset, int frameSpeed){
		this.offset=offset;
		this.spriteSheet = spriteSheet;
		this.frameSpeed=frameSpeed;
		spriteSheet.scaleImages(displayW, displayH);
	}
	
	public void setOffset(int offsetX, int offsetY){
		offset = new Vector(offsetX, offsetY);
	}
	
	public Vector getOffset(){
		return offset;
	}
	
	@Override
	public String getType() {
		return "Animation";
	}

	@Override
	public void update(GameObject obj) {
		frameCount++;
		
		//change to next image
		if(frameCount>=frameSpeed){
			frameCount=0;
			
			//decide image to move to in the grid
			//move right
			if(frameX+1 < spriteSheet.gridWidth())
				frameX++;
			
			//move down
			else if(frameX+1 >= spriteSheet.gridWidth()){
				//move down
				if(frameY+1 < spriteSheet.gridHeight()){
					frameY++;
				}
				//reset to 0,0
				else if(frameY+1 >= spriteSheet.gridHeight()){
					frameX=0;
					frameY=0;
				}
			}
		}
		
	}

	@Override
	public void draw(Drawer g, Vector objPos) {
		BufferedImage image = spriteSheet.getFrame(frameX, frameY);
		g.drawImage(image, objPos.intX()+ offset.intX(), objPos.intY() + offset.intY(), image.getWidth(), image.getHeight());
	}

}
