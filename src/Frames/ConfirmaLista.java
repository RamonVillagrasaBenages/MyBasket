package Frames;

import Util.Fuentes;
import client.Client;
import domain.Lista;
import domain.Product;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfirmaLista extends JFrame implements ActionListener,MouseListener {

    Productos frame_Productos;

    Listas frame_Listas;

    JPanel MainPanel = new JPanel(new GridLayout(4,0));
    JPanel panel_titulo = new JPanel(new GridLayout(3,0));
    JPanel panel_lista = new JPanel(new GridLayout(1,0));
    JPanel panel_lista_int = new JPanel(new GridLayout(1,0));
    JPanel panel_guardar = new JPanel(new GridLayout(0,2));
    JPanel panel_cancelar = new JPanel(new GridLayout(0,2));

    JLabel lbl_titulo = new JLabel("ESCRIBA EL NOMBRE DE");
    JLabel lbl_titulo2 = new JLabel("LA LISTA");

    JTextField txt_lista = new JTextField();

    JButton btn_actualizar = new JButton("C R E A R  L I S T A");
    JButton btn_cancelar = new JButton("C A N C E L A R");

    Color color_borde = new Color(76, 166, 94);

    boolean frame;
    String lista;

    public ConfirmaLista(Listas frame_lista, String lista){
        this.frame_Listas = frame_lista;
        frame_Listas.setEnabled(false);
        this.lista = lista;
        panel();
        frame=true;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_Listas.setEnabled(true);
            }
        });
    }

    public ConfirmaLista(Productos frame_productos){
        this.frame_Productos = frame_productos;
        frame_Productos.setEnabled(false);
        panel();
        frame=false;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                frame_Productos.setEnabled(true);
            }
        });
    }

    public void panel() {

        txt_lista.setFocusable(false);
        btn_actualizar.setFocusable(false);
        btn_cancelar.setFocusable(false);
        lbl_titulo.setFont(Fuentes.f_datos);
        lbl_titulo2.setFont(Fuentes.f_datos);
        Border borde_titled = new MatteBorder(1, 1, -1, 1,  Color.BLACK);
        Font fuente = Fuentes.f_american_15;
        Border borde_nombre = new TitledBorder(borde_titled, "Nombre lista", TitledBorder.LEFT,
                TitledBorder.TOP, fuente, Color.GRAY);

        //Panel Titulo
        panel_titulo.add(lbl_titulo);
        panel_titulo.add(lbl_titulo2);
        panel_titulo.setBackground(Color.WHITE);

        //Panel Correo
        //DocumentListener para ver cambios
        txt_lista.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { cambios(txt_lista,panel_lista_int); }
            public void removeUpdate(DocumentEvent e) { cambios(txt_lista,panel_lista_int); }
            public void insertUpdate(DocumentEvent e) { cambios(txt_lista,panel_lista_int); }
        });
        txt_lista.setText("Escriba el nombre de la lista");
        txt_lista.setBorder(borde_nombre);
        panel_lista_int.add(txt_lista);
        panel_lista.setBorder(new MatteBorder(10, 1, 30, 1,  Color.white));
        panel_lista.add(panel_lista_int);
        panel_lista.setBackground(Color.WHITE);
        panel_lista_int.setBackground(Color.WHITE);

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
        panel_cancelar.setBorder(new MatteBorder(1, 1, 30, 1,  Color.white));

        btn_actualizar.addActionListener(this);
        btn_cancelar.addActionListener(this);
        btn_actualizar.addMouseListener((MouseListener) this);
        btn_cancelar.addMouseListener((MouseListener) this);
        txt_lista.addMouseListener((MouseListener) this);

        MainPanel.add(panel_titulo);
        MainPanel.add(panel_lista);
        MainPanel.add(panel_guardar);
        MainPanel.add(panel_cancelar);

        //Añadir el panel
        getContentPane().add(MainPanel);
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setBorder(new MatteBorder(20, 30, 1, 50,  Color.white));

        //Ventana
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
        }else if(texto.getText().equals("Escriba el nombre de la lista")){
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
        }else if(target == txt_lista){
            txt_lista.setFocusable(true);
            if(txt_lista.getText().equals("Escriba el nombre de la lista")){
                txt_lista.setText("");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_lista.setFocusable(false);
        }else if(target == btn_cancelar) {
            btn_actualizar.setFocusable(false);
            btn_cancelar.setFocusable(false);
            txt_lista.setFocusable(false);
        }else if(target == txt_lista){
            if(txt_lista.getText().equals("")){
                txt_lista.setText("Escriba el nombre de la lista");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if(target == btn_actualizar){
            if(frame){
                Client cliente = Client.getInstance();
                HashMap<String,String> datos = new HashMap<String,String>();
                String correo = InicioSesion.getUsuario();
                datos.put("correo",correo);
                datos.put("lista",lista);
                datos.put("nueva_lista",txt_lista.getText());
                boolean cambiado = (boolean)cliente.clienteServidor("/getListaModificada",datos);
                if(cambiado){
                    frame_Listas.setEnabled(true);
                    frame_Listas.ActualizarListasPestañas(txt_lista.getText());
                    frame_Listas.revalidate();
                    frame_Listas.repaint();
                    frame_Listas.setFocusLista(txt_lista.getText());
                    this.setVisible (false);
                    this.dispose();
                }
            }else{

                ArrayList<Product> productos = Productos.getProductosSeleccionados();
                Client cliente = Client.getInstance();
                String correo = InicioSesion.getUsuario();
                Lista lista = new Lista(correo, txt_lista.getText(), productos);
                boolean lista_creada = (boolean)cliente.clienteServidor("/crearLista",lista);
                if(lista_creada){
                    frame_Productos.setEnabled(true);
                    this.setVisible (false);
                    this.dispose();
                }

            }
        }else if(target == btn_cancelar){
            if(frame){
                frame_Listas.setEnabled(true);
            }else{
                frame_Productos.setEnabled(true);
            }
            this.setVisible (false);
            this.dispose();
        }
    }

}
