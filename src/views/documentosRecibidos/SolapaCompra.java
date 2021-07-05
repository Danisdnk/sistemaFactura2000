package views.documentosRecibidos;

import controllers.ControladorProveedor;
import models.dtos.DDlProveedorItemDTO;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import models.dtos.DDLItemDTO;
import controllers.ControladorItem;
import controllers.ControladorProveedor;
import models.proveedor.Proveedor;
import models.dtos.DDLItemDTO;
import controllers.ControladorProveedor;
import models.proveedor.Proveedor;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.MiTableModelCompra;
import views.utils.MiTableModelFactura;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import views.utils.DateParse;
import java.awt.event.ActionListener;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SolapaCompra extends JDialog {
    private JPanel prinicipalSolapaCompra;
    private JPanel panelProveedor;
    private JComboBox<DDLItemDTO> comboBox1;
    private JTextField txtCUIT;
    private JComboBox comboBox2;
    private JTextField txtIDItem;
    private JTextField textCant;
    private JButton agregarButton;
    private JButton quitarButton;
    private JTable tablaItemsFactura;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField textField4;
    private JTextField textDate;
    private Integer proveedorID;
    private ControladorProveedor controlador;
    private Proveedor itemSeleccionado;
    private MiTableModelCompra miModeloCompra = new MiTableModelCompra();
    private Integer rubroID;

    //botones nav
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;
    private JPanel principalSolapaCompra;
    private JScrollPane scrollPane;


    public SolapaCompra(){

        this.setContentPane(prinicipalSolapaCompra);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
        //this.setDDLProductos();    //iniciarlo despues de seleccionar un proveedor
        LocalDate dateTime = LocalDate.now();
        this.textDate.setText(DateParse.unparse(dateTime));
        tablaItemsFactura.setModel(miModeloCompra);





        miModeloCompra.add(1, "Bananas", 8, 20.0);
        miModeloCompra.add(2, "Bananas", 5, 27.5);
        miModeloCompra.add(3, "Bananas", 10, 38.2);
        miModeloCompra.add(4, "Bananas", 7, 28.2);

        this.comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) comboBox1.getSelectedItem();

                if ( sel != null ) {  //si la seleccion es distinta de nulo hacemos varias cosas
                    itemSeleccionado = controlador.getProveedorByID(sel.id);
                    proveedorID = sel.id;

                    txtCUIT.setText(
                            !itemSeleccionado.getCuit()
                                    .isEmpty() ?
                                    itemSeleccionado.getCuit()
                                    : "");
                    setDDLProductos(itemSeleccionado.getCuit());   //iniciar ddlProductos luego de selecionar un proveedor
                }

                assert sel != null;
                if ( ( ( sel.descripcion ).equals("Nuevo Proveedor") ) ) {

                    txtCUIT.setVisible(true);
                /*

                    txtNombreFantasia.setVisible(true);
                    txtDireccion.setVisible(true);
                    txtEmail.setVisible(true);
                    txtIngresosBrutos.setVisible(true);
                    DateActividades.setVisible(true);
                    txtRubro.setVisible(true);
                    txtRazonSocial.setVisible(true);

*/
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

        this.comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDlProveedorItemDTO)comboBox2.getSelectedItem();

                if (sel != null) {
                    rubroID = sel.id;
                    txtIDItem.setText(rubroID.toString());

                } else {
                    rubroID = null;
                }


            }

        });


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

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDlProveedorItemDTO)comboBox2.getSelectedItem() ;
                miModeloCompra.add(sel.id,sel.producto,Integer.parseInt(textCant.getText()), sel.precio);
                miModeloCompra.fireTableDataChanged();
            }
        });

        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miModeloCompra.removeRowAt(miModeloCompra.getRowCount()-1);
            }
        });
    }

    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.comboBox1.setModel(new DefaultComboBoxModel(model.toArray()));
    }
    private void setDDLProductos(String cuit) {
        var model = ControladorItem.getInstancia().getOpcionesDDLItemsByProveedor(cuit);
        this.comboBox2.setModel(new DefaultComboBoxModel(model.toArray()));
    }


}
