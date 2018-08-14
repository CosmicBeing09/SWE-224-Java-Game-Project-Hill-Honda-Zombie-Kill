/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Accessories;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Raihan
 */

public class ScoreBoard {

    private Player player;

    private BufferedImage image, lb1, lb2, lb3, lb4, lb5, lc1, lc2, lc3, ab1, ab2, ab3, ab4, ab5, ab6, ab7;
    private Font font, font2, font3;

    public ScoreBoard(Player p) {
        player = p;
        try {
            lb1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/lb1.png"
                    )
            );

            lb2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/lb2.png"
                    )
            );

            lb3 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/lb3.png"
                    )
            );

            lb4 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/lb4.png"
                    )
            );

            lb5 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/lb5.png"
                    )
            );

            lc1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/heart2.png"
                    )
            );

            lc2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/heart3.png"
                    )
            );

            lc3 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/heart4.png"
                    )
            );

            ab1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab1.png"
                    )
            );

            ab2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab2.png"
                    )
            );
            ab3 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab3.png"
                    )
            );

            ab4 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab4.png"
                    )
            );
            ab5 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab5.png"
                    )
            );
            ab6 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab6.png"
                    )
            );
            ab7 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/ScoreBoardPack/ab7.png"
                    )
            );

            font = new Font("Arial", Font.PLAIN, 14);
            font2 = new Font("Arial", Font.PLAIN, 12);
            font3 = new Font("Arial", Font.BOLD, 13);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {

        g.drawImage(image, 0, 10, null);
		g.setFont(font3);
		g.setColor(Color.CYAN);
		g.drawString(
			"SCORE: " + player.getScore(),
			240,
			12
		);
	/*	g.drawString(
				player.getLives() + "/" + player.getMaxLives(),
				53,
				25
			);
		g.setFont(font);
		g.drawString(
			player.getFire() / 100 + "/" + player.getMaxFire() / 100,
			30,
			45
		);*/
       
        if(player.getFire() == 30)
            g.drawImage(ab1, 0, 35, null);
        else if(player.getFire()>=26 && player.getFire()<30)
            g.drawImage(ab2, 0, 35, null);
         else if(player.getFire()>=21 && player.getFire()<26)
            g.drawImage(ab3, 0, 35, null);
         else if(player.getFire()>=15 && player.getFire()<21)
            g.drawImage(ab4, 0, 35, null);
         else if(player.getFire()>=8 && player.getFire()<15)
            g.drawImage(ab5, 0, 35, null);
         else if(player.getFire()>=2 && player.getFire()<8)
            g.drawImage(ab6, 0, 35, null);
         else if(player.getFire() <= 1 )
            g.drawImage(ab7, 0, 35, null);
        
        
        
        
        
        if (player.getLives() == player.getMaxLives()) {
            g.drawImage(lc1, 0, 0, null);
        } else if (player.getLives() == player.getMaxLives() - 1) {
            g.drawImage(lc2, 0, 0, null);
        } else if (player.getLives() == player.getMaxLives() - 2) {
            g.drawImage(lc3, 0, 0, null);
        }

        if (player.getHealth() == player.getMaxHealth()) {
            g.drawImage(lb1, 0, 22, null);
        } else if (player.getHealth() == player.getMaxHealth() - 1) {
            g.drawImage(lb2, 0, 22, null);
        } else if (player.getHealth() == player.getMaxHealth() - 2) {
            g.drawImage(lb3, 0, 22, null);
        } else if (player.getHealth() == player.getMaxHealth() - 3) {
            g.drawImage(lb4, 0, 22, null);
        } else if (player.getHealth() == player.getMaxHealth() - 4) {
            g.drawImage(lb5, 0, 22, null);
        }
        g.setColor(Color.blue);
        g.setFont(font2);
        g.drawString(
                "Level " + player.getLevel(),
                2,
                57
        );

    }

}
