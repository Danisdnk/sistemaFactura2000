package views.ordenesDePago;

import controllers.ControladorOrdenesDePagos;
import models.documento.OrdenPago;
import models.dtos.ComprobanteDTO;
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

    private ControladorOrdenesDePagos controlador;

    public OrdenesDePagoFrame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

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

            if (op.getID() != opID) {
                addRow(op);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addRow(OrdenPago op) {
        if (op != null) {
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
                var opID = (int)tbOPs.getValueAt(row, 1);
                abrirModalOP(opID);
            }
        });

        var column = this.tbOPs.getColumn("");
        column.setCellRenderer(btnEdit);
        column.setCellEditor(btnEdit);
    }

    private  Object[] crearObjTabla(OrdenPago op){
        return new Object[]{ "", op.getID() };
    }

    private Object[] getHeaderTabla() {
        return new Object[] { "", "Nro" };
    }
}
