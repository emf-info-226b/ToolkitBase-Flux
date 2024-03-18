package fluxtoolkitbase.ihm;

import fluxtoolkitbase.beans.IhmState;
import fluxtoolkitbase.ctrl.ICtrlIhm;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

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
 * Cette classe renferme l'implémentation de l'Ihm principale de l'application.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class Ihm extends javax.swing.JFrame implements IIhmCtrl {

    /**
     * Le constructeur de la classe Ihm.
     */
    public Ihm() {
        // Make it look like a native Windows application
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e ) {
        }

        // Init Ihm components
        initComponents();

        // Application title
        setTitle( "Module 226 / Boîte à outils flux (texte, binaire, sérialisation) v1.0 - by Paul Friedli" );

        //
        // Application icon
        //
        ArrayList<Image> iconImages = new ArrayList<Image>();
        iconImages.add( new ImageIcon( getClass().getResource( "resources/appIcon48.png" ) ).getImage() );
        iconImages.add( new ImageIcon( getClass().getResource( "resources/appIcon32.png" ) ).getImage() );
        iconImages.add( new ImageIcon( getClass().getResource( "resources/appIcon22.png" ) ).getImage() );
        iconImages.add( new ImageIcon( getClass().getResource( "resources/appIcon16.png" ) ).getImage() );
        setIconImages( iconImages );

        // Set frame minimum and maximum size
        setMinimumSize( new Dimension( 750, 480 ) );
