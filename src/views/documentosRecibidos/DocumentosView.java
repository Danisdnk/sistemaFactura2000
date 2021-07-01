package views.documentosRecibidos;

import views.documentosRecibidos.SolapaFactura;
import views.documentosRecibidos.SolapaCompra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentosView extends JDialog {


    private JPanel docuMain;
    private JButton facturaButton;
    private JButton ordenDeCompraButton;
    private JDesktopPane desktopPaneFactura;

    public DocumentosView(){
        this.setContentPane(docuMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(300,85);
        this.setModal(true);


        facturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolapaFactura solapaF = new SolapaFactura();
                solapaF.setVisible(true);
            }
        });
        ordenDeCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolapaCompra solapaC = new SolapaCompra();
                solapaC.setVisible(true);
            }
        });
    }
}
