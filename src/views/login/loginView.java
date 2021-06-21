package views.login;

import views.MenuPrincipal;
import views.proveedores.provedorView;

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

    public loginView() {

        this.setSize(600, 900); // creamos el frame con las medidas
        this.setContentPane(loginPanel); //ponemos como panel el nombre de variable del panel
        this.setVisible(true); //lo volvemos visible
        this.setLocationRelativeTo(null); // centra la pantalla
        btnIngresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = textUsuario.getText();
                String pass = textPass.getText();

                if ( nombre.equals("dani") && pass.equals("123") ) {
                    var menuPrincipal = new MenuPrincipal("Factura 2000");
                    menuPrincipal.setVisible(true);

                    dispose(); //cierra la ventana para mostrar la otra vista
                } else {
                    JOptionPane.showMessageDialog(
                            btnIngresar,
                            "credenciales erroneas",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}