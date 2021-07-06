package views.documentosRecibidos;

import controllers.ControladorComprobantes;
import controllers.ControladorItem;
import controllers.ControladorProveedor;
import models.documento.Factura;
import models.documento.ItemFactura;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Proveedor;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.utils.DateParse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewAltaFacturas extends JFrame{
    private JPanel altaDocus;
    private JPanel compulsa;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTabbedPane tabbedPane1;
    private JPanel prinicipalSolapaCompra;
    private JPanel panelProveedor;
    private JComboBox ddlProveedor;
    private JTextField txtCUIT;
    private JComboBox ddlProductos;
    private JTextField txtIDItem;
    private JTextField textCant;
    private JButton agregarButton;
    private JButton quitarButton;
    private JTable tablaItemsFactura;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField textTotal;
    private JTextField textDate;
    private JTextField textIva;
    private JTextField textNeto;
    private JPanel ocTAB;
    private JPanel textFecha;
    private Proveedor itemSeleccionado;
    private DefaultTableModel model;


    DecimalFormat formato1 = new DecimalFormat("#.##");

    public ViewAltaFacturas(){


        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(altaDocus);
        this.setSize(1000, 900);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        model.addColumn("IVA");
        model.addColumn("Precio");
        model.addColumn("Subtotal");
        tablaItemsFactura.setModel(model);

        this.setDDLProveedor();
        textNeto.setText("0");
        textTotal.setText("0");
        textIva.setText("0");
        this.textDate.setText(DateParse.unparse(LocalDate.now()));
        double montoIva =0;


        this.ddlProveedor.addActionListener(e -> {

            var sel = (DDLItemDTO) ddlProveedor.getSelectedItem();

            if ( sel != null ) {  //si la seleccion es distinta de nulo hacemos varias cosas
                itemSeleccionado = ControladorProveedor.getInstancia().getProveedorByID(sel.id);

                txtCUIT.setText(
                        !itemSeleccionado.getCuit()
                                .isEmpty() ?
                                itemSeleccionado.getCuit()
                                : "");
                setDDLProductos(itemSeleccionado.getCuit());   //iniciar ddlProductos luego de selecionar un proveedor
            }

        });

        this.ddlProductos.addActionListener(e -> {
            Integer rubroID;
            var sel = (DDlProveedorItemDTO)ddlProductos.getSelectedItem();

            if (sel != null) {
                rubroID = sel.id;
                txtIDItem.setText(rubroID.toString());

            } else {
                rubroID = null;
            }


        });

        agregarButton.addActionListener(e -> {
            var sel = (DDlProveedorItemDTO)ddlProductos.getSelectedItem() ;
            assert sel != null;
            SetTotales(setTable(sel), sel.iva, montoIva);


        });

        quitarButton.addActionListener(e -> model.removeRow(model.getRowCount()-1));

        guardarButton.addActionListener(e -> {
            try {
                var sel = (DDLItemDTO) ddlProveedor.getSelectedItem();
                var sel2 = (DDLItemDTO) ddlProductos.getSelectedItem();
                if (sel != null) {

                    assert sel2 != null;
                    var ItemFactura = generarListaDeItemsFactura();
                    var proveedor = ControladorProveedor.getInstancia().getProveedorByID(sel.id);
                    var fecha = DateParse.parse(textDate.getText());
                    var iva = Float.parseFloat(textIva.getText());
                    Double montoNeto = Double.parseDouble(textNeto.getText());
                    System.out.println(textTotal.getText());
                    var montoTotal = Float.parseFloat(textTotal.getText().toString());
                    Factura f = new Factura(proveedor, 41f, iva, (float) montoIva, montoTotal, fecha, ItemFactura);
                    ControladorComprobantes.getInstancia().agregarFactura(f);
                }

                JOptionPane.showMessageDialog(
                        guardarButton,
                        "Nueva factura creada",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);




            }catch  (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        guardarButton,
                        "Error al ingresar la factura, intente nuevamente",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);



            }



        });

        cancelarButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            dispose();
        });

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales cons = new ViewConsultasGenerales();
            cons.setVisible(true);
            dispose();
        });

        DocumentosButton.addActionListener(e -> {
            DocumentosView docu = new DocumentosView();
            docu.setVisible(true);
            dispose();
        });

        usuariosButton.addActionListener(e -> {
            loginView principal = new loginView();
            principal.setVisible(true);
            dispose();//esto cierra la ventana anterior
        });

    }

    private List<ItemFactura> generarListaDeItemsFactura() {

        List<ItemFactura> itemsFactura = new ArrayList<>();
        for(int i= 0; i<model.getRowCount(); i++){

            var Item = ControladorItem.getInstancia().getItemByID((int)model.getValueAt(i,0));

            var cant = model.getValueAt(i, 2);
            var precio = model.getValueAt(i, 5);

            itemsFactura.add(new ItemFactura(Item, Float.parseFloat(cant.toString()) , Float.parseFloat(precio.toString())));

        };
        return itemsFactura;

    }

    private float setTable(DDlProveedorItemDTO sel) {

        float sum = 0;


        var itemPorProveedor =  ControladorItem.getInstancia().getProveedorItemByProveedor(txtCUIT.getText(), sel.id);
        model.addRow(new Object[]{
                itemPorProveedor.id,
                itemPorProveedor.producto,
                textCant.getText(),
                itemPorProveedor.iva,
                itemPorProveedor.precio,
                itemPorProveedor.precio*Integer.parseInt(textCant.getText())
        });
        sum+=  itemPorProveedor.precio*Integer.parseInt(textCant.getText());
        model.fireTableDataChanged();
        return  sum;
    }


    private void SetTotales(float neto, double iva, double montoIva) {

        montoIva = neto * (iva / 100);
        float netoExistente = Float.parseFloat(textNeto.getText()) ;
        netoExistente = netoExistente + neto;
        this.textNeto.setText(String.valueOf(formato1.format( netoExistente)));

        if(iva > Float.parseFloat(textIva.getText())){
            textIva.setText(String.valueOf(iva));
        }

        this.textTotal.setText(String.valueOf(formato1.format(netoExistente + montoIva)));
        
        

    }




    private void setDDLProveedor() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
       // List<String> lista = new ArrayList<>();
        //for (DDLItemDTO m : model){
       //     var datecomps =  m.descripcion.split("-");
       //     lista.add(datecomps[0]);
       // }
        this.ddlProveedor.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos(String cuit) {
        var model = ControladorItem.getInstancia().getOpcionesDDLItemsByProveedor(cuit);
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }





}
