package views.ordenesDePago;

import controllers.ControladorComprobantes;
import models.documento.Comprobante;
import models.documento.Factura;
import models.documento.OrdenPago;
import models.dtos.ComprobanteDTO;
import models.dtos.DDLItemDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ComprobantesAsociadosDialog extends JDialog implements ActionListener  {
    private JPanel main;
    private JTable tbFacsAsoc;
    private JButton btnAsociar;
    private JComboBox ddlComprobantesSinAsoc;

    private List<Comprobante> comprobantesAsociados;
    private List<Comprobante> comprobantesSinAsociar;
    private Comprobante seleccionado;
    private Integer indiceSeleccionado;

    public ComprobantesAsociadosDialog(Dialog owner, int provID, OrdenPago op) {
        super(owner);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(main);
        this.setSize(700,400);
        this.pack();

        this.comprobantesAsociados = new ArrayList<>();

        var comprobantes = ControladorComprobantes
                .getInstancia()
                .getComprobantesByProveedor(provID);

        if (op != null){
            this.comprobantesAsociados = op.getComprobantesAsociados();
        }

        this.setDDLComprobantesSinAsoc(comprobantes);

        this.ddlComprobantesSinAsoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlComprobantesSinAsoc.getSelectedItem();

                indiceSeleccionado = ddlComprobantesSinAsoc.getSelectedIndex();
                seleccionado = comprobantesSinAsociar
                        .stream()
                        .filter(c -> c.getID() == sel.id)
                        .findFirst()
                        .get();
            }
        });

        btnAsociar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //agregar a la lista de asociados
                comprobantesAsociados.add(seleccionado);

                //agregar a la tabla
                addRow(seleccionado.toCompDTO());

                //sacar del ddl
                ddlComprobantesSinAsoc.remove(indiceSeleccionado);

                //limpiar seleccionado
                seleccionado = null;
                indiceSeleccionado = null;
            }
        });

        setTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
    }

    public List<Comprobante> showDialog() {
        setVisible(true);
        return this.comprobantesAsociados;
    }

    private void createTable(){
        this.tbFacsAsoc = new JTable(new DefaultTableModel());
    }

    private void setTable(){
        var comps = this.comprobantesAsociados.stream().map(Comprobante::toCompDTO).toList();

        DefaultTableModel dm = new DefaultTableModel();

        var dataVector = new Object[comps.size()][2];
        for (int i = 0; i < comps.size(); i++) {
            dataVector[i] = crearObjTabla(comps.get(i));
        }

        dm.setDataVector(dataVector, new Object[] { "Fecha", "Nro", "Monto" });

        this.tbFacsAsoc.setModel(dm);
    }

    private void setDDLComprobantesSinAsoc(List<Comprobante> comprobantes) {
        this.comprobantesSinAsociar = comprobantes
                .stream()
                .filter(comp -> !this.comprobantesAsociados.stream().anyMatch(asoc -> asoc.getNro() == comp.getNro()))
                .toList();

        this.ddlComprobantesSinAsoc.setModel(new DefaultComboBoxModel(
                this.comprobantesSinAsociar
                        .stream()
                        .map(Comprobante::toDDL)
                        .toArray()));
    }

    private void addRow(ComprobanteDTO comp) {
        DefaultTableModel model = (DefaultTableModel) this.tbFacsAsoc.getModel();
        model.addRow(crearObjTabla(comp));
    }

    private  Object[] crearObjTabla(ComprobanteDTO comp){
        return new Object[]{comp.fecha, comp.nro, comp.total};
    }

    private void createUIComponents() {
        createTable();
    }
}
