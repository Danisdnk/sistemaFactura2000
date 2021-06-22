package views.ordenesDePago;

import controllers.ControladorFacturas;
import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import views.utils.DateParse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// TODO leer valores para crear OP en metodo guardar
public class OrdenDePagoDialog extends JDialog implements ActionListener{
    private JPanel opMain;
    private JComboBox<DDLItemDTO> ddlFormasPago;
    private JComboBox<DDLItemDTO> ddlProveedores;
    private JComboBox<DDLItemDTO> ddlFacturas;
    private JTextField txtTotalPagar;
    private JButton btnGuardar;
    private JTextField txtFechaPago;

    private ControladorOrdenesDePagos controlador;
    private OrdenPago op;

    public OrdenDePagoDialog(Integer opID){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

        this.controlador = ControladorOrdenesDePagos.getInstancia();

        this.setDDLProveedores();
        this.setDDLFormasPago();

        this.ddlFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlFacturas.getSelectedItem();

                if (sel != null) {
                    var factura = ControladorFacturas.getInstancia().getFacturaByID(sel.getId());
                    txtTotalPagar.setText(String.valueOf(factura.getMonto()));
                }
            }
        });

        this.ddlProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlProveedores.getSelectedItem();

                if (sel != null) {
                    setDDLFacturas(sel.getId());
                } else {
                    clearDDLFacturas();
                }
            }
        });

        if (opID != null) {
            this.setupForm(opID);
        }

        btnGuardar.addActionListener(this);
    }

    private void setDDLFormasPago() {
        var model = this.controlador.getOpcionesDDLFormasDePago();
        this.ddlFormasPago.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLFacturas(int provID) {
        var model = ControladorFacturas.getInstancia().getOpcionesDDLFacturaByProveedor(provID);
        this.ddlFacturas.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void clearDDLFacturas(){
        this.ddlFacturas.setModel(new DefaultComboBoxModel());
    }

    private void setDDLProveedores() {
       var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
       this.ddlProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setupForm(Integer opID) {
        this.op = this.controlador.getOPByID(opID);

        this.ddlFormasPago.setSelectedItem(this.op.getFormaPago().toDDL());
        this.ddlProveedores.setSelectedItem(this.op.getProveedor().toDDL());
        this.ddlFacturas.setSelectedItem(this.op.getFactura().toDDL());
        this.txtTotalPagar.setText(String.valueOf(this.op.getTotalACancelar()));
    }

    // Guardar
    public void actionPerformed(ActionEvent e) {
        var proveedor = (DDLItemDTO)this.ddlProveedores.getSelectedItem();

        // lista de comprobantes. nueva pantalla de agregar comprobante?
        var factura = (DDLItemDTO)this.ddlFacturas.getSelectedItem();

        // switch case tipo de pago? nueva pantalla para pagos?
        // dos botones? Agregar pago fvo y Agregar pago cheque?
        var tipoPago = (DDLItemDTO)this.ddlFormasPago.getSelectedItem();

        var fechaPago = DateParse.parse(this.txtFechaPago.getText());
        var total = this.txtTotalPagar.getText();

        var op = new OrdenPago();
        op.setFechaPago(fechaPago);
        op.setTotalACancelar(Float.valueOf(total));

        this.op = op;
        this.controlador.agregarOP(this.op);
        setVisible(false);
        dispose();
    }

    public OrdenPago showDialog() {
        setVisible(true);
        return this.op;
    }
}
