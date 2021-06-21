package views.ordenesDePago;

import controllers.ControladorOrdenPago;
import models.documento.OrdenPago;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OrdenesDePago extends JFrame {
    private JPanel opMain;
    private JButton btnAddOP;
    private JTable tbOPs;

    private ControladorOrdenPago controlador;

    public OrdenesDePago() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

        btnAddOP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrdenDePago dialog = new OrdenDePago();
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

                    addRow(dialog.showDialog());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void addRow(OrdenPago op) {
        DefaultTableModel model = (DefaultTableModel) this.tbOPs.getModel();
        model.addRow(new Object[]{"Editar", op.getOpID()});
    }

    private void createUIComponents() {
        this.controlador = ControladorOrdenPago.getInstancia();
        var ops = this.controlador.getOPs();
        DefaultTableModel dm = new DefaultTableModel();

        var dataVector = new Object[ops.size()][2];
        for (int i = 0; i < ops.size(); i++) {
            dataVector[i] = new Object[]{ "Editar", ops.get(i).getOpID() };
        }

        dm.setDataVector(dataVector, new Object[] { "Acciones", "Ordenes de Pago" });

        this.tbOPs = new JTable(dm);

        this.tbOPs.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }

            return this;
        }
    }
}
