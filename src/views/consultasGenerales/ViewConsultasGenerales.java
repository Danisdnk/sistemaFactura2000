package views.consultasGenerales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import views.MenuPrincipal;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;


public class ViewConsultasGenerales extends JFrame{

    private JPanel consultas;
    private JPanel panelCentral;

    private JButton cancelarButton;
    private JButton aceptarButton;

    private JRadioButton consultaDeLibroIVARadioButton;
    private JRadioButton totalDeImpuestosRetenidosRadioButton;
    private JRadioButton totalDeDeudaPorRadioButton;
    private JRadioButton ordenesDePagosEmitidasRadioButton;
    private JRadioButton compulsaDePreciosRadioButton;
    private JRadioButton cuentaCorrienteDeProveedoresRadioButton;
    private JRadioButton totalDeFacturasRecibidasRadioButton;

    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;




    public ViewConsultasGenerales() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(consultas);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);




        ButtonGroup group = new ButtonGroup();group.add(totalDeFacturasRecibidasRadioButton);
        group.add(totalDeImpuestosRetenidosRadioButton);
        group.add(cuentaCorrienteDeProveedoresRadioButton);
        group.add(compulsaDePreciosRadioButton);
        group.add(totalDeDeudaPorRadioButton);
        group.add(ordenesDePagosEmitidasRadioButton);
        group.add(consultaDeLibroIVARadioButton);

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(totalDeFacturasRecibidasRadioButton.isSelected()){
                    var ViewFacturasRecibidas = new ViewFacturasRecibidas();
                    ViewFacturasRecibidas.setVisible(true);

                    dispose();

                } else if(cuentaCorrienteDeProveedoresRadioButton.isSelected()) {
                    var ViewCuentaCorriente = new ViewCuentaCorriente();
                    ViewCuentaCorriente.setVisible(true);
                    //TODO ViewCentaCorriente

                    dispose();

                } else if (compulsaDePreciosRadioButton.isSelected()) {
                    var ViewCompulsaPrecios = new ViewCompulsaPrecios();
                    ViewCompulsaPrecios.setVisible(true);
                    //TODO ViewCompulsaPrecios

                    dispose();

                } else if (ordenesDePagosEmitidasRadioButton.isSelected()){
                    var ViewOrdenesPago = new ViewOrdenesPago();
                    ViewOrdenesPago.setVisible(true);
                    //TODO ViewOrdenesPagosEmitidas

                    dispose();

                } else if (totalDeDeudaPorRadioButton.isSelected()){
                    var ViewTotalDeDeuda = new ViewTotalDeDeuda();
                    ViewTotalDeDeuda.setVisible(true);
                    //TODO ViewTotalDeDeuda

                    dispose();

                } else if (totalDeImpuestosRetenidosRadioButton.isSelected()){
                   var ViewTotalDeImpuestosRetenidos = new ViewTotalDeImpuestosRetenidos();
                   ViewTotalDeImpuestosRetenidos.setVisible(true);
                    //TODO ViewTotalDeImpuestosRetenidos

                    dispose();
                } else if (consultaDeLibroIVARadioButton.isSelected()){
                    var ViewConsultaLibroIVACompras = new ViewConsultaLibroIVACompras();
                    ViewConsultaLibroIVACompras.setVisible(true);
                    //TODO ViewConsultaLibro

                    dispose();
                } else {//no se selecciono nada

                    JOptionPane.showMessageDialog(
                            aceptarButton,
                            "Seleccione una opcion para continuar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        );

        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var MenuPrincipal = new MenuPrincipal("Factura 2000");
                MenuPrincipal.setVisible(true);

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


