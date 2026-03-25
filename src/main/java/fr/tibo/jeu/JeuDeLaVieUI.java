package fr.tibo.jeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;

/**
 * Interface Graphique principale du jeu
 * Elle hérite de JFrame pour créer la fenêtre et implémente le pattern Observateur
 * Elle affiche la grille, les statistiques, le panneau de contrôle et se met à jour automatiquement à chaque nouvelle génération
 * * @author Thibaut Gasnier
 */
public class JeuDeLaVieUI extends JFrame implements Observateur {
    
    private JeuDeLaVie jeu;

    private int taille_cellule = 10;
    
    // Variables de vitesse
    private final int VITESSE_MAX = 10;       
    private final int VITESSE_MIN = 1000;     
    private final int VITESSE_DEPART = 400;  
    
    private GrillePanel grillePanel;
    private Timer timer;
    private boolean enLecture = false;
    private Color couleurCellules = Color.BLACK;
    private JLabel labelGenerations;
    private JLabel labelPopulation;

    /**
     * Construit l'interface graphique et la connecte au moteur du jeu
     * Initialise et place tous les composants et configure leurs écouteurs d'événements
     * @param jeu L'instance du modèle
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        
        this.jeu = jeu;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        grillePanel = new GrillePanel();
        grillePanel.setPreferredSize(new Dimension(jeu.getXMax() * taille_cellule, jeu.getYMax() * taille_cellule));
        JScrollPane scrollPane = new JScrollPane(grillePanel);
        this.add(scrollPane, BorderLayout.CENTER);

        
        labelGenerations = new JLabel("Générations : 0");
        labelPopulation = new JLabel("    Nombre de Cellules : 0    ");
        
        JPanel panneauStats = new JPanel();
        panneauStats.add(labelGenerations);
        panneauStats.add(labelPopulation);
        
        // On accroche cette barre tout en haut (NORTH) de la fenêtre
        this.add(panneauStats, BorderLayout.NORTH);

        JPanel panneauControle = new JPanel();
        panneauControle.setLayout(new GridLayout(3, 1, 0, 3)); 

        // Composants
        JButton btnPlayPause = new JButton("Play / Pause");
        JButton btnAvancer = new JButton("Prochaine génération");
        JSlider sliderVitesse = new JSlider(VITESSE_MAX, VITESSE_MIN, VITESSE_DEPART);
        sliderVitesse.setInverted(true);
        JLabel labelValeurVitesse = new JLabel(sliderVitesse.getValue() + " ms");

        String[] regles = {"Classique", "HighLife", "Day and Night"};
        JComboBox<String> comboRegles = new JComboBox<>(regles);

        JSlider sliderDensite = new JSlider(1, 100, 50);
        JLabel labelDensite = new JLabel("Densité : " + sliderDensite.getValue() + "%");
        JButton btnReset = new JButton("Générer nouvelle grille");

        JButton btnCouleur = new JButton("Couleur cellules");
        JSlider sliderZoom = new JSlider(2, 40, taille_cellule);
        JLabel labelZoom = new JLabel("Zoom : x" + sliderZoom.getValue());


        // Paramètre de jeu
        JPanel blocJeu = new JPanel();
        blocJeu.setBorder(BorderFactory.createTitledBorder("Paramètres Jeu"));
        blocJeu.add(btnPlayPause);
        blocJeu.add(btnAvancer);
        blocJeu.add(new JLabel(" Vitesse :"));
        blocJeu.add(sliderVitesse);
        blocJeu.add(labelValeurVitesse);

        // Parmètre de grille 
        JPanel blocGrille = new JPanel();
        blocGrille.setBorder(BorderFactory.createTitledBorder("Paramètres Grille"));
        blocGrille.add(new JLabel("Règles :"));
        blocGrille.add(comboRegles);
        blocGrille.add(new JLabel("  "));
        blocGrille.add(labelDensite);
        blocGrille.add(sliderDensite);
        blocGrille.add(btnReset);

        // Paramètre d'affichage 
        JPanel blocAffichage = new JPanel();
        blocAffichage.setBorder(BorderFactory.createTitledBorder("Paramètres Affichage"));
        blocAffichage.add(btnCouleur);
        blocAffichage.add(new JLabel("  "));
        blocAffichage.add(labelZoom);
        blocAffichage.add(sliderZoom);

        panneauControle.add(blocJeu);
        panneauControle.add(blocGrille);
        panneauControle.add(blocAffichage);

        this.add(panneauControle, BorderLayout.SOUTH);

       
        // Le chrono qui calcul la generation suivante
        timer = new Timer(VITESSE_DEPART, e -> jeu.calculerGenerationSuivante());

        // Démarre ou met en pause l'animation en activant/désactivant le chrono
        btnPlayPause.addActionListener(e -> {
            if (enLecture) 
                timer.stop();
            else 
                timer.start();
            enLecture = !enLecture;
        });

        // Fait avancer le jeu d'une generation manuellement
        btnAvancer.addActionListener(e -> {
            if (!enLecture) jeu.calculerGenerationSuivante();
        });

        // Modifie la vitesse du jeu
        sliderVitesse.addChangeListener(e -> {
            int vitesse = sliderVitesse.getValue();
            timer.setDelay(vitesse);
            labelValeurVitesse.setText(vitesse + " ms");
        });

        // Change les regles
        comboRegles.addActionListener(e -> {

        if (enLecture) btnPlayPause.doClick(); // Met en pause

            String choix = (String) comboRegles.getSelectedItem();

            if ("Classique".equals(choix)){
                jeu.setVisiteur(new VisiteurClassique(jeu));
            }
            else if ("HighLife".equals(choix)){
                jeu.setVisiteur(new VisiteurHighLife(jeu));
            }
            else if("Day and Night".equals(choix)){
                jeu.setVisiteur(new VisiteurDayNight(jeu));
            } 
        });

        // Met à jour le texte pour le curseur de densité
        sliderDensite.addChangeListener(e -> {
            labelDensite.setText("Densité : " + sliderDensite.getValue() + "%");
        });

        // générer une nouvelle grille aléatoire selon la densité choisie
        btnReset.addActionListener(e -> {
            jeu.densiteAleatoire(sliderDensite.getValue());
        });

        // Choisir une nouvelle couleur pour les cellules 
        btnCouleur.addActionListener(e -> {
            Color nouvelleCouleur = JColorChooser.showDialog(this, "Choisir la couleur des cellules", couleurCellules);

            if (nouvelleCouleur != null) {
                couleurCellules = nouvelleCouleur;
                grillePanel.repaint(); 
            }
        });

        // Action pour modifier le Zoom
        sliderZoom.addChangeListener(e -> {
            taille_cellule = sliderZoom.getValue(); 
            labelZoom.setText("Zoom : x" + taille_cellule);
            
            grillePanel.setPreferredSize(new Dimension(jeu.getXMax() * taille_cellule, jeu.getYMax() * taille_cellule));
            
            grillePanel.revalidate(); 
            grillePanel.repaint();
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
    * Met à jour les compteurs de statistiques et force le redessin de la grille
    */
    @Override
    public void actualise() {
        labelGenerations.setText("Générations : " + jeu.getNbGenerations());
        labelPopulation.setText("    Nombre de Cellules : " + jeu.getNbCellule());
        grillePanel.repaint(); 
    }

    /**
     * Panneau interne (JPanel) dédié au dessin de la grille de cellules
     */
    private class GrillePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g){

            int i,j;

            super.paintComponent(g);
            g.setColor(couleurCellules);

            for(i=0; i < jeu.getXMax(); i++) {
                for(j=0; j < jeu.getYMax(); j++) {
                    if (jeu.getGrilleXY(i,j).estVivante()) {
                        g.fillOval(i* taille_cellule,j* taille_cellule, taille_cellule, taille_cellule);
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
}