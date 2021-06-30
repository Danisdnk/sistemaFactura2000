package views.consultasGenerales;

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
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;

    public ViewTotalDeImpuestosRetenidos() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(impuestos);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               var ViewConsultasGenerales = new ViewConsultasGenerales();
                ViewConsultasGenerales.setVisible(true);

                dispose();
            }
        });
    }
}
