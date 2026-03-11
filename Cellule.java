public class Cellule (){

  private CelluleEtat etat;
  private int x;
  private int y;

  public Cellule (CelluleEtat etat, int x , int y){
    this.x = x;
    this.y = y;
    this.etat = etat;
  }

  public void vit (){
    this.etat = this.etat.vit();
  }

  public void meurt (){
    this.etat = this.etat.meurt();
  }

  public boolean estVivante(){
    return this.etat.estVivante();
  }

  public nombreVoisinesVivantes (JeuDeLaVie jeu){

    int nb_voisines = 0;
    int i,j;

    int max_x = jeu.getXMax();
    int max_y = jeu.getYMax();

    for(i=-1 ; i<=1 ; i++){
      for(j=-1 ; j<=1 ; j++){

        int voisinX = this.x + i;
        int voisinY = this.y + j;

        if (jeu.getGrilleXY(voisinX,voisinY).estVivante()) {
          if (! ( i==0 && j==0) || voisinX < 0 || voisinX >= max_x || voisinY < 0 || voisinY >= max_y){
            nb_voisines ++;
          }
      }
    }
  }

}
