package views.ordenesDePago;

import controllers.ControladorOrdenesDePagos;
import models.documento.OrdenPago;
import models.dtos.ComprobanteDTO;
import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.proveedores.provedorView;
import views.utils.DateParse;
import views.utils.TableButton;
import views.utils.TableButtonListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class OrdenesDePagoFrame extends JFrame {
    private JPanel opMain;
    private JButton btnAddOP;
    private JTable tbOPs;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton hideButton;

    private ControladorOrdenesDePagos controlador;

    public OrdenesDePagoFrame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
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

        btnAddOP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirModalOP(null);
            }
        });
    }

    private void abrirModalOP(Integer opID){
        try {
            OrdenDePagoDialog dialog = new OrdenDePagoDialog(opID);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

            var op = dialog.showDialog();

            //solo los nuevos se agregan a la tabla.
            if (opID == null) {
                addRow(op);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addRow(OrdenPago op) {
        if (op != null && op.getProveedor() != null) {
            DefaultTableModel model = (DefaultTableModel) this.tbOPs.getModel();
            model.addRow(crearObjTabla(op));
        }
    }

    private void createUIComponents() {
        this.controlador = ControladorOrdenesDePagos.getInstancia();
        var ops = this.controlador.getOPs();

        DefaultTableModel dm = new DefaultTableModel(getHeaderTabla(), 1);

        var dataVector = new Object[ops.size()][2];
        for (int i = 0; i < ops.size(); i++) {
            dataVector[i] = crearObjTabla(ops.get(i));
        }

        dm.setDataVector(dataVector, getHeaderTabla());

        this.tbOPs = new JTable(dm);

        var btnEdit = new TableButton("Editar");
        btnEdit.addTableButtonListener(new TableButtonListener() {
            @Override
            public void tableButtonClicked(int row, int col) {
                var opID = (int)tbOPs.getValueAt(row, col);
                abrirModalOP(opID);
            }
        });

        var column = this.tbOPs.getColumn("");
        column.setCellRenderer(btnEdit);
        column.setCellEditor(btnEdit);
    }

    private  Object[] crearObjTabla(OrdenPago op){
        return new Object[]{ op.getID(), op.getProveedor().toString(), op.getNro(), op.getMontoTotal(), DateParse.unparse(op.getFecha())};
    }

    private Object[] getHeaderTabla() {
        return new Object[] { "", "Proveedor", "Nro", "Total", "Fecha pago" };
    }
}
