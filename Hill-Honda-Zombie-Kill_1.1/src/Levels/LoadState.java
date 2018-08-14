/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Levels;

import Music.AudioPlayer;
import Main.GameBoard;
import SavingAccessories.SavePlayer;
import Tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raihan
 */

public class LoadState extends GameController {

    private Background bg;
    private AudioPlayer bgMusic;
    private static boolean mute;
private Font font;
    public LoadState(GameControlManager gsm) {

        this.gsm = gsm;

        try {

            bg = new Background("/Backgrounds/menu1.png", 1);
            bg.setVector(-0.1, 0);

            bgMusic = new AudioPlayer("/Music/menu.mp3");
            bgMusic.loop();
        } catch (Exception e) {
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
               font = new Font("Arial", Font.PLAIN, 12);
               g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Press Enter", 70, 80);
                g.drawString("When You Are Ready", 70, 100);
                
                
                g.setFont(font);
	
			g.setColor(Color.MAGENTA);
			g.drawString("Press B to Back", 10, GameBoard.HEIGHT - 20);

    
    }

    private void select() throws IOException, ClassNotFoundException {

  
        
        loadGame = true;
        File fin2 = new File("Player.txt");
        FileInputStream fis2 = new FileInputStream(fin2);
        ObjectInputStream oos2 = new ObjectInputStream(fis2);
        ArrayList<SavePlayer> sp = (ArrayList<SavePlayer>) oos2.readObject();
        oos2.close();
        fis2.close();
        bgMusic.stop();
        if(sp.get(0).currentState == 1)
        gsm.setState(GameControlManager.LEVEL1STATE);
        if(sp.get(0).currentState == 2)
        gsm.setState(GameControlManager.LEVEL2STATE);
        if(sp.get(0).currentState == 3)
        gsm.setState(GameControlManager.LEVEL3STATE);

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            try {
                select();
            } catch (IOException ex) {
                Logger.getLogger(LoadState.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoadState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(k == KeyEvent.VK_B){
			gsm.setState(GameControlManager.MENUSTATE);
		}
        
        if (k == KeyEvent.VK_M && !mute) {
            mute = true;
            bgMusic.stop();
            System.out.println(mute);
        } else if (k == KeyEvent.VK_M && mute) {
            mute = false;
            bgMusic.loop();
            //System.out.println("false");
        }

    }

    public void keyReleased(int k) {
    }

    public static boolean getMute() {
        return mute;
    }
}
