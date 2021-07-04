package views.consultasGenerales;

import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTotalDeImpuestosRetenidos extends JFrame{


    private JPanel impuestos;
    private JTextField numero;
    private JButton volverButton;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextField textCuit;

    public ViewTotalDeImpuestosRetenidos() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(impuestos);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales principal = new ViewConsultasGenerales();
                principal.setVisible(true);
                dispose();
            }
        });

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame principal = new OrdenesDePagoFrame();
                principal.setVisible(true);
                //dispose();
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales principal = new ViewConsultasGenerales();
                principal.setVisible(true);
                dispose();
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView principal = new loginView();
                principal.setVisible(true);
                dispose();
            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView principal = new provedorView();
                principal.setVisible(true);
                dispose();
            }
        });
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView principal = new DocumentosView();
                principal.setVisible(true);
                dispose();
            }
        });

    }
}
