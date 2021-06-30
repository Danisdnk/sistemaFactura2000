package views.proveedores;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import controllers.ControladorComprobantes;
import controllers.ControladorProveedor;
import models.documento.Comprobante;
import models.dtos.DDLItemDTO;
import views.ordenesDePago.OrdenesDePagoFrame;
import models.proveedor.Proveedor;

import static java.util.stream.Collectors.toList;

public class provedorView extends JFrame {
    private JPanel vistaProv;
    private JButton ordenesDePagoButton;
    private JPanel panelProveedores;
    private JButton addProveedor;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton usuariosButton;
    private JLabel labelProveedor;
    private JComboBox<DDLItemDTO> dropListaProveedores;
    private JTextField txtNombreFantasia;
    private JTextField txtRazonSocial;
    private JTextField txtCuit;
    private JTextField txtDireccion;
    private JTextField txtResponsabilidadIva;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtIngresosBrutos;
    private JTextField txtInicioActividades;
    private JTextField txtRubro;
    private JButton RemoveProveedor;
    private JSpinner DateActividades;
    private JButton borrarButton;
    private JButton modificarButton;
    private ControladorProveedor controlador;
    private List<Comprobante> proveedoresCargados;
    private Integer proveedorID;
    private Proveedor itemSeleccionado;

    public provedorView() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 1000);
        this.setContentPane(vistaProv);
        this.setLocationRelativeTo(null);
        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                //dispose();//esto cierra la ventana anterior
            }
        });
        //dropdown proveedor seleccionar opcion
        ;
        this.dropListaProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();

                if ( sel != null ) {  //si la seleccion es distinta de nulo hacemos varias cosas
                    proveedorID = sel.id;

                    itemSeleccionado = controlador.getProveedorByID(sel.id); //hacemos un get del objeto seleccionda
                    txtNombreFantasia.setText(  //hacemos un setText para asignar valor al dropdown
                            !itemSeleccionado.getNombre() //hacemos esta condicion "ternaria"
                                    .isEmpty() ? //con isEmpty verificamos que no sea vacio desde ( hasta el "?" es
                                    // la condicion
                                    itemSeleccionado.getNombre() //si se cumple cargamos el item
                                    : ""); //si no se cumple porque el dato no se completo cargamos el dato como
                    // string vacio.asi almenos no pincha. ternario es (condicion? caso true : caso false )
                    txtDireccion.setText(
                            !itemSeleccionado.getDireccion()
                                    .isEmpty() ?
                                    itemSeleccionado.getDireccion()
                                    : "");

                    txtEmail.setText(
                            !itemSeleccionado.getEmail()
                                    .isEmpty() ?
                                    itemSeleccionado.getEmail()
                                    : "");

                    txtIngresosBrutos.setText(
                            !itemSeleccionado.getNumeroIIBB()
                                    .isEmpty() ?
                                    itemSeleccionado.getNumeroIIBB()
                                    : "");
                    txtRubro.setText(
                            !itemSeleccionado.getRubros()
                                    .isEmpty() ?
                                    itemSeleccionado.getRubros()
                                    : "");
                    txtRazonSocial.setText(
                            !itemSeleccionado.getRazonSocial()
                                    .isEmpty() ?
                                    itemSeleccionado.getRazonSocial()
                                    : "");
                    txtCuit.setText(
                            !itemSeleccionado.getCuit()
                                    .isEmpty() ?
                                    itemSeleccionado.getCuit()
                                    : "");
                }

                assert sel != null;
                if ( ( ( sel.descripcion ).equals("Nuevo Proveedor") ) ) {

                    txtNombreFantasia.setVisible(true);
                    txtDireccion.setVisible(true);
                    txtEmail.setVisible(true);
                    txtIngresosBrutos.setVisible(true);
                    DateActividades.setVisible(true);
                    txtRubro.setVisible(true);
                    txtRazonSocial.setVisible(true);
                    txtCuit.setVisible(true);

                } else {

                    proveedorID = null;
                    // txtNombreFantasia.setText(sel.descripcion);
                    // txtDireccion.setVisible(false);
                    // txtEmail.setVisible(false);
                    //  txtIngresosBrutos.setVisible(false);
                    //   DateActividades.setVisible(false);
                    //  txtRubro.setVisible(false);
                    //     txtRazonSocial.setVisible(false);
                    //       txtCuit.setVisible(false);
                }
            }
        });

       /* addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    provedorView dialog = new provedorView();
                    JOptionPane.showMessageDialog(
                            addProveedor,
                            "proveedor",
                            "Nuevo proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                *//*addRow(dialog.showDialog());*//* catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
*/

        addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //  SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", Locale.getDefault());
                var patata = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();

                Proveedor provModel = new Proveedor(
                        txtNombreFantasia.getText(),
                        txtDireccion.getText(),
                        txtEmail.getText(),
                        txtIngresosBrutos.getText(),
                        DateActividades.getValue().toString(),
                        txtRubro.getText(),
                        (float) 2000,
                        txtRazonSocial.getText(),
                        txtCuit.getText()
                );

                var p = patata
                        .stream()
                        .anyMatch(model ->
                                model.descripcion
                                        .equals(provModel.getNombre())
                        );
                var a = p;

                if ( !p ) {
                    controlador.agregarProveedor(provModel);
                    JOptionPane.showMessageDialog(
                            addProveedor,
                            "proveedor",
                            "Nuevo proveedor",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(
                            addProveedor,
                            "No se pudo cargar.Ya existe proveedor con ese nombre",
                            "Nuevo proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            };
        });

    }

    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.dropListaProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }


    private void createUIComponents() { //componente custom para la fecha //TODO en facturas?
        Date date = new Date();
        SpinnerDateModel sm =
                new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH);
        DateActividades = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(DateActividades, "d MMM yyyy");
        DateActividades.setEditor(de);

    }
}


