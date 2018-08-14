/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Levels;

import Tiles.TileAccessories;
import Tiles.Background;
import Zombies.Bat;
import Zombies.ZombieBoss;
import Zombies.Zombie;
import Accessories.Player;
import Accessories.Enemy;
import Accessories.ScoreBoard;
import Accessories.WritingClass;
import Accessories.Explosion;
import Main.GameBoard;
import Accessories.Object;
import Bonuces.ExtraHeart;
import Music.AudioPlayer;
import Bonuces.ExtraAmmo;
import static Levels.GameControlManager.score;
import static Levels.GameControlManager.loadGame;
import SavingAccessories.SaveEnemy;
import SavingAccessories.SavePlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Raihan
 */

public class Level3Class extends GameController {
	
	private AudioPlayer bgMusic;
	private AudioPlayer item;
	private TileAccessories tileMap;
	private Background bg;
	Random rand = new Random();
	
	private static Player player;
	private boolean deathScreen, gameOver, levelStart, messagePlayed,ammoOrheart;
	private long deathScreenTimer, levelStartTimerDiff, levelStartTimer = 0;
	private long deathScreenDelay = 2000;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private ArrayList<Object> objects;
	private ArrayList<WritingClass> texts;
         private ArrayList<SaveEnemy> se;
	private ArrayList<SavePlayer> sp;
	
	private ScoreBoard hud;
	
	public Level3Class(GameControlManager gsm) {
		this.gsm = gsm;
		init();
		
	}
	
	public void init() {
		
		levelStart = true;
		tileMap = new TileAccessories(30);
		tileMap.loadTiles("/Tilesets/tile1.gif");
		tileMap.loadMap("/Maps/level3-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.07);
		
		
		
		bg = new Background("/Backgrounds/hb8.png", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(10, 80);
		player.setLevel(3);
		
		
            try {
                populateEnemies();
            } catch (IOException ex) {
                Logger.getLogger(Level3Class.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Level3Class.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		explosions = new ArrayList<Explosion>();
		objects = new ArrayList<Object>();
		texts = new ArrayList<WritingClass>();
		
		hud = new ScoreBoard(player);
		
		bgMusic = new AudioPlayer("/Music/02-overworld.mp3");
		item = new AudioPlayer("/SFX/item.mp3");
		if(!player.getMute())
		{
		bgMusic.loop();
		}
		
	}
	
        private void loadSave() throws FileNotFoundException, IOException, ClassNotFoundException
        {
               se = new ArrayList<SaveEnemy>();
               sp = new ArrayList<>();
               
               File fin = new File("Enemy.txt");
               File fin2 = new File("Player.txt");
	      FileInputStream fis = new FileInputStream(fin);
               ObjectInputStream oos = new ObjectInputStream(fis);
               se = (ArrayList<SaveEnemy>) oos.readObject();
               oos.close();
               fis.close();
                FileInputStream fis2 = new FileInputStream(fin2);
               ObjectInputStream oos2 = new ObjectInputStream(fis2);
               sp = (ArrayList<SavePlayer>) oos2.readObject();
               oos2.close();
               fis2.close();
                score = sp.get(0).score;
              player.setPosition(sp.get(0).playerX, sp.get(0).playerY);
              player.setFire(sp.get(0).ammo);
              player.setHealth(sp.get(0).health);
              player.setLives(sp.get(0).life);
               //System.out.println(sp.get(0).playerX+ " "+ sp.get(0).playerY+" "+sp.get(0).currentState+" "+sp.get(0).ammo+" "+sp.get(0).health+" ");
                
        }
        
	private void populateEnemies() throws IOException, FileNotFoundException, ClassNotFoundException {
		
		enemies = new ArrayList<Enemy>();
		
                if(loadGame){
                   loadSave();
                  for(int i=0;i<se.size();i++)
                  {
                      if(se.get(i).enemyType == 1)
                      {
                          Zombie s = new Zombie(tileMap);
                          s.setPosition(se.get(i).enemyX, se.get(i).enemyY);
                          enemies.add(s);
                      }
                      else if(se.get(i).enemyType == 2)
                      {
                          Bat sp = new Bat(tileMap, se.get(i).enemyX, 0);
                          sp.setPosition(se.get(i).enemyX, se.get(i).enemyY);
                          enemies.add(sp);
                      }
                       else if(se.get(i).enemyType == 3)
                      {
                          ZombieBoss bo = new ZombieBoss(tileMap);
                          bo.setPosition(se.get(i).enemyX, se.get(i).enemyY);
                          enemies.add(bo);
                      }
                     
                  }
               }
                else{
		Zombie s;
		Point[] points = new Point[] {
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(200)+1500, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			new Point(rand.nextInt(150)+1760, 180),
			
		};
		for(int i = 0; i < points.length; i++) {
			s = new Zombie(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		Bat spider;
		Point[] spiderpoints = new Point[] {
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(1350)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75)),
			new Point(rand.nextInt(2500)+160, rand.nextInt(75))
			
		};
		for(int i = 0; i < spiderpoints.length; i++) {
			spider = new Bat(tileMap, spiderpoints[i].x, 00);
			spider.setPosition(spiderpoints[i].x, spiderpoints[i].y);
			enemies.add(spider);
		}
		ZombieBoss boss;
		Point[] bosspoints = new Point[]
				{
					new Point(rand.nextInt(150)+2150, 120),
					new Point(rand.nextInt(150)+2150, 120),
					new Point(rand.nextInt(150)+2150, 120)
					
				};
		for(int i = 0; i < bosspoints.length; i++) {
			boss = new ZombieBoss(tileMap);
			boss.setPosition(bosspoints[i].x, bosspoints[i].y);
			enemies.add(boss);
		}
		
	
                }	
	}
        
         private void saveState() throws FileNotFoundException, IOException
        {
           
            se = new ArrayList<SaveEnemy>();
            
            System.out.println(enemies.size());
            for (int i=0;i<enemies.size();i++)
                {
                   se.add(new SaveEnemy(enemies.get(i).getEnemyType(),enemies.get(i).getx(),enemies.get(i).gety()));
                }
            
            File fout = new File("Enemy.txt");
           if(!fout.exists())
               fout.createNewFile();
	       FileOutputStream fos = new FileOutputStream(fout);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(se);
               oos.close();
               fos.close();
              // FileOutputStream fos2 = new FileOutputStream(fout2);
              // ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
             //  oos2.writeObject(sp);
             //  oos2.close();
              // fos2.close();
            
        }
        public void savePlayerState() throws FileNotFoundException, IOException
        {
            sp = new ArrayList<SavePlayer>();
            sp.add(new SavePlayer(3, player.getx(), player.gety(), player.getHealth(), player.getLives(), score, player.getFire()));
            File fout2 = new File("Player.txt");
            if(!fout2.exists())
               fout2.createNewFile();
            FileOutputStream fos2 = new FileOutputStream(fout2);
               ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
               oos2.writeObject(sp);
               oos2.close();
               fos2.close();
        }
        
	public void setDeathScreen(boolean b)
	{
		deathScreen = b;
	}
	public void update() {
		
		if(levelStartTimer == 0&& levelStart)
		{

			levelStartTimer = System.nanoTime();

		}
		else
		{

			levelStartTimerDiff = (System.nanoTime() - levelStartTimer)/1000000;
			if(levelStartTimerDiff > 5000)
			{

				
				levelStartTimerDiff = 0;
				levelStart = false;
				
				
				
				

				
			}
		}
		if(player.getx()<= 0)
		
		{
			player.setPosition(0, 80);
		}
	
		if(player.getx()>tileMap.getWidth()-player.getWidth() && enemies.size() ==0)
		{
			
			bgMusic.stop();
                        System.exit(0);
		}
		if(player.getx()>tileMap.getWidth()-player.getWidth() && enemies.size() != 0 && !messagePlayed)
		{
			JOptionPane.showMessageDialog(null, "You must kill all of the enemies before advancing to the next level!");
			messagePlayed = true;
		}
		
		
		if(player.gety()>= tileMap.getHeight()-player.getHeight()){
			player.kill();
		}
		
		if(player.gety() < 0)
		{
			player.setPosition(player.getx(), 0);
		}
		if(player.isDead())
		{
			player.setPosition(0, 80);
			deathScreen = true;
			deathScreenTimer = System.nanoTime();
			player.loseLife();
			player.setHealth(5);
		}
		if (player.getLives() == 0)
		{
			gameOver = true;
		}
		
	
		
		
		player.update();
		tileMap.setPosition(GameBoard.WIDTH / 2 - player.getx(),
			GameBoard.HEIGHT / 2 - player.gety()
		);
	
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		
		player.checkAttack(enemies);
		player.checkObjects(objects);
		
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
                                  if(e.getEnemyType()==1)
                                score+=20;
                            else if(e.getEnemyType() == 2)
                                score+=50;
                            else if(e.getEnemyType() == 3)
                                score+=100;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
				 int r = rand.nextInt(30);
				if(r<10)
				{
				createExtraAmmo(e.getx(),e.gety());
                                ammoOrheart = false;
				}
                                else if(r>10 && r<21)
                                {
                                  createExtraHeart(e.getx(),e.gety());
                                  ammoOrheart = true;
                                }
			}
		}
		
		for(int i = 0; i<objects.size(); i++){
			Object o = objects.get(i);
			o.update();
			if(o.isDead())
			{
				
				objects.remove(i);
				i--;
                                if(ammoOrheart){
				if(player.getHealth()<player.getMaxHealth())
				{
				
				player.increaseHealth(1);
				texts.add(new WritingClass(GameBoard.WIDTH/2,player.gety(),3000, "+20% Health!"));
				item.play();
				}
				else
				{
					texts.add(new WritingClass(GameBoard.WIDTH/2,player.gety(),3000, "Already Max Health!"));
				}
                                }
                                else if(!ammoOrheart)
                                {
                                   if(player.getFire()<player.getMaxFire())
				{
				
				player.increaseAmmo(5);
				texts.add(new WritingClass(GameBoard.WIDTH/2,player.gety(),3000, "+5 Ammo!"));
				item.play();
				}
				else
				{
					texts.add(new WritingClass(GameBoard.WIDTH/2,player.gety(),3000, "Already Max Ammo!"));
				} 
                                }
				
			}
			
			
		}
		
				for(int i = 0; i<texts.size(); i++)
				{
					WritingClass t = texts.get(i);
					if(texts.get(i).update())
					{
						texts.remove(i);
						i--;
					}
				}
		
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
	}
	public static Player getPlayer()
	{
		return player;
	}
	private void createExtraHeart(int x, int y)
	{
		
		ExtraHeart e = new ExtraHeart(tileMap);
		e.setPosition(x,y);
		objects.add(e);
		
	}
         private void createExtraAmmo(int x, int y)
	{
		
		ExtraAmmo ea = new ExtraAmmo(tileMap);
		ea.setPosition(x,y);
		objects.add(ea);
		
	}
	public void draw(Graphics2D g) {
		
	
		
	
		bg.draw(g);
		
		
		tileMap.draw(g);
		
		
		player.draw(g);
		
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		
		for(int i = 0; i<objects.size(); i++)
		{
			objects.get(i).draw(g);
		}
		
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		
		hud.draw(g);
		
		g.setFont((new Font ("Comic Sans MS", Font.PLAIN, 18)));
		String s = "Level 3: The Bat Hub";
		int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
		int alpha = (int) (255 * Math.sin(3.14 * levelStartTimerDiff / 5000));
		if(alpha > 255) alpha = 255;
		g.setColor(new Color(255, 255, 255, alpha));
		int ypos = (int)levelStartTimerDiff/5;
		if (ypos <=GameBoard.HEIGHT/2) g.drawString(s, GameBoard.WIDTH/2 - length/2, ypos);
		else
		{
			g.drawString(s, GameBoard.WIDTH/2 - length/2, GameBoard.HEIGHT/2);
		}
		
				for (int i = 0; i<texts.size();i++)
				{
				texts.get(i).draw(g);
				}
		
		if (deathScreen == true)
		{
				long currentTime = System.nanoTime();
				long elapsed = (currentTime-deathScreenTimer)/1000000;
					if (elapsed >= deathScreenDelay)
					{
						levelStart = true;
						levelStartTimer = 0;
					}
					if(elapsed < deathScreenDelay)
					{
						g.setColor(Color.BLACK);
						g.fillRect(0,0,GameBoard.WIDTH,GameBoard.HEIGHT);
						Font font = new Font("Arial", Font.PLAIN, 14);
						g.setFont(font);
						g.setColor(Color.RED);
							if(!gameOver)
							{
								g.drawString("You Died!", GameBoard.WIDTH/2 - 30, GameBoard.HEIGHT/2);
                                                                player.setHealth(player.getMaxHealth());
                                                                player.setFire(30);
							}
							else if(gameOver)
							{
								g.drawString("GAME OVER", GameBoard.WIDTH/2 - 40, GameBoard.HEIGHT/2);
								bgMusic.stop();
							}

						}
						
						else{
							deathScreen = false;
							if(gameOver)
							{
								
								gsm.setState(GameControlManager.MENUSTATE);	
								gameOver = false;
							}
						}
	
		}
		
		
		
	}

	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setJumping(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_SPACE) player.setFiring();
		if(k == KeyEvent.VK_M && !player.getMute()) {player.setMute(true);bgMusic.stop();}
		else if(k == KeyEvent.VK_M && player.getMute()) {player.setMute(false);bgMusic.loop();}
                   if(k == KeyEvent.VK_S) {
                    
                        
                    try {
                        saveState();
                        savePlayerState();
                        System.exit(0);
                    } catch (IOException ex) {
                        Logger.getLogger(Level1Class.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                    
                    }
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setJumping(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
	}
	
}












