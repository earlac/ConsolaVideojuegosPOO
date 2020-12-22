package tennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Raqueta{
    private static final int Y = 330;
    private static final int WITH = 60;
    private static final int HEIGHT = 10;
    private static int xa;
    private static Game game;
    int x = 0;

    public Raqueta(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WITH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WITH, HEIGHT);
    }

    public void levanto(String llave) {
        xa = 0;
    }

    public static void presiono(String llave) {
        if (llave.equals("izq"))
            xa = -game.speed*2;
        if(llave.equals("der"))
            xa = game.speed*2;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WITH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }


}