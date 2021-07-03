package views.consultasGenerales;

import controllers.ControladorComprobantes;
import models.dtos.ComprobanteDTO;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewTotalDeDeuda extends JFrame{

    private JPanel deudaProveedor;

    private JButton consultarButton;
    private JButton volverButton;


    private JTextField textCUIT;
    private JTextField textMonto;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton consultarDeudaButton;
    private JButton cancelarButton;

    public ViewTotalDeDeuda() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(deudaProveedor);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        consultarDeudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cuit = null;


                if (!textCUIT.getText().isEmpty()){
                    cuit = textCUIT.getText();
                    var comprobantes = ControladorComprobantes.getInstancia().getComprobantesByCuitDTO(cuit);
                    setJtextArea(comprobantes);
                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Ingrese un CUIT para continuar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        cancelarButton.addActionListener(new ActionListener() {

            @Override
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

    private void setJtextArea(List<ComprobanteDTO> comprobantes) {

        if (comprobantes.isEmpty()){
            this.textMonto.setText(" El proveedor correspondiente al cuit ingresado no posee deuda alguna o no existe ");
        }else{
            String text = "" ;
            calculodeDeuda(comprobantes);
        }


    }

    private Integer calculodeDeuda(List<ComprobanteDTO> comprobantes) {

        Float pagado = 0F;
        Float debitado = 0F;
        Float acreditado = 0F;

        for (int i=0; i<comprobantes.size(); i++){

            if (comprobantes.get(i).getTipo() == "FAC"){
                pagado = pagado + comprobantes.get(i).getTotal();
            }else{
                if (comprobantes.get(i).getTipo() == "NOTA "){

                }
            }


        }
        return null;
    }
}
