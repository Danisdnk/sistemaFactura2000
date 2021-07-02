package views.ordenesDePago;

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

// TODO leer valores para crear OP en metodo guardar
public class OrdenDePagoDialog extends JDialog implements ActionListener{
    private JPanel opMain;
    private JComboBox<DDLItemDTO> ddlFormasPago;
    private JComboBox<DDLItemDTO> ddlProveedores;
    private JTextField txtTotalComprobantes;
    private JButton btnGuardar;
    private JTextField txtFechaPago;
    private JButton btnAgregarComprobante;
    private JButton btnAgregarPago;
    private JTextField txtTotalACancelar;
    private JTextField txtDeuda;

    private ControladorOrdenesDePagos controlador;
    private OrdenPago op;
    private java.util.List<Comprobante> comprobantesAsociados;
    private Integer proveedorID;

    public OrdenDePagoDialog(Integer opID){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

        this.controlador = ControladorOrdenesDePagos.getInstancia();

        this.setDDLProveedores();
        this.setDDLFormasPago();

        this.ddlProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlProveedores.getSelectedItem();

                if (sel != null) {
                    proveedorID = sel.id;
                } else {
                    proveedorID = null;
                }
            }
        });

        btnAgregarComprobante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirModalComprobantes();
            }
        });

        if (opID != null) {
            this.setupForm(opID);
        }

        btnGuardar.addActionListener(this);
    }

    private void abrirModalComprobantes(){
        try {
            if (this.proveedorID != null) {
                var dialog = new ComprobantesAsociadosDialog(this, this.proveedorID, this.op);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

                this.comprobantesAsociados = dialog.showDialog();
                var total = this.comprobantesAsociados
                        .stream()
                        .mapToDouble(Comprobante::getTotal)
                        .sum();

                this.txtTotalComprobantes.setText(String.valueOf(total));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDDLFormasPago() {
        var model = this.controlador.getOpcionesDDLFormasDePago();
        this.ddlFormasPago.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProveedores() {
       var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
       this.ddlProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setupForm(Integer opID) {
        this.op = this.controlador.getOPByID(opID);

        this.ddlFormasPago.setSelectedItem(this.op.getFormaPago().toDDL());
        this.ddlProveedores.setSelectedItem(this.op.getProveedor().toDDL());
        this.txtTotalComprobantes.setText(String.valueOf(this.op.getTotal()));
    }

    // Guardar
    public void actionPerformed(ActionEvent e) {
        var prov = (DDLItemDTO)this.ddlProveedores.getSelectedItem();

        // switch case tipo de pago? nueva pantalla para pagos?
        // dos botones? Agregar pago fvo y Agregar pago cheque?
        var tipoPago = (DDLItemDTO)this.ddlFormasPago.getSelectedItem();

        var fechaPago = DateParse.parse(this.txtFechaPago.getText());
        var total = this.txtTotalComprobantes.getText();

        var op = new OrdenPago();
        op.setProveedor(ControladorProveedor.getInstancia().getProveedorByID(prov.id));
        op.setFecha(fechaPago);
        op.setTotal(Float.valueOf(total));
        op.setComprobantesAsociados(this.comprobantesAsociados);

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
