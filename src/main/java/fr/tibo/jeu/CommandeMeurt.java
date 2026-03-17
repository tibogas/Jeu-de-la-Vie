package fr.tibo.jeu;

public class CommandeMeurt extends Commande {

    public CommandeMeurt(Cellule cellule) {
        super(cellule);
    }

    @Override
    public void executer() {
        cellule.meurt();
    }
}