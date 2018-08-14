/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Accessories;

import Tiles.TileAccessories;

/**
 *
 * @author Raihan
 */

public class Object extends MapObject{
	protected boolean dead, bounce, fire, heart;

	public Object(TileAccessories tm) {
		super(tm);
		
	}
	public void kill(){dead = true;}
	public boolean isDead(){return dead;}
	public boolean getBounce(){return bounce;}
	public boolean isFire(){return fire;}
	public boolean isHeart(){return heart;}
	
	public void update(){}

}
