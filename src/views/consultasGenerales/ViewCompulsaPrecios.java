package views.consultasGenerales;

import controllers.ControladorItem;
import models.dtos.DDLItemDTO;
import models.dtos.DDlProveedorItemDTO;
import views.documentosRecibidos.DocumentosView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        model.addColumn("Precio");
        tablePrecios.setModel(model);



        this.setDDLRubros();
        this.ddlProductos.addItem("Selecione un Rubro");
        //this.setDDLProductos((String) this.ddlRubros.getSelectedItem());

        /*
        this.ddlProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) ddlProductos.getSelectedItem();

                if (sel != null) {
                    provductoID = sel.id;
                } else {
                    provductoID = null;
                }
            }
        });*/

        this.ddlRubros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlRubros.getSelectedItem();

                if (sel != null ) {
                    rubroID = sel.id;
                    setDDLProductos(sel.descripcion);
                } else {
                    rubroID = null;
                }


            }

        });

        this.consultarPreciosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if( ddlProductos.getSelectedItem() != ("Selecione un Rubro")) {
                    var sel = (DDLItemDTO)ddlProductos.getSelectedItem() ;
                    if (sel != null) {
                        setJtextArea(sel.descripcion);
                        setTablePrecios(sel.descripcion);
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
            }

        });









        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var ViewConsultasGenerales = new ViewConsultasGenerales();
                ViewConsultasGenerales.setVisible(true);

                dispose();
            }
        });

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                //dispose();//esto cierra la ventana anterior
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });

        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView docu = new DocumentosView();
                docu.setVisible(true);

            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView principal = new provedorView();
                principal.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });


    }




    private void setDDLRubros() {
        var model = ControladorItem.getInstancia().getOpcionesDDLRubros();
        this.ddlRubros.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos(String descripcion) {
        var model = ControladorItem.getInstancia().getOpcionesDDLItems(descripcion);
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos() {
        var model = ControladorItem.getInstancia().getOpcionesDDLItems();
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setJtextArea(String descripcion) {
        var itemPorProveedor = ControladorItem.getInstancia().getProveedorItemsByItem(descripcion);
        if (itemPorProveedor.isEmpty()){
            this.TextResultado.setText("Este producto no posee precio fijados por vendedores actualmente ");
        }
        else{

            String text = "";

            for (int i=0; i<itemPorProveedor.size(); i++ ){
                System.out.println(text);

                text = text
                        + "El Proveedor: "
                        + itemPorProveedor.get(i).descripcion +
                        " lo vende a "+
                        itemPorProveedor.get(i).precio +
                        "$ \n";
            }
            this.TextResultado.setText(text);
        }
        System.out.println(itemPorProveedor);
    }

    private void setTablePrecios(String descripcion) {

        model.getDataVector().removeAllElements();
        for(DDlProveedorItemDTO itemPorProveedor : ControladorItem.getInstancia().getProveedorItemsByItem(descripcion)){
            model.addRow(new Object[]{
                    itemPorProveedor.descripcion,
                    itemPorProveedor.precio,
            });
        }
        model.fireTableDataChanged();
    }





}
