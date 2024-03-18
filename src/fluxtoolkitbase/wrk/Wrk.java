package fluxtoolkitbase.wrk;

import fluxtoolkitbase.beans.IhmState;
import fluxtoolkitbase.wrk.flux.WrkDataFile;
import fluxtoolkitbase.wrk.flux.WrkTextFile;
import fluxtoolkitbase.wrk.flux.serialisation.WrkObjectFile;
import java.io.File;
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
 * Cette classe renferme le worker principal de l'application.
 * Elle délègue quasiment toutes les opérations sur les fichiers à ses
 * workers spécialisés sur les flux texte, flux binaires et sérialisation.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class Wrk implements IWrkCtrl {

    /**
     * Le constructeur de notre worker principal.
     */
    public Wrk() {
        wrkTextFile = new WrkTextFile();
        wrkDataFile = new WrkDataFile();
        wrkObjectFile = new WrkObjectFile();
    }

    /**
     * Cette méthode vérifie l'existence d'un fichier passé en paramètres.
     *
     * @param filepath le chemin complet sur le fichier
     * @return true si le fichier existe
     */
    public boolean checkIfFileExists( String filepath ) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }

    /**
     * Cette méthode permets de supprimer un fichier passé en paramètres.
     *
     * @param filepath le chemin complet du fichier à supprimer
     * @return true s'il a pu être supprimé
     */
    public boolean deleteFile( String filepath ) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }

    /**
     * Cette méthode permet de lire toutes les lignes de texte contenues dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @return null pour tout problème rencontré, respectivement l'ensemble des lignes lues si tout s'est bien passé
     */
    public ArrayList<String> readTextFile( String filepath ) {
        return getWrkTextFile().readTextFile( filepath );
    }

    /**
     * Cette méthode permet d'écrire les lignes de texte souhaitées dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @param linesToWrite les lignes de texte qu'on souhaite écrire
     * @return true si et seulement si tout s'est bien passé
     */
    public boolean writeTextFile( String filepath, ArrayList<String> linesToWrite ) {
        return getWrkTextFile().writeTextFile( filepath, linesToWrite );
    }

    /**
     * Cette méthode permet de rajouter une ligne à la fin du contenu existant d'un fichier texte.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     * @param newLineContent la ligne de texte à rajouter à la fin du fichier texte
     * @return true si et seulement si tout s'est bien passé
     */
    public boolean appendToTextFile( String filepath, String newLineContent ) {
        return getWrkTextFile().appendToTextFile( filepath, newLineContent );
    }

    /**
     * Cette méthode permet de lire les données binaires contenues dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @return null pour tout problème rencontré, respectivement les données binaires lues
     */
    public boolean[][] readBinaryFile( String filepath ) {
        return getWrkDataFile().readBinaryFile( filepath );
    }

    /**
     * Cette méthode permet d'écrire les données binaires souhaitées dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @param data les données binaires à écrire
     * @return true si et seulement si tout s'est bien passé
     */
    public boolean writeBinaryFile( String filepath, boolean[][] data ) {
        return getWrkDataFile().writeBinaryFile( filepath, data );
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "String" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public String readObjectString( String filepath ) {
        return getWrkObjectFile().readObjectString( filepath );
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "String" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param str l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectString( String filepath, String str ) {
        return getWrkObjectFile().writeObjectString( filepath, str );
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "ArrayList<String>" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public ArrayList<String> readObjectStringArray( String filepath ) {
        return getWrkObjectFile().readObjectStringArray( filepath );
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "ArrayList<String>" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param lines l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectStringArray( String filepath, ArrayList<String> lines ) {
        return getWrkObjectFile().writeObjectStringArray( filepath, lines );
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "IhmState" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public IhmState readObjectAppState( String filepath ) {
        return getWrkObjectFile().readObjectAppState( filepath );
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "IhmState" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la sérialisation de cet objet
     * @param state l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectAppState( String filepath, IhmState state ) {
        return getWrkObjectFile().writeObjectAppState( filepath, state );
    }

    /**
     * Getter de notre worker spécialisé sur les fichiers texte.
     *
     * @return notre worker spécialisé sur les fichiers texte
     */
    public WrkTextFile getWrkTextFile() {
        return wrkTextFile;
    }

    /**
     * Getter de notre worker spécialisé sur les fichiers binaires.
     *
     * @return notre worker spécialisé sur les fichiers binaires
     */
    public WrkDataFile getWrkDataFile() {
        return wrkDataFile;
    }

    /**
     * Getter de notre worker spécialisé sur la sérialisation.
     *
     * @return notre worker spécialisé sur la sérialisation
     */
    public WrkObjectFile getWrkObjectFile() {
        return wrkObjectFile;
    }
    /**
     * Worker spécialisé sur les fichiers texte.
     */
    private WrkTextFile wrkTextFile;
    /**
     * Worker spécialisé sur les fichiers binaires.
     */
    private WrkDataFile wrkDataFile;
    /**
     * Worker spécialisé sur la sérialisation.
     */
    private WrkObjectFile wrkObjectFile;
}
