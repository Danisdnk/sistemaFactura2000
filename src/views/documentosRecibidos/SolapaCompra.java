package views.documentosRecibidos;

import controllers.ControladorItem;
import controllers.ControladorOrdenCompra;
import controllers.ControladorProveedor;
import models.documento.ItemOrdenCompra;
import models.documento.OrdenCompra;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import models.proveedor.Item;
import models.proveedor.Proveedor;
import views.consultasGenerales.ViewConsultasGenerales;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import views.utils.DateParse;
import views.utils.MiTableModelCompra;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolapaCompra extends JFrame {
    private JPanel prinicipalSolapaCompra;
    private JPanel panelProveedor;
    private JComboBox<DDLItemDTO> comboBox1;
    private JTextField txtCUIT;
    private JComboBox comboBox2;
    private JTextField txtIDItem;
    private JTextField textCant;
    private JButton agregarButton;
    private JButton quitarButton;
    private JTable tablaItemsFactura;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField totalCompra;
    private JTextField textDate;
    private Integer proveedorID;
    private ControladorProveedor controlador;
    private Proveedor itemSeleccionado;
    private MiTableModelCompra miModeloCompra = new MiTableModelCompra();
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
    private JPanel principalSolapaCompra;
    private JScrollPane scrollPane;


    public SolapaCompra(){

        this.setContentPane(prinicipalSolapaCompra);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);

        this.controlador = ControladorProveedor.getInstancia();
        this.setDDLProveedores();
        //this.setDDLProductos();    //iniciarlo despues de seleccionar un proveedor
        LocalDate dateTime = LocalDate.now();
        this.textDate.setText(DateParse.unparse(dateTime));
        tablaItemsFactura.setModel(miModeloCompra);
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
                    setDDLProductos(itemSeleccionado.getCuit());   //iniciar ddlProductos luego de selecionar un proveedor
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

                miModeloCompra.add(sel.id,sel.producto,Integer.parseInt(textCant.getText()), sel.precio);



                    Item item= new Item(sel.id,sel.descripcion,Integer.parseInt(textCant.getText()),sel.precio);
                    ItemOrdenCompra itemOC = new ItemOrdenCompra(item,Integer.parseInt(textCant.getText()),sel.precio);
                    listaOrdenCompra.add(itemOC);
                miModeloCompra.fireTableDataChanged();
                getSumItems();
            }
        });

        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                miModeloCompra.removeRowAt(miModeloCompra.getRowCount() - 1);
                if (listaOrdenCompra.size() > 0) {
                    listaOrdenCompra.remove(listaOrdenCompra.size() - 1);


                }
                miModeloCompra.fireTableDataChanged();
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
                    JOptionPane.showMessageDialog(
                            guardarButton,
                            "Orden de compra generada",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView dc= new DocumentosView();
                dc.setVisible(true);
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


}
