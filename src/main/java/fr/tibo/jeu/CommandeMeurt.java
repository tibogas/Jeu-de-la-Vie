package fr.tibo.jeu;

/**
 * Commande  chargée d'appliquer l'état "Mort" à une cellule.
 * * @author Thibaut Gasnier
 */
public class CommandeMeurt extends Commande {

    public CommandeMeurt(Cellule cellule) {
        super(cellule);
    }

    @Override
    public void executer() {
        cellule.meurt();
    }
}