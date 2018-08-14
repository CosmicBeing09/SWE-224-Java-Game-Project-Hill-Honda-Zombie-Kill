/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;
import javax.swing.JFrame;

/**
 *
 * @author Raihan
 */
public class MainClass {
    
    public static boolean loadGame = false;

	public static void main(String[] args)
	{
		JFrame window = new JFrame("Hill Honda Zombie Kill");
		window.setContentPane(new GameBoard());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
