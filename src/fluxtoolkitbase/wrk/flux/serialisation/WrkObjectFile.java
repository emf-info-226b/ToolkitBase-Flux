package fluxtoolkitbase.wrk.flux.serialisation;

import fluxtoolkitbase.beans.IhmState;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
 * Cette classe renferme les fonctionnalités permettant de
 * sérialiser/désérialiser des objets de toute nature.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class WrkObjectFile {

    /**
     * Cette méthode tente de désérialiser un objet de type "String" depuis le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait
     * contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public String readObjectString(String filepath) {
        String result = null;
        try {
            result = (String) deserialiseObjet(filepath);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "ArrayList<String>"
     * depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait
     * contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public ArrayList<String> readObjectStringArray(String filepath) {
        ArrayList<String> result = null;
        try {
            result = (ArrayList<String>) deserialiseObjet(filepath);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "IhmState" depuis le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait
     * contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public IhmState readObjectAppState(String filepath) {
        IhmState result = null;
        try {
            result = (IhmState) deserialiseObjet(filepath);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "String" dans le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la
     * sérialisation de cet objet
     * @param str l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectString(String filepath, String str) {
        return serialiseObjet(filepath, str);
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "ArrayList<String>"
     * dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la
     * sérialisation de cet objet
     * @param lines l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectStringArray(String filepath, ArrayList<String> lines) {
        return serialiseObjet(filepath, lines);
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "IhmState" dans le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la
     * sérialisation de cet objet
     * @param state l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectAppState(String filepath, IhmState state) {
        return serialiseObjet(filepath, state);
    }

    /**
     * Cette fonction sérialise l'objet passé en paramètre dans un fichier.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @param obj l'objet a sérialiser
     * @return l'objet a-t-il été correctement sérialisé ?
     */
    private boolean serialiseObjet(String filepath, Object obj) {

        //
        // VOTRE CODE ICI...
        //
        return false;
    }

    /**
     * Cette fonction desérialise un objet précédemment sérialisé dans un
     * fichier.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @return l'objet s'il a été correctement désérialisé, sinon null
     */
    private Object deserialiseObjet(String filepath) {

        //
        // VOTRE CODE ICI...
        //
        return null;
    }
}
