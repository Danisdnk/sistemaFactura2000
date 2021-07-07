package views.ordenesDePago;

import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.proveedores.provedorView;
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
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton hideButton;

    private ControladorOrdenesDePagos controlador;
    private OrdenPago op;
    private Integer proveedorID;

    public OrdenDePagoDialog(Integer opID){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);

        this.controlador = ControladorOrdenesDePagos.getInstancia();
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

        this.setDDLProveedores();

        if (opID != null) {
            this.setupForm(opID);
        } else {
            this.op = new OrdenPago();
        }

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

                this.txtTotalComprobantes.setText(String.valueOf(this.op.getMontoTotal()));
                this.txtTotalACancelar.setText(String.valueOf(this.op.getMontoTotal()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDDLProveedores() {
       var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
       this.ddlProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
        ddlProveedores.removeItemAt(0);
    }

    private void setupForm(Integer opID) {
        this.op = this.controlador.getOPByID(opID);
        this.proveedorID = this.op.getProveedor().getID();

        this.ddlProveedores.setSelectedItem(this.op.getProveedor().toDDL());
        this.ddlProveedores.setEditable(false);
        this.ddlProveedores.setEnabled(false);
        this.txtFechaPago.setText(DateParse.unparse(this.op.getFecha()));
        this.txtTotalComprobantes.setText(String.valueOf(this.op.getMontoTotal()));
        this.txtTotalACancelar.setText(String.valueOf(this.op.getMontoTotal()));
    }

    // Guardar
    public void actionPerformed(ActionEvent e) {
        var prov = (DDLItemDTO)this.ddlProveedores.getSelectedItem();

        var fechaPago = DateParse.parse(this.txtFechaPago.getText());

        this.op.setProveedor(ControladorProveedor.getInstancia().getProveedorByID(prov.id));
        this.op.setFecha(fechaPago);

        this.controlador.agregarOP(this.op);
        setVisible(false);
        dispose();
    }

    public OrdenPago showDialog() {
        setVisible(true);
        return this.op;
    }
}
