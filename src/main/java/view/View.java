package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import model.Model;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class View<T extends Model> { // NOTA: a fonte só e para ser usada no gameview ... reposta dps ...
    // adicionar buttons como opções(fora daqui) para uma melhor ligação com o model e uma pre-definição melhor no output

    private static final int height = 25;
    private static final int width = 50;

    protected T model;
    private Screen screen;
    protected TextGraphics graphics;
    protected Font font;

    protected View(T model, String fontName) {
        this.model = model;
        loadFont(fontName);
        initWindow();
    }

    public View(T model, String fontName, Screen screen, TextGraphics textGraphics) {
        this.model = model;
        loadFont(fontName);
        this.screen = screen;
        this.graphics = textGraphics;
    }

    protected void initWindow() {
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory factory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            DefaultTerminalFactory defaultTerminalFactory = factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);

            Terminal terminal = factory.createTerminal();
            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
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

    public void setModel(T model) {
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

    protected void loadFont(String fontName){
        try {
            URL resource = getClass().getClassLoader().getResource(fontName);
            assert resource != null;
            File fontFile = new File(resource.toURI());
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (URISyntaxException | IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    protected static int getHeightCenter() {
        return height / 2;
    }

    protected static int getWidthCenter() {
        return width / 2;
    }

    protected static int getHeight() {
        return height;
    }

    protected static int getWidth() {
        return width;
    }


    public abstract void draw() throws IOException;

    public void close() throws IOException {
        screen.close();
    }

    public Screen getScreen() {
        return this.screen;
    }
}
