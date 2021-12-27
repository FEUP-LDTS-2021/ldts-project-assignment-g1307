package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Model;

import java.io.IOException;

public abstract class View { // NOTA: a fonte só e para ser usada no gameview ... reposta dps ...
    // adicionar buttons como opções(fora daqui) para uma melhor ligação com o model e uma pre-definição melhor no output

    private static int height = 25;
    private static int width = 50;

    protected Model model;
    private Screen screen;
    protected TextGraphics graphics;

    protected View(Model model) {
        this.model = model;
        initWindow();
    }

    public void initWindow() {
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.doResizeIfNecessary();
            graphics = screen.newTextGraphics();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    protected void clear() {
        screen.clear();
    }

    protected void refresh() throws IOException {
        screen.refresh();
    }

    protected abstract void draw();

}