//        setMaximumSize( new Dimension( 800, 800 ) ); // Java bug, doesn't work

        // Make sure we catch exit events to store the state
        addWindowListener( new WindowAdapter() {

            @Override
            public void windowClosing( WindowEvent we ) {
                ihmExiting();
            }
        } );
    }

    /**
     * Cette méthode est appelée pour indiquer que l'application est en train de se fermer.
     * Cela permet d'indiquer au contrôleur cet état de fait.
     */
    public void ihmExiting() {
        getRefCtrl().ihmExiting();
        dispose();
    }

    /**
     * Cette méthode permet de rendre l'ihm visible à l'écran.
     */
    public void display() {
        setVisible( true );
    }

    /**
     * Cette méthode permet d'afficher un message d'information à l'utilisateur.
     *
     * @param message le message à afficher
     */
    public void showInformationMessage( String message ) {
        JOptionPane.showMessageDialog( this, message, "Information", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Cette méthode permet d'afficher un message d'erreur à l'utilisateur.
     *
     * @param message le message à afficher
     */
    public void showErrorMessage( String message ) {
        JOptionPane.showMessageDialog( this, message, "Erreur", JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Cette méthode permet de poser une question à l'utilisateur à laquelle il devra répondre par oui ou par non.
     *
     * @param message la question à afficher
     * @return true si l'utilisateur presse le bouton "oui", sinon false
     */
    public boolean askYesNoQuestion( String yesNoQuestion ) {
        int n = JOptionPane.showConfirmDialog( this, yesNoQuestion, "Question", JOptionPane.YES_NO_OPTION );
        return ( n == JOptionPane.YES_OPTION );
    }

    /**
     * Cette méthode permet de demander la saisie d'une chaîne de caractères à l'utilisateur.
     *
     * @param message la question
     * @return la chaîne que l'utilisateur a entrée, null s'il annule l'opération
     */
    public String askTextString( String message ) {
        return JOptionPane.showInputDialog( message );
    }

    /**
     * Cette méthode remplace le contenu de notre zone d'édition multiligne de texte (notre JTextArea)
     * par les lignes passées en paramètre.
     *
     * @param lignes les lignes à afficher
     */
    public void setTextContent( ArrayList<String> lignes ) {
        String content = "";

        if ( lignes != null ) {
            for ( String ligne : lignes ) {
                if ( !content.isEmpty() ) {
                    content += "\n";
                }
                content += ligne;
            }
        }
        jTextAreaTextContent.setText( content );
    }

    /**
     * Cette méthode permet de remplacer le dessin actuel par un nouveau dessin.
     *
     * @param data le nouveau dessin
     */
    public void setBinaryContent( boolean[][] data ) {
        ( (PenDrawPanel)jPanelBinaryDrawZone ).setData( data );
    }

    /**
     * Cette méthode permet d'obtenir l'état actuel de l'ihm sous forme
     * d'un objet transportant l'ensemble des informations souhaitées.
     *
     * @return un objet de type IhmState encapsulant l'état actuel de l'ihm
     */
    public IhmState getIhmState() {
        return new IhmState( jTextFieldTextFilename.getText(), jTextFieldBinaryFilename.getText(), jTextFieldObjectFilename.getText(),
                             jTabbedPaneStreamType.getSelectedIndex(), getWidth(), getHeight(), getLocation() );
    }

    /**
     * Cette méthode permet de remettre l'ihm en l'état correspondant à celui passé en paramètres.
     *
     * @param state le nouvel état de l'ihm
     */
    public void setIhmState( IhmState state ) {
        jTextFieldTextFilename.setText( state.getLastTextFilePath() );
        jTextFieldBinaryFilename.setText( state.getLastBinaryFilePath() );
        jTextFieldObjectFilename.setText( state.getLastObjectFilePath() );
        jTabbedPaneStreamType.setSelectedIndex( state.getLastSelectedTab() );
        setSize( state.getWidth(), state.getHeight() );
        setLocation( state.getLocation() );
    }

    /**
     * Cette méthode permet de demander à l'utilisateur le nom de fichier à utiliser lors la sauvegarde.
     *
     * @param desiredExtension l'extension à utiliser pour le fichier
     * @param filetypeDescription la description textuelle de cette extension de fichier (pour l'utilisateur)
     * @return le chemin complet du fichier sélectionné/défini ou null en cas d'annullation
     */
    private String getSaveFileNameCompletePath( String desiredExtension, String filetypeDescription ) {
        String result = null;

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
        fc.setAcceptAllFileFilterUsed( false );
        FileNameExtensionFilter filter = new FileNameExtensionFilter( filetypeDescription, desiredExtension );
        fc.setFileFilter( filter );
        fc.setApproveButtonText( "Set" );
        fc.setApproveButtonMnemonic( 's' );
        fc.setApproveButtonToolTipText( "Set file" );

        int returnVal = fc.showSaveDialog( this );
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            File file = fc.getSelectedFile();
            result = file.getAbsolutePath();
            if ( !result.endsWith( "." + desiredExtension ) ) {
                result += "." + desiredExtension;
            }
        }

        return result;
    }

    /**
     * Getter de la référence au contrôleur de l'application.
     * 
     * @return référence au contrôleur de l'application
     */
    public ICtrlIhm getRefCtrl() {
        return refCtrl;
    }

    /**
     * Setter de la référence au contrôleur de l'application.
     *
     * @param refCtrl la référence au contrôleur de l'application
     */
    public void setRefCtrl( ICtrlIhm refCtrl ) {
        this.refCtrl = refCtrl;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneStreamType = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTextFilename = new javax.swing.JTextField();
        jButtonTextFilenameSelect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTextContent = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldTextNewLine = new javax.swing.JTextField();
        jButtonTextAddNewLine = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButtonTextFileRead = new javax.swing.JButton();
        jButtonTextFileWrite = new javax.swing.JButton();
        jButtonTextFileExists = new javax.swing.JButton();
        jButtonTextFileDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanelBinaryDrawZone = new PenDrawPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBinaryFilename = new javax.swing.JTextField();
        jButtonBinaryFilenameSelect = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jButtonBinarySetAllBlack = new javax.swing.JButton();
        jButtonBinarySetAllWhite = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButtonBinaryFileRead = new javax.swing.JButton();
        jButtonBinaryFileWrite = new javax.swing.JButton();
        jButtonBinaryFileExists = new javax.swing.JButton();
        jButtonBinaryFileDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldObjectFilename = new javax.swing.JTextField();
        jButtonObjectFilenameSelect = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButtonObjectFileExists = new javax.swing.JButton();
        jButtonObjectFileDelete = new javax.swing.JButton();
        jButtonObjectFileReadString = new javax.swing.JButton();
        jButtonObjectFileWriteString = new javax.swing.JButton();
        jButtonObjectFileReadStringArray = new javax.swing.JButton();
        jButtonObjectFileWriteStringArray = new javax.swing.JButton();
        jButtonObjectFileReadAppState = new javax.swing.JButton();
        jButtonObjectFileWriteAppState = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Fichier texte ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jLabel3.setText("Fichier texte");

        jButtonTextFilenameSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/folder_16.png"))); // NOI18N
        jButtonTextFilenameSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextFilenameSelectActionPerformed(evt);
            }
        });

        jLabel4.setText("Contenu du fichier texte");

        jTextAreaTextContent.setColumns(20);
        jTextAreaTextContent.setRows(5);
        jScrollPane1.setViewportView(jTextAreaTextContent);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTextFilename, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTextFilenameSelect))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTextFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTextFilenameSelect)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Fonctions ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Appondre une ligne ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jButtonTextAddNewLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_add_32.png"))); // NOI18N
        jButtonTextAddNewLine.setText("Rajouter ligne en fin de fichier");
        jButtonTextAddNewLine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTextAddNewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextAddNewLineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonTextAddNewLine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(jTextFieldTextNewLine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldTextNewLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTextAddNewLine)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setLayout(new java.awt.GridLayout(2, 2));

        jButtonTextFileRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_read_32.png"))); // NOI18N
        jButtonTextFileRead.setText("Lire fichier texte");
        jButtonTextFileRead.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTextFileRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextFileReadActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonTextFileRead);

        jButtonTextFileWrite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_save_32.png"))); // NOI18N
        jButtonTextFileWrite.setText("Ecrire fichier texte");
        jButtonTextFileWrite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTextFileWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextFileWriteActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonTextFileWrite);

        jButtonTextFileExists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_info_32.png"))); // NOI18N
        jButtonTextFileExists.setText("Le fichier existe-t-il ?");
        jButtonTextFileExists.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTextFileExists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextFileExistsActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonTextFileExists);

        jButtonTextFileDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_delete_32.png"))); // NOI18N
        jButtonTextFileDelete.setText("Supprimer le fichier");
        jButtonTextFileDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTextFileDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTextFileDeleteActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonTextFileDelete);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPaneStreamType.addTab("Flux fichiers texte", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Fichier binaire ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanelBinaryDrawZone.setBackground(new java.awt.Color(0, 82, 147));
        jPanelBinaryDrawZone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 82, 147)));

        javax.swing.GroupLayout jPanelBinaryDrawZoneLayout = new javax.swing.GroupLayout(jPanelBinaryDrawZone);
        jPanelBinaryDrawZone.setLayout(jPanelBinaryDrawZoneLayout);
        jPanelBinaryDrawZoneLayout.setHorizontalGroup(
            jPanelBinaryDrawZoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        jPanelBinaryDrawZoneLayout.setVerticalGroup(
            jPanelBinaryDrawZoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );

        jLabel2.setText("Contenu du fichier binaire (dessin) - cliquez pour dessiner !");

        jLabel5.setText("Fichier");

        jButtonBinaryFilenameSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/folder_16.png"))); // NOI18N
        jButtonBinaryFilenameSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinaryFilenameSelectActionPerformed(evt);
            }
        });

        jPanel12.setLayout(new java.awt.GridLayout(1, 2, 12, 0));

        jButtonBinarySetAllBlack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/blue_32.png"))); // NOI18N
        jButtonBinarySetAllBlack.setText("Remplir");
        jButtonBinarySetAllBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinarySetAllBlackActionPerformed(evt);
            }
        });
        jPanel12.add(jButtonBinarySetAllBlack);

        jButtonBinarySetAllWhite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/white_32.png"))); // NOI18N
        jButtonBinarySetAllWhite.setText("Remplir");
        jButtonBinarySetAllWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinarySetAllWhiteActionPerformed(evt);
            }
        });
        jPanel12.add(jButtonBinarySetAllWhite);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBinaryDrawZone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBinaryFilename, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBinaryFilenameSelect))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBinaryFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBinaryFilenameSelect)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBinaryDrawZone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Fonctions ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanel11.setLayout(new java.awt.GridLayout(2, 2));

        jButtonBinaryFileRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_read_32.png"))); // NOI18N
        jButtonBinaryFileRead.setText("Lire fichier binaire");
        jButtonBinaryFileRead.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonBinaryFileRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinaryFileReadActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonBinaryFileRead);

        jButtonBinaryFileWrite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_save_32.png"))); // NOI18N
        jButtonBinaryFileWrite.setText("Ecrire fichier binaire");
        jButtonBinaryFileWrite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonBinaryFileWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinaryFileWriteActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonBinaryFileWrite);

        jButtonBinaryFileExists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_info_32.png"))); // NOI18N
        jButtonBinaryFileExists.setText("Le fichier existe-t-il ?");
        jButtonBinaryFileExists.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonBinaryFileExists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinaryFileExistsActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonBinaryFileExists);

        jButtonBinaryFileDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_delete_32.png"))); // NOI18N
        jButtonBinaryFileDelete.setText("Supprimer le fichier");
        jButtonBinaryFileDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonBinaryFileDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBinaryFileDeleteActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonBinaryFileDelete);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(145, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPaneStreamType.addTab("Flux fichiers binaires", jPanel2);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Sérialisation d'objets Java ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jLabel7.setText("Fichier");

        jButtonObjectFilenameSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/folder_16.png"))); // NOI18N
        jButtonObjectFilenameSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFilenameSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldObjectFilename, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonObjectFilenameSelect)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldObjectFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonObjectFilenameSelect)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Fonctions ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanel15.setLayout(new java.awt.GridLayout(4, 2));

        jButtonObjectFileExists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_info_32.png"))); // NOI18N
        jButtonObjectFileExists.setText("Le fichier existe-t-il ?");
        jButtonObjectFileExists.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileExists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileExistsActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileExists);

        jButtonObjectFileDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_delete_32.png"))); // NOI18N
        jButtonObjectFileDelete.setText("Supprimer le fichier");
        jButtonObjectFileDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileDeleteActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileDelete);

        jButtonObjectFileReadString.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_read_32.png"))); // NOI18N
        jButtonObjectFileReadString.setText("Lire un String");
        jButtonObjectFileReadString.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileReadString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileReadStringActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileReadString);

        jButtonObjectFileWriteString.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_save_32.png"))); // NOI18N
        jButtonObjectFileWriteString.setText("Ecrire un String");
        jButtonObjectFileWriteString.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileWriteString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileWriteStringActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileWriteString);

        jButtonObjectFileReadStringArray.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_read_32.png"))); // NOI18N
        jButtonObjectFileReadStringArray.setText("Lire un ArrayList<String>");
        jButtonObjectFileReadStringArray.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileReadStringArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileReadStringArrayActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileReadStringArray);

        jButtonObjectFileWriteStringArray.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_save_32.png"))); // NOI18N
        jButtonObjectFileWriteStringArray.setText("Ecrire un ArrayList<String>");
        jButtonObjectFileWriteStringArray.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileWriteStringArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileWriteStringArrayActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileWriteStringArray);

        jButtonObjectFileReadAppState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_read_32.png"))); // NOI18N
        jButtonObjectFileReadAppState.setText("Lire l'état de l'Ihm");
        jButtonObjectFileReadAppState.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileReadAppState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileReadAppStateActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileReadAppState);

        jButtonObjectFileWriteAppState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/file_save_32.png"))); // NOI18N
        jButtonObjectFileWriteAppState.setText("Ecrire l'état de l'Ihm");
        jButtonObjectFileWriteAppState.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonObjectFileWriteAppState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObjectFileWriteAppStateActionPerformed(evt);
            }
        });
        jPanel15.add(jButtonObjectFileWriteAppState);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPaneStreamType.addTab("Sérialisation d'objets Java", jPanel3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fluxtoolkitbase/ihm/resources/appIcon48.png"))); // NOI18N
        jLabel1.setText("Boîte à outils flux et sérialisation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPaneStreamType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPaneStreamType, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTextFilenameSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextFilenameSelectActionPerformed
        String filepath = getSaveFileNameCompletePath( "txt", "Fichier texte" );
        jTextFieldTextFilename.setText( filepath );
    }//GEN-LAST:event_jButtonTextFilenameSelectActionPerformed

    private void jButtonBinaryFilenameSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinaryFilenameSelectActionPerformed
        String filepath = getSaveFileNameCompletePath( "dat", "Fichier binaire" );
        jTextFieldBinaryFilename.setText( filepath );
    }//GEN-LAST:event_jButtonBinaryFilenameSelectActionPerformed

    private void jButtonObjectFilenameSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFilenameSelectActionPerformed
        String filepath = getSaveFileNameCompletePath( "object", "Fichier objets Java" );
        jTextFieldObjectFilename.setText( filepath );
    }//GEN-LAST:event_jButtonObjectFilenameSelectActionPerformed

    private void jButtonTextFileExistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextFileExistsActionPerformed
        getRefCtrl().checkIfFileExists( jTextFieldTextFilename.getText() );
    }//GEN-LAST:event_jButtonTextFileExistsActionPerformed

    private void jButtonBinaryFileExistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinaryFileExistsActionPerformed
        getRefCtrl().checkIfFileExists( jTextFieldBinaryFilename.getText() );
    }//GEN-LAST:event_jButtonBinaryFileExistsActionPerformed

    private void jButtonObjectFileExistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileExistsActionPerformed
        getRefCtrl().checkIfFileExists( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileExistsActionPerformed

    private void jButtonTextFileDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextFileDeleteActionPerformed
        getRefCtrl().deleteFile( jTextFieldTextFilename.getText() );
    }//GEN-LAST:event_jButtonTextFileDeleteActionPerformed

    private void jButtonBinaryFileDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinaryFileDeleteActionPerformed
        getRefCtrl().deleteFile( jTextFieldBinaryFilename.getText() );
    }//GEN-LAST:event_jButtonBinaryFileDeleteActionPerformed

    private void jButtonObjectFileDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileDeleteActionPerformed
        getRefCtrl().deleteFile( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileDeleteActionPerformed

    private void jButtonTextFileReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextFileReadActionPerformed
        getRefCtrl().readTextFile( jTextFieldTextFilename.getText() );
    }//GEN-LAST:event_jButtonTextFileReadActionPerformed

    private void jButtonTextFileWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextFileWriteActionPerformed
        String textFileContent = jTextAreaTextContent.getText();
        String[] lines = textFileContent.split( "\n" );
        ArrayList<String> linesToWrite = new ArrayList<String>( Arrays.asList( lines ) );
        getRefCtrl().writeTextFile( jTextFieldTextFilename.getText(), linesToWrite );
    }//GEN-LAST:event_jButtonTextFileWriteActionPerformed

    private void jButtonTextAddNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextAddNewLineActionPerformed
        getRefCtrl().appendToTextFile( jTextFieldTextFilename.getText(), jTextFieldTextNewLine.getText() );
    }//GEN-LAST:event_jButtonTextAddNewLineActionPerformed

    private void jButtonBinarySetAllBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinarySetAllBlackActionPerformed
        ( (PenDrawPanel)jPanelBinaryDrawZone ).clearAll();
    }//GEN-LAST:event_jButtonBinarySetAllBlackActionPerformed

    private void jButtonBinarySetAllWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinarySetAllWhiteActionPerformed
        ( (PenDrawPanel)jPanelBinaryDrawZone ).fillAll();
    }//GEN-LAST:event_jButtonBinarySetAllWhiteActionPerformed

    private void jButtonBinaryFileReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinaryFileReadActionPerformed
        getRefCtrl().readBinaryFile( jTextFieldBinaryFilename.getText() );
    }//GEN-LAST:event_jButtonBinaryFileReadActionPerformed

    private void jButtonBinaryFileWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBinaryFileWriteActionPerformed
        getRefCtrl().writeBinaryFile( jTextFieldBinaryFilename.getText(), ( (PenDrawPanel)jPanelBinaryDrawZone ).getData() );
    }//GEN-LAST:event_jButtonBinaryFileWriteActionPerformed

    private void jButtonObjectFileReadStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileReadStringActionPerformed
        getRefCtrl().readObjectString( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileReadStringActionPerformed

    private void jButtonObjectFileWriteStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileWriteStringActionPerformed
        getRefCtrl().writeObjectString( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileWriteStringActionPerformed

    private void jButtonObjectFileReadStringArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileReadStringArrayActionPerformed
        getRefCtrl().readObjectStringArray( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileReadStringArrayActionPerformed

    private void jButtonObjectFileWriteStringArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileWriteStringArrayActionPerformed
        getRefCtrl().writeObjectStringArray( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileWriteStringArrayActionPerformed

    private void jButtonObjectFileReadAppStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileReadAppStateActionPerformed
        getRefCtrl().readObjectIhmState( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileReadAppStateActionPerformed

    private void jButtonObjectFileWriteAppStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObjectFileWriteAppStateActionPerformed
        getRefCtrl().writeObjectIhmState( jTextFieldObjectFilename.getText() );
    }//GEN-LAST:event_jButtonObjectFileWriteAppStateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBinaryFileDelete;
    private javax.swing.JButton jButtonBinaryFileExists;
    private javax.swing.JButton jButtonBinaryFileRead;
    private javax.swing.JButton jButtonBinaryFileWrite;
    private javax.swing.JButton jButtonBinaryFilenameSelect;
    private javax.swing.JButton jButtonBinarySetAllBlack;
    private javax.swing.JButton jButtonBinarySetAllWhite;
    private javax.swing.JButton jButtonObjectFileDelete;
    private javax.swing.JButton jButtonObjectFileExists;
    private javax.swing.JButton jButtonObjectFileReadAppState;
    private javax.swing.JButton jButtonObjectFileReadString;
    private javax.swing.JButton jButtonObjectFileReadStringArray;
    private javax.swing.JButton jButtonObjectFileWriteAppState;
    private javax.swing.JButton jButtonObjectFileWriteString;
    private javax.swing.JButton jButtonObjectFileWriteStringArray;
    private javax.swing.JButton jButtonObjectFilenameSelect;
    private javax.swing.JButton jButtonTextAddNewLine;
    private javax.swing.JButton jButtonTextFileDelete;
    private javax.swing.JButton jButtonTextFileExists;
    private javax.swing.JButton jButtonTextFileRead;
    private javax.swing.JButton jButtonTextFileWrite;
    private javax.swing.JButton jButtonTextFilenameSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelBinaryDrawZone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPaneStreamType;
    private javax.swing.JTextArea jTextAreaTextContent;
    private javax.swing.JTextField jTextFieldBinaryFilename;
    private javax.swing.JTextField jTextFieldObjectFilename;
    private javax.swing.JTextField jTextFieldTextFilename;
    private javax.swing.JTextField jTextFieldTextNewLine;
    // End of variables declaration//GEN-END:variables
    private ICtrlIhm refCtrl;
}
