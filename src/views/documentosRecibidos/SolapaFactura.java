package views.documentosRecibidos;

import controllers.ControladorItem;
import controllers.ControladorOrdenCompra;
import controllers.ControladorProveedor;
import models.documento.ItemOrdenCompra;
import models.documento.OrdenCompra;
import models.dtos.DDLItemDTO;
import models.dtos.DDLOrdenDeCompraProveedor;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Item;
import models.proveedor.Proveedor;
import views.MenuPrincipal;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.DateParse;
import views.utils.MiTableModelFactura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolapaFactura extends JFrame {
    private JComboBox<DDLItemDTO> comboBox1;
    private JTextField txtCUIT;
    private JPanel panelProveedor;
    private JComboBox comboBox2;
    private JTextField txtIDItem;
    private JTextField textCant;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField totalCompra;
    private JTextField textDate;
    private JButton agregarButton;
    private JButton quitarButton;
    private JPanel prinicipalSolapaFactura;
    private JComboBox<DDLOrdenDeCompraProveedor> comboBox3;
    private JTable tablaItemsFactura;
    private Integer proveedorID;
    private ControladorProveedor controlador;
    private Proveedor itemSeleccionado;
    private MiTableModelFactura miModeloFactura = new MiTableModelFactura();
    private Integer rubroID;
    private ControladorOrdenCompra controladorOC;
    //botones nav
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;

    public SolapaFactura() {

        this.setContentPane(prinicipalSolapaFactura);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);

        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
        //this.setDDLProductos();    //iniciarlo despues de seleccionar un proveedor
        LocalDate dateTime = LocalDate.now();
        this.textDate.setText(DateParse.unparse(dateTime));
        tablaItemsFactura.setModel(miModeloFactura);
        List<ItemOrdenCompra> listaOrdenCompra= new ArrayList<>();


        this.comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) comboBox1.getSelectedItem();

                if ( sel != null ) {  //si la seleccion es distinta de nulo hacemos varias cosas
                    itemSeleccionado = controlador.getProveedorByID(sel.id);
                    proveedorID = sel.id;

                    txtCUIT.setText(
                            !itemSeleccionado.getCuit()
                                    .isEmpty() ?
                                    itemSeleccionado.getCuit()
                                    : "");
                    setDDLProductos(itemSeleccionado.getCuit());
                    setDDLOC();//iniciar ddlProductos luego de selecionar un proveedor
                }

                assert sel != null;
                if ( ( ( sel.descripcion ).equals("Nuevo Proveedor") ) ) {

                    txtCUIT.setVisible(true);
                /*

                    txtNombreFantasia.setVisible(true);
                    txtDireccion.setVisible(true);
                    txtEmail.setVisible(true);
                    txtIngresosBrutos.setVisible(true);
                    DateActividades.setVisible(true);
                    txtRubro.setVisible(true);
                    txtRazonSocial.setVisible(true);

*/
                } else {

                    proveedorID = null;
                    // txtNombreFantasia.setText(sel.descripcion);
                    // txtDireccion.setVisible(false);
                    // txtEmail.setVisible(false);
                    //  txtIngresosBrutos.setVisible(false);
                    //   DateActividades.setVisible(false);
                    //  txtRubro.setVisible(false);
                    //     txtRazonSocial.setVisible(false);
                    //       txtCuit.setVisible(false);
                }
            }
        });

        this.comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDlProveedorItemDTO)comboBox2.getSelectedItem();

                if (sel != null) {
                    rubroID = sel.id;
                    txtIDItem.setText(rubroID.toString());

                } else {
                    rubroID = null;
                }


            }

        });


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

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDlProveedorItemDTO)comboBox2.getSelectedItem();
                var sel2 = (DDLItemDTO)comboBox1.getSelectedItem() ;
                var proveedor = ControladorProveedor.getInstancia().getProveedorByID(sel2.id);

                miModeloFactura.add(sel.id,sel.producto,Integer.parseInt(textCant.getText()), sel.precio);
                miModeloFactura.fireTableDataChanged();


                Item item= new Item(sel.id,sel.descripcion,Integer.parseInt(textCant.getText()),sel.precio);
                ItemOrdenCompra itemOC = new ItemOrdenCompra(item,Integer.parseInt(textCant.getText()),sel.precio);
                listaOrdenCompra.add(itemOC);

                getSumItems();
            }
        });

        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                miModeloFactura.removeRowAt(miModeloFactura.getRowCount()-1);
                if (listaOrdenCompra.size() > 0) {
                    listaOrdenCompra.remove(listaOrdenCompra.size() - 1);


                }
                miModeloFactura.fireTableDataChanged();
                getSumItems();

            }
        });

/*
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) comboBox1.getSelectedItem();
                if (sel != null){
                    var proveedor = controlador.getProveedorByID(sel.id);
                    var fecha = DateParse.parse(textDate.getText());
                    Float total = Float.parseFloat(totalCompra.getText());
                    OrdenCompra oc = new OrdenCompra(fecha,proveedor,total);
                    controladorOC.getInstancia().agregarOrdenCompra(oc);
                }



            }
        }); */

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDlProveedorItemDTO)comboBox2.getSelectedItem();
                var idproveedor = (DDLItemDTO)comboBox1.getSelectedItem() ;
                if (idproveedor!=null){
                    var proveedor = ControladorProveedor.getInstancia().getProveedorByID(idproveedor.id);
                    OrdenCompra ordenCompra= new OrdenCompra(DateParse.parse(textDate.getText()),proveedor,listaOrdenCompra,Float.parseFloat(totalCompra.getText()));
                    controladorOC.getInstancia().agregarOrdenCompra(ordenCompra);
                }

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var viewPrincipal = new MenuPrincipal("Sistema Factura 2000");
                viewPrincipal.setVisible(true);
                dispose();
            }
        });
    }

    private void getSumItems(){
        float sum = 0;
        int numOfRows = tablaItemsFactura.getRowCount();
        for (int i =0; i < numOfRows; i++){
            Object cost = tablaItemsFactura.getValueAt(i, 3);
            Object cant = tablaItemsFactura.getValueAt(i,2);
            if (cost instanceof Number) {
                cost = ((Number) cost).floatValue()*((Number) cant).floatValue() ;
                sum += ((Number) cost).floatValue();
            }

        }
        totalCompra.setText(String.valueOf(sum));
    }

    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.comboBox1.setModel(new DefaultComboBoxModel(model.toArray()));
        comboBox1.removeItemAt(0);
    }
    private void setDDLProductos(String cuit) {
        var model = ControladorItem.getInstancia().getOpcionesDDLItemsByProveedor(cuit);
        this.comboBox2.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private  void setDDLOC(){
        var model = ControladorOrdenCompra.getInstancia().getOCByProveedor(proveedorID);
        this.comboBox3.setModel(new DefaultComboBoxModel(model.toArray()));
    }

}

