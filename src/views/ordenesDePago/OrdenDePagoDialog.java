package views.ordenesDePago;

import controllers.ControladorFacturas;
import controllers.ControladorOrdenesDePagos;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class OrdenDePagoDialog extends JDialog implements ActionListener{
    private JPanel opMain;
    private JComboBox<DDLItemDTO> ddlFormasPago;
    private JComboBox<DDLItemDTO> ddlProveedores;
    private JComboBox<DDLItemDTO> ddlFacturas;
    private JTextField txtTotalPagar;
    private JButton btnGuardar;

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

        this.ddlProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlProveedores.getSelectedItem();

                setDDLFacturas(sel.getId());
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

    private void setDDLProveedores() {
//        var model = ControladorFacturas.getInstancia().getOpcionesDDLFacturaByProveedor(provID);
//        this.ddlFacturas.setModel(new DefaultComboBoxModel((Vector) model));
    }

    private void setupForm(Integer opID) {
        this.op = this.controlador.getOPByID(opID);

        this.ddlFormasPago.setSelectedItem(this.op.getFormaPago().toDDL());
        this.ddlProveedores.setSelectedItem(this.op.getProveedor().toDDL());
        this.ddlFacturas.setSelectedItem(this.op.getFactura().toDDL());
        this.txtTotalPagar.setText(String.valueOf(this.op.getTotalACancelar()));
    }

    public void actionPerformed(ActionEvent e) {
        this.op = new OrdenPago();
        this.controlador.agregarOP(this.op);
        setVisible(false);
        dispose();
    }

    public OrdenPago showDialog() {
        setVisible(true);
        return this.op;
    }
}
