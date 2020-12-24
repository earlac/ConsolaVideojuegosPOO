package tennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*Clase principal del juego de tennis, llama a la bola y a la raqueta
y posee el tablero y el juego en si
*/

public class Game extends JPanel implements Runnable{

    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);
    int speed = 1;

    private int getScore() {
        return speed - 1;
    }

    public Game(){
        //Servidor del juego. Recibe las acciones de los botones
        Thread mihilo= new Thread(this);
        mihilo.start();

    }

    @Override
    public void run(){
        //Recibe las instrucciones del servidor (los botones) y decide cómo actuar al respecto
        try {
            ServerSocket servidor = new ServerSocket(9999);  //Abre el puerto 9999
            while(true) { //Este ciclo lo mantiene continuamente recibiendo instrucciones
                Socket misocket = servidor.accept();//Acepta la entrada

                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());//Crea el canal entre ambos

                String mensaje_texto = flujo_entrada.readUTF(); //Lee el string, este es el mensaje que envia el boton
                                                                   //Y el que los diferencia entre si
                if(mensaje_texto.equals("izq")){
                    racquet.moverIzquierda();

                }else if(mensaje_texto.equals("der")){
                    racquet.moverDerecha();

                }else{
                    System.out.println("Cen");
                }
                misocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        setFocusable(true);
    }

    private void move() {
        ball.move();
        racquet.move();
    }

    //Parte de la interfaz

    @Override
    public void paint(Graphics g) {//Dibujamos la bola y la raqueta
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
    }

    public void gameOver() {//Muestra el mensaje de juego terminado y el puntaje

        JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
                "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {//Configuracion de la pantalla y la crea
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}