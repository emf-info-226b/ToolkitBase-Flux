package fluxtoolkitbase;

import fluxtoolkitbase.ctrl.Ctrl;
import fluxtoolkitbase.ihm.Ihm;
import fluxtoolkitbase.wrk.Wrk;

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
 * Cette classe implémente le main() de l'application où le contrôleur,
 * l'ihm et le workeur son créés, mis en relation puis démarrés.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class Main {

    /**
     * Notre implémentation du main() de l'application.
     *
     * @param args les paramètres sur la ligne de commande
     */
    public static void main( String[] args ) {
        Ihm ihm = new Ihm();
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();
        ctrl.setRefWrk( wrk );
        ctrl.setRefIhm( ihm );
        ihm.setRefCtrl( ctrl );
        ctrl.start();
    }
}
