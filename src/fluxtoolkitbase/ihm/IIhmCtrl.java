package fluxtoolkitbase.ihm;

import fluxtoolkitbase.beans.IhmState;
import java.util.ArrayList;

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
 * Cette interface renferme les fonctionnalités de l'ihm à disposition
 * du contrôleur de l'application.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public interface IIhmCtrl {

    /**
     * Cette méthode permet de rendre l'ihm visible à l'écran.
     */
    void display();

    /**
     * Cette méthode permet d'afficher un message d'information à l'utilisateur.
     *
     * @param message le message à afficher
     */
    void showInformationMessage( String message );

    /**
     * Cette méthode permet d'afficher un message d'erreur à l'utilisateur.
     *
     * @param message le message à afficher
     */
    void showErrorMessage( String message );

    /**
     * Cette méthode permet de poser une question à l'utilisateur à laquelle il devra répondre par oui ou par non.
     *
     * @param message la question à afficher
     * @return true si l'utilisateur presse le bouton "oui", sinon false
     */
    boolean askYesNoQuestion( String yesNoQuestion );

    /**
     * Cette méthode permet de demander la saisie d'une chaîne de caractères à l'utilisateur.
     *
     * @param message la question
     * @return la chaîne que l'utilisateur a entrée, null s'il annule l'opération
     */
    String askTextString( String message );

    /**
     * Cette méthode remplace le contenu de notre zone d'édition multiligne de texte (notre JTextArea)
     * par les lignes passées en paramètre.
     *
     * @param lignes les lignes à afficher
     */
    void setTextContent( ArrayList<String> lignes );

    /**
     * Cette méthode permet de remplacer le dessin actuel par un nouveau dessin.
     *
     * @param data le nouveau dessin
     */
    void setBinaryContent( boolean[][] data );

    /**
     * Cette méthode permet d'obtenir l'état actuel de l'ihm sous forme
     * d'un objet transportant l'ensemble des informations souhaitées.
     *
     * @return un objet de type IhmState encapsulant l'état actuel de l'ihm
     */
    IhmState getIhmState();

    /**
     * Cette méthode permet de remettre l'ihm en l'état correspondant à celui passé en paramètres.
     *
     * @param state le nouvel état de l'ihm
     */
    void setIhmState( IhmState state );
}
