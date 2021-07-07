package views;

import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.hidePanel;

import javax.swing.*;

public class MenuPrincipal extends JFrame {
    private JPanel mainPanel;

    private JLabel titulo;
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;
    private JPanel panelCentral;

    public MenuPrincipal(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            dispose();
        });

        proveedoresButton.addActionListener(e -> {
            provedorView prov = new provedorView();
            prov.setVisible(true);
            dispose();
        });
        usuariosButton.addActionListener(e -> {
            loginView login = new loginView();
            login.setVisible(true);
            dispose();
        });
        DocumentosButton.addActionListener(e -> {
            DocumentosView docu = new DocumentosView();
            docu.setVisible(true);
            dispose();
        });
        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales cons = new ViewConsultasGenerales();
            cons.setVisible(true);
            dispose();
        });
        hideButton.addActionListener(e -> {
            hidePanel about = new hidePanel();
            about.setVisible(true);
            dispose();
        });

    }
}
