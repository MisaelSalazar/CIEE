package views.core;

import java.util.function.Supplier;
import javax.swing.JPanel;
import org.kordamp.ikonli.Ikon;

public record ViewSpec(String key, String title, Ikon icon, Supplier<JPanel> factory) {
}