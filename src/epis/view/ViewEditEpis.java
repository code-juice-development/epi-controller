package epis.view;

import epis.controller.ProcessAction;
import epis.models.DataObject;
import epis.models.Epi;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * View Edit of Epis
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class ViewEditEpis extends ViewEdit {

    private JPanel panelEpi;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelCodeCa;
    private JTextField textFieldCodeCa;
    private JLabel labelFrequency;
    private JComboBox comboBoxFrequency;
    
    public ViewEditEpis(DataObject entity, ProcessAction process) {
        super(entity, process);
        
        this.setTitle("EPI");
        this.setSize(300, 220);
    }
    
    @Override
    public void createCustomFields() {
        this.panelEpi = new JPanel();
        this.labelName = new JLabel();
        this.textFieldName = new JTextField();
        this.labelCodeCa = new JLabel();
        this.textFieldCodeCa = new JTextField();
        this.labelFrequency = new JLabel();
        this.comboBoxFrequency = new JComboBox(new String[] {"30", "60", "90"});
        
        this.labelName.setText("Nome:");
        this.labelCodeCa.setText("Código CA:");
        this.labelFrequency.setText("Periodicidade:");
        
        this.panelEpi.setBorder(BorderFactory.createTitledBorder("Dados do EPI"));
        
        GroupLayout groupLayout = new GroupLayout(this.panelEpi);
        
        this.panelEpi.setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.labelName)
            .addComponent(this.labelCodeCa)
            .addComponent(this.labelFrequency)
        );
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.textFieldName)
            .addComponent(this.textFieldCodeCa)
            .addComponent(this.comboBoxFrequency)
        );
        
        groupLayout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelName).addComponent(this.textFieldName));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelCodeCa).addComponent(this.textFieldCodeCa));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelFrequency).addComponent(this.comboBoxFrequency));
        groupLayout.setVerticalGroup(vGroup);
        
        this.panelComponents.add(this.panelEpi);
    }

    @Override
    public void bindDataToFields() {
        Epi epi = (Epi) this.getDataObject();
        
        this.textFieldName.setText(epi.getName());
        this.textFieldCodeCa.setText(epi.getCodeCa());
        this.comboBoxFrequency.setSelectedItem(String.valueOf(epi.getFrequency()));
    }

    @Override
    public void bindDataFromFields() {
        Epi epi = (Epi) this.getDataObject();
        
        epi.setName(this.textFieldName.getText());
        epi.setCodeCa(this.textFieldCodeCa.getText());
        epi.setFrequency(Integer.parseInt((String) this.comboBoxFrequency.getSelectedItem()));
    }

    @Override
    public boolean validatedData() {
        if(this.textFieldName.getText().isEmpty()) {
            this.addError("Não foi informado o nome do EPI");
        }
        
        if(this.textFieldCodeCa.getText().isEmpty()) {
            this.addError("Não foi informado o Código CA do EPI");
        }
        
        return super.validatedData();
    }
    
}
