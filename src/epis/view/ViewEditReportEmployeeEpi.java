package epis.view;

import epis.controller.ProcessAction;
import epis.models.DataObject;
import epis.models.Employee;
import epis.models.ReportEmployeeEpi;
import epis.utils.StringUtil;
import epis.view.components.ExternFieldEmployees;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * View Class of Report
 * @author Jean Poffo
 * @since  03/09/2020
 */
public class ViewEditReportEmployeeEpi extends ViewEdit {
    
    private JPanel panelReport;
    private JLabel labelEmployee;
    private JComboBox comboBoxEmployee;
    private JLabel labelInitialDate;
    private JFormattedTextField textFieldInitialDate;
    private JLabel labelFinalDate;
    private JFormattedTextField textFieldFinalDate;
    
    public ViewEditReportEmployeeEpi(DataObject entity, ProcessAction process) {
        super(entity, process);
        
        this.setTitle("Relatório de Funcionários x EPI");
        this.setSize(300, 220);
    }

    @Override
    public void createCustomFields() {
        this.panelReport = new JPanel();
        this.labelEmployee = new JLabel();
        this.comboBoxEmployee = new ExternFieldEmployees();
        this.labelInitialDate = new JLabel();
        this.labelFinalDate = new JLabel();
        
        try {
            this.textFieldInitialDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
            this.textFieldFinalDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } 
        catch (ParseException ex) {}
        
        this.labelEmployee.setText("Funcionários");
        this.labelInitialDate.setText("Data Inicial");
        this.labelFinalDate.setText("Data Final");
        
        this.setDateToFields();
        
        this.panelReport.setBorder(BorderFactory.createTitledBorder("Dados do Relatório"));
        
        GroupLayout groupLayout = new GroupLayout(this.panelReport);
        
        this.panelReport.setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.labelEmployee)
            .addComponent(this.labelInitialDate)
            .addComponent(this.labelFinalDate)
        );
        hGroup.addGroup(groupLayout.createParallelGroup()
            .addComponent(this.comboBoxEmployee)
            .addComponent(this.textFieldInitialDate)
            .addComponent(this.textFieldFinalDate)
        );
        
        groupLayout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelEmployee).addComponent(this.comboBoxEmployee));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelInitialDate).addComponent(this.textFieldInitialDate));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.labelFinalDate).addComponent(this.textFieldFinalDate));
        groupLayout.setVerticalGroup(vGroup);
        
        this.panelComponents.add(this.panelReport);
    }
    
    private void setDateToFields() {
        LocalDate localDate = LocalDate.now();
        String todayDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        this.textFieldInitialDate.setText(todayDate);
        this.textFieldFinalDate.setText(todayDate);
    }

    @Override
    public void bindDataToFields() {}

    @Override
    public void bindDataFromFields() {
        ReportEmployeeEpi report = (ReportEmployeeEpi) this.getDataObject();
        
        for (int itemIndex = 0; itemIndex < this.comboBoxEmployee.getItemCount(); itemIndex++) {
            Employee employee = (Employee) this.comboBoxEmployee.getItemAt(itemIndex);
            
            if(employee.isSelected()) {
                report.getEmployees().add(employee);
            }
        }
        
        report.setInitialDate(this.textFieldInitialDate.getText());
        report.setFinalDate(this.textFieldFinalDate.getText());
    }

    @Override
    protected boolean validatedData() {
        if(!this.validateSelectedEmployee()) {
            this.addError("Nenhum funcionário selecionado");
        }
        
        if(!this.validateDateFormat()) {
            this.addError("Data informada é inválida");
        }
        
        if(!this.validateDateInitialLessThanFinal()) {
            this.addError("Data final não pode ser maior que a data inicial");
        }
        
        return super.validatedData();
    }
    
    private boolean validateSelectedEmployee() {
        for (int itemIndex = 0; itemIndex < this.comboBoxEmployee.getItemCount(); itemIndex++) {
            Employee employee = (Employee) this.comboBoxEmployee.getItemAt(itemIndex);
            
            if(employee.isSelected()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean validateDateFormat() {
        try {
            LocalDate.parse(this.textFieldInitialDate.getText(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            LocalDate.parse(this.textFieldFinalDate.getText(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    private boolean validateDateInitialLessThanFinal() {
        int dateInitial = Integer.parseInt(StringUtil.removeStrangeCharacter(this.textFieldInitialDate.getText()));
        int dateFinal = Integer.parseInt(StringUtil.removeStrangeCharacter(this.textFieldFinalDate.getText()));
        
        return dateInitial <= dateFinal;
    }
    
}
