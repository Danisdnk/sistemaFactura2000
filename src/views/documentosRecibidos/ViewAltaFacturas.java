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
import views.proveedores.provedorView;
import views.utils.DateParse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.ParseException;
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
    private float montoIva = 0F;
    private float montoNeto = 0F;
    private float montoTotal = 0F;
    private float Iva = 0F;
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
        this.textDate.setText(DateParse.unparse(LocalDate.now()));
        textNeto.setText("0");
        textTotal.setText("0");
        textIva.setText("0");
        txtCUIT.setEnabled(false);
        txtIDItem.setEnabled(false);
        ddlProductos.addItem("Selecione un Proveedor");


        this.ddlProveedor.addActionListener(e -> {

            //factura es a un proveedor solo | inicializacoin de variables
            montoIva = 0F;
            montoNeto = 0F;
            montoTotal = 0F;
            Iva = 0F;
            textNeto.setText("0");
            textTotal.setText("0");
            textIva.setText("0");
            model.getDataVector().removeAllElements();

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

        agregarButton.addActionListener(e -> { ;
            var sel = (DDlProveedorItemDTO)ddlProductos.getSelectedItem() ;
            try {
                if((Float.parseFloat(textCant.getText()) > 0 && !textCant.getText().equals(""))) {
                    if (sel != null) {
                        setTable(sel);
                    } else {
                        JOptionPane.showMessageDialog(
                                guardarButton,
                                "Elija un producto",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(
                            guardarButton,
                            "Ingrese una cantidad valida",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception NumberFormatException){

                JOptionPane.showMessageDialog(
                        guardarButton,
                        "Error",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            SetTotales(Float.parseFloat(String.valueOf(sel.precio)) * Float.parseFloat(textCant.getText()), sel.iva);
            textCant.setText("0");


        });

        quitarButton.addActionListener(e -> {
            if (model.getRowCount() != 0 ){

                if(model.getRowCount()>0) {
                    int rows = model.getRowCount();
                    var subtotal = model.getValueAt(model.getRowCount()-1 , 5);
                    var Iva = model.getValueAt(model.getRowCount() -1, 3);


                    montoIva =  montoIva - Float.parseFloat(subtotal.toString())*(Float.parseFloat(Iva.toString())/100);
                    montoNeto = montoNeto - Float.parseFloat(subtotal.toString());
                    montoTotal = montoTotal - Float.parseFloat(subtotal.toString())*(Float.parseFloat(Iva.toString())/100) - Float.parseFloat(subtotal.toString());
                    textNeto.setText(String.valueOf(formato1.format(montoNeto)));
                    textTotal.setText(String.valueOf(formato1.format(montoTotal)));
                }
                model.removeRow(model.getRowCount()-1);
                if(model.getRowCount()==0) {
                    textIva.setText("0");
                    Iva = 0F;
                }

            }

        });

        guardarButton.addActionListener(e -> {
            try {
                if(model.getRowCount() != 0){

                    var sel = (DDLItemDTO) ddlProveedor.getSelectedItem();
                    var sel2 = (DDLItemDTO) ddlProductos.getSelectedItem();
                    if (sel != null) {
                        assert sel2 != null;
                        detectarPreciosImputs();
                        var ItemFactura = generarListaDeItemsFactura();
                        var proveedor = ControladorProveedor.getInstancia().getProveedorByID(sel.id);
                        var fecha = DateParse.parse(textDate.getText());
                        Factura f = new Factura(proveedor, montoNeto, Iva, montoIva, montoTotal, fecha, ItemFactura);
                        ControladorComprobantes.getInstancia().agregarFactura(f);

                        textTotal.setText("0");
                        textNeto.setText("0");
                        textIva.setText("0");
                        textCant.setText("");
                        montoIva = 0F;
                        montoNeto = 0F;
                        montoTotal = 0F;
                        Iva = 0F;
                        model.getDataVector().removeAllElements();
                        model.fireTableDataChanged();

                        JOptionPane.showMessageDialog(
                                guardarButton,
                                "Nueva factura creada",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                     }

                }else{
                        JOptionPane.showMessageDialog(
                                guardarButton,
                                "Agrege algun item a la factura",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                }


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
            DocumentosView dv = new DocumentosView();
            dv.setVisible(true);
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
            dispose();
        });
        proveedoresButton.addActionListener(e -> {
            provedorView principal = new provedorView();
            principal.setVisible(true);
            dispose();
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

    private void setTable(DDlProveedorItemDTO sel) {

        var itemPorProveedor =  ControladorItem.getInstancia().getProveedorItemByProveedor(txtCUIT.getText(), sel.id);
        model.addRow(new Object[]{
                itemPorProveedor.id,
                itemPorProveedor.producto,
                textCant.getText(),
                itemPorProveedor.iva,
                itemPorProveedor.precio,
                itemPorProveedor.precio*Integer.parseInt(textCant.getText())
        });
        model.fireTableDataChanged();
;
    }


    private void SetTotales(float subNetoP, double ivaP) {

        if(ivaP > Iva){
            textIva.setText(String.valueOf(ivaP));
            Iva = (float) ivaP;
        }
        montoIva =  montoIva + subNetoP * (Iva/ 100);
        montoNeto = montoNeto + subNetoP;
        this.textNeto.setText(String.valueOf(formato1.format(montoNeto)));
        montoTotal = montoNeto  + montoIva;
        this.textTotal.setText(String.valueOf(formato1.format(montoTotal)));
        
        

    }

    private void detectarPreciosImputs() throws ParseException {


        var netoText = Float.parseFloat(String.valueOf(formato1.parse(textNeto.getText()).floatValue()));
        var totalText = Float.parseFloat(String.valueOf(formato1.parse(textTotal.getText()).floatValue()));
        var ivaText = Float.parseFloat(String.valueOf(formato1.parse(textIva.getText()).floatValue()));

        if(netoText != montoNeto)
            montoNeto = netoText;
        if(totalText != montoTotal)
            montoTotal = totalText;
        if(ivaText != montoIva)
            montoIva = ivaText;
    }




    private void setDDLProveedor() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.ddlProveedor.setModel(new DefaultComboBoxModel(model.toArray()));
        ddlProveedor.removeItemAt(0);
    }

    private void setDDLProductos(String cuit) {
        var model = ControladorItem.getInstancia().getOpcionesDDLItemsByProveedor(cuit);
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }





}
