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

public class Game extends JPanel implements Runnable{

    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);
    int speed = 1;

    private int getScore() {
        return speed - 1;
    }

    public Game(){
        Thread mihilo= new Thread(this);
        mihilo.start();

    }

    @Override
    public void run(){
        try {
            ServerSocket servidor = new ServerSocket(9999);
            while(true) {
                Socket misocket = servidor.accept();

                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());

                String mensaje_texto = flujo_entrada.readUTF();

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

    @Override
    public void paint(Graphics g) {
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

    public void gameOver() {

        JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
                "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {
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