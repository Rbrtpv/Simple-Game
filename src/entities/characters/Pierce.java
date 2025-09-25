package entities.characters;

public class Pierce extends GameCharacter {

    private int x, y, w, h, speed;

    public Pierce(int x, int y, int w, int h, int speed) {
        super(x, y, w, h, speed);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
    }

    @Override
    public void update() {
        System.out.println("update pierce");
    }
}
