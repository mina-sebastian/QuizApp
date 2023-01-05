package me.minutz.trv.eng.etc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	
	public BufferedImage loadImage(String str){
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(str));
		} catch (IOException e) {
		}
		return image;
	}

}
