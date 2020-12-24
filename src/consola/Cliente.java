package consola;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MarcoCliente mimarco=new MarcoCliente();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoCliente extends JFrame{
    public MarcoCliente(){
        setBounds(600,300,280,150);
        LaminaMarcoCliente milamina=new LaminaMarcoCliente();
        add(milamina);

        setVisible(true);
    }
}

class LaminaMarcoCliente extends JPanel{
    public LaminaMarcoCliente(){
        EnviaIzquierda eventoIzquierda = new EnviaIzquierda();
        EnviaDerecha eventoDerecha = new EnviaDerecha();
        EnviaCentro eventoCentro = new EnviaCentro();



        botonIzquierda=new JButton("Izquierda");
        botonIzquierda.addActionListener(eventoIzquierda);
        add(botonIzquierda);

        botonDerecha=new JButton("Derecha");
        botonDerecha.addActionListener(eventoDerecha);
        add(botonDerecha);

        botonCentro=new JButton("Espacio");
        botonCentro.addActionListener(eventoCentro);
        add(botonCentro);
    }

    private class EnviaIzquierda implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket miSocket = new Socket("192.168.0.15", 9999);
                DataOutputStream flujo_salida= new DataOutputStream(miSocket.getOutputStream());

                flujo_salida.writeUTF("izq");
                flujo_salida.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    private class EnviaDerecha implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket miSocket = new Socket("192.168.0.15", 9999);
                DataOutputStream flujo_salida= new DataOutputStream(miSocket.getOutputStream());

                flujo_salida.writeUTF("der");
                flujo_salida.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    private class EnviaCentro implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket miSocket = new Socket("192.168.0.15", 9999);
                DataOutputStream flujo_salida= new DataOutputStream(miSocket.getOutputStream());

                flujo_salida.writeUTF("cen");
                flujo_salida.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private JButton botonIzquierda;
    private JButton botonDerecha;
    private JButton botonCentro;

}