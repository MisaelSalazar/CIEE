package views.grades;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import views.components.ui.UITheme;

public class GradesPanel extends javax.swing.JPanel {

    public GradesPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND);

        JLabel placeholder = new JLabel("Grados - Próximamente");
        placeholder.setFont(UITheme.FONT_SUBTITLE);
        placeholder.setForeground(UITheme.TEXT_MUTED);
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);

        add(placeholder, BorderLayout.CENTER);
    }
}