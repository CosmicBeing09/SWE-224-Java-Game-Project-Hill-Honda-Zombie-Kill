/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Accessories;

import Tiles.TileAccessories;
import Music.AudioPlayer;
import Levels.GameControlManager;

import java.util.ArrayList;
import java.util.HashMap;


import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Raihan
 */

public class Player extends MapObject {
	
	private static int fireRegen = 0;
	private static int fireDelay = 100;
	private static int health = 5;
	private static int level;
	private static int maxHealth = 5;
	private static int fire = 30;
	private static int maxFire = 30;
	private static int lives = 3;
	private static int maxLives = 3;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	private double maxJumpHeight;
	
	
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;
	

	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	private static boolean mute;
	
	private boolean gliding;
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5
	};
	
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	private HashMap<String, AudioPlayer> sfx;
	public Player(TileAccessories tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		
		
		
		facingRight = true;
		

		
		
		fireCost = 1;
		fireBallDamage = 5;
		
		fireBalls = new ArrayList<FireBall>();
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/player4.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != SCRATCHING) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		sfx = new HashMap<String,AudioPlayer>();
		sfx.put("jump",new AudioPlayer("/SFX/bikeJump.mp3"));
		sfx.put("scratch",new AudioPlayer("/SFX/scratch.mp3"));
		sfx.put("fire",new AudioPlayer("/SFX/gun.mp3"));
		sfx.put("explosion",new AudioPlayer("/SFX/explosion.mp3"));
	}
	
	public boolean getFalling(){return falling;}
	public int getHealth() { return health; }
	public int getLives() { return lives; }
	public int getMaxLives(){return maxLives;}
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getMaxFire() { return maxFire; }
	public void setFire(int fire){this.fire = fire;}
	public boolean isDead(){return dead;}
	public void setLevel(int level)
	{this.level = level;}
	public void setHealth(int health){this.health = health;}
	public int getLevel() { return level;}
	public void setMaxFire(int maxFire){this.maxFire = maxFire;fire = maxFire;}
	public void setLives(int lives){this.lives = lives;}
	public void increaseHealth(int health){this.health += health;}
        public void increaseAmmo(int ammo){this.fire+=ammo;}
	public void setFireDelay(int fireDelay){this.fireDelay = fireDelay;}
	public void setFireRegen(int fireRegen){this.fireRegen = fireRegen;}
	public int getFireDelay(){return fireDelay;}
	public void kill(){dead = true;}
        public int getScore()
        {
            return GameControlManager.score;
          
        }
	
	
	public void setFiring() { 
		firing = true;
	}
	public void setScratching() {
		scratching = true;
	}
	public void setGliding(boolean b) { 
		gliding = b;
	}
	public void setMute(boolean mute)
	{
		this.mute = mute;
	}
	public boolean getMute()
	{
		return mute;
	}
	
	public void checkAttack(ArrayList<Enemy> enemies) {
		
		
		for(int i = 0; i < enemies.size(); i++) {
			
			Enemy e = enemies.get(i);
			
			if(scratching) {
				
				if(facingRight) {
					if(
						e.getx() > x &&
						e.getx() < x + scratchRange && 
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
				else {
					if(
						e.getx() < x &&
						e.getx() > x - scratchRange &&
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
			}
			
			
			for(int j = 0; j < fireBalls.size(); j++) {
				if(fireBalls.get(j).intersects(e)) {
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();
					break;
				}
			}
			
			
			if(intersects(e)) {
				
				
				if(e.getBounce()&&falling&&dy>0)
				{
					e.hit(5);
					bounce();
				}
				else
				{
					hit(e.getDamage());
				}
				
			
			}
			
		}
		
	}
	public void checkObjects(ArrayList<Object> objects)
	{

	for(int i = 0; i < objects.size(); i++) {
		
		Object o = objects.get(i);
		
		if(intersects(o)) {
				if(o.getBounce()&&falling&&dy>0&& y < o.gety()-3)
				{
					bounce();
				}
				else if(!o.getBounce())
				{
					o.kill();
				}
		}
		
	}
	
}
	
	public void hit(int damage) {
		if(flinching) return;
		else
		{
		health -= damage;
		if(facingRight)dx=-3;
		if(!facingRight)dx=+3;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
		}
	}
	public void bounce()
	{
		setJumping(true); numJumps = 1;
	}
	public void loseLife()
	{
		lives--;
		health = 5;
	}
	private void getNextPosition() {
		
		
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
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		
		if(
		(currentAction == SCRATCHING || currentAction == FIREBALL) &&
		!(jumping || falling)) {
			dx = 0;
		}
		
		
		if(jumping && numJumps <1 && dy >=0) {
			sfx.get("jump").play();
			dy = jumpStart;
			numJumps++;
			falling = true;
			
			
			
		}

		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() {
		
		if(health!= 0)
		{dead = false;}
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) scratching = false;
		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()) firing = false;
		}
		
		
		fire += fireRegen;
		if(fire > maxFire) fire = maxFire;
		if(firing && currentAction != FIREBALL) {
			if(fire > fireCost) {
				sfx.get("fire").play();
				
				fire -= fireCost;
				FireBall fb = new FireBall(tileMap, facingRight);
				fb.setPosition(x, y);
				fireBalls.add(fb);
			}
		}
		
		
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) {
				fireBalls.remove(i);
				i--;
			}
		}
		
	
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}
		
	
		if(scratching) {
			if(currentAction != SCRATCHING) {
				sfx.get("scratch").play();
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if(firing) {
			if(currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(fireDelay);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
		
		// set direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
	
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}
	
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

















