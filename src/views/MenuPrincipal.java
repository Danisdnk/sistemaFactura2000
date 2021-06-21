package views;

import views.ordenesDePago.OrdenesDePagoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MenuPrincipal extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JPanel panelCentral;
    private JButton proveedoresButton;
    private JButton ordenesDePagoButton;

    public MenuPrincipal(String title) {
       super(title);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.setSize(400,400);
       this.pack();
        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
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
