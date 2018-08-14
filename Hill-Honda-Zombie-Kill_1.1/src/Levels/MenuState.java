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

public class MenuState extends GameController {
	
	
	private Background bg;
	private AudioPlayer bgMusic;
	private static boolean mute;
	
	private int currentChoice = 0;
	
	private String[] options = {
		"Start",
		"Instruction",
		"Level",
                "LoadGame",
                "Quit"
	};
	
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameControlManager gsm) {
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/hb5.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gothic",
					Font.TRUETYPE_FONT,
					28);
			
			font = new Font("Arial", Font.CENTER_BASELINE, 15);
			bgMusic = new AudioPlayer("/Music/menu.mp3");
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
		
	
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Hill Honda", 70, 50);
                g.drawString("Zombie Kill", 70, 80);
		
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 100, 140 + i * 15);
		}
		
	}
	
	
	private void select() {
		
		
		if(currentChoice == 0) {
			gsm.setState(GameControlManager.LEVEL1STATE);
			bgMusic.stop();
		}
		if(currentChoice == 1) {
			gsm.setState(GameControlManager.HELPSTATE);
			bgMusic.stop();
		}
		if(currentChoice == 4) {
			System.exit(0);
			bgMusic.stop();
		}
                if(currentChoice == 3)
                {
                    gsm.setState(GameControlManager.LOADSTATE);
                    bgMusic.stop();
                }
                if(currentChoice == 2)
                {
                    gsm.setState(GameControlManager.LEVELSTATE);
                    bgMusic.stop();
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








