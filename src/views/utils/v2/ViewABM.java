package views.utils.v2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewABM<TModelo, TModeloTabla> {
    private Controlador<TModelo, TModeloTabla> controlador;

    private JFrame frm;
    private JTable tabla;
    private SetRow<TModeloTabla> setRowFunc;

    public ViewABM(JFrame frm) {
        this.frm = frm;
    }

    public ActionListener addAction(
            SetDialog<TModelo> setDialog,
            MapModeloTabla<TModelo, TModeloTabla> map) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var dialog = setDialog.setDialog();

                    addRow(map.map(dialog.showDialog()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    public void setControlador(Controlador<TModelo, TModeloTabla> controlador) {
        this.controlador = controlador;
    }

    private void addRow(TModeloTabla m) {
        DefaultTableModel model = (DefaultTableModel) this.tabla.getModel();
        model.addRow(this.setRowFunc.setRow(m));
    }

    public void crearTabla(
            JTable tabla,
            String botonera,
            Object[] nombresColumnas,
            SetRow<TModeloTabla> setRowFunc) {
        this.setRowFunc = setRowFunc;
        var d = this.controlador.getDatos();

        var datos = new Object[d.size()][2];

        for (int i = 0; i < d.size(); i++) {
            datos[i] = setRowFunc.setRow(d.get(i));
        }

        var model = new DefaultTableModel(datos, nombresColumnas);

        tabla = new JTable(model);

        var btnEditar = new ButtonRenderer();

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        });

        tabla.getColumn(botonera).setCellRenderer(btnEditar);

        this.tabla = tabla;
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
