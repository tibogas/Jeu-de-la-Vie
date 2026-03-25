package fr.tibo.jeu;

/**
 * Représente une cellule individuelle dans la grille du jeu
 * Une cellule possède des coordonnées (x, y) et un état courant (vivante ou morte)
 * Elle utilise le pattern État pour déléguer son comportement selon qu'elle soit en vie ou non
 * * @author Thibaut Gasnier
 */
public class Cellule {

  private CelluleEtat etat;
  private int x;
  private int y;

  /**
   * Construit une nouvelle cellule avec un état initial et des coordonnées précises
   * * @param etat L'état initial de la cellule 
   * @param x    La position en abscisse de la cellule dans la grille
   * @param y    La position en ordonnée de la cellule dans la grille
   */
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

  /**
   * Calcule le nombre de cellules voisines actuellement en vie autour de la cellule
   * @param jeu L'instance actuelle du jeu pour accéder à l'état global de la grille
   * @return Le nombre de voisines vivantes
   */
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

  /**
   * Permet à un visiteur d'appliquer ses règles 
   * * @param visiteur Le visiteur contenant les règles du jeu
   */
  public void accepte(Visiteur visiteur) {
      this.etat.accepte(visiteur, this);
  }


}
