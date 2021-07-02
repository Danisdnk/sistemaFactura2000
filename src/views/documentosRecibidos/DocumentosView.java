package views.documentosRecibidos;

import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.SolapaFactura;
import views.documentosRecibidos.SolapaCompra;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentosView extends JDialog {
    private JLabel titulo;
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JPanel docuMain;
    private JButton facturaButton;
    private JButton ordenDeCompraButton;
    private JToolBar barraNavegacion;
    private JButton hideButton;
    private JDesktopPane desktopPaneFactura;

    public DocumentosView(){
        this.setContentPane(docuMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        facturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolapaFactura solapaF = new SolapaFactura();
                solapaF.setVisible(true);
            }
        });
        ordenDeCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolapaCompra solapaC = new SolapaCompra();
                solapaC.setVisible(true);
            }
        });
        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                dispose();
            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView prov = new provedorView();
                prov.setVisible(true);
                dispose();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView login = new loginView();
                login.setVisible(true);
                dispose();
            }
        });
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView docu = new DocumentosView();
                docu.setVisible(true);
                dispose();
            }
        });
        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();
            }
        });

    }
}
