/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Zombies;

import Accessories.Enemy;
import Accessories.Animation;
import Tiles.TileAccessories;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 *
 * @author Raihan
 */

public class ZombieBoss extends Enemy {
	
	private BufferedImage[] sprites;
	private Random rand;
	
	public ZombieBoss(TileAccessories tm) {
		
		super(tm);
		rank = 2;
                 enemyType = 3;
		moveSpeed = 1;
		maxSpeed = 2;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		health = maxHealth = 20;
		damage = 1;
		bounce = true;
		
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/zombie1.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(100);
		
		right = true;
		facingRight = false;
		
	}
	
	private void getNextPosition() {
		
		if(health >=1) maxSpeed = (maxHealth/(health));
		
		
		
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
	
		if(falling) {
			dy += fallSpeed;
			dx = 0;
		}
		
	}
	
	public void update() {
		
		
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
	
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
			
		}
		
		
		
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = true;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = false;
		}
		
		
		
		
		
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		
		
		setMapPosition();
		
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		
		super.draw(g);
		
	}
	
}





