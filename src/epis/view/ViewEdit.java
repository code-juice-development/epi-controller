package epis.view;

import epis.controller.ProcessAction;
import epis.models.DataObject;
import epis.utils.MessageUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * View Edit Base
 * @author Jean Poffo
 * @since  30/08/2020
 */
public abstract class ViewEdit extends JFrame implements View {
    
    private JPanel panelButtons;
    private JButton buttonOk;
    private JButton buttonCancel;
    
    protected JPanel panelComponents;
    
    protected DataObject dataObject;

    protected ProcessAction process;
    
    private final List<String> errors = new ArrayList();
    
    public ViewEdit(DataObject dataObject, ProcessAction process) {
        this.dataObject = dataObject;
        this.process = process;
        
        this.initComponents();
        this.addListeners();
        
        this.setIconImage(new ImageIcon(getClass().getResource("/epis/resources/helmet.png")).getImage());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.bindDataToFields();
    }

    public JPanel getPanelComponents() {
        return this.panelComponents;
    }    

    public ProcessAction getProcess() {
        return this.process;
    }
    
    protected DataObject getDataObject() {
        return this.dataObject;
    }

    protected void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }
    
    protected void addError(String error) {
        this.errors.add(error);
    }
    
    @Override
    public void initComponents() {
        this.panelComponents = new JPanel();
        this.panelButtons = new JPanel();
        this.buttonOk = new JButton();
        this.buttonCancel = new JButton();
        
        this.panelComponents.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EmptyBorder(0, 0, 0, 0)));
        this.panelComponents.setLayout(new BoxLayout(this.panelComponents, BoxLayout.Y_AXIS));
        
        this.getContentPane().add(this.panelComponents, BorderLayout.CENTER);

        this.panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 6, 5, 0));
        this.panelButtons.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.buttonOk.setText("Salvar");
        this.buttonOk.setPreferredSize(new Dimension(100, 30));
        this.panelButtons.add(this.buttonOk);

        this.buttonCancel.setText("Cancelar");
        this.buttonCancel.setPreferredSize(new Dimension(100, 30));
        this.panelButtons.add(this.buttonCancel);

        this.getContentPane().add(this.panelButtons, BorderLayout.SOUTH);

        this.createCustomFields();
        
        this.pack();
    }

    @Override
    public void addListeners() {
        this.buttonOk.addActionListener(e -> this.callProcess());
        this.buttonCancel.addActionListener(e -> this.dispose());
    }
    
    protected void callProcess() {
        if(this.validatedData()) {
            this.bindDataFromFields();
            this.getProcess().execute(this.getDataObject());
            this.dispose();
        }
    }
    
    public abstract void createCustomFields();
    
    public abstract void bindDataToFields();
    
    public abstract void bindDataFromFields();
    
    protected boolean validatedData() {
        if(!this.errors.isEmpty()) {
            String errorMessage = "";
            
            for (String error : this.errors) {
                errorMessage += "\n • " + error;
            }
            MessageUtil.warning("Erro ao inserir o registro: " + errorMessage, "Verifique os Valores");
            
            this.errors.clear();
            
            return false;
        }
        
        return true;
    }
    
}
