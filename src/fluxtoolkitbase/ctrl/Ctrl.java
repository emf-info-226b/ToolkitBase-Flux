package fluxtoolkitbase.ctrl;

import fluxtoolkitbase.beans.IhmState;
import fluxtoolkitbase.ihm.IIhmCtrl;
import fluxtoolkitbase.wrk.IWrkCtrl;
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
 * Cette classe renferme l'implémentation du contrôleur de l'application.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class Ctrl implements ICtrlIhm {

    /**
     * Le nom de fichier qui sera utilisé pour sérialiser/désérialiser
     * l'état de l'application clors de sa fermeture/ouverture.
     */
    private static final String IHM_STATE_FILENAME = "IhmState.state";

    /**
     * La méthode permettant de faire démarrer l'application.
     */
    public void start() {
        getRefIhm().display();
        // Tenter de relire l'état de l'ihm sauvée en quittant
        IhmState state = getRefWrk().readObjectAppState( IHM_STATE_FILENAME );
        if ( state != null ) {
            getRefIhm().setIhmState( state );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour indiquer qu'elle est en train de se fermer.
     */
    public void ihmExiting() {
        // Tenter de sauvegarder l'état de l'ihm en quittant
        IhmState state = getRefIhm().getIhmState();
        boolean ok = getRefWrk().writeObjectAppState( IHM_STATE_FILENAME, state );
    }

    /**
     * Cette méthode est appelée par l'ihm pour demander si un fichier existe.
     *
     * @param filepath le chemin complet du fichier concerné
     */
    public void checkIfFileExists( String filepath ) {
        boolean doesExist = getRefWrk().checkIfFileExists( filepath );
        if ( doesExist ) {
            getRefIhm().showInformationMessage( "Le fichier [" + filepath + "] existe !" );
        }
        else {
            getRefIhm().showErrorMessage( "Le fichier [" + filepath + "] n'existe pas !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour demander de supprimer un fichier.
     *
     * @param filepath le chemin complet du fichier concerné
     */
    public void deleteFile( String filepath ) {
        boolean result = getRefIhm().askYesNoQuestion( "Voulez-vous vraiment supprimer le fichier [" + filepath + "] ?" );
        if ( result ) {
            boolean deleted = getRefWrk().deleteFile( filepath );
            if ( deleted ) {
                getRefIhm().showInformationMessage( "Le fichier [" + filepath + "] a été supprimé avec succès !" );
            }
            else {
                getRefIhm().showErrorMessage( "Le fichier [" + filepath + "] n'a pas pu être supprimé !" );
            }
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour que le fichier texte passé en paramètres soit lu.
     *
     * @param filepath le chemin complet du fichier texte qui doit être lu
     */
    public void readTextFile( String filepath ) {
        ArrayList<String> lines = getRefWrk().readTextFile( filepath );
        if ( lines == null ) {
            getRefIhm().showErrorMessage( "Le fichier [" + filepath + "] n'a pas pu être lu !" );
        }
        else {
            getRefIhm().setTextContent( lines );
            getRefIhm().showInformationMessage( "Le fichier [" + filepath + "] a été lu avec succès !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour que le texte passé en paramètres soit écrit dans le fichier texte spécifié.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     */
    public void writeTextFile( String filepath, ArrayList<String> linesToWrite ) {
        boolean result = getRefWrk().writeTextFile( filepath, linesToWrite );
        if ( result ) {
            getRefIhm().showInformationMessage( "Le fichier [" + filepath + "] a été sauvegardé avec succès !" );
        }
        else {
            getRefIhm().showErrorMessage( "Le fichier [" + filepath + "] n'a pas pu être sauvegardé !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour que le texte passé en paramètres soit rajouté à la fin du fichier texte spécifié.
     *
     * @param filepath le chemin complet du fichier texte à utiliser
     */
    public void appendToTextFile( String filepath, String newLineContent ) {
        boolean result = getRefWrk().appendToTextFile( filepath, newLineContent );
        if ( result ) {
            getRefIhm().showInformationMessage( "La ligne a été rajoutée au fichier [" + filepath + "] avec succès !" );
        }
        else {
            getRefIhm().showErrorMessage( "La ligne n'a pas pu être rajoutée au fichier [" + filepath + "] !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour que le fichier binaire passé en paramètres soit lu.
     *
     * @param filepath le chemin complet du fichier binaire qui doit être lu
     */
    public void readBinaryFile( String filepath ) {
        boolean[][] data = getRefWrk().readBinaryFile( filepath );
        if ( data == null ) {
            getRefIhm().showErrorMessage( "Le fichier de données binaires [" + filepath + "] n'a pas pu être lu !" );
        }
        else {
            getRefIhm().setBinaryContent( data );
            getRefIhm().showInformationMessage( "Le fichier de données binaires [" + filepath + "] a été lu avec succès !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour que les données binaires passées en paramètres soient écrites dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void writeBinaryFile( String filepath, boolean[][] data ) {
        boolean result = getRefWrk().writeBinaryFile( filepath, data );
        if ( result ) {
            getRefIhm().showInformationMessage( "Les données binaires ont été écrites dans le fichier [" + filepath + "] avec succès !" );
        }
        else {
            getRefIhm().showErrorMessage( "Les données binaires n'ont pas été écrites dans le fichier [" + filepath + "] !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un String soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void readObjectString( String filepath ) {
        String str = getRefWrk().readObjectString( filepath );
        if ( str == null ) {
            getRefIhm().showErrorMessage( "La désérialisation du String depuis le fichier [" + filepath + "] a échoué !" );
        }
        else {
            getRefIhm().showInformationMessage( "Désérialisation du String depuis le fichier [" + filepath + "] OK !\nLe String contient [" + str + "]" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un String soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void writeObjectString( String filepath ) {
        String str = getRefIhm().askTextString( "Veuillez entrer le String à sérialiser" );
        if ( str != null ) {
            if ( getRefWrk().writeObjectString( filepath, str ) ) {
                getRefIhm().showInformationMessage( "Sérialisation du String dans le fichier [" + filepath + "] OK !" );
            }
            else {
                getRefIhm().showErrorMessage( "La sérialisation du String dans le fichier [" + filepath + "] a échoué !" );
            }
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un ArrayList<String> soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void readObjectStringArray( String filepath ) {
        ArrayList<String> lines = getRefWrk().readObjectStringArray( filepath );
        if ( lines == null ) {
            getRefIhm().showErrorMessage( "La désérialisation du ArrayList<String> depuis le fichier [" + filepath + "] a échoué !" );
        }
        else {
            String content = "";
            int lineNum = 1;
            for ( String line : lines ) {
                content += "N°" + lineNum + " = [" + line + "]\n";
                lineNum++;
            }
            getRefIhm().showInformationMessage(
                    "Désérialisation du ArrayList<String> depuis le fichier [" + filepath + "] OK !\nVoici son contenu :\n" + content );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un ArrayList<String> soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void writeObjectStringArray( String filepath ) {
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        int i = 1;
        do {
            line = getRefIhm().askTextString( "Veuillez entrer le String N°" + i + " (ou vide pour conclure la liste)" );
            if ( ( line != null ) && ( !line.isEmpty() ) ) {
                lines.add( line );
                i++;
            }
        }
        while ( ( line != null ) && ( !line.isEmpty() ) );
        if ( i > 1 ) {
            if ( getRefWrk().writeObjectStringArray( filepath, lines ) ) {
                getRefIhm().showInformationMessage( "Sérialisation du ArrayList<String> dans le fichier [" + filepath + "] OK !" );
            }
            else {
                getRefIhm().showErrorMessage( "La sérialisation du ArrayList<String> dans le fichier [" + filepath + "] a échoué !" );
            }
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un IhmState soit désérialisé du fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void readObjectIhmState( String filepath ) {
        IhmState state = getRefWrk().readObjectAppState( filepath );
        if ( state != null ) {
            getRefIhm().showInformationMessage( "Désérialisation du IhmState depuis le fichier [" + filepath + "] OK !" );
            getRefIhm().showInformationMessage( "Cet état va maintenant être appliqué à l'Ihm..." );
            getRefIhm().setIhmState( state );
        }
        else {
            getRefIhm().showErrorMessage( "La désérialisation du IhmState depuis le fichier [" + filepath + "] a échoué !" );
        }
    }

    /**
     * Cette méthode est appelée par l'ihm pour qu'un IhmState soit sérialisé dans le fichier spécifié.
     *
     * @param filepath le chemin complet du fichier à utiliser
     */
    public void writeObjectIhmState( String filepath ) {
        IhmState state = getRefIhm().getIhmState();
        if ( getRefWrk().writeObjectAppState( filepath, state ) ) {
            getRefIhm().showInformationMessage( "Sérialisation du IhmState dans le fichier [" + filepath + "] OK !" );
        }
        else {
            getRefIhm().showErrorMessage( "La sérialisation du IhmState dans le fichier [" + filepath + "] a échoué !" );
        }
    }

    /**
     * Getter de la référence au worker principal de l'application.
     *
     * @return la référence au worker principal de l'application
     */
    public IWrkCtrl getRefWrk() {
        return refWrk;
    }

    /**
     * Setter de la référence au worker principal de l'application.
     * @param refWrk la nouvelle référence au worker principal de l'application
     */
    public void setRefWrk( IWrkCtrl refWrk ) {
        this.refWrk = refWrk;
    }

    /**
     * Getter de la référence à l'ihm de l'application.
     *
     * @return la référence à l'ihm de l'application.
     */
    public IIhmCtrl getRefIhm() {
        return refIhm;
    }

    /**
     * Setter de la référence à l'ihm de l'application.
     * @param refWrk la nouvelle référence à l'ihm de l'application
     */
    public void setRefIhm( IIhmCtrl refIhm ) {
        this.refIhm = refIhm;
    }
    /**
     * La référence au worker principal de l'application.
     */
    private IWrkCtrl refWrk;
    /**
     * La référence à l'ihm de l'application.
     */
    private IIhmCtrl refIhm;
}
