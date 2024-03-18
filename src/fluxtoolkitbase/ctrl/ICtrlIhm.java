package fluxtoolkitbase.ctrl;

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
 * Cette interface renferme les fonctionnalités du contrôleur
 * de l'application à disposition de l'ihm.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public interface ICtrlIhm {

    /**
     * Cette méthode est appelée par l'ihm pour indiquer qu'elle est en train de se fermer.
     */
    void ihmExiting();

    /**
     * Cette méthode est appelée par l'ihm pour demander si un fichier existe.
     *
     * @param filepath le chemin complet du fichier concerné
     */
    void checkIfFileExists( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour demander de supprimer un fichier.
     *
     * @param filepath le chemin complet du fichier concerné
     */
    void deleteFile( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour que le fichier texte passé en paramètres soit lu.
     *
     * @param filepath le chemin complet du fichier texte qui doit être lu
     */
    void readTextFile( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour que le texte passé en paramètres soit écrit dans le fichier texte spécifié.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     */
    void writeTextFile( String filepath, ArrayList<String> linesToWrite );

    /**
     * Cette méthode est appelée par l'ihm pour que le texte passé en paramètres soit rajouté à la fin du fichier texte spécifié.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     */
    void appendToTextFile( String filepath, String newLineContent );

    /**
     * Cette méthode est appelée par l'ihm pour que le fichier binaire passé en paramètres soit lu.
     *
     * @param filepath le chemin complet du fichier binaire qui doit être lu
     */
    void readBinaryFile( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour que les données binaires passées en paramètres soient écrites dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void writeBinaryFile( String filepath, boolean[][] data );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un String soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void readObjectString( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un String soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void writeObjectString( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un ArrayList<String> soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void readObjectStringArray( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un ArrayList<String> soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void writeObjectStringArray( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un IhmState soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void readObjectIhmState( String filepath );

    /**
     * Cette méthode est appelée par l'ihm pour qu'un IhmState soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    void writeObjectIhmState( String filepath );
}
