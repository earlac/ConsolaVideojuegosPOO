package tennis;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
    private static final int Y = 330;
    private static final int WITH = 60;
    private static final int HEIGHT = 10;
    int x = 0;
    int xa = 10;
    private Game game;

    public Racquet(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WITH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WITH, HEIGHT);
    }

    public void detener() {
        xa = 0;
    }


    public void moverIzquierda(){
        xa = -game.speed*2;
    }
    public void moverDerecha(){
        xa = game.speed*2;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WITH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}