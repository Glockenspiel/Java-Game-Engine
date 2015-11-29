package components;

import java.awt.image.BufferedImage;

import levelloading.Cast;
import levelloading.LevelConstructor;
import misc.Vector;
import display.Drawer;
import framework.Component;
import framework.Game;
import framework.GameObject;

public class Animation extends Component {

	private SpriteSheet spriteSheet;
	private Vector offset;
	private int frameX = 0, frameY=0;
	private int frameSpeed; //number of game frames until change to next image in animation
	private int frameCount=0;
	private Vector displaySize;
	private String name="";
	private int spriteSheetID=-1;
	
	/*
	 * width and height are display sizes
	 * offsetX and offsetY are offsets to append to the GameObject origin
	 */
	public Animation(){}
	
	public Animation(SpriteSheet spriteSheet, int displayW, int displayH, Vector offset, int frameSpeed){
		this.offset=offset;
		this.spriteSheet = spriteSheet;
		this.frameSpeed=frameSpeed;
		displaySize = new Vector(displayW, displayH);
	}
	
	public Animation(int animID, SpriteSheet spriteSheet, int displayW, int displayH, Vector offset, int frameSpeed){
		this.offset=offset;
		this.spriteSheet = spriteSheet;
		this.frameSpeed=frameSpeed;
		displaySize = new Vector(displayW, displayH);
	}
	
	//reset animation to the begining
	public void reset(){
		frameCount=0;
	}
	
	//offset of animation from GameObject position
	public void setOffset(int offsetX, int offsetY){
		offset = new Vector(offsetX, offsetY);
	}
	
	//returns the offset
	public Vector getOffset(){
		return offset;
	}
	
	//returns dimensions of image displayed
	public Vector getDisplaySize(){
		return displaySize;
	}

	//changes to the next frame in the animation if required
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

	//draw the current frame
	@Override
	public void draw(Drawer g, Vector objPos) {
		BufferedImage image = spriteSheet.getFrame(frameX, frameY);
		Vector position = Vector.add(objPos, offset);
		g.drawImage(image, position.intX(), position.intY(), displaySize.intX(), displaySize.intY());
	}

	@Override
	public void construct(String[] args) {
		spriteSheetID = Cast.toInt(args[0]);
		spriteSheet = (SpriteSheet) LevelConstructor.getSharedObj(spriteSheetID);
		displaySize = new Vector(Cast.toInt(args[1]), Cast.toInt(args[2]));
		offset = new Vector(Cast.toFloat(args[3]), Cast.toFloat(args[4]));
		frameSpeed = Cast.toInt(args[5]);
		name = args[6];
	}

	@Override
	public String[] getSaveArgs() {
		String args[] =new String[]{
			String.valueOf(spriteSheetID),
			String.valueOf(displaySize.intX()),
			String.valueOf(displaySize.intY()),
			String.valueOf(offset.getX()),
			String.valueOf(offset.getY()),
			String.valueOf(frameSpeed),
			name
		};
		return args;
	}

	public String getName() {
		return name;
	}
}
