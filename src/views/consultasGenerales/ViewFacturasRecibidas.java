package views.consultasGenerales;

import controllers.ControladorComprobantes;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.DateParse;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class ViewFacturasRecibidas extends JFrame {

    private JPanel facturasRecibidas;

    private JTextField textCUIT;
    private JTextField textDia;
    private JTextField textMes;
    private JTextField textAnio;

    private JButton consultarButton;
    private JButton volverButton;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextArea textArea1;


    public ViewFacturasRecibidas() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(facturasRecibidas);
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



        consultarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                Integer CUIT = null;
                LocalDate fecha = null;

                //fecha = LocalDate.parse(textDia.getText() + textMes.getText() + textAnio.getText());
                //var fecha = DateParse.parse(textDia.getText() + textMes.getText() + textAnio.getText());

                if (!textCUIT.getText().isEmpty() || !(textDia.getText() + textMes.getText() + textAnio.getText()).isEmpty()){
                    if (!textCUIT.getText().isEmpty()){
                        CUIT = Integer.parseInt(textCUIT.getText());
                        System.out.println("cuit agregado");
                    }

                    if(!(textDia.getText() + textMes.getText() + textAnio.getText()).isEmpty()){
                        fecha = LocalDate.parse(textDia.getText()+ ("-") + textMes.getText()+ ("-") + textAnio.getText());
                        System.out.println("fecha agregado");
                    }

                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Seleccione al menos un proveedor o fecha para continuar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }






                //getComprobantesByProveedor
                //CUIT = Integer.parseInt(textCUIT.getText());



                System.out.println("primer if");
                if (CUIT != null && fecha != null){         // tengo cuit y fecha
                    System.out.println("segundo if");
                }else{
                    if (CUIT != null && fecha == null) {           //solo cuit
                        System.out.println("tercer if");
                        var facturas = ControladorComprobantes.getInstancia().getFacturasByProveedor(CUIT);


                        textArea1.setText(String.valueOf(facturas));
                        System.out.println(facturas);
                    }else{                                  //solo fecha
                        System.out.println("cuarto if");
                        var facturas = ControladorComprobantes.getInstancia().getFacturasByFecha(fecha);
                    }

                }




                //var facturas = ControladorComprobantes.getInstancia().getFacturasByProveedor(CUIT);
                //textArea1.setText(String.valueOf(facturas));
                //System.out.println(facturas);
                //System.out.println(fecha);

            }

        });

        volverButton.addActionListener(new ActionListener() {

            @Override
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