package views.ordenesDePago;

import controllers.ControladorComprobantes;
import controllers.ControladorOrdenesDePagos;
import models.documento.Comprobante;
import models.documento.OrdenPago;
import models.dtos.DDLItemDTO;
import models.documento.ItemOrdenPago;
import models.mediopago.TipoPago;
import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.proveedores.provedorView;
import views.utils.DateParse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemOrdenDePagoDialog extends JDialog implements ActionListener  {
    private JPanel main;
    private JTable tbCompsAsoc;
    private JButton btnAsociar;
    private JComboBox ddlComprobantesSinAsoc;
    private JButton btnGuardar;
    private JComboBox ddlTiposPago;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton hideButton;

    private TipoPago tipoDePago;
    private List<Comprobante> comprobantesAsociados;
    private List<Comprobante> comprobantesSinAsociar;
    private Comprobante seleccionado;
    private Integer indiceSeleccionado;

    public ItemOrdenDePagoDialog(Dialog owner, List<Comprobante> comprobantes, ItemOrdenPago itemOrdenPago) {
        super(owner);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(main);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);

        this.comprobantesAsociados = new ArrayList<>();
        this.comprobantesSinAsociar = comprobantes;

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



        if (itemOrdenPago != null){
            this.comprobantesAsociados = itemOrdenPago.getComprobantesAsociados();
            this.ddlTiposPago.setSelectedItem(itemOrdenPago.getTipoDePago().toDDL());
        }

        this.setTable();

        this.setDDLComprobantesSinAsoc();
        this.setDDLFormasPago();

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
                addRow(seleccionado);

                //sacar del ddl
                ddlComprobantesSinAsoc.removeItemAt(indiceSeleccionado);

                //limpiar seleccionado
                seleccionado = null;
                indiceSeleccionado = null;
            }
        });

        this.btnGuardar.addActionListener(this);
    }

    //Guardar
    @Override
    public void actionPerformed(ActionEvent e) {
        var tipo = (DDLItemDTO)this.ddlTiposPago.getSelectedItem();

        this.tipoDePago = ControladorOrdenesDePagos.getInstancia().getTipoDePagoPorID(tipo.id);

        setVisible(false);
        dispose();
    }

    public ItemOrdenPago showDialog() {
        setVisible(true);

        return this.tipoDePago != null ? new ItemOrdenPago(this.tipoDePago, this.comprobantesAsociados) : null;
    }

    private void setDDLComprobantesSinAsoc() {
        this.ddlComprobantesSinAsoc.setModel(new DefaultComboBoxModel(
                this.comprobantesSinAsociar
                        .stream()
                        .map(Comprobante::toDDL)
                        .toArray()));
    }

    private void setDDLFormasPago() {
        var model = ControladorOrdenesDePagos.getInstancia().getOpcionesDDLFormasDePago();
        this.ddlTiposPago.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void addRow(Comprobante comp) {
        DefaultTableModel model = (DefaultTableModel) this.tbCompsAsoc.getModel();
        model.addRow(crearObjTabla(comp));
    }

    private void createTable(){
        var model = new DefaultTableModel(getHeaderTabla(), 1);
        this.tbCompsAsoc = new JTable();
    }

    private void setTable(){
        var comps = this.comprobantesAsociados;

        DefaultTableModel dm = new DefaultTableModel();

        var dataVector = new Object[comps.size()][2];
        for (int i = 0; i < comps.size(); i++) {
            dataVector[i] = crearObjTabla(comps.get(i));
        }

        dm.setDataVector(dataVector, getHeaderTabla());

        this.tbCompsAsoc.setModel(dm);
    }

    private  Object[] crearObjTabla(Comprobante comp){
        return new Object[]{DateParse.unparse(comp.getFecha()), comp.toString(), comp.getTotal()};
    }

    private Object[] getHeaderTabla() {
        return new Object[] { "Fecha", "Tipo - Nro", "Total" };
    }

    private void createUIComponents() {
        createTable();
    }
}
