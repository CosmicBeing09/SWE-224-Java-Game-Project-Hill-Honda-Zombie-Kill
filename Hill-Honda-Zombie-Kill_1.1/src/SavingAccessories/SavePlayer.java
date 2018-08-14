/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SavingAccessories;

import java.io.Serializable;

/**
 *
 * @author Raihan
 */
public class SavePlayer implements Serializable{
  public int currentState;
    public int playerX;
    public int playerY;
    public int health;
    public int life;
    public int score;
    public int ammo;
    
    public SavePlayer(int currentState, int playerX, int playerY,int health,int life,int score,int ammo)
    {
      this.currentState = currentState;
      this.playerX = playerX;
      this.playerY = playerY;
      this.health = health;
      this.life = life;
      this.ammo = ammo;
      this.score = score;
    }  
}
