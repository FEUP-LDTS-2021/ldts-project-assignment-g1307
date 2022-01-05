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

public abstract class View<T extends Model> {
    private int height;
    private int width;

    protected T model;
    private Screen screen;
    protected TextGraphics graphics;
    protected Font font;


    protected View(T model, String fontName, int fontSize, int height, int width) {
        this.model = model;
        loadFont(fontName);
        this.height = height;
        this.width = width;
        initWindow(fontSize);
    }

    public View(T model, String fontName, Screen screen, TextGraphics textGraphics) {
        this.model = model;
        loadFont(fontName);
        this.screen = screen;
        this.graphics = textGraphics;
    }

    protected void initWindow(int fontSize) {
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory factory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Font loadedFont = font.deriveFont(Font.PLAIN, fontSize);
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

    protected int getHeightCenter() {
        return height / 2;
    }

    protected int getWidthCenter() {
        return width / 2;
    }

    protected int getHeight() {
        return height;
    }

    protected int getWidth() {
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
