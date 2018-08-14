/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tiles;

import Main.GameBoard;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 *
 * @author Raihan
 */
public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GameBoard.WIDTH;
		this.y = (y * moveScale) % GameBoard.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(image,
				(int)x + GameBoard.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(image,
				(int)x - GameBoard.WIDTH,
				(int)y,
				null
			);
		}
	}
	
}

