package fr.tibo.jeu;


public class CelluleEtatMort implements CelluleEtat{

  private static final CelluleEtatMort c = new CelluleEtatMort ;

  private CelluleEtatMort() {}

  public static getInstance {
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
  public estVivante(){
    return false;
  }

}
