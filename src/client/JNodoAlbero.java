package client;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe usata per visualizzare il file system graficamente
 *
 */

public class JNodoAlbero extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;
    public int id;
    public boolean isFolder;

    public JNodoAlbero(String nomeNodo, int refID, boolean isFolder) {
        super(nomeNodo);
        id = refID;
        this.isFolder = isFolder;
    }
}
