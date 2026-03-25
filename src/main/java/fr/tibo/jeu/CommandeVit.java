package fr.tibo.jeu;

/**
 * Commande  chargée d'appliquer l'état "Vivant" à une cellule.
 * * @author Thibaut Gasnier
 */
public class CommandeVit extends Commande{
    
    public CommandeVit (Cellule cellule){
        super(cellule);
    }

    @Override
    public void executer(){
        cellule.vit();
    }
}
