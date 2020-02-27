import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;
    private Hero hero;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        hero = new Hero(x,y);
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    public void run() {
        while(true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                if(key.getKeyType()==KeyType.EOF) break;
                processKey(key);
                draw();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) {
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
        hero.setPosition(position);
    }
}
