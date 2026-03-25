package fr.tibo.jeu;

/**
 * Représente l'état Vivant d'une cellule dans le jeu
 * Implémente le Design Pattern Singleton pour garantir qu'une seule et unique instance de l'état vivant existe pour toute la grille
 * * @author Thibaut Gasnier
 */
public class CelluleEtatVivant implements CelluleEtat{

  private static final CelluleEtatVivant c = new CelluleEtatVivant() ;

  private CelluleEtatVivant () {}

  public static CelluleEtatVivant getInstance() {
    return c ;
  }

  @Override
  public CelluleEtat vit (){
    return this;
  }

  @Override
  public CelluleEtat meurt(){
    return CelluleEtatMort.getInstance(); // On appelle le singleton
  }

  @Override
  public boolean estVivante(){
    return true;
  }

  @Override
  public void accepte(Visiteur visiteur, Cellule cellule) {
      visiteur.visiteCelluleVivante(cellule);
  }

}
