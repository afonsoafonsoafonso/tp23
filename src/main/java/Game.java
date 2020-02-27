import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Terminal terminal;
    private int x = 10;
     private int y = 10;

    public Game() throws IOException {
        this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(this.terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, new TextCharacter('X'));
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
            this.y--;
        }
        else if(key.getKeyType() == KeyType.ArrowDown) {
            this.y++;
        }
        else if(key.getKeyType() == KeyType.ArrowLeft) {
            this.x--;
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            this.x++;
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter()=='q') {
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
