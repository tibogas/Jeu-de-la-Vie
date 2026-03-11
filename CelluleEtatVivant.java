public Class CelluleEtatVivant implements CelluleEtat{

  private static final CelluleEtatVivant c = new CelluleEtatVivant ;

  private CelluleEtatVivant () {}

  public static getInstance {
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
  public estVivante(){
    return true;
  }

}
