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
    private Arena arena;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        arena = new Arena(30, 30);
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen);
        screen.refresh();
    }

    public void run() {
        while(true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                if(key.getKeyType()==KeyType.EOF) break;
                arena.processKey(key, screen);
                draw();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
