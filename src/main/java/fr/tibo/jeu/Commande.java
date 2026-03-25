package fr.tibo.jeu;

/**
 * Classe abstraite définissant le Design Pattern Commande
 * Ce pattern permet d'encapsuler une action (naissance ou mort d'une cellule) pour retarder son exécution à la fin du calcul d'une génération.
 * * @author Thibaut Gasnier
 */
public abstract class Commande {
    
    protected Cellule cellule;

    public Commande(Cellule cellule) {
        this.cellule = cellule;
    }

    public abstract void executer();
}
