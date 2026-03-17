package fr.tibo.jeu;

import java.awt.Graphics;
import javax.swing.JFrame;

public class JeuDeLaVieUI extends JFrame implements Observateur {
    
    private JeuDeLaVie jeu;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        setSize(jeu.getXMax() * 10, jeu.getYMax() * 10); // Taille minimale
        setVisible(true);
    }

    public void actualise() {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(int x = 0; x < jeu.getXMax(); x++) {
            for(int y = 0; y < jeu.getYMax(); y++) {
                if (jeu.getGrilleXY(x,y).estVivante()) {
                    g.fillOval(x * 10, y * 10, 10, 10);
                }
            }
        }
    }
}