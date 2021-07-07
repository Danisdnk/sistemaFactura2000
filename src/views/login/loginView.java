package views.login;

import controllers.ControladorUsuario;
import models.usuario.Usuario;
import views.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class loginView extends JFrame {
    private JPanel loginPanel;
    private JLabel labelUsuario;
    private JLabel labelFactura;
    private JLabel labelPass;
    private JTextField textUsuario;
    private JPasswordField textPass;
    private JButton btnIngresar;
    private JButton btnCrearUsuario;
    private ControladorUsuario controlador;

    public loginView() {
        this.controlador = ControladorUsuario.getInstancia();
        this.setSize(600, 900); // creamos el frame con las medidas
        this.setContentPane(loginPanel); //ponemos como panel el nombre de variable del panel
        this.setVisible(true); //lo volvemos visible
        this.setLocationRelativeTo(null); // centra la pantalla
        btnIngresar.addActionListener(e -> {

            String nombreUsuario = textUsuario.getText();
            String pass = textPass.getText();

            if ( controlador.getUsuarioByNombreUsuarioPass(nombreUsuario, pass) ) {
                var menuPrincipal = new MenuPrincipal("Factura 2000");
                menuPrincipal.setVisible(true);

                dispose(); //cierra la ventana para mostrar la otra vista
            } else {
                JOptionPane.showMessageDialog(
                        btnIngresar,
                        "Credenciales Erroneas",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCrearUsuario.addActionListener(e -> {
            String nombreUsuario = textUsuario.getText();

        if(!ControladorUsuario.getInstancia().getUsuarioByCredenciales(nombreUsuario) && !nombreUsuario.equals("")){
            Usuario usModel = new Usuario(
                    textUsuario.getText(),
                    textPass.getText()

            );
            System.out.println(nombreUsuario);
            controlador.agregarUsuario(usModel);
            JOptionPane.showMessageDialog(
                    btnCrearUsuario,
                    "Se creo el nuevo usuario",
                    "Nuevo Usuario",
                    JOptionPane.INFORMATION_MESSAGE);

        }

        else {
            if (nombreUsuario.equals("")) {
                JOptionPane.showMessageDialog(
                        btnCrearUsuario,
                        "Ingrese un Usuario valido",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(
                        btnCrearUsuario,
                        "El Usuario ya existe",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        });

    }
}