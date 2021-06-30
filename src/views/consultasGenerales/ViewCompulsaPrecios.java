package views.consultasGenerales;

import controllers.ControladorItem;
import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.dtos.DDLItemDTO;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCompulsaPrecios extends JFrame{


    private JPanel compulsa;
    private JPanel panelCentral;

    private JComboBox<DDLItemDTO> ddlRubros;
    private JComboBox<DDLItemDTO> ddlProductos;


    private JButton consultarPreciosButton;
    private JButton cancelarButton;

    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextArea TextResultado;


    private Integer rubroID;
    private Integer provductoID;


    public ViewCompulsaPrecios() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(compulsa);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



        this.setDDLProductos();
        this.setDDLRubros();

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
        });

        this.ddlRubros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO)ddlRubros.getSelectedItem();

                if (sel != null) {
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
                var sel = (DDLItemDTO)ddlProductos.getSelectedItem() ;

                if (sel != null ) {
                    setTextResultado(sel.descripcion);
                } else {
                    rubroID = null;

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

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView principal = new provedorView();
                principal.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });


    }


    private void setDDLProductos() {
        var model = ControladorItem.getInstancia().getOpcionesDDLItems();
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }


    private void setDDLRubros() {
        var model = ControladorItem.getInstancia().getOpcionesDDLRubros();
        this.ddlRubros.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLProductos(String descripcion) {
        var model = ControladorItem.getInstancia().getOpcionesDDLRubros(descripcion);
        this.ddlProductos.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setTextResultado(String descripcion) {
        var model = ControladorItem.getInstancia().getProveedorItemByItem(descripcion);
        if (model.isEmpty()){
            this.TextResultado.setText("Es producto no posee precio fijados por vendedores actualmente ");
        }
        else{
            this.TextResultado.setText(model.toString());
        }


        System.out.println(model);
    }





}
