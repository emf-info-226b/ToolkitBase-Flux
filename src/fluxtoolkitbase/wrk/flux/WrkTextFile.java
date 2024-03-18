package fluxtoolkitbase.wrk.flux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
 * Cette classe renferme les fonctionnalités permettant d'écrire et de lire du texte dans des fichiers à l'aide des flux
 * de type texte.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class WrkTextFile {

    /**
     * Cette constante spécifie l'encodage à utiliser lors de l'écriture et la lecture du fichier texte. Il est très
     * important de toujours spécifier afin d'éviter de dépendre de la valeur par défaut qui est justement dépendante de
     * la plateforme, voir même la version de Java.
     */
    public final static Charset TEXT_FILE_CHARSET = StandardCharsets.UTF_8; 

    /**
     * Lit et retourne l'ensemble des lignes présentes dans un fichier texte. En cas de problème(s) rencontré aucune
     * ligne ne sera retournée (null) de manière à ce qu'on puisse s'en rendre compte.
     *
     * @param filepath le chemin complet du fichier texte à lire
     *
     * @return l'ensemble des lignes du fichier texte, ou null en cas de problème(s) rencontré(s)
     */
    public ArrayList<String> readTextFile( String filepath ) {

        //
        // VOTRE CODE ICI...
        //
        return null;
    }

    /**
     * Écrit des lignes de texte dans un fichier texte. Cette méthode s'assure que le fichier texte produit soit
     * complet et refermé ou alors, en cas de problème(s) rencontré, celui-ci sera supprimé et ne traînera pas
     * incomplet.
     *
     * @param filepath     le chemin complet sur le fichier texte à produire
     * @param linesToWrite l'ensemble des lignes de texte à écrire dans le fichier texte
     *
     * @return vrai si et seulement si le fichier texte a pu être produit avec l'intégralité des lignes de texte et
     *         correctement refermé
     */
    public boolean writeTextFile( String filepath, ArrayList<String> linesToWrite ) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }

    /**
     * Rajoute une ligne à la fin du contenu existant d'un fichier texte. 
     *
     * @param filepath le chemin complet du fichier texte à la fin duquel il faut écrire
     * @param newLineContent  la ligne à rajouter en fin de fichier
     *
     * @return vrai si et seulement si l'ajout a pu s'effectuer correctement
     */
    public boolean appendToTextFile( String filepath, String newLineContent ) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }
}
    