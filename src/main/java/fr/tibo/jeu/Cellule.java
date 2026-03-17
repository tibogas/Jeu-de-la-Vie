package fr.tibo.jeu;


public class Cellule {

  private CelluleEtat etat;
  private int x;
  private int y;

  public Cellule (CelluleEtat etat,int x ,int y){
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

  public int nombreVoisinesVivantes(JeuDeLaVie jeu) {

    int i,j;
    int nb_voisines = 0;
    
    int max_x = jeu.getXMax();
    int max_y = jeu.getYMax();

    for (i =-1; i<=1; i++) {
      for (j =-1; j<=1; j++) {
        
        if(!(i == 0 && j == 0)) {

          int voisinX = this.x + i;
          int voisinY = this.y + j;

          if (voisinX >= 0 && voisinX < max_x && voisinY >= 0 && voisinY < max_y) {
                
            if (jeu.getGrilleXY(voisinX, voisinY).estVivante()) {
              nb_voisines++;
            }
          }
        }
      }
    } 

    return nb_voisines; 

  }

  public void accepte(Visiteur visiteur) {
      this.etat.accepte(visiteur, this);
  }


}
