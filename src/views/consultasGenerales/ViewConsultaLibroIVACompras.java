package views.consultasGenerales;

import controllers.ControladorComprobantes;
import models.dtos.ComprobanteDTO;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

public class ViewConsultaLibroIVACompras extends JFrame{



    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton cancelarButton;
    private JPanel libroIVA;
    private JTable tablaLibroIva;
    private JLabel iva25;
    private JLabel iva5;
    private JLabel iva105;
    private JLabel iva21;
    private JLabel iva27;
    private JLabel textTotalIVA;
    private JLabel textNeto;
    private JLabel textTotal;
    private JLabel textDocu;
    private DefaultTableModel model;



    public ViewConsultaLibroIVACompras() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(libroIVA);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Cuit");
        model.addColumn("Fecha");
        model.addColumn("Nro Documento");
        model.addColumn("Monto Neto");
        model.addColumn("IVA (%)");
        model.addColumn("Monto IVA");
        model.addColumn("Monto Total");
        tablaLibroIva.setModel(model);

        setTablePrecios();



        cancelarButton.addActionListener(e -> {
            var ViewConsultasGenerales = new ViewConsultasGenerales();
            ViewConsultasGenerales.setVisible(true);
            dispose();
        });

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            //dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales cons = new ViewConsultasGenerales();
            cons.setVisible(true);
            dispose();
        });

        usuariosButton.addActionListener(e -> {
            loginView principal = new loginView();
            principal.setVisible(true);
            dispose();
        });

        proveedoresButton.addActionListener(e -> {
            provedorView principal = new provedorView();
            principal.setVisible(true);
            dispose();
        });
        DocumentosButton.addActionListener(e -> {
            DocumentosView principal = new DocumentosView();
            principal.setVisible(true);
            dispose();
        });

    }

    private void setTablePrecios() {

        double textiva25 = 0F;
        double textiva5 = 0F;
        double textiva105 = 0F;
        double textiva21 = 0F;
        double textiva27 = 0F;
        double neto = 0f;
        double total = 0f;
        int docus = 0;

        DecimalFormat formato1 = new DecimalFormat("#.##");
        model.getDataVector().removeAllElements();
        for(ComprobanteDTO Comprobante : ControladorComprobantes.getInstancia().getAllComprobantesDTO()){
            model.addRow(new Object[]{
                    Comprobante.getProveedor().getNombre(),
                    Comprobante.getProveedor().getCuit(),
                    Comprobante.getFecha(),
                    Comprobante.getTipo() +(" ")+ Comprobante.getNro(),
                    formato1.format(Comprobante.getMontoTotal() - Comprobante.getMontoIva()),
                    Comprobante.getIva(),
                    formato1.format(Comprobante.getMontoIva()),
                    formato1.format(Comprobante.getMontoTotal())

            });
            neto+=(Comprobante.getMontoTotal() - Comprobante.getMontoIva());
            total+=Comprobante.getMontoTotal();
            docus++;

            if (Comprobante.getIva() != 0){
                if(Comprobante.getIva() == 2.5)
                    textiva25 =  textiva25 + Comprobante.getMontoIva();
                    if(Comprobante.getIva() == 5)
                        textiva5 =  textiva5 + Comprobante.getMontoIva();
                        if(Comprobante.getIva() == 10.5)
                            textiva105 =  textiva105 + Comprobante.getMontoIva();
                            if(Comprobante.getIva() == 21)
                                textiva21 =  textiva21 + Comprobante.getMontoIva();
                                if(Comprobante.getIva() == 27)
                                    textiva27 = textiva27 + Comprobante.getMontoIva();
            }
        }

        model.fireTableDataChanged();
        iva25.setText(String.valueOf(formato1.format(textiva25)));
        iva5.setText(String.valueOf(formato1.format(textiva5)));
        iva105.setText(String.valueOf(formato1.format(textiva105)));
        iva21.setText(String.valueOf(formato1.format(textiva21)));
        iva27.setText(String.valueOf(formato1.format(textiva27)));
        textTotalIVA.setText(String.valueOf(formato1.format(textiva25+textiva5+textiva105+textiva21+textiva27)));
        textNeto.setText(String.valueOf(formato1.format(neto)));
        textTotal.setText(String.valueOf(formato1.format(total)));
        textDocu.setText(String.valueOf(docus));
    }
}
