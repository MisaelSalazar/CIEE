package views;

import java.awt.*;
import javax.swing.*;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.swing.FontIcon;
import views.organization.Resume;

public class Index extends JFrame {

    private static final Color COLOR_SIDEBAR_BG = new Color(26, 26, 46);
    private static final Color COLOR_SIDEBAR_HOVER = new Color(40, 40, 70);
    private static final Color COLOR_SIDEBAR_ACTIVE = new Color(55, 55, 90);
    private static final Color COLOR_BACKGROUND = new Color(240, 242, 245);
    private static final Color COLOR_PANEL_BG = Color.WHITE;
    private static final Color COLOR_TEXT = new Color(51, 51, 51);

    private static final Font FONT_MENU = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 20);

    private static final int SIDEBAR_WIDTH = 250;

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JLabel titleLabel;
    private JButton activeButton;

    public Index() {
        initFrame();
    }

    private void initFrame() {
        setTitle("CIEE - Panel Principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));
        setPreferredSize(new Dimension(1200, 700));
        setBackground(COLOR_BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BACKGROUND);

        JPanel sidebar = createSidebar();
        JPanel topBar = createTopBar();
        JPanel contentArea = createContentArea();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(COLOR_BACKGROUND);
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
        sidebar.setBackground(COLOR_SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
        sidebar.setMinimumSize(new Dimension(SIDEBAR_WIDTH, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(COLOR_SIDEBAR_BG);
        logoPanel.setMaximumSize(new Dimension(SIDEBAR_WIDTH, 100));
        logoPanel.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 100));

        JLabel logoLabel = new JLabel("CIEE");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logoLabel.setForeground(new Color(150, 150, 200));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoPanel.add(logoLabel);

        sidebar.add(logoPanel);

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(SIDEBAR_WIDTH, 1));
        separator.setForeground(new Color(60, 60, 90));
        sidebar.add(separator);

        String[] menuItems = {"Inicio", "Incidencias", "Eventos", "Grados", "Grupos", "Personal", "Configuración"};
        org.kordamp.ikonli.Ikon[] menuIcons = {
            MaterialDesignH.HOME,
            MaterialDesignA.ALERT,
            MaterialDesignC.CALENDAR_MONTH,
            MaterialDesignA.ACCOUNT_SCHOOL,
            MaterialDesignA.ACCOUNT_GROUP,
            MaterialDesignA.ACCOUNT,
            MaterialDesignC.COG
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = createMenuButton(menuItems[i], menuIcons[i]);
            sidebar.add(button);

            if (i == 0) {
                setActiveButton(button);
            }
        }

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JButton createMenuButton(String text, org.kordamp.ikonli.Ikon icon) {
        JButton button = new JButton(text);
        button.setIcon(FontIcon.of(icon, 18, new Color(160, 160, 200)));
        button.setIconTextGap(14);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setFont(FONT_MENU);
        button.setForeground(Color.WHITE);
        button.setBackground(COLOR_SIDEBAR_BG);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(SIDEBAR_WIDTH, 45));
        button.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 45));
        button.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(COLOR_SIDEBAR_HOVER);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(COLOR_SIDEBAR_BG);
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
            activeButton.setBackground(COLOR_SIDEBAR_BG);
        }
        activeButton = button;
        activeButton.setBackground(COLOR_SIDEBAR_ACTIVE);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(COLOR_PANEL_BG);
        topBar.setPreferredSize(new Dimension(0, 60));
        topBar.setMinimumSize(new Dimension(0, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        titleLabel = new JLabel("Inicio");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topBar.add(titleLabel, BorderLayout.WEST);

        return topBar;
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(COLOR_BACKGROUND);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(COLOR_BACKGROUND);

        contentPanel.add(new Resume(), "Inicio");

        contentArea.add(contentPanel, BorderLayout.CENTER);

        return contentArea;
    }

    private void showCard(String name) {
        cardLayout.show(contentPanel, name);
    }
}
