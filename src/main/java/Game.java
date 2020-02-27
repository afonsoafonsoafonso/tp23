import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Terminal terminal;


    public Game() throws IOException {
        this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(this.terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(10, 10, new TextCharacter('X'));
        screen.setCharacter(12, 12, new TextCharacter('Y'));
        screen.setCharacter(14, 14, new TextCharacter('Z'));
        screen.refresh();
    }

    public void run() {
        try {
            draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
