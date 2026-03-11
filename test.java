public nombreVoisinesVivantes (JeuDeLaVie jeu){

  int nb_voisines = 0;
  int i,j;

  for(i=-1 ; i<=1 ; i++){
    for(j=-1 ; j<=1 ; j++){

      int voisinX = this.x + i;
      int voisinY = this.y + j;

      if (jeu.getGrilleXY(voisinX,voisinY) != NULL ) {
          if (jeu.getGrilleXY(voisinX,voisinY).estVivante()){
            nb_voisines ++;
        }
      }
    }
  }
