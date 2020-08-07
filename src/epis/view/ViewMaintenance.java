package epis.view;

import epis.controller.ControllerMaintenance;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;

/**
 * View Maintenance Base
 * @author Jean Poffo
 * @since  29/08/2020
 */
public abstract class ViewMaintenance extends JFrame implements View, Observer {

    private JScrollPane scrollPanelTable;
    private JTable tableData;
    private JPanel panelEdit;
    private JButton buttonEdit;
    private JButton buttonAdd;
    private JButton buttonDelete;

    private AbstractTableModel tableModel;
    
    private ControllerMaintenance controller;
    
    public ViewMaintenance(ControllerMaintenance controller, String title) {
        this.controller = controller;
        this.tableModel = this.getTableModel();
        
        this.initComponents();
        this.addListeners();
        
        this.setTitle(title);
        this.setIconImage(new ImageIcon(getClass().getResource("/epis/resources/helmet.png")).getImage());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.getController().addObserver(this);
    }

    protected JTable getTableData() {
        return tableData;
    }

    protected JPanel getPanelEdit() {
        return panelEdit;
    }
    
    protected final ControllerMaintenance getController() {
        return this.controller;
    }
    
    @Override
    public void update() {
        this.tableModel.fireTableDataChanged();
    }
    
    @Override
    public void initComponents() {
        this.scrollPanelTable = new JScrollPane();
        this.tableData = new JTable();
        this.panelEdit = new JPanel();
        this.buttonEdit = new JButton();
        this.buttonAdd = new JButton();
        this.buttonDelete = new JButton();        

        this.scrollPanelTable.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), new EtchedBorder()));

        this.tableData.setModel(this.tableModel);
        
        this.scrollPanelTable.setViewportView(this.tableData);

        this.getContentPane().add(this.scrollPanelTable, BorderLayout.CENTER);

        this.panelEdit.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 3, 10, 0), new EmptyBorder(0, 0, 0, 0)));
        this.panelEdit.setMaximumSize(new Dimension(32767, 10));
        this.panelEdit.setMinimumSize(new Dimension(100, 40));
        this.panelEdit.setPreferredSize(new Dimension(708, 55));

        this.buttonEdit.setText("Editar");
        this.buttonEdit.setPreferredSize(new Dimension(100, 30));

        this.buttonAdd.setText("Adicionar");
        this.buttonAdd.setPreferredSize(new Dimension(100, 30));

        this.buttonDelete.setText("Excluir");
        this.buttonDelete.setPreferredSize(new Dimension(100, 30));

        GroupLayout panelEditLayout = new GroupLayout(this.panelEdit);
        
        this.panelEdit.setLayout(panelEditLayout);
        
        panelEditLayout.setHorizontalGroup(
            panelEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.buttonAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.buttonEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(this.buttonDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
        );
        
        panelEditLayout.setVerticalGroup(
            panelEditLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelEditLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEditLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.buttonAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.buttonEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.buttonDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        this.getContentPane().add(this.panelEdit, BorderLayout.PAGE_START);

        this.pack();
    }

    @Override
    public void addListeners() {
        this.buttonAdd.addActionListener(e -> {
            this.controller.callActionAdd();
            this.update();
        });
        
        this.buttonEdit.addActionListener(e -> {
            this.controller.callActionEdit(this.tableData.getSelectedRow());
            this.update();
        });
        
        this.buttonDelete.addActionListener(e -> {
            this.controller.callActionDelete(this.tableData.getSelectedRow());
            this.update();
        });
    }
    
    public abstract AbstractTableModel getTableModel();
    
}
