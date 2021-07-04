package views.proveedores;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import controllers.ControladorComprobantes;
import controllers.ControladorItem;
import controllers.ControladorProveedor;
import models.documento.Comprobante;
import models.dtos.DDLItemDTO;
import models.proveedor.Rubro;
import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import models.proveedor.Proveedor;

public class provedorView extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JPanel panelCentral;
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;
    private JPanel vistaProv;
    private JPanel panelProveedores;
    private JButton addProveedor;
    private JLabel labelProveedor;
    private JComboBox<List> dropListaProveedores;
    private JTextField txtNombreFantasia;
    private JTextField txtRazonSocial;
    private JTextField txtCuit;
    private JTextField txtDireccion;
    private JComboBox<DDLItemDTO> dropResponsableIva;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtIngresosBrutos;
    private JTextField txtInicioActividades;
    private JComboBox <DDLItemDTO>dropRubro;
    private JButton removeProveedor;
    private JSpinner DateActividades;
    private JButton modifyProveedor;
    private JComboBox ddlCertificadoRetencion;
    private JButton addRubroProveedor;
    private JButton borrarButton;
    private JButton modificarButton;
    private ControladorProveedor controlador;
    private List<Comprobante> proveedoresCargados;
    private Integer proveedorID;
    private Proveedor itemSeleccionado;

    public provedorView() {
        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
        this.setDDLRubros();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(vistaProv);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDDLResponsableIva();

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                dispose();
            }
        });
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView prov = new provedorView();
                prov.setVisible(true);
                dispose();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView login = new loginView();
                login.setVisible(true);
                dispose();
            }
        });
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView docu = new DocumentosView();
                docu.setVisible(true);
                dispose();
            }
        });
        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();
            }
        });

        this.dropListaProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();

                if ( sel != null ) {  //si la seleccion es distinta de nulo hacemos varias cosas
                    proveedorID = sel.id;
                    itemSeleccionado = controlador.getProveedorByID(sel.id); //hacemos un get del objeto seleccionda
                    txtNombreFantasia.setText(  //hacemos un setText para asignar valor al dropdown
                            !itemSeleccionado.getNombre() //hacemos esta condicion "ternaria"
                                    .isEmpty() ?
                                    itemSeleccionado.getNombre() //si se cumple cargamos el item
                                    : ""); //si no se cumple porque el dato no se completo cargamos el dato como
                    // string vacio.asi almenos no pincha. ternario es (condicion? caso true : caso false )
                    txtDireccion.setText(!itemSeleccionado.getDireccion().isEmpty() ? itemSeleccionado.getDireccion() : "");
                    txtEmail.setText(!itemSeleccionado.getEmail().isEmpty() ? itemSeleccionado.getEmail() : "");
                    txtIngresosBrutos.setText(!itemSeleccionado.getNumeroIIBB().isEmpty() ? itemSeleccionado.getNumeroIIBB() : "");
                   // txtRubro.setText(!itemSeleccionado.getRubros().isEmpty() ? itemSeleccionado.getRubros() : "");
                    txtRazonSocial.setText(!itemSeleccionado.getRazonSocial().isEmpty() ? itemSeleccionado.getRazonSocial() : "");
                    txtCuit.setText(!itemSeleccionado.getCuit().isEmpty() ? itemSeleccionado.getCuit() : "");
                    dropResponsableIva.setSelectedItem(itemSeleccionado.getResponsableIva());
         } else {

                    proveedorID = null;

                }
            }
        });
        List<Rubro> rubrosAgregados=null; //TODO esto hay que modificarlo para que al hacer "+" agregue un rubro a la
        // lista del futuro nuevo proveedor

        addRubroProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               var sel = (DDLItemDTO)dropRubro.getSelectedItem();

                if ( sel != null ) {
                   rubrosAgregados.add(new Rubro(sel.descripcion));
                }
            }
        });

        addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //  SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", Locale.getDefault());
                var patata = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();
                assert sel != null;
                itemSeleccionado = controlador.getProveedorByID(sel.id);

                Proveedor provModel = new Proveedor(
                        txtNombreFantasia.getText(),
                        txtDireccion.getText(),
                        txtEmail.getText(),
                        txtIngresosBrutos.getText(),
                        DateActividades.getValue().toString(),
                        dropRubro.getSelectedItem().toString(),
                        //(float) 2000,
                        txtRazonSocial.getText(),
                        txtCuit.getText(),
                        dropResponsableIva.getSelectedItem().toString(),
                        sel.id
                );
                var isProvedorExiste=controlador.existsProveedor(txtCuit.getText());

                if ( !isProvedorExiste ) {
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

        removeProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();


                if ( sel != null ) {
                 var provedor =controlador.getProveedorByID(sel.id);
                    controlador.eliminarProveedor(provedor);
                }
            }
        });


        modifyProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //  SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", Locale.getDefault());
                var patata = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();
                assert sel != null;
                Proveedor provModel = new Proveedor(
                        txtNombreFantasia.getText(),
                        txtDireccion.getText(),
                        txtEmail.getText(),
                        txtIngresosBrutos.getText(),
                        DateActividades.getValue().toString(),
                        dropRubro.getSelectedItem().toString(),
                        //(float) 2000,
                        txtRazonSocial.getText(),
                        txtCuit.getText(),
                        dropResponsableIva.getSelectedItem().toString(),
                        sel.id
                );
                var isProvedorExiste=controlador.existsProveedor(txtCuit.getText());

                if ( isProvedorExiste ) {
                    controlador.actualizarProveedor(provModel);

                    JOptionPane.showMessageDialog(
                            modifyProveedor,
                            "proveedor",
                            "modificar proveedor",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(
                            modifyProveedor,
                            "No se pudo modificar el proveedor",
                            "Error modificar proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            };
        });
    }

    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.dropListaProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }
    private void setDDLRubros() {
        var controladorItem= ControladorItem.getInstancia().getOpcionesDDLRubros();
        this.dropRubro.setModel(new DefaultComboBoxModel(controladorItem.toArray()));
    }

    private void setDDLResponsableIva() {
        var controladorProv= ControladorProveedor.getInstancia().getOpcionesDDLResponsableIva();
        this.dropResponsableIva.setModel(new DefaultComboBoxModel(controladorProv.toArray()));
    }

    private void createUIComponents() { //componente custom para la fecha //TODO en facturas?
        Date date = new Date();
        SpinnerDateModel sm =
                new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH);
        DateActividades = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(DateActividades, "dd MM yyyy");
        DateActividades.setEditor(de);

    }
}


