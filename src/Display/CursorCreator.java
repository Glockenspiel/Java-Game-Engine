package display;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import framework.Game;

public class CursorCreator {
	public static Cursor getBlankCursor(){
		return getCustomCursor(null);
	}
	
	public static Cursor getCustomCursor(String imgSrc){
		BufferedImage cursorImg=null;
		if(imgSrc!=null){
			try { 
				File file = new File(imgSrc);
				if(file.exists()==false){
					Game.print().log("File not found for cursor: " + imgSrc);
				}
				cursorImg = javax.imageio.ImageIO.read(file);
				
			} 
			catch (IOException ex){
				Game.print().log("File cuked: " + imgSrc);
					ex.printStackTrace();
			}
		}
		if(cursorImg==null)
			cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "cursor");
		
		return cursor;
	}
}
