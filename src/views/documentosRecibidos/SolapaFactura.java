package views.documentosRecibidos;

import models.dtos.DDLItemDTO;
import controllers.ControladorProveedor;
import models.proveedor.Proveedor;
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
    private JTextField textField3;
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

    public SolapaFactura() {

        this.setContentPane(prinicipalSolapaFactura);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(800, 600);
        this.setModal(true);

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

                if (sel != null) {  //si la seleccion es distinta de nulo hacemos varias cosas
                    itemSeleccionado = controlador.getProveedorByID(sel.id);
                    proveedorID = sel.id;

                    txtCUIT.setText(
                            !itemSeleccionado.getCuit()
                                    .isEmpty() ?
                                    itemSeleccionado.getCuit()
                                    : "");
                }

                assert sel != null;
                if (((sel.descripcion).equals("Nuevo Proveedor"))) {

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
    }
    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.comboBox1.setModel(new DefaultComboBoxModel(model.toArray()));
    }
}

