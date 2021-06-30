package views;

import views.consultasGenerales.ViewConsultasGenerales;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MenuPrincipal extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JPanel panelCentral;
    private JTextField fecha;
    private JLabel nombreCiudad;
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;

    public MenuPrincipal(String title) {
       super(title);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.setSize(1000,900);
       this.setLocationRelativeTo(null);

       ordenesDePagoButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               OrdenesDePagoFrame op = new OrdenesDePagoFrame();
               op.setVisible(true);
               //dispose();//esto cierra la ventana anterior
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

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();//esto cierra la ventana anterior
            }
        });
   }

   public void agregarCiudad() {
       /*Ciudad.CiudadDTO dto = new Ciudad.CiudadDTO();
       dto.nombre = "Buenos aires";
       try {
           CiudadesController.getInstancia().crear(dto);
       }
       catch (CiudadSinNombreException c) {
           //MUESTRO UN CARTEL DE ERROR
       }*/
   }

   public void buscarCiudadConMasPasajeros(LocalDate unDia) {
       /*Ciudad.CiudadDTO dto = CiudadesController.getInstancia().ciudadQueMasPasajerosRecibio(unDia);
       this.nombreCiudad.setText(dto.nombre);*/
   }
}
