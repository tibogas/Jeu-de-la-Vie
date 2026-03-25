package fr.tibo.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal du Jeu de la Vie
 * Elle gère la grille de cellules, le calcul des générations et notifie l'interface graphique des changements
 * Elle utilise les Design Patterns Observateur, Visiteur et Commande
 * * @author Thibaut Gasnier
 */
public class JeuDeLaVie implements Observable{

  private Cellule grille [][];
  private int xMax;
  private int yMax;
  public Visiteur visiteur;
  private int nb_gen = 0;

  private List<Observateur> observateurs = new ArrayList<>();
  private List<Commande> commandes = new ArrayList<>();

  /**
  * Construit une nouvelle instance du jeu.
  * * @param xMax Le nombre de colonnes de la grille
  * @param yMax Le nombre de lignes de la grille
  */
  public JeuDeLaVie(int xMax,int yMax){
    this.xMax = xMax;
    this.yMax = yMax;
    this.grille = new Cellule[xMax][yMax];
  }

  /**
   * Initialise la grille avec une répartition aléatoire de 50% de cellules vivantes et 50% de cellules mortes
   */
  public void initialiserGrille(){
    int i,j;
    nb_gen = 0;

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

  /**
   * Réinitialise la grille en forçant un pourcentage précis de cellules vivantes
   * * @param p Le pourcentage de chance qu'une cellule naisse
   */
  public void densiteAleatoire(int p) {

    int i,j;
    nb_gen = 0;

    for (i = 0; i < xMax; i++) {
      for (j = 0; j < yMax; j++) {
        if (Math.random() * 100 < p) {
          grille[i][j].vit();
        } else {
          grille[i][j].meurt();
        }
      }
    }

    notifieObservateurs();
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

  public void setVisiteur(Visiteur v) {
      this.visiteur = v;
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

  /**
   * Exécute toutes les commandes accumulées par le visiteur puis vide la liste pour la génération suivante.
   */
  public void executeCommandes() {

      for (Commande c : commandes) {
          c.executer();
      }
      commandes.clear();
  
  }

  /**
   * Fait passer le visiteur actuel sur absolument toutes les cellules de la grille pour évaluer si elles doivent vivre ou mourir au prochain tour
   */
  public void distribueVisiteur() {
      for (int i = 0; i < xMax; i++) {
          for (int j = 0; j < yMax; j++) {
              grille[i][j].accepte(visiteur);
          }
      }
  }

  public int getNbGenerations() {
      return nb_gen;
  }

  public int getNbCellule() {

      int nb = 0;
      int i,j;

      for (i = 0; i < xMax; i++) {
          for (j= 0; j < yMax; j++) {
              if (grille[i][j].estVivante()) {
                  nb++;
              }
          }
      }
      return nb;
  }

  /**
   * Méthode qui fait le calcul complet d'un cycle de vie
   * Elle incrémente le compteur, évalue les règles, applique les changements et déclenche le rafraîchissement graphique
   */
  public void calculerGenerationSuivante(){
      nb_gen++;
      distribueVisiteur();    
      executeCommandes();   
      notifieObservateurs();
  }

  public static void main(String[] args) {

      JeuDeLaVie jeu = new JeuDeLaVie(180, 80);
      jeu.initialiserGrille();

      jeu.visiteur = new VisiteurClassique(jeu); 
      
      ObservateurConsole obsConsole = new ObservateurConsole(jeu);
      jeu.attacheObservateur(obsConsole);

      JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
      jeu.attacheObservateur(ui);
  }
}