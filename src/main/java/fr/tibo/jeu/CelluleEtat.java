package fr.tibo.jeu;

/**
 * Interface définissant le comportement lié à l'état d'une cellule 
 * Permet à une cellule de changer dynamiquement ses actions selon qu'elle soit en vie ou non
 * * @author Thibaut Gasnier
 */
public interface CelluleEtat  {
  
  
  CelluleEtat vit();
  CelluleEtat meurt();
  boolean estVivante();
  void accepte(Visiteur visiteur,Cellule cellule);

}
  