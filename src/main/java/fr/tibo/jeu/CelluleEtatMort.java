package fr.tibo.jeu;

/**
 * Représente l'état Mort d'une cellule dans le jeu
 * Implémente le Design Pattern Singleton pour garantir qu'une seule et unique instance de l'état mort existe pour toute la grille
 * * @author Thibaut Gasnier
 */
public class CelluleEtatMort implements CelluleEtat{

  private static final CelluleEtatMort c = new CelluleEtatMort();
  
  private CelluleEtatMort() {}

  public static CelluleEtatMort getInstance(){
    return c ;
  }

  @Override
  public CelluleEtat vit (){
    return CelluleEtatVivant.getInstance(); // On appelle le singleton
  }

  @Override
  public CelluleEtat meurt(){
    return this ;
  }

  @Override
  public boolean estVivante(){
    return false;
  }

  @Override
  public void accepte(Visiteur visiteur, Cellule cellule) {
      visiteur.visiteCelluleMorte(cellule);
  }

}
