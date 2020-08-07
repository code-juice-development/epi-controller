package epis.view;

import epis.controller.ProcessAction;
import epis.models.DataObject;
import epis.models.Employee;
import epis.models.Epi;
import epis.utils.MessageUtil;
import epis.utils.StringUtil;
import epis.view.components.ExternFieldEpis;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 * View Edit of Employees
 * @author Jean Poffo
 * @since  30/08/2020
 */
public class ViewEditEmployees extends ViewEdit {

    private JPanel panelEmployee;
    private JPanel panelEpis;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelCpf;
    private JFormattedTextField textFieldCpf;
    private JLabel labelFunction;
    private JTextField textFieldOccupation;
    private JCheckBox checkBoxActive;
    
    private JButton buttonAdd;
    private JButton buttonRemove;
    private DefaultTableModel tableEpisModel;
    private JScrollPane scrollPanelTableEpis;
    private JTable tableEpis;
    
    public ViewEditEmployees(DataObject entity, ProcessAction process) {
        super(entity, process);
        
        this.setTitle("Funcionários");
    }
    
    @Override
    public void createCustomFields() {
        this.createPanelEpis();
        this.createPanelEmployee();
        
        this.panelComponents.add(this.panelEmployee);
        this.panelComponents.add(this.panelEpis);
    }

    private void createPanelEmployee() {
        this.panelEmployee = new JPanel();
        this.labelName = new JLabel();
        this.textFieldName = new JTextField();
        this.labelCpf = new JLabel();
        
        try {
            this.textFieldCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } 
        catch (ParseException ex) {}
        
        this.labelFunction = new JLabel();
        this.textFieldOccupation = new JTextField();
        this.checkBoxActive = new JCheckBox();
        
        this.labelName.setText("Nome:");
        this.labelCpf.setText("CPF:");
        this.labelFunction.setText("Função:");
        
        this.checkBoxActive.setText("Funcionário Ativo?");
        this.checkBoxActive.setSelected(true);
        
        this.panelEmployee.setBorder(BorderFactory.createTitledBorder("Dados do Funcionário"));
        
        GroupLayout groupLayout = new GroupLayout(this.panelEmployee);
        
        this.panelEmployee.setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.labelName)
            .addComponent(this.labelCpf)
            .addComponent(this.labelFunction)
        );
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.textFieldName)
            .addComponent(this.textFieldCpf)
            .addComponent(this.textFieldOccupation)
            .addComponent(this.checkBoxActive)
        );
        groupLayout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelName).addComponent(this.textFieldName));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelCpf).addComponent(this.textFieldCpf));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.labelFunction).addComponent(this.textFieldOccupation));
        vGroup.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.checkBoxActive));
        
        groupLayout.setVerticalGroup(vGroup);
    }
    
    private void createPanelEpis() {
        this.panelEpis = new JPanel();
        this.buttonAdd = new JButton();
        this.buttonRemove = new JButton();
        this.scrollPanelTableEpis = new JScrollPane();
        this.tableEpisModel = new DefaultTableModel();
        this.tableEpis = new JTable();
        
        this.panelEpis.setBorder(BorderFactory.createTitledBorder("EPI's"));
        
        this.buttonAdd.setText("Adicionar");
        this.buttonAdd.setPreferredSize(new Dimension(100, 30));
        
        this.buttonRemove.setText("Remover");
        this.buttonRemove.setPreferredSize(new Dimension(100, 30));
        
        this.scrollPanelTableEpis.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), new EtchedBorder()));
        this.scrollPanelTableEpis.setViewportView(this.tableEpis);
        
        GroupLayout groupLayout = new GroupLayout(this.panelEpis);
        
        this.panelEpis.setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.scrollPanelTableEpis, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(1, 1, 10)
                        .addComponent(this.buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.buttonRemove)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buttonAdd)
                    .addComponent(this.buttonRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(this.scrollPanelTableEpis, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        this.tableEpisModel.addColumn("Código");
        this.tableEpisModel.addColumn("Nome");
        this.tableEpisModel.addColumn("Código CA");
        
        this.tableEpis.setModel(tableEpisModel);
        
        /* Hide Column Código */
        this.tableEpis.removeColumn(this.tableEpis.getColumnModel().getColumn(0));
        this.tableEpis.getColumnModel().getColumn(1).setWidth(60);
    }

    @Override
    public void addListeners() {
        super.addListeners();
        
        this.buttonAdd.addActionListener(e -> this.addItemTableEpi());
        this.buttonRemove.addActionListener(e -> this.removeItemTableEpi());
    }
    
    protected void addItemTableEpi() {
        List<Long> listEpiIdFilter = new ArrayList();
        
        for (int row = 0; row < this.tableEpisModel.getRowCount(); row++) {
            listEpiIdFilter.add(Long.parseLong(String.valueOf(this.tableEpisModel.getValueAt(row, 0))));
        }
        
        Epi selectedEpi = (Epi) MessageUtil.questExternField(new ExternFieldEpis(listEpiIdFilter), "Selecione o EPI");
        
        if(selectedEpi != null) {
            this.tableEpisModel.addRow(new String[] {
                String.valueOf(selectedEpi.getId()), 
                selectedEpi.getName(),
                selectedEpi.getCodeCa(),
            });
        }
    }
    
    protected void removeItemTableEpi() {
        int selectedRow = this.tableEpis.getSelectedRow();
        
        if(selectedRow != -1) {
            this.tableEpisModel.removeRow(selectedRow);
        }
        else {
            MessageUtil.warning("Nenhum registro selecionado", "Aviso");
        }
    }
    
    @Override
    public void bindDataToFields() {
        Employee employee = (Employee) this.getDataObject();
        
        this.textFieldName.setText(employee.getName());
        this.textFieldCpf.setText(employee.getCpf());
        this.textFieldOccupation.setText(employee.getOccupation());
        this.checkBoxActive.setSelected(employee.isActive());
        
        for (Epi epi : employee.getEpis()) {
            this.tableEpisModel.addRow(new String[] {
                String.valueOf(epi.getId()), 
                epi.getName(),
                epi.getCodeCa(),
            });
        }
    }

    @Override
    public void bindDataFromFields() {
        Employee employee = (Employee) this.getDataObject();
        
        employee.setName(this.textFieldName.getText());
        employee.setCpf(StringUtil.removeStrangeCharacter(this.textFieldCpf.getText()));
        employee.setOccupation(this.textFieldOccupation.getText());        
        employee.setActive(this.checkBoxActive.isSelected());        
        
        employee.getEpis().clear();
        
        for (int row = 0; row < this.tableEpisModel.getRowCount(); row++) {
            employee.getEpis().add(new Epi(Long.parseLong(String.valueOf(this.tableEpisModel.getValueAt(row, 0)))));
        }
    }

    @Override
    public boolean validatedData() {
        if(this.textFieldName.getText().isEmpty()) {
            this.addError("Não foi informado o nome do Funcionário");
        }
        
        if(StringUtil.removeStrangeCharacter(this.textFieldCpf.getText()).isEmpty()) {
            this.addError("Não foi informada o CPF do Funcionário");
        }
        else if(!StringUtil.validateCpf(this.textFieldCpf.getText())) {
            this.addError("Não foi informado um CPF válido para o Funcionário");
        }
        
        if(this.textFieldOccupation.getText().isEmpty()) {
            this.addError("Não foi informada a função do Funcionário");
        }
        
        return super.validatedData();
    }
    
}
