package Util;

import Frames.ModificarLista;
import Frames.Productos;
import domain.Product;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

public class PanelProductoIndividual {

    public static JPanel getPanel(Product product, int cantidad, ArrayList<Product> mi_seleccion, URL url_mas, URL url_menos, boolean botones, boolean modificar, boolean pintar, boolean precio){

        JPanel producto_individual = new JPanel(new BorderLayout());
        JPanel panel_producto = new JPanel(new BorderLayout());
        JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel_btn_2 = new JPanel(new GridLayout(0, 3));
        JPanel panel_sur_ind= new JPanel(new GridLayout(3, 0));
        JPanel panel_cantidad = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel_descripcion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel_marca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel_precio = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel_cantidad.setBackground(Color.WHITE);
        producto_individual.setBackground(Color.WHITE);
        if(pintar){
            producto_individual.setBorder(new MatteBorder(0, 0, 0, 4, Fuentes.color_logo));

        }
        panel_producto.setBackground(Color.WHITE);
        panel_btn.setBackground(Color.WHITE);
        panel_btn_2.setBackground(Color.WHITE);
        panel_btn.setBackground(Color.WHITE);
        panel_btn_2.setBackground(Color.WHITE);
        panel_descripcion.setBackground(Color.WHITE);
        panel_sur_ind.setBackground(Color.WHITE);
        panel_marca.setBackground(Color.WHITE);
        panel_precio.setBackground(Color.WHITE);

        ImageIcon imagen = product.getImagen();
        JLabel lbl_imagen = new JLabel(Imagenes.resize(imagen,150,150));

        JLabel lbl_cantidad = new JLabel();
        lbl_cantidad.setText(String.valueOf(cantidad));
        lbl_cantidad.setFont(Fuentes.f_eliminar);

        JLabel lbl_menos;
        JLabel lbl_mas;

        if(botones){
            ImageIcon imagen_menos = new ImageIcon(url_menos);
            lbl_menos = new JLabel(Imagenes.resize(imagen_menos,15,15));
            ImageIcon imagen_mas = new ImageIcon(url_mas);
            lbl_mas = new JLabel(Imagenes.resize(imagen_mas,18,18));

            lbl_mas.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lbl_menos.setCursor(new Cursor(Cursor.HAND_CURSOR));

            lbl_mas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int unidades = Integer.parseInt(lbl_cantidad.getText());
                    unidades++;
                    lbl_cantidad.setText(String.valueOf(unidades));
                    if(precio) {
                        if (modificar) {
                            Productos.modificar_precio(product, true);
                        } else {
                            ModificarLista.modificar_precio(product, true);
                        }
                    }
                    mi_seleccion.add(product);
                }
            });

            lbl_menos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int unidades = Integer.parseInt(lbl_cantidad.getText());
                    unidades--;
                    if(unidades<0){
                        unidades=0;
                    }else{
                        lbl_cantidad.setText(String.valueOf(unidades));
                        if(precio){
                            if(modificar){
                                Productos.modificar_precio(product,false);
                            }else{
                                ModificarLista.modificar_precio(product, false);
                            }
                        }
                        mi_seleccion.remove(product);
                    }
                }
            });
            lbl_cantidad.setText(String.valueOf(cantidad));
        }else{
            lbl_menos = new JLabel();
            lbl_mas = new JLabel();
            lbl_cantidad.setText("x"+String.valueOf(cantidad));
        }

        panel_cantidad.add(lbl_cantidad);


        JLabel lbl_descripcion = new JLabel("<html>"+product.getDescripcion()+"</html>");
        JLabel lbl_marca = new JLabel(product.getMarca());
        JLabel lbl_precio = new JLabel(String.valueOf(product.getPrecio())+" €");
        lbl_marca.setForeground(Color.LIGHT_GRAY);
        panel_descripcion.add(lbl_descripcion);
        panel_marca.add(lbl_marca);
        panel_precio.add(lbl_precio);

        panel_sur_ind.add(panel_descripcion);
        panel_sur_ind.add(panel_marca);
        panel_sur_ind.add(panel_precio);

        panel_btn_2.add(lbl_menos);
        panel_btn_2.add(panel_cantidad);
        panel_btn_2.add(lbl_mas);
        panel_btn.add(panel_btn_2);
        panel_producto.add(lbl_imagen,BorderLayout.CENTER);
        panel_producto.add(panel_btn,BorderLayout.SOUTH);
        producto_individual.add(panel_producto,BorderLayout.CENTER);
        producto_individual.add(panel_sur_ind,BorderLayout.SOUTH);
        return  producto_individual;
    }

}
