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

        miboton=new JButton("Izquierda");
        miboton.addActionListener(eventoIzquierda);
        add(miboton);

        miboton=new JButton("Derecha");
        miboton.addActionListener(eventoDerecha);
        add(miboton);

        miboton=new JButton("Espacio");
        miboton.addActionListener(eventoCentro);
        add(miboton);
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

    private JButton miboton;

}