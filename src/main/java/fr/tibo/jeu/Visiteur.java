package fr.tibo.jeu;

/**
 * Classe abstraite définissant le Design Pattern Visiteur.
 * Elle permet de séparer l'algorithme de la structure des cellules. Il évalue l'état des cellules et génère des commandes.
 * * @author Thibaut Gasnier
 */
public abstract class Visiteur {
    
    protected JeuDeLaVie jeu;

    public Visiteur (JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public abstract void visiteCelluleVivante(Cellule cellule);
    public abstract void visiteCelluleMorte(Cellule cellule);
}
