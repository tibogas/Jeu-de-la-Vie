public class JeuDeLaVie (){

  private Cellule [][];
  private int xMax;
  private int yMax;

  public JeuDeLaVie(int xMax,int yMax){
    this.xMax = xMax;
    this.yMax = yMax;
    this.grille = new Cellule[xMax][yMax];
  }


  public initialiserGrille(){
    int i,j

    for (i=0; i<xMax ; i++){
      for (y=0 ; y<yMax ; y++){

        // Cellule vivante
        if (Math.random > 0.5){
          grille[i][j] = new Cellule(i,j,CelluleEtatVivant.getInstance())
        }
        // Cellule morte
        else {
          grille[i][j] = new Cellule(i,j,CelluleEtatMort.getInstance());
        }

      }
    }

  }

  public getGrilleXY(int x, int y){
    return grille[x][y];
  }

  public getXMax(){
    return xMax;
  }

  public getYMax(){
    return YMax;
  }

}
