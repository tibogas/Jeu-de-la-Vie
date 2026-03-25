package fr.tibo.jeu;

/**
 * Interface définissant le rôle de l'Observateur dans le Design Pattern Observateur
 * Les classes qui l'implémentent doivent définir la méthode actualise() pour réagir aux notifications de l'Observable
 * * @author Thibaut Gasnier
 */
public interface Observateur {
    void actualise();
}
