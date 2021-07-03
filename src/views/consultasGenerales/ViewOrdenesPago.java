package views.consultasGenerales;

import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.OrdenPago;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewOrdenesPago extends JFrame{

    private JPanel ordenesPago;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton cancelarButton;
    private JTextArea textAreaResultado;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextField textCuit;
    private JButton consultarButton;
    DefaultTableModel model;
    private List<OrdenPago> ordenPago;


    public ViewOrdenesPago() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(ordenesPago);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //var op = ControladorOrdenesDePagos.getInstancia().getOPs();
        //setJtextAreaResultado(op);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Proveedor");
        model.addColumn("Fecha");
        model.addColumn("Monto Total");
        model.addColumn("Monto retenido");
        table1.setModel(model);




        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cuit = textCuit.getText();

                System.out.println(cuit);
                if(!cuit.equals("")) {
                    if (ControladorProveedor.getInstancia().existsProveedor(cuit)) {
                        model.getDataVector().removeAllElements();
                        setJtextAreaResultado(ControladorOrdenesDePagos.getInstancia().getOPsByCuit(cuit));
                        for (OrdenPago op : ControladorOrdenesDePagos.getInstancia().getOPsByCuit(cuit)) {
                            model.addRow(new Object[]{

                                    op.getID(),
                                    op.getProveedor().getNombre(),
                                    op.getFecha(),
                                    op.getTotal(),
                                    op.getTotalRetenciones()

                            });

                        }
                        model.fireTableDataChanged();

                    } else {

                        JOptionPane.showMessageDialog(
                                consultarButton,
                                "El proveedor seleccionado no existe",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);


                    }
                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Ingrese un cuit",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


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

    private void setJtextAreaResultado(List<OrdenPago> op) {

        if(op.isEmpty()){

            this.textAreaResultado.setText("No hay Ordenes de pago emitidas en el sistema");

        }else{

            String text = "";

            for (int i=0; i<op.size(); i++ ){
                System.out.println(text);

                text = text +("La Orden de pago emitida del proveedor ")
                        +op.get(i).getProveedor().getNombre()
                        +(" con el numero ")
                        + op.get(i).getNro()
                        +(" tiene un monto total de ")
                        +(op.get(i).getTotal())
                        +("$\n");

            }
            this.textAreaResultado.setText(text);

        }
    }
}
