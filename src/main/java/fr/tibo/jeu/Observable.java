package fr.tibo.jeu;

/**
 * Interface le rôle du l'Observable dans le Design Pattern Observateur
 * Permet à un objet de maintenir une liste d'observateurs et de les notifier lors d'un changement d'état.
 * * @author Thibaut Gasnier
 */
public interface Observable {

    void attacheObservateur(Observateur o);
    void detacheObservateur(Observateur o);
    void notifieObservateurs();
    
}