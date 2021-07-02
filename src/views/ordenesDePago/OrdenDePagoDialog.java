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
    private JComboBox<DDLItemDTO> ddlProveedores;
    private JTextField txtTotalComprobantes;
    private JButton btnGuardar;
    private JTextField txtFechaPago;
    private JButton btnDetalle;
    private JTextField txtTotalACancelar;
    private JTextField txtDeuda;

    private ControladorOrdenesDePagos controlador;
    private OrdenPago op;
    private Integer proveedorID;

    public OrdenDePagoDialog(Integer opID){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

        this.controlador = ControladorOrdenesDePagos.getInstancia();

        if (opID != null) {
            this.setupForm(opID);
        } else {
            this.op = new OrdenPago();
        }

        this.setDDLProveedores();

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

        btnGuardar.addActionListener(this);

        btnDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDetalleOrdenPago();
            }
        });
    }

    private void abrirDetalleOrdenPago(){
        try {
            if (this.proveedorID != null) {
                var dialog = new OrdenDePagoItemsDialog(this, this.proveedorID, this.op.getItems());
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

                var items = dialog.showDialog();
                this.op.setItems(items);

                this.txtTotalComprobantes.setText(String.valueOf(this.op.getTotal()));
                this.txtTotalACancelar.setText(String.valueOf(this.op.getTotal()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDDLProveedores() {
       var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
       this.ddlProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setupForm(Integer opID) {
        this.op = this.controlador.getOPByID(opID);

        this.ddlProveedores.setSelectedItem(this.op.getProveedor().toDDL());
        this.txtTotalComprobantes.setText(String.valueOf(this.op.getTotal()));
    }

    // Guardar
    public void actionPerformed(ActionEvent e) {
        var prov = (DDLItemDTO)this.ddlProveedores.getSelectedItem();

        var fechaPago = DateParse.parse(this.txtFechaPago.getText());
        var total = this.txtTotalComprobantes.getText();

        this.op.setProveedor(ControladorProveedor.getInstancia().getProveedorByID(prov.id));
        this.op.setFecha(fechaPago);
        this.op.setTotal(Float.valueOf(total));

        this.controlador.agregarOP(this.op);
        setVisible(false);
        dispose();
    }

    public OrdenPago showDialog() {
        setVisible(true);
        return this.op;
    }
}
