package views.consultasGenerales;

import controllers.ControladorComprobantes;
import controllers.ControladorItem;
import models.documento.Factura;
import models.dtos.ComprobanteDTO;
import views.documentosRecibidos.DocumentosView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;


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
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextArea textAreaResultado;


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


                String cuit = null;
                LocalDate fecha = null;

                if (!textCUIT.getText().isEmpty() || !(textDia.getText() + textMes.getText() + textAnio.getText()).isEmpty()){

                    if (!textCUIT.getText().isEmpty()){
                        //CUIT = Integer.parseInt(textCUIT.getText());
                        cuit = textCUIT.getText();
                        System.out.println("cuit agregado");
                    }

                    if(!(textDia.getText() + textMes.getText() + textAnio.getText()).isEmpty()){
                        try {
                            fecha = LocalDate.parse(textAnio.getText() + ("-") + textMes.getText() + ("-") + textDia.getText());
                            System.out.println("fecha agregado");
                        }catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    consultarButton,
                                    "Fecha ingresada no valida",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                        }
                    }

                    System.out.println("TENGO O FECHA O CUIT");
                    if (cuit != null && fecha != null){         // tengo cuit y fecha

                        System.out.println("CUIT Y FECHA");
                        var facturas = ControladorComprobantes.getInstancia().getFacturasDTOByFechaYProveedor(cuit, fecha);
                        setJtextArea(facturas);
                        System.out.println(facturas);

                    }else{
                        if (cuit != null && fecha == null) {           //solo cuit

                            System.out.println("CUIT");
                            var facturas = ControladorComprobantes.getInstancia().getFacturasDTOsByProveedor(cuit);
                            setJtextArea(facturas);
                            System.out.println(facturas);

                        }else{                                  //solo fecha

                            System.out.println("FECHA");
                            var facturas = ControladorComprobantes.getInstancia().getFacturasDTOsByFecha(fecha);
                            setJtextArea2(facturas);
                            System.out.println(facturas);

                        }

                    }

                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Seleccione al menos un proveedor o fecha para continuar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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

        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView docu = new DocumentosView();
                docu.setVisible(true);

            }
        });


    }



    private void setJtextArea(List<ComprobanteDTO> facturas) {

        if (facturas.isEmpty()){

            this.textAreaResultado.setText("El proveedor no posee facturas");
        }
        else{

            String text = "El proveedor " + facturas.get(0).getProveedor().getNombre()+ " tiene las facturas: \n";

            for (int i=0; i<facturas.size(); i++ ){
                System.out.println(text);

                text = text +("La factura numero ")+ facturas.get(i).getNro()+(" con fecha ")+ facturas.get(i).getFecha() +(" con un monto total de ")+(facturas.get(i).getTotal())+ ("$\n");

            }
            this.textAreaResultado.setText(text);
        }
        System.out.println("END1");

    }

    private void setJtextArea2(List<ComprobanteDTO> facturas) {
        if (facturas.isEmpty()){

            this.textAreaResultado.setText("No hay facturas en la fecha ingresada");
        }
        else{

            String text = "En la fecha " + facturas.get(0).getFecha()+ " se encuentran las siguientes facturas: \n";

            for (int i=0; i<facturas.size(); i++ ){
                System.out.println(text);

                text = text +("La factura del proveedor ") +facturas.get(i).getProveedor().getNombre() +(" con el numero ")+ facturas.get(i).getNro()+(" con un monto total de ")+(facturas.get(i).getTotal())+ ("$\n");

            }
            this.textAreaResultado.setText(text);
        }
        System.out.println("END2");
    }
}