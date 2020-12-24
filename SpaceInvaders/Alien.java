package SpaceInvaders;


import javax.swing.ImageIcon;

public class Alien extends Sprite {
    private Bomb bomb;

    public Alien(int x, int y) {
        initAlien(x, y);
    }

    private void initAlien(int x, int y) {
        //F: Inicializa el objeto alien
        //E: NA
        //S: NA
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        var alienImg = "src/SpaceInvaders/alien.png";
        var ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    public void act(int direction) {
        //F: Crea la dirección hacia abajo para las bodas
        //E: NA
        //S: NA
        this.x += direction;
    }

    public Bomb getBomb() {
        //F: Inicializa el objeto bomba
        //E: NA
        //S: NA
        return bomb;
    }

    public class Bomb extends Sprite {

        private boolean destroyed;
        public Bomb(int x, int y) {
            //F: Llama al inicializador de la bomba en la posición x, y
            //E: NA
            //S: NA
            initBomb(x, y);
        }

        private void initBomb(int x, int y) {
            //F: Crea la posición de la bomba para inicializarla y explotarla
            //E: NA
            //S: NA
            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = "src/SpaceInvaders/bomb.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            //F: Inicializa el destructor de la bomba
            //E: NA
            //S: NA
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            //F: Detecta si un objeto está destruido
            //E: NA
            //S: NA
            return destroyed;
        }
    }
}