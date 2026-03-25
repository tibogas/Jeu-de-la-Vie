package fr.tibo.jeu;
/**
 * Visiteur implémentant les règles Day and Nigth du Jeu de la Vie
 * * @author Thibaut Gasnier
 */
public class VisiteurDayNight extends Visiteur {
    
    public VisiteurDayNight (JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {

        int nbVoisines = cellule.nombreVoisinesVivantes(jeu);

        // cellule vivante meurt si elle a 0,1,2 ou 5 voisines
        if (nbVoisines == 0 || nbVoisines == 1 || nbVoisines == 2 || nbVoisines == 5) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {

        int nbVoisines = cellule.nombreVoisinesVivantes(jeu);

        // cellule morte revit si elle a 3,6,7 ou 8 voisines
        if (nbVoisines == 3 || nbVoisines == 6 || nbVoisines == 7 || nbVoisines == 8) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }


}
