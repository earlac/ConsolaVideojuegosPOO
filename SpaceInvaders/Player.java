package SpaceInvaders;



import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Player extends Sprite implements Runnable{
    private int width;

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        var playerImg = "src/SpaceInvaders/player.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);

        Thread mihilo= new Thread(this);
        mihilo.start();
    }

    public void act() {
        x += dx;
        if (x <= 2) {
            x = 2;
        }
        if (x >= Commons.BOARD_WIDTH - 2 * width) {
            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(9999);
            while(true) {
                Socket misocket = servidor.accept();

                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());

                String mensaje_texto = flujo_entrada.readUTF();

                if(mensaje_texto.equals("izq")){
                    System.out.println("Izq");
                    dx = -20;

                }else if(mensaje_texto.equals("der")){
                    System.out.println("Der");
                    dx = 20;

                }else{
                    System.out.println("Cen");
                }
                misocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}