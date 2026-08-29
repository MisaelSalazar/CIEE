package views.staff;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import views.components.ui.UITheme;

public class StaffPanel extends javax.swing.JPanel {

    public StaffPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND);

        JLabel placeholder = new JLabel("Personal - Próximamente");
        placeholder.setFont(UITheme.FONT_SUBTITLE);
        placeholder.setForeground(UITheme.TEXT_MUTED);
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);

        add(placeholder, BorderLayout.CENTER);
    }
}