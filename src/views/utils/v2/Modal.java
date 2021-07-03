package views.utils.v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Modal<TModelo> {
    private JDialog frm;
    private JPanel main;
    private JButton btnGuardar;

    private Controlador controlador;
    private TModelo modelo;

    public Modal(
            JDialog frm,
            Controlador controlador){
        this.frm = frm;
        this.controlador = controlador;
    }

    public void configure() {
        this.frm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.frm.setContentPane(main);
        this.frm.setSize(400,400);
        this.frm.pack();
    }

    protected TModelo setModelo(ActionEvent e) {
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        this.modelo = setModelo(e);
        this.controlador.agregar(this.modelo);
        this.frm.setVisible(false);
        this.frm.dispose();
    }

    public TModelo showDialog() {
        this.frm.setVisible(true);
        return this.modelo;
    }
}
