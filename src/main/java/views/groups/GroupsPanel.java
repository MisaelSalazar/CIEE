package views.groups;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import views.components.ui.UITheme;

public class GroupsPanel extends javax.swing.JPanel {

    public GroupsPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND);

        JLabel placeholder = new JLabel("Grupos - Próximamente");
        placeholder.setFont(UITheme.FONT_SUBTITLE);
        placeholder.setForeground(UITheme.TEXT_MUTED);
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);

        add(placeholder, BorderLayout.CENTER);
    }
}