package views.consultasGenerales;

import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.OrdenPago;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewOrdenesPago extends JFrame{

    private JPanel ordenesPago;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
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
    private ControladorProveedor controladorP;
    private ControladorOrdenesDePagos controladorOP;


    public ViewOrdenesPago() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(ordenesPago);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.controladorP = ControladorProveedor.getInstancia();
        this.controladorOP = ControladorOrdenesDePagos.getInstancia();


        model = new DefaultTableModel();
        model.addColumn("Orden De Pago ID");
        model.addColumn("Proveedor");
        model.addColumn("Fecha");
        model.addColumn("Monto Total");
        model.addColumn("Total de Retencion");
        model.addColumn("Pago en Cheque");
        model.addColumn("Pago en Efectivo");
        table1.setModel(model);




        consultarButton.addActionListener(e -> {

            String cuit = textCuit.getText();

            System.out.println(cuit);
            if(!cuit.equals("")) {
                if (controladorP.existsProveedorCuit(cuit)) {
                    model.getDataVector().removeAllElements();
                    setJtextAreaResultado(controladorOP.getOPsByCuit(cuit));
                    for (OrdenPago op : controladorOP.getOPsByCuit(cuit)) {

                        boolean ch = op.getItems().stream().anyMatch(u -> u.getTipoDePago().getType().equals("Cheque"));
                        boolean ef = op.getItems().stream().anyMatch(u -> u.getTipoDePago().getType().equals("Efectivo"));

                        if (ch || ef) {
                            if(ef){
                                model.addRow(new Object[]{
                                        op.getID(),
                                        op.getProveedor().getNombre(),
                                        op.getFecha(),
                                        op.getMontoTotal(),
                                        op.getTotalRetenciones(),
                                        "Si",
                                        "Si"
                              });

                            } else {
                                model.addRow(new Object[]{
                                        op.getID(),
                                        op.getProveedor().getNombre(),
                                        op.getFecha(),
                                        op.getMontoTotal(),
                                        op.getTotalRetenciones(),
                                        "Si",
                                        "No"
                                });
                            }


                        }else{
                            model.addRow(new Object[]{

                                    op.getID(),
                                    op.getProveedor().getNombre(),
                                    op.getFecha(),
                                    op.getMontoTotal(),
                                    op.getTotalRetenciones(),
                                    "No",
                                    "No"

                            });
                        }


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
        });


        cancelarButton.addActionListener(e -> {
            var ViewConsultasGenerales = new ViewConsultasGenerales();
            ViewConsultasGenerales.setVisible(true);

            dispose();
        });



        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame principal = new OrdenesDePagoFrame();
            principal.setVisible(true);
            //dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales principal = new ViewConsultasGenerales();
            principal.setVisible(true);
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
                        +(" con un monto neto de ")
                        +(op.get(i).getMontoNeto())
                        +(" y un monto total de ")
                        +(op.get(i).getMontoTotal())
                        +("$\n");

            }
            this.textAreaResultado.setText(text);

        }
    }
}
