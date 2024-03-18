package fluxtoolkitbase.wrk.flux;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
 * Cette classe renferme les fonctionnalités permettant d'écrire et de lire
 * un tableau à deux dimensions de booléens.
 *
 * Chaque cas étant différent, cette classe n'a d'utilité que pour montrer
 * le principe général.
 *
 * ATTENTION :
 * ---------
 * - à lire les informations binaires dans le même ordre qu'elles
 * ont été écrites.
 * - pour les séries de données (par ex. tableaux), il faut
 * indiquer le nombre d'éléments avant de les écrire, sinon à la lecture
 * on ne saura pas combien il faut en lire :-)
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class WrkDataFile {

    /**
     * Cette méthode permet de lire les données binaires contenues dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @return null pour tout problème rencontré, respectivement les données binaires lues
     */
    public boolean[][] readBinaryFile( String filepath ) {

        //
        // VOTRE CODE ICI...
        //
        return null;
    }

    /**
     * Cette méthode permet d'écrire les données binaires souhaitées dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @param data les données binaires à écrire
     * @return true si et seulement si tout s'est bien passé
     */
    public boolean writeBinaryFile( String filepath, boolean[][] data ) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }
}
