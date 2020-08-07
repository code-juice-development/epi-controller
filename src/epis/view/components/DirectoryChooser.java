package epis.view.components;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Jean Poffo
 * @since  04/09/2020
 */
public class DirectoryChooser extends JFileChooser {

    public DirectoryChooser(String title) {
        this.setDialogTitle(title);
        this.initComponent();
    }
    
    private void initComponent() {
        this.setCurrentDirectory(new File("."));
        this.setApproveButtonText("Salvar");
        this.setFileSelectionMode(DIRECTORIES_ONLY);
        this.setAcceptAllFileFilterUsed(false);
    }
    
    public boolean callSelectDirectory() {
        return this.showOpenDialog(null) == APPROVE_OPTION;
    }
    
}
