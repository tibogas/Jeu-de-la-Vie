package fr.tibo.jeu;

public class ObservateurConsole implements Observateur {
    
    private JeuDeLaVie jeu;
    private int numeroGeneration;

    public ObservateurConsole(JeuDeLaVie jeu) {
        this.jeu = jeu;
        this.numeroGeneration = 0;
    }

    @Override
    public void actualise() {

        int i,j;
        this.numeroGeneration++;
        int cellulesEnVie = 0;

        // On parcourt la grille pour compter les cellules vivantes
        for (i=0; i < jeu.getXMax(); i++) {
            for (j= 0; j < jeu.getYMax(); j++) {
                if (jeu.getGrilleXY(i,j).estVivante()) {
                    cellulesEnVie++;
                }
            }
        }

        System.out.println("Génération " + numeroGeneration + " : " + cellulesEnVie + " cellules en vie");
    }
}