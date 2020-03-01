import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    Position position;

    Element(int x, int y) {
        position = new Position(x, y);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return  position.getY();
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void draw(TextGraphics graphics);
}
