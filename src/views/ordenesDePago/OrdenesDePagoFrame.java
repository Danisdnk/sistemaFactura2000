package views.ordenesDePago;

import controllers.ControladorOrdenesDePagos;
import models.documento.OrdenPago;
import views.utils.ButtonRenderer;

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

            addRow(dialog.showDialog());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addRow(OrdenPago op) {
        DefaultTableModel model = (DefaultTableModel) this.tbOPs.getModel();
        model.addRow(new Object[]{"Editar", op.getID()});
    }

    private void createUIComponents() {
        this.controlador = ControladorOrdenesDePagos.getInstancia();
        var ops = this.controlador.getOPs();
        DefaultTableModel dm = new DefaultTableModel();

        var dataVector = new Object[ops.size()][2];
        for (int i = 0; i < ops.size(); i++) {
            dataVector[i] = new Object[]{ "Editar", ops.get(i).getID() };
        }

        dm.setDataVector(dataVector, new Object[] { "", "Ordenes de Pago" });

        this.tbOPs = new JTable(dm);

        var btnEdit = new ButtonRenderer();

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                var row = table.getSelectedRow();

                // Columna 1 posee el id de la op.
                var opID = (int)table.getValueAt(row, 1);

                abrirModalOP(opID);
            }
        });

        this.tbOPs.getColumn("").setCellRenderer(btnEdit);
    }
}
