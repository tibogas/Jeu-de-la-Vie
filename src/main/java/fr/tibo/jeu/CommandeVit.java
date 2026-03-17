package fr.tibo.jeu;

public class CommandeVit extends Commande{
    
    public CommandeVit (Cellule cellule){
        super(cellule);
    }

    @Override
    public void executer(){
        cellule.vit();
    }
}
