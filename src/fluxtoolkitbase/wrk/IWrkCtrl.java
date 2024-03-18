package fluxtoolkitbase.wrk;

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
 * Cette interface renferme les fonctionnalités du worker à disposition
 * du contrôleur de l'application.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public interface IWrkCtrl {

    /**
     * Cette méthode vérifie l'existence d'un fichier passé en paramètres.
     *
     * @param filepath le chemin complet sur le fichier
     * @return true si le fichier existe
     */
    boolean checkIfFileExists( String filepath );

    /**
     * Cette méthode permets de supprimerun fichier passé en paramètres.
     *
     * @param filepath le chemin complet du fichier à supprimer
     * @return true s'il a pu être supprimé
     */
    boolean deleteFile( String filepath );

    /**
     * Cette méthode permet de lire toutes les lignes de texte contenues dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @return null pour tout problème rencontré, respectivement l'ensemble des lignes lues si tout s'est bien passé
     */
    ArrayList<String> readTextFile( String filepath );

    /**
     * Cette méthode permet d'écrire les lignes de texte souhaitées dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @param linesToWrite les lignes de texte qu'on souhaite écrire
     * @return true si et seulement si tout s'est bien passé
     */
    boolean writeTextFile( String filepath, ArrayList<String> linesToWrite );

    /**
     * Cette méthode permet de rajouter une ligne à la fin du contenu existant d'un fichier texte.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @param newLineContent la ligne de texte à rajouter à la fin du fichier texte
     * @return true si et seulement si tout s'est bien passé
     */
    boolean appendToTextFile( String filepath, String newLineContent );

    /**
     * Cette méthode permet de lire les données binaires contenues dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @return null pour tout problème rencontré, respectivement les données binaires lues
     */
    boolean[][] readBinaryFile( String filepath );

    /**
     * Cette méthode permet d'écrire les données binaires souhaitées dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @param data les données binaires à écrire
     * @return true si et seulement si tout s'est bien passé
     */
    boolean writeBinaryFile( String filepath, boolean[][] data );

    /**
     * Cette méthode tente de désérialiser un objet de type "String" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    String readObjectString( String filepath );

    /**
     * Cette méthode tente de sérialiser un objet de type "String" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param str l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    boolean writeObjectString( String filepath, String str );

    /**
     * Cette méthode tente de désérialiser un objet de type "ArrayList<String>" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    ArrayList<String> readObjectStringArray( String filepath );

    /**
     * Cette méthode tente de sérialiser un objet de type "ArrayList<String>" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param lines l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    boolean writeObjectStringArray( String filepath, ArrayList<String> lines );

    /**
     * Cette méthode tente de désérialiser un objet de type "IhmState" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    IhmState readObjectAppState( String filepath );

    /**
     * Cette méthode tente de sérialiser un objet de type "IhmState" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param state l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    boolean writeObjectAppState( String filepath, IhmState state );
}
