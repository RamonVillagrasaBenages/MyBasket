package Frames;

import Util.Fuentes;
import client.Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ModificarContraseña extends JFrame implements MouseListener, ActionListener {

    MiCuenta frame_MiCuenta;

    JPanel MainPanel = new JPanel(new GridLayout(5,0));
    JPanel panel_titulo = new JPanel(new GridLayout(3,0));
    JPanel panel_contraseña = new JPanel(new GridLayout(1,0));
    JPanel panel_contraseña_int = new JPanel(new GridLayout(1,0));
    JPanel panel_contraseña2 = new JPanel(new GridLayout(1,0));
    JPanel panel_contraseña_int2 = new JPanel(new GridLayout(1,0));
    JPanel panel_guardar = new JPanel(new GridLayout(0,2));
    JPanel panel_cancelar = new JPanel(new GridLayout(0,2));

    JLabel lbl_titulo = new JLabel("MODIFICA TU CONTRASEÑA DE CORREO");
    JLabel lbl_titulo2 = new JLabel("ELECTRÓNICO");

    JPasswordField txt_contraseña = new JPasswordField();
    JPasswordField txt_contraseña2 = new JPasswordField();

    JButton btn_actualizar = new JButton("G U A R D A R  C A M B I O S");
    JButton btn_cancelar = new JButton("C A N C E L A R");

    Color color_borde = new Color(76, 166, 94);

    public ModificarContraseña(MiCuenta frame_MiCuenta) {

        txt_contraseña.setFocusable(false);
        txt_contraseña2.setFocusable(false);
        txt_contraseña.setEchoChar((char)0);
        txt_contraseña2.setEchoChar((char)0);
        txt_contraseña.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me){
                char[] arrayC = txt_contraseña.getPassword();
                String pass = new String(arrayC);
                if( pass.equals("Escriba su antigua contraseña")){
                    txt_contraseña.setText("");
                    txt_contraseña.setEchoChar('\u25cf');
                }
            }
        });
        txt_contraseña2.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me){
                char[] arrayC = txt_contraseña2.getPassword();
                String pass = new String(arrayC);
                if( pass.equals("Escriba la nueva contraseña")){
                    txt_contraseña2.setText("");
                    txt_contraseña2.setEchoChar('\u25cf');
                }
            }
        });
        btn_actualizar.setFocusable(false);
        btn_cancelar.setFocusable(false);
        lbl_titulo.setFont(Fuentes.f_datos);
        lbl_titulo2.setFont(Fuentes.f_datos);
        Border borde_titled = new MatteBorder(1, 1, -1, 1,  Color.BLACK);
        Font fuente = Fuentes.f_american_15;
        Border borde_contraseña = new TitledBorder(borde_titled, "Contraseña antigua", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);
        Border borde_contraseña2 = new TitledBorder(borde_titled, "Nueva contraseña", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);

        //Panel Titulo
        panel_titulo.add(lbl_titulo);
        panel_titulo.add(lbl_titulo2);
        panel_titulo.setBackground(Color.WHITE);

        //Panel Contraseñas
        //DocumentListener para ver cambios
        txt_contraseña.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_contraseña,panel_contraseña_int); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_contraseña,panel_contraseña_int); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_contraseña,panel_contraseña_int); }
        });
        txt_contraseña2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_contraseña2,panel_contraseña_int2); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_contraseña2,panel_contraseña_int2); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_contraseña2,panel_contraseña_int2); }
        });
        txt_contraseña.setText("Escriba su antigua contraseña");
        txt_contraseña2.setText("Escriba la nueva contraseña");
        txt_contraseña.setBorder(borde_contraseña);
        txt_contraseña2.setBorder(borde_contraseña2);
        panel_contraseña_int.add(txt_contraseña);
        panel_contraseña.setBorder(new MatteBorder(10, 1, 20, 1,  Color.white));
        panel_contraseña.add(panel_contraseña_int);
        panel_contraseña.setBackground(Color.WHITE);
        panel_contraseña_int.setBackground(Color.WHITE);

        panel_contraseña_int2.add(txt_contraseña2);
        panel_contraseña2.setBorder(new MatteBorder(10, 1, 20, 1,  Color.white));
        panel_contraseña2.add(panel_contraseña_int2);
        panel_contraseña2.setBackground(Color.WHITE);
        panel_contraseña_int2.setBackground(Color.WHITE);

        //PANEL CAMBIOS
        btn_actualizar.setForeground(Color.WHITE);
        btn_actualizar.setBackground(Color.BLACK);
        btn_actualizar.setFont(Fuentes.f_eliminar);
        panel_guardar.setBackground(Color.WHITE);
        panel_guardar.add(btn_actualizar);
        panel_guardar.setBorder(new MatteBorder(1, 1, 30, 1,  Color.white));

        //PANEL CANCELAR
        btn_cancelar.setFont(Fuentes.f_eliminar);
        btn_cancelar.setBackground(Color.WHITE);
        panel_cancelar.setBackground(Color.WHITE);
        panel_cancelar.add(btn_cancelar);
        this.frame_MiCuenta = frame_MiCuenta;
        panel_cancelar.setBorder(new MatteBorder(1, 1, 30, 1,  Color.white));

        btn_actualizar.addActionListener(this);
        btn_cancelar.addActionListener(this);
        btn_actualizar.addMouseListener(this);
        btn_cancelar.addMouseListener(this);
        txt_contraseña.addMouseListener(this);
        txt_contraseña2.addMouseListener(this);

        MainPanel.add(panel_titulo);
        MainPanel.add(panel_contraseña);
        MainPanel.add(panel_contraseña2);
        MainPanel.add(panel_guardar);
        MainPanel.add(panel_cancelar);

        frame_MiCuenta.setEnabled(false);

        //Añadir el panel
        getContentPane().add(MainPanel);
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setBorder(new MatteBorder(20, 30, 1, 50,  Color.white));

        //Ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_MiCuenta.setEnabled(true);
            }
        });
        Image icon = new ImageIcon(getClass().getResource("/images/LogoSinTexto.png")).getImage();
        this.setIconImage(icon);
        this.setSize(700,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        getContentPane().setBackground(Color.white);


    }

    public void cambios(JTextField texto, JPanel panel){
        if (texto.getText().equals("")) {
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, Color.RED));
        }else if(texto.getText().equals("Escriba su antigua contraseña")||texto.getText().equals("Escriba la nueva contraseña")){
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, Color.BLACK));
        }else{
            panel.setBorder(new MatteBorder(-1, -1, 2, -1, color_borde));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(true);
        }else if(target == btn_cancelar) {
            btn_cancelar.setFocusable(true);
        }else if(target == txt_contraseña){
            txt_contraseña.setFocusable(true);
            if(txt_contraseña.getText().equals("Escriba su antigua contraseña")){
                txt_contraseña.setText("");
            }
        }else if(target == txt_contraseña2){
            txt_contraseña2.setFocusable(true);
            if(txt_contraseña2.getText().equals("Escriba la nueva contraseña")){
                txt_contraseña2.setText("");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_contraseña.setFocusable(false);
            txt_contraseña2.setFocusable(false);
        }else if(target == btn_cancelar) {
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_contraseña.setFocusable(false);
            txt_contraseña2.setFocusable(false);
        }else if(target == txt_contraseña){
            if(txt_contraseña.getText().equals("")){
                txt_contraseña.setText("Escriba su antigua contraseña");
                txt_contraseña.setEchoChar((char)0);
            }
        }else if(target == txt_contraseña2){
            if(txt_contraseña2.getText().equals("")){
                txt_contraseña2.setText("Escriba la nueva contraseña");
                txt_contraseña2.setEchoChar((char)0);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            Client cliente = Client.getInstance();
            HashMap<String,String> datos = new HashMap<String,String>();
            String correo = InicioSesion.getUsuario();
            datos.put("correo",correo);
            datos.put("contraseña",txt_contraseña.getText());
            boolean completado = (boolean)cliente.clienteServidor("/getModificarContraseña",datos);
            if(completado){
                frame_MiCuenta.setEnabled(true);
                this.setVisible (false);
                this.dispose();
            }
        }else if(target == btn_cancelar){
            frame_MiCuenta.setEnabled(true);
            this.setVisible (false);
            this.dispose();
        }
    }

}

