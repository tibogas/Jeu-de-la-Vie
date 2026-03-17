package fr.tibo.jeu;


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
