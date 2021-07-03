package views.documentosRecibidos;

import models.dtos.DDLItemDTO;
import controllers.ControladorProveedor;
import models.proveedor.Proveedor;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.MiTableModelFactura;
import controllers.ControladorComprobantes;
import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.Comprobante;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import views.utils.DateParse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolapaFactura extends JDialog {
    private JComboBox<DDLItemDTO> comboBox1;
    private JTextField txtCUIT;
    private JPanel panelProveedor;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTextField textCant;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField textField4;
    private JTextField textField5;
    private JButton agregarButton;
    private JButton quitarButton;
    private JPanel prinicipalSolapaFactura;
    private JComboBox comboBox3;
    private JTable tablaItemsFactura;
    private Integer proveedorID;
    private ControladorProveedor controlador;
    private Proveedor itemSeleccionado;
    private MiTableModelFactura miModeloFactura = new MiTableModelFactura();
    //botones nav
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;

    public SolapaFactura() {

        this.setContentPane(prinicipalSolapaFactura);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        tablaItemsFactura.setModel(miModeloFactura);

        miModeloFactura.add(1, "Bananas", 8, 20.0);
        miModeloFactura.add(2, "Bananas", 5, 27.5);
        miModeloFactura.add(3, "Bananas", 10, 38.2);
        miModeloFactura.add(4, "Bananas", 7, 28.2);

        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
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
                var sel = (DDLItemDTO)comboBox2.getSelectedItem() ;
                miModeloFactura.add(sel.id,sel.descripcion,Integer.parseInt(textCant.getText()),28.00);
                miModeloFactura.fireTableDataChanged();
            }
        });
    }
    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.comboBox1.setModel(new DefaultComboBoxModel(model.toArray()));
    }
}

