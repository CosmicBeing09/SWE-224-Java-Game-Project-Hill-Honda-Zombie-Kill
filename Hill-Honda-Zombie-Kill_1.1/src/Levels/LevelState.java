/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Levels;

import Music.AudioPlayer;
import Tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author Raihan
 */

public class LevelState extends GameController {
	
	
	private Background bg;
	private AudioPlayer bgMusic;
	private static boolean mute;
	
	private int currentChoice = 0;
	
	private String[] options = {
		"Level 1",
		"Level 2",
		"Level 3",
                "Back",
                
	};
	
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public LevelState(GameControlManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/lb.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gothic",
					Font.PLAIN,
					28);
			
			font = new Font("Arial", Font.PLAIN, 12);
			bgMusic = new AudioPlayer("/Music/menu2.mp3");
			bgMusic.loop();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {
		
	}
	
	public void update() {
		bg.update();
		
		
		
	}
	
	public void draw(Graphics2D g) {
		
	
		bg.draw(g);
		
	
		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("Select Level", 80, 70);
		
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
		
	}
	
	
	private void select() {
		
		
		if(currentChoice == 0) {
			gsm.setState(GameControlManager.LEVEL1STATE);
			bgMusic.stop();
		}
		if(currentChoice == 1) {
			gsm.setState(GameControlManager.LEVEL2STATE);
			bgMusic.stop();
		}
		if(currentChoice == 2) {
			gsm.setState(GameControlManager.LEVEL3STATE);
			bgMusic.stop();
		}
                if(currentChoice == 3)
                {
                    gsm.setState(GameControlManager.MENUSTATE);
                }
             
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		
		if(k == KeyEvent.VK_M && !mute){mute = true; bgMusic.stop();System.out.println(mute);}
		else if(k == KeyEvent.VK_M && mute){mute = false; bgMusic.loop();System.out.println("false");}
		
	}
	public void keyReleased(int k) {}
	public static boolean getMute()
	{
		return mute;
	}
	
}








