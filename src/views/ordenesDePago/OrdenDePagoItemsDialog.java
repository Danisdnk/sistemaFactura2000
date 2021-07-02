package views.ordenesDePago;

import controllers.ControladorComprobantes;
import models.documento.Comprobante;
import models.documento.ItemOrdenPago;
import views.utils.MultiLineTableCell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;
import java.util.List;

public class OrdenDePagoItemsDialog extends JDialog implements ActionListener  {
    private JTable tbPagos;
    private JButton btnAgregarPago;
    private JButton btnGuardar;
    private JPanel main;

    private List<ItemOrdenPago> itemsOP;

    public OrdenDePagoItemsDialog(Dialog owner, int provId, List<ItemOrdenPago> itemsOP) {
        super(owner);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(main);
        this.setSize(400,400);
        this.pack();

        this.itemsOP = itemsOP;

        btnAgregarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirModalItemOrdenPago(provId, null);
            }
        });

        btnGuardar.addActionListener(this);

        this.setTable();
    }

    //Guardar
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }

    public List<ItemOrdenPago> showDialog() {
        setVisible(true);

        return this.itemsOP;
    }

    private void abrirModalItemOrdenPago(int provId, ItemOrdenPago item) {
        try {
            var asociados = this.itemsOP
                    .stream()
                    .flatMap(x -> x.getComprobantesAsociados().stream())
                    .toList();

            //todos los del proveedor que aun no esten asociados
            var comprobantesSinAsociar = ControladorComprobantes
                    .getInstancia()
                    .getComprobantesByProveedor(provId)
                    .stream()
                    .filter(x -> !asociados.stream().anyMatch(y -> x.getID() == y.getID()))
                    .toList();

            var dialog = new ItemOrdenDePagoDialog(this, comprobantesSinAsociar, item);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

            var itemOP = dialog.showDialog();

            if (itemOP != null) {
                this.itemsOP.add(itemOP);
                addRow(itemOP);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addRow(ItemOrdenPago pago) {
        DefaultTableModel model = (DefaultTableModel) this.tbPagos.getModel();
        model.addRow(crearObjTabla(pago));
    }

    private void createTable(){
        var model = new DefaultTableModel(getHeaderTabla(), 1);
        this.tbPagos = new JTable(model);
        //comprobantes uno por linea
        //var renderer = new MultiLineTableCell();
        //tbPagos.setDefaultRenderer(String[].class, renderer);
        //tbPagos.getColumnModel().getColumn(3).setCellRenderer(renderer);
    }

    private void setTable(){
        var pagos = this.itemsOP;

        DefaultTableModel dm = (DefaultTableModel) this.tbPagos.getModel();

        var dataVector = new Object[pagos.size()][2];
        for (int i = 0; i < pagos.size(); i++) {
            dataVector[i] = crearObjTabla(pagos.get(i));
        }

        dm.setDataVector(dataVector, getHeaderTabla());

        this.tbPagos.setModel(dm);
    }

    private  Object[] crearObjTabla(ItemOrdenPago pago){
        var total = pago.getTotal();
        var comprobantes = pago
                .getComprobantesAsociados()
                .stream()
                .map(Comprobante::toString)
                .collect(Collectors.joining(",\n"));
                // TODO para multi line cell. Muestra direccion de memoria en vez de valor
                //.toList()
                //.toArray(String[]::new);

        return new Object[]{ pago.getTipoDePago().getType(), "11111", total, comprobantes};
    }

    private Object[] getHeaderTabla() {
        return new Object[] { "Tipo", "Nro", "Total", "Comprobantes" };
    }

    private void createUIComponents() {
        createTable();
    }
}
