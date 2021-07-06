package views.proveedores;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.github.lgooddatepicker.components.DatePicker;

import controllers.ControladorItem;
import controllers.ControladorProveedor;
import models.documento.Comprobante;
import models.dtos.DDLItemDTO;
import models.proveedor.Rubro;
import views.consultasGenerales.ViewConsultasGenerales;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import models.proveedor.Proveedor;
import models.proveedor.Proveedor;
public class provedorView extends JFrame {
    private JPanel mainPanel;
    private JLabel titulo;
    private JPanel panelCentral;
    private JButton usuariosButton;
    private JButton ordenesDePagoButton;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton hideButton;
    private JPanel vistaProv;
    private JPanel panelProveedores;
    private JButton addProveedor;
    private JLabel labelProveedor;
    private JComboBox<List> dropListaProveedores;
    private JTextField txtNombreFantasia;
    private JTextField txtRazonSocial;
    private JTextField txtCuit;
    private JTextField txtDireccion;
    private JComboBox<DDLItemDTO> dropResponsableIva;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtIngresosBrutos;
    private JComboBox<DDLItemDTO> dropRubro;
    private JButton removeProveedor;
    private JSpinner DateActividades;
    private JButton modifyProveedor;
    private JComboBox ddlCertificadoRetencion;
    private JButton addRubroProveedor;
    private DatePicker datePickerAct;
    private JButton borrarButton;
    private JButton modificarButton;
    private ControladorProveedor controlador;
    private List<Comprobante> proveedoresCargados;
    private Integer proveedorID;
    private Proveedor itemSeleccionado;
    public provedorView() {
        this.controlador = ControladorProveedor.getInstancia();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(vistaProv);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDDLResponsableIva();
        this.setDDLProveedores();
        this.setDDLRubros();
        datePickerAct.setDateToToday();
        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                dispose();
            }
        });
        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView prov = new provedorView();
                prov.setVisible(true);
                dispose();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView login = new loginView();
                login.setVisible(true);
                dispose();
            }
        });
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView docu = new DocumentosView();
                docu.setVisible(true);
                dispose();
            }
        });
        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();
            }
        });

        this.dropListaProveedores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();
