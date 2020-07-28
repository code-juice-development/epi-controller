package epis.view;

import epis.controller.ControllerRoot;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import static javax.swing.SwingConstants.BOTTOM;
import static javax.swing.SwingConstants.CENTER;

/**
 * View Root of System
 * @author Jean Poffo
 * @since  28/07/2020
 */
public final class ViewRoot extends JFrame {

    private JToolBar toolBar;
    private JButton buttonEmployees;
    private JButton buttonEpis;
    private JButton buttonReport;
    private JPanel panelContainer;
    private JMenuBar menuBar;
    private JMenu menuArchive;
    private JMenuItem menuItemExit;
    private JMenuItem menuEmployees;
    private JMenuItem menuItemEpis;
    private JMenuItem menuItemEmployees;
    private JMenu menuHelp;
    private JMenuItem menuItemAbout;

    private ControllerRoot controller;
    
    public ViewRoot(ControllerRoot controller) throws HeadlessException {
        super("EPI Controller");
        
        this.controller = controller;
        
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(1200, 800));
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setIconImage(new ImageIcon(getClass().getResource("/epis/resources/helmet-mini.png")).getImage());
        
        this.initComponents();
        this.addListeners();
    }
    
    private void initComponents() {
        this.toolBar = new JToolBar();
        this.buttonEmployees = new JButton();
        this.buttonEpis = new JButton();
        this.buttonReport = new JButton();
        this.panelContainer = new JPanel();
        this.menuBar = new JMenuBar();
        this.menuArchive = new JMenu();
        this.menuItemExit = new JMenuItem();
        this.menuEmployees = new JMenu();
        this.menuItemEpis = new JMenuItem();
        this.menuItemEmployees = new JMenuItem();
        this.menuHelp = new JMenu();
        this.menuItemAbout = new JMenuItem();

        this.toolBar.setRollover(true);

        this.buttonEmployees.setIcon(new ImageIcon(getClass().getResource("/epis/resources/person.png")));
        this.buttonEmployees.setText("Funcionários");
        this.buttonEmployees.setMaximumSize(new Dimension(105, 70));
        this.buttonEmployees.setMinimumSize(new Dimension(105, 70));
        this.buttonEmployees.setHorizontalTextPosition(CENTER);
        this.buttonEmployees.setVerticalTextPosition(BOTTOM);
        
        this.toolBar.add(this.buttonEmployees);

        this.buttonEpis.setIcon(new ImageIcon(getClass().getResource("/epis/resources/helmet.png")));
        this.buttonEpis.setText("Epis");
        this.buttonEpis.setMaximumSize(new Dimension(105, 70));
        this.buttonEpis.setMinimumSize(new Dimension(105, 70));
        this.buttonEpis.setHorizontalTextPosition(CENTER);
        this.buttonEpis.setVerticalTextPosition(BOTTOM);
        this.toolBar.add(this.buttonEpis);

        this.buttonReport.setIcon(new ImageIcon(getClass().getResource("/epis/resources/report.png")));
        this.buttonReport.setText("Relatório");
        this.buttonReport.setMaximumSize(new Dimension(105, 70));
        this.buttonReport.setMinimumSize(new Dimension(105, 70));
        this.buttonReport.setHorizontalTextPosition(CENTER);
        this.buttonReport.setVerticalTextPosition(BOTTOM);
        this.toolBar.add(this.buttonReport);

        this.getContentPane().add(this.toolBar, BorderLayout.PAGE_START);

        GroupLayout panelContainerLayout = new GroupLayout(this.panelContainer);
        
        this.panelContainer.setLayout(panelContainerLayout);
        
        panelContainerLayout.setHorizontalGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 761, Short.MAX_VALUE));
        panelContainerLayout.setVerticalGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 353, Short.MAX_VALUE));

        this.getContentPane().add(this.panelContainer, BorderLayout.CENTER);

        this.menuArchive.setText("Arquivo");
        
        this.menuItemExit.setText("Sair");
        
        this.menuArchive.add(this.menuItemExit);

        this.menuBar.add(this.menuArchive);

        this.menuEmployees.setText("Cadastros");

        this.menuItemEpis.setText("Funcionários");
        this.menuEmployees.add(this.menuItemEpis);

        this.menuItemEmployees.setText("Epis");
        this.menuEmployees.add(this.menuItemEmployees);

        this.menuBar.add(this.menuEmployees);

        this.menuHelp.setText("Ajuda");

        this.menuItemAbout.setText("Sobre");
        this.menuHelp.add(this.menuItemAbout);

        this.menuBar.add(this.menuHelp);

        this.setJMenuBar(this.menuBar);

        this.pack();
    }
    
    private void addListeners() {
        this.menuItemExit.addActionListener(e -> this.controller.callActionCloseSystem());
        this.menuItemEmployees.addActionListener(e -> this.controller.callActionOpenEmployeesDisplay());
        this.menuItemEpis.addActionListener(e -> this.controller.callActionOpenEpisDisplay());
        this.menuItemAbout.addActionListener(e -> this.controller.callActionAboutSystemDisplay());
        this.buttonEmployees.addActionListener(e -> this.controller.callActionOpenEmployeesDisplay());
        this.buttonEpis.addActionListener(e -> this.controller.callActionOpenEpisDisplay());
        this.buttonReport.addActionListener(e -> this.controller.callActionOpenReportDisplay());
    }
    
}
