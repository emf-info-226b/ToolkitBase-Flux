package fluxtoolkitbase.ihm;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

//
// ######################################################################
// #                        _       _        ____  ____   __            #
// #        /\/\   ___   __| |_   _| | ___  |___ \|___ \ / /_           #
// #       /    \ / _ \ / _` | | | | |/ _ \   __) | __) | '_ \          #
// #      / /\/\ \ (_) | (_| | |_| | |  __/  / __/ / __/| (_) |         #
// #      \/    \/\___/ \__,_|\__,_|_|\___| |_____|_____|\___/  FRI     #
// #                        __                                          #
// #                       |_   |    /  \  \_/                          #
// #                       |    |__  \__/  / \                          #
// #                                                                    #
// ######################################################################
// # Projet "caisse à outils" flux pour apprenant, avec Ihm soignée,    #
// # pour les flux texte, flux binaire et sérialisation d'objets.       #
// ######################################################################
// # Ecrit par # Paul Friedli      # VERSION # 1.0 # DATE # 02.05.2012 #
// ######################################################################
//
/**
 * Cette classe renferme l'implémentation d'une version modifiée
 * d'un JPanel permettant de dessiner avec la souris et d'entretenir
 * un tableau de booléens correspondant à l'état du dessin noir/blanc
 * réalisé.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class PenDrawPanel extends JPanel {

    /**
     * La largeur souhaitée du trait réalisé avec la souris.
     */
    private final static int STROKE_WIDTH = 3;  // Nombres impairs svp

    /**
     * Le constructeur qui réalise presque toute la magie.
     */
    public PenDrawPanel() {
        super();
        currentColor = true;
        addMouseListener( new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed( java.awt.event.MouseEvent evt ) {
                // System.out.println( "mousePressed " + evt.getX() + "/" + evt.getY() );
            }

            @Override
            public void mouseReleased( java.awt.event.MouseEvent evt ) {
                // System.out.println( "mouseReleased " + evt.getX() + "/" + evt.getY() );
                invalidate();
                updateUI();
            }
        } );
        addMouseMotionListener( new java.awt.event.MouseMotionAdapter() {

            @Override
            public void mouseDragged( java.awt.event.MouseEvent evt ) {
                // System.out.println( "mouseDragged " + evt.getX() + "/" + evt.getY() );
                int x = evt.getX();
                int y = evt.getY();

                int extraStrokeWidth = STROKE_WIDTH / 2;

                int lboundsX = x - extraStrokeWidth;
                if ( lboundsX < 0 ) {
                    lboundsX = 0;
                }

                int uboundsX = x + extraStrokeWidth;
                if ( uboundsX > ( pointsDessin.length - 1 ) ) {
                    uboundsX = pointsDessin.length - 1;
                }

                int lboundsY = y - extraStrokeWidth;
                if ( lboundsY < 0 ) {
                    lboundsY = 0;
                }

                int uboundsY = y + extraStrokeWidth;
                if ( uboundsY > ( pointsDessin[0].length - 1 ) ) {
                    uboundsY = pointsDessin[0].length - 1;
                }

                for ( int i = lboundsX; i <= uboundsX; i++ ) {
                    for ( int j = lboundsY; j <= uboundsY; j++ ) {
                        pointsDessin[i][j] = currentColor;
                        invalidate();
                        updateUI();
                    }
                }
            }
        } );
        addComponentListener( new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized( java.awt.event.ComponentEvent evt ) {
                allocateDrawSpace();
                invalidate();
                updateUI();
            }
        } );
    }

    /**
     * Cette méthode alloue un nouveau tableau de la taille du dessin actuel.
     */
    private void allocateDrawSpace() {
        pointsDessin = new boolean[ getWidth() ][ getHeight() ];
    }

    /**
     * La nouvelle façon de dessiner ce JPanel "maison".
     *
     * @param g l'objet Graphics à utiliser pour dessiner
     */
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );

        g.setColor( new Color( 0, 82, 147 ) );  // set the drawing color
        g.fillRect( 0, 0, getWidth(), getHeight() );

        g.setColor( Color.WHITE );  // set the drawing color
        for ( int x = 0; x < pointsDessin.length; x++ ) {
            for ( int y = 0; y < pointsDessin[0].length; y++ ) {
                if ( pointsDessin[x][y] ) {
                    g.fillRect( x, y, 1, 1 );
                }
            }
        }
    }

    /**
     * Cette méthode efface complètement tout le dessin actuel.
     */
    public void clearAll() {
        currentColor = true;
        for ( int x = 0; x < pointsDessin.length; x++ ) {
            for ( int y = 0; y < pointsDessin[0].length; y++ ) {
                pointsDessin[x][y] = false;
            }
        }
        invalidate();
        updateUI();
    }

    /**
     * Cette méthode remplit complètement tout le dessin actuel.
     */
    public void fillAll() {
        currentColor = false;
        for ( int x = 0; x < pointsDessin.length; x++ ) {
            for ( int y = 0; y < pointsDessin[0].length; y++ ) {
                pointsDessin[x][y] = true;
            }
        }
        invalidate();
        updateUI();
    }

    /**
     * Cette méthode permet de remplacer le dessin actuel par un autre.
     *
     * @param data le nouveau dessin
     */
    public void setData( boolean[][] data ) {
        pointsDessin = data;
        invalidate();
        updateUI();
    }

    /**
     * Cette méthode retourne le dessin actuel.
     *
     * @return le dessin actuel
     */
    public boolean[][] getData() {
        return pointsDessin;
    }
    /**
     * Le dessin actuel.
     */
    boolean[][] pointsDessin = null;
    /**
     * La couleur actuelle.
     */
    boolean currentColor;
}
