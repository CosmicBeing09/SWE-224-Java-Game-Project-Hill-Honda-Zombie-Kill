/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tiles;

import java.awt.image.BufferedImage;
/**
 *
 * @author Raihan
 */
public class TileController {
	private BufferedImage image;
	private int type;
	
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public TileController(BufferedImage image, int type)
	{
		this.image = image;
		this.type = type;
	}
	public BufferedImage getImage(){return image;}
	public int getType(){return type;}
}
