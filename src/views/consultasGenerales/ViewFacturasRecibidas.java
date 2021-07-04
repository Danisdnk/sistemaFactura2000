package views.consultasGenerales;

import controllers.ControladorComprobantes;
import models.dtos.ComprobanteDTO;
import views.documentosRecibidos.DocumentosView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextArea textAreaResultado;
    private JTable JTabbedPane1;

    private DefaultTableModel model;


    public ViewFacturasRecibidas() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(facturasRecibidas);
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Nro Factura");
        model.addColumn("Fecha");
        model.addColumn("Monto Total");
        table1.setModel(model);


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
                        setTableFacturas(facturas);
                        System.out.println(1);

                    }else{
                        if (cuit != null) {           //solo cuit

                            System.out.println("CUIT");
                            var facturas = ControladorComprobantes.getInstancia().getFacturasDTOsByProveedor(cuit);
                            setJtextArea(facturas);
                            setTableFacturas(facturas);
                            System.out.println(2);

                        }else{                                  //solo fecha

                            System.out.println("FECHA");
                            var facturas = ControladorComprobantes.getInstancia().getFacturasDTOsByFecha(fecha);
                            setJtextArea2(facturas);
                            setTableFacturas(facturas);
                            System.out.println(3);


                        }

                    }

                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Ingrese al menos un proveedor o fecha para continuar",
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
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView principal = new DocumentosView();
                principal.setVisible(true);
                dispose();
            }
        });


    }



    private void setJtextArea(List<ComprobanteDTO> facturas) {

        if (facturas.isEmpty()){

            this.textAreaResultado.setText("El proveedor no posee facturas");
        }
        else{

            String text = "El proveedor " + facturas.get(0).getProveedor().getNombre()+ " tiene las facturas: \n";

            for (ComprobanteDTO factura : facturas) {  //int i=0; i<facturas.size(); i++
                System.out.println(text);

                text = text + ("La factura numero ")
                        + factura.getNro() + (" con fecha ") + factura.getFecha()
                        + (" con un monto total de ") + (factura.getMontoTotal()) + ("$\n");

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

            for (ComprobanteDTO factura : facturas) { //int i=0; i<facturas.size(); i++
                System.out.println(text);

                text = text + ("La factura del proveedor ")
                        + factura.getProveedor().getNombre() + (" con el numero ")
                        + factura.getNro() + (" con un monto total de ") + (factura.getMontoTotal()) + ("$\n");

            }
            this.textAreaResultado.setText(text);
        }
        System.out.println("END2");
    }

    private void setTableFacturas(List<ComprobanteDTO> descripcion ) {

        model.getDataVector().removeAllElements();
        for(ComprobanteDTO factura : descripcion){
            model.addRow(new Object[]{
                    factura.getProveedor().getNombre(),
                    factura.getNro(),
                    factura.getFecha(),
                    factura.getMontoTotal()
            });
        }
        model.fireTableDataChanged();
    }
}