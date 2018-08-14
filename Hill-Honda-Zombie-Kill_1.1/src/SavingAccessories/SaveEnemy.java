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
public class SaveEnemy implements Serializable{
    public int enemyType;
    public int enemyX;
    public int enemyY;  
    
    public SaveEnemy(int enemyType, int enemyX, int enemyY)
    {
        this.enemyType = enemyType;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }
    
}
