package fr.tibo.jeu;

/**
 * Visiteur implémentant les règles originales du Jeu de la Vie
 * * @author Thibaut Gasnier
 */
public class VisiteurClassique extends Visiteur {
    
    public VisiteurClassique (JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {

        int nbVoisines = cellule.nombreVoisinesVivantes(jeu);

        // cellule vivante meurt si elle a moins de 2 ou plus de 3 voisines
        if (nbVoisines < 2 || nbVoisines > 3) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {

        int nbVoisines = cellule.nombreVoisinesVivantes(jeu);

        // cellule morte revit si elle a 3 voisines
        if (nbVoisines == 3) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }


}
