import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;

    Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
    }

    void draw(Screen screen) {
        hero.draw(screen);
    }

    public void processKey(KeyStroke key, Screen screen) {
        System.out.println(key);
        if(key.getKeyType() == KeyType.ArrowUp) {
            moveHero(hero.moveUp());
        }
        else if(key.getKeyType() == KeyType.ArrowDown) {
            moveHero(hero.moveDown());
        }
        else if(key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter()=='q') {
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
        hero.setPosition(position);
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() >= 0 && position.getX() <= width && position.getY() >= 0 && position.getY() <= height)
            return true;
        else return false;
    }

}
