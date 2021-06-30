package views.consultasGenerales;

import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCuentaCorriente extends JFrame{


    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton cancelarButton;
    private JTable table1;
    private JPanel cuentaCorriente;

    public ViewCuentaCorriente(){

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(cuentaCorriente);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var ViewConsultasGenerales = new ViewConsultasGenerales();
                ViewConsultasGenerales.setVisible(true);

                dispose();
            }
        });

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                //dispose();//esto cierra la ventana anterior
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView principal = new provedorView();
                principal.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });
    }

}
