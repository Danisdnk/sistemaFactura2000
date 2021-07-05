package views.consultasGenerales;

import views.MenuPrincipal;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;
import javax.swing.*;


public class ViewConsultasGenerales extends JFrame{

    private JPanel consultas;
    private JPanel panelCentral;

    private JButton cancelarButton;


    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton totalDeFacturasRecibidasButton;
    private JButton compulsaDePreciosButton;
    private JButton ordenesDePagosEmitidasButton;
    private JButton cuentaCorrienteDeProveedoresButton;
    private JButton totalDeImpuestosRetenidosButton;
    private JButton totalDeDeudaPorButton;
    private JButton consultaDeLibroIVAButton;


    public ViewConsultasGenerales() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(consultas);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        totalDeFacturasRecibidasButton.addActionListener(e -> {
            var ViewFacturasRecibidas = new ViewFacturasRecibidas();
            ViewFacturasRecibidas.setVisible(true);
            dispose();
        });

        compulsaDePreciosButton.addActionListener(e -> {
            var ViewCompulsaPrecios = new ViewCompulsaPrecios();
            ViewCompulsaPrecios.setVisible(true);
            dispose();
        });

        cuentaCorrienteDeProveedoresButton.addActionListener(e -> {
            var ViewCuentaCorriente = new ViewCuentaCorriente();
            ViewCuentaCorriente.setVisible(true);
            dispose();
        });

        ordenesDePagosEmitidasButton.addActionListener(e -> {
            var ViewOrdenesPago = new ViewOrdenesPago();
            ViewOrdenesPago.setVisible(true);
            dispose();
        });

        totalDeDeudaPorButton.addActionListener(e -> {
            var ViewTotalDeDeuda = new ViewTotalDeDeuda();
            ViewTotalDeDeuda.setVisible(true);
            dispose();
        });

        totalDeImpuestosRetenidosButton.addActionListener(e -> {
                var ViewTotalDeImpuestosRetenidos = new ViewTotalDeImpuestosRetenidos();
                ViewTotalDeImpuestosRetenidos.setVisible(true);
                dispose();
        });

        consultaDeLibroIVAButton.addActionListener(e -> {
            var ViewConsultaLibroIVACompras = new ViewConsultaLibroIVACompras();
            ViewConsultaLibroIVACompras.setVisible(true);
            dispose();
        });

        cancelarButton.addActionListener(e -> {
            var MenuPrincipal = new MenuPrincipal("Factura 2000");
            MenuPrincipal.setVisible(true);
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
}


