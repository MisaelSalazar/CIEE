package views.shell;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;
import views.components.ui.UITheme;
import views.core.ViewRegistry;
import views.core.ViewSpec;

public class MainFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JLabel titleLabel;
    private JButton activeButton;

    public MainFrame() {
        initFrame();
    }

    private void initFrame() {
        setTitle("CIEE - Panel Principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setPreferredSize(new Dimension(1200, 700));
        setBackground(UITheme.BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND);

        JPanel sidebar = createSidebar();
        JPanel topBar = createTopBar();
        JPanel contentArea = createContentArea();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(UITheme.BACKGROUND);
        rightPanel.add(topBar, BorderLayout.NORTH);
        rightPanel.add(contentArea, BorderLayout.CENTER);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UITheme.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(UITheme.SIDEBAR_WIDTH, 0));
        sidebar.setMinimumSize(new Dimension(UITheme.SIDEBAR_WIDTH, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel logoPanel = new JPanel(new java.awt.GridBagLayout());
        logoPanel.setBackground(UITheme.SIDEBAR_BG);
        logoPanel.setMaximumSize(new Dimension(UITheme.SIDEBAR_WIDTH, 100));
        logoPanel.setPreferredSize(new Dimension(UITheme.SIDEBAR_WIDTH, 100));

        JLabel logoLabel = new JLabel("CIEE");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(UITheme.SIDEBAR_LOGO);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoPanel.add(logoLabel);

        sidebar.add(logoPanel);

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(UITheme.SIDEBAR_WIDTH, 1));
        separator.setForeground(UITheme.SIDEBAR_SEPARATOR);
        sidebar.add(separator);

        int index = 0;
        for (ViewSpec spec : ViewRegistry.getAll()) {
            JButton button = createMenuButton(spec.title(), spec.icon());
            sidebar.add(button);

            if (index == 0) {
                setActiveButton(button);
            }
            index++;
        }

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JButton createMenuButton(String text, Ikon icon) {
        JButton button = new JButton(text);
        button.setIcon(FontIcon.of(icon, 18, UITheme.SIDEBAR_TEXT));
        button.setIconTextGap(14);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setFont(UITheme.FONT_MENU_ITEM);
        button.setForeground(Color.WHITE);
        button.setBackground(UITheme.SIDEBAR_BG);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(UITheme.SIDEBAR_WIDTH, 45));
        button.setPreferredSize(new Dimension(UITheme.SIDEBAR_WIDTH, 45));
        button.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(UITheme.SIDEBAR_HOVER);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(UITheme.SIDEBAR_BG);
                }
            }
        });

        button.addActionListener(e -> {
            setActiveButton(button);
            titleLabel.setText(text);
            showCard(text);
        });

        return button;
    }

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setBackground(UITheme.SIDEBAR_BG);
        }
        activeButton = button;
        activeButton.setBackground(UITheme.SIDEBAR_ACTIVE);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(UITheme.PANEL_BG);
        topBar.setPreferredSize(new Dimension(0, 60));
        topBar.setMinimumSize(new Dimension(0, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER));

        titleLabel = new JLabel("Inicio");
        titleLabel.setFont(UITheme.FONT_TITLE);
        titleLabel.setForeground(UITheme.TEXT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topBar.add(titleLabel, BorderLayout.WEST);

        return topBar;
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(UITheme.BACKGROUND);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(UITheme.BACKGROUND);

        for (ViewSpec spec : ViewRegistry.getAll()) {
            contentPanel.add(spec.factory().get(), spec.key());
        }

        contentArea.add(contentPanel, BorderLayout.CENTER);

        return contentArea;
    }

    private void showCard(String name) {
        cardLayout.show(contentPanel, name);
    }
}