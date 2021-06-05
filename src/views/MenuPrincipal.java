package views;

import javax.swing.*;
import java.time.LocalDate;

public class MenuPrincipal extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JPanel panelCentral;
    private JTextField fecha;
    private JLabel nombreCiudad;

   public MenuPrincipal(String title) {
       super(title);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setContentPane(mainPanel);
       this.setSize(400,400);
       this.pack();
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
