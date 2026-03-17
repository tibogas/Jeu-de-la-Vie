package fr.tibo.jeu;

import java.util.ArrayList;
import java.util.List;

public class JeuDeLaVie implements Observable{

  private Cellule grille [][];
  private int xMax;
  private int yMax;
  private Visiteur visiteur;

  private List<Observateur> observateurs = new ArrayList<>();
  private List<Commande> commandes = new ArrayList<>();

  public JeuDeLaVie(int xMax,int yMax){
    this.xMax = xMax;
    this.yMax = yMax;
    this.grille = new Cellule[xMax][yMax];
  }


  public void initialiserGrille(){
    int i,j;

    for (i=0; i<xMax ; i++){
      for (j=0 ; j<yMax ; j++){ 

        // Cellule vivante
        if (Math.random() > 0.5){
          grille[i][j] = new Cellule(CelluleEtatVivant.getInstance(),i,j);
        }
        // Cellule morte
        else {
          grille[i][j] = new Cellule(CelluleEtatMort.getInstance(),i,j);
        }

      }
    }

  }

  public Cellule getGrilleXY(int x, int y){
    return grille[x][y];
  }

  public int getXMax(){
    return xMax;
  }

  public int getYMax(){
    return yMax;
  }

  public void attacheObservateur(Observateur o){ 
    observateurs.add(o); 
  }

  public void detacheObservateur(Observateur o){ 
    observateurs.remove(o); 
  }

  public void notifieObservateurs(){
      for (Observateur o : observateurs) o.actualise();
  }

  public void ajouteCommande(Commande c){
      commandes.add(c);
  }

  public void executeCommandes() {

      for (Commande c : commandes) {
          c.executer();
      }
      commandes.clear();
  
  }

  public void distribueVisiteur() {
      for (int i = 0; i < xMax; i++) {
          for (int j = 0; j < yMax; j++) {
              grille[i][j].accepte(visiteur);
          }
      }
  }

  public void calculerGenerationSuivante(){
      distribueVisiteur();    
      executeCommandes();   
      notifieObservateurs();
  }

  public static void main(String[] args) {

      JeuDeLaVie jeu = new JeuDeLaVie(200, 200);
      jeu.initialiserGrille();

      JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
      jeu.attacheObservateur(ui);
      
      jeu.visiteur = new VisiteurClassique(jeu); 

      while(true) {
          try {
              Thread.sleep(1000); // Met en pause
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          jeu.calculerGenerationSuivante();
      }
  }

}








