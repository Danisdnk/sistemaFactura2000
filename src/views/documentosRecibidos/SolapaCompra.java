package views.documentosRecibidos;

import javax.swing.*;

public class SolapaCompra extends JDialog {
    private JPanel prinicipalSolapaCompra;
    private JPanel panelProveedor;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTextField textField3;
    private JButton agregarButton;
    private JButton quitarButton;
    private JTable tablaItemsFactura;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField textField4;
    private JTextField textField5;

    public SolapaCompra(){
        this.setContentPane(prinicipalSolapaCompra);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(800,600);
        this.setModal(true);

    }
}
