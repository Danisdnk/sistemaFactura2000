package views.consultasGenerales;

import controllers.ControladorComprobantes;
import controllers.ControladorProveedor;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.text.DecimalFormat;

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
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton consultarDeudaButton;
    private JButton cancelarButton;
    private JTextField labelMonto;

    public ViewTotalDeDeuda() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(deudaProveedor);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        consultarDeudaButton.addActionListener(e -> {

            String cuit = null;


            if (!textCUIT.getText().isEmpty()){
                cuit = textCUIT.getText();
                setJtextArea(cuit);
            }else{
                JOptionPane.showMessageDialog(
                        consultarButton,
                        "Ingrese un CUIT para continuar",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        });


        cancelarButton.addActionListener(e -> {

            var ViewConsultasGenerales = new ViewConsultasGenerales();
            ViewConsultasGenerales.setVisible(true);

            dispose();

        });

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            //dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales cons = new ViewConsultasGenerales();
            cons.setVisible(true);
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
        DocumentosButton.addActionListener(e -> {
            DocumentosView principal = new DocumentosView();
            principal.setVisible(true);
            dispose();
        });

    }

    private void setJtextArea(String cuit) {

        DecimalFormat formato1 = new DecimalFormat("#.##");
       if (!ControladorProveedor.getInstancia().existsProveedorCuit(cuit)){

            this.labelMonto.setText(" El proveedor correspondiente al cuit ingresado no existe ");
        }else{
            this.labelMonto.setText(String.valueOf(formato1.format(ControladorComprobantes.getInstancia().calcularDeudaDeProveedorByCuit(cuit))));
        }
    }


}
