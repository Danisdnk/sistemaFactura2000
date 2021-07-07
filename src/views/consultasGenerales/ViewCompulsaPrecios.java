package views.consultasGenerales;

import controllers.ControladorItem;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewCompulsaPrecios extends JFrame{


    private JPanel compulsa;
    private JPanel panelCentral;

    private JComboBox<DDLItemDTO> ddlRubros;
    private JComboBox<String> ddlProductos;


    private JButton consultarPreciosButton;
    private JButton cancelarButton;

    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextArea TextResultado;
    private JTabbedPane tab;
    private JTable tablePrecios;
    private DefaultTableModel model;
    private ControladorItem controlador;


    private Integer rubroID;
    private Integer provductoID;


    public ViewCompulsaPrecios() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(compulsa);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Precio Neto");
        tablePrecios.setModel(model);
        this.controlador = ControladorItem.getInstancia();



        this.setDDLRubros();
        this.ddlProductos.addItem("Selecione un Rubro");

        this.ddlRubros.addActionListener(e -> {
            var sel = (DDLItemDTO)ddlRubros.getSelectedItem();

            if (sel != null ) {
                rubroID = sel.id;
                setDDLProductos(rubroID);
            } else {
                rubroID = null;
            }


        });

        this.consultarPreciosButton.addActionListener(e -> {


            if( ddlProductos.getSelectedItem() != ("Selecione un Rubro")) {
                var sel = (DDLItemDTO)ddlProductos.getSelectedItem() ;
                if (sel != null) {
                    setJtextArea(sel.id);
                    setTablePrecios(sel.id);
                } else {
                    rubroID = null;

                    JOptionPane.showMessageDialog(
                            consultarPreciosButton,
                            "No se selecciono ningun producto",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);


                }
            }else{
                JOptionPane.showMessageDialog(
                        consultarPreciosButton,
                        "No se selecciono ningun Rubro",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);


            }
        });









        cancelarButton.addActionListener(e -> {
            DocumentosView principal = new DocumentosView();
            principal.setVisible(true);
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


    }




    private void setDDLRubros() {
        var model = controlador.getOpcionesDDLRubros();
        this.ddlRubros.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos(int rubroID) {
        var model = controlador.getOpcionesDDLItems(rubroID);
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos() {
        var model = controlador.getOpcionesDDLItems();
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setJtextArea(int itemID) {
        var itemPorProveedor = controlador.getProveedorItemsByItem(itemID);
        if (itemPorProveedor.isEmpty()){
            this.TextResultado.setText("Este producto no posee precio fijados por vendedores actualmente ");
        }
        else{

            String text = "";

            for (int i=0; i<itemPorProveedor.size(); i++ ){
                System.out.println(text);

                text = text
                        + "El Proveedor: "
                        + itemPorProveedor.get(i).proveedor +
                        " lo vende a "+
                        itemPorProveedor.get(i).precio +
                        "$ \n";
            }
            this.TextResultado.setText(text);
        }
        System.out.println(itemPorProveedor);
    }

    private void setTablePrecios(int itemID) {

        model.getDataVector().removeAllElements();
        var itemPorProveedor = controlador.getProveedorItemsByItem(itemID);
        if (!itemPorProveedor.isEmpty()) {
            for (DDlProveedorItemDTO itemP : itemPorProveedor) {
                model.addRow(new Object[]{
                        itemP.proveedor,
                        itemP.precio,
                });
            }
            model.fireTableDataChanged();
        }else{
            JOptionPane.showMessageDialog(
                    consultarPreciosButton,
                    "No hay precios para los productos selecionados",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);


        }
    }





}
