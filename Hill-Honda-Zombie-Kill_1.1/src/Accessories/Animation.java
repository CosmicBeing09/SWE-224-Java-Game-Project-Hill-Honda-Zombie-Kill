/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package Accessories;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Raihan
 */

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation()
	{
		playedOnce = false;
		
	}
	public void setFrames(BufferedImage[] frames)
	{
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
		
	}
	public void setDelay(long d){delay = d;}
	public void setFrame(int i){currentFrame = i;}
	
	public void update()
	{
		if (delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime)/1000000;
		
		if (elapsed>delay)
		{
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length)
		{
			currentFrame = 0;
			playedOnce = true;
		}
	}
	public int getFrame(){return currentFrame;}
	public BufferedImage getImage(){return frames[currentFrame];}
	public boolean hasPlayedOnce(){return playedOnce;}
}
