import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    public static Font ItimCursive;
    public static Font ItimCursiveDefaultSize;
    public static float defaultFontSize = 16;

    public static void loadFont() {
        defaultFontSize = UIManager.getDefaults().getFont("Label.font").getSize();

        try {
            InputStream inpStream = FontManager.class.getResourceAsStream("/resources/Itim/Itim-Regular.ttf");

            ItimCursive = Font.createFont(Font.TRUETYPE_FONT, inpStream);
            ItimCursiveDefaultSize = ItimCursive.deriveFont(defaultFontSize);
        } catch (FontFormatException | IOException e) {
            System.out.println("[FontManager]: Failed to load fonts!");
            e.printStackTrace();
            return;
        }
        System.out.println("[FontManager]: fonts loaded!");
    }
}