/* si la seleccion es distinta de nulo hacemos un get del objeto seleccionda hacemos un setText para asignar valor al
 dropdown Hacemos esta condicion "ternaria"  si se cumple cargamos el item si no se cumple porque el dato no se
 completo cargamos el dato como string vacio.asi almenos no pincha. ternario es (condicion? caso true : caso false )**/
                if ( sel != null ) {

                    proveedorID = sel.id;
                    itemSeleccionado = controlador.getProveedorByID(sel.id);
                    checkIfEmpty(txtNombreFantasia,itemSeleccionado.getNombre());
                    checkIfEmpty(txtDireccion,itemSeleccionado.getDireccion());
                    checkIfEmpty(txtEmail,itemSeleccionado.getEmail());
                    checkIfEmpty(txtRazonSocial,itemSeleccionado.getRazonSocial());
                    checkIfEmpty(txtIngresosBrutos,itemSeleccionado.getNumeroIIBB());
                    checkIfEmpty(txtTelefono,itemSeleccionado.getTelefono());
                    checkIfEmpty(txtCuit,itemSeleccionado.getCuit());
                    dropRubro.setSelectedItem(itemSeleccionado.getRubros());
                    dropResponsableIva.setSelectedItem(itemSeleccionado.getResponsableIva());
                    datePickerAct.setDate(itemSeleccionado.getInicioActividades());

                } else {
                    proveedorID = null;
                }
            }
        });
        List<Rubro> rubrosAgregados = null; //TODO esto hay que modificarlo para que al hacer "+" agregue un rubro a la
        // lista del futuro nuevo proveedor

        addRubroProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                var sel = (DDLItemDTO) dropRubro.getSelectedItem();

                if ( sel != null ) {
                    rubrosAgregados.add(new Rubro(sel.descripcion));
                }
            }
        });

        addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", Locale.getDefault());
                var patata = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();

                assert sel != null;
                itemSeleccionado = controlador.getProveedorByID(sel.id);

                var prov = getCamposProveedor(sel);
                var ProvedorCuitExiste = controlador.existsProveedorCuit(txtCuit.getText());
                var ProveedorNombreExiste = controlador.existsProveedorNombre(txtNombreFantasia.getText());

                if ( !ProveedorNombreExiste ) {

                    if ( !ProvedorCuitExiste ) {
                        controlador.agregarProveedor(prov);
                        setDDLProveedores();
                        JOptionPane.showMessageDialog(
                                addProveedor,
                                "El nuevo proveedor se agrego con exito",
                                "Nuevo proveedor",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                addProveedor,
                                "No se pudo cargar.Ya existe proveedor con ese CUIT",
                                "Nuevo proveedor",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(
                            addProveedor,
                            "El nombre Fantasia Ingresado ya pertenece a otro proveedor",
                            "Nuevo proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            };
        });
        removeProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();
                var seleccionado= dropListaProveedores.getSelectedIndex();
                boolean eliminado=false;
                if ( sel != null ) {
                    var provedor = controlador.getProveedorByID(sel.id);
                     eliminado = controlador.eliminarProveedor(provedor);
                    if ( eliminado ) {
                        JOptionPane.showMessageDialog(
                                removeProveedor,
                                "El proveedor fue eliminado con exito",
                                "Proveedor Eliminado",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(
                                removeProveedor,
                                "No se pudo eliminar el proveedor",
                                "Proveedor Eliminado",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(eliminado){
                    setDDLProveedores();
                }
            }
        });

        modifyProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //  SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy", Locale.getDefault());
                var patata = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
                var sel = (DDLItemDTO) dropListaProveedores.getSelectedItem();

                assert sel != null;
                var proveedor = getCamposProveedor(sel);

                var isProvedorExiste = controlador.existsProveedorCuit(txtCuit.getText());

                if ( isProvedorExiste ) {
                    controlador.actualizarProveedor(proveedor);
                    setDDLProveedores();
                    JOptionPane.showMessageDialog(
                            modifyProveedor,
                            "proveedor",
                            "modificar proveedor",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(
                            modifyProveedor,
                            "No se pudo modificar el proveedor",
                            "Error modificar proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            ;
        });
        //obliga al usuario a salo ingresar numeros >:D
        txtCuit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtextNumbers(txtCuit, e);
            }
        });
        txtIngresosBrutos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtextNumbers(txtIngresosBrutos, e);
            }
        });
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtextNumbers(txtTelefono, e);
            }
        });
        //no permite ingresar numeros o caracteres especiales

        txtDireccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtext(txtDireccion, e);
            }
        });
        txtNombreFantasia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtext(txtNombreFantasia, e);
            }
        });
        txtRazonSocial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                genericValidateJtext(txtRazonSocial, e);
            }
        });

    }

    private void setDDLProveedores() {
        var model = ControladorProveedor.getInstancia().getOpcionesDDLProveedores();
        this.dropListaProveedores.setModel(new DefaultComboBoxModel(model.toArray()));
    }

    private void setDDLRubros() {
        vistaProv.validate();// for JFrame up to Java7 is there only validate()
        vistaProv.repaint();
        var controladorItem = ControladorItem.getInstancia().getOpcionesDDLRubros();
        this.dropRubro.setModel(new DefaultComboBoxModel(controladorItem.toArray()));
    }

    private Proveedor getCamposProveedor(DDLItemDTO sel) {

        Date date = (Date) DateActividades.getValue();
        LocalDate fecha = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        return new Proveedor(
                txtNombreFantasia.getText(),
                txtDireccion.getText(),
                txtEmail.getText(),
                txtTelefono.getText(),
                txtIngresosBrutos.getText(),
                datePickerAct.getDate(),
                dropRubro.getSelectedItem().toString(),
                //(float) 2000,
                txtRazonSocial.getText(),
                txtCuit.getText(),
                dropResponsableIva.getSelectedItem().toString(),
                sel.id
        );

    }

    private void setDDLResponsableIva() {
        vistaProv.validate();// for JFrame up to Java7 is there only validate()
        vistaProv.repaint();
        var controladorProv = ControladorProveedor.getInstancia().getOpcionesDDLResponsableIva();
        this.dropResponsableIva.setModel(new DefaultComboBoxModel(controladorProv.toArray()));
    }

    private void genericValidateJtextNumbers(JTextField inputText, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ( Character.isDigit(c) || Character.isISOControl(c) ) {
            inputText.setEditable(true);
        } else {
            inputText.setEditable(false);
        }
    }

    private void genericValidateJtext(JTextField inputText, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ( Character.isLetter(c) || Character.isISOControl(c) || Character.isWhitespace(c) || !Character.isDigit(c) ) {
            inputText.setEditable(true);
        } else {
            inputText.setEditable(false);
        }
    }

    private void checkIfEmpty(JTextField inputText,String p) {

        inputText.setText(!p.isEmpty() ? p : "");

    }

   private void createUIComponents() { //componente custom para la fecha //TODO en facturas?
        Date date = new Date();
        SpinnerDateModel sm =
                new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH);
        DateActividades = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(DateActividades, "dd MM yyyy");
        DateActividades.setEditor(de);

    }
}


