package views.shell;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.swing.FontIcon;
import views.components.PlaceholderPasswordField;
import views.components.PlaceholderTextField;
import views.components.ui.UITheme;

public class LoginFrame extends JFrame {

    private PlaceholderTextField usernameField;
    private PlaceholderPasswordField passwordField;
    private JToggleButton togglePasswordButton;
    private javax.swing.JButton loginButton;

    public LoginFrame() {
        initFrame();
    }

    private void initFrame() {
        setTitle("CIEE - Inicio de Sesión");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 500));
        setPreferredSize(new Dimension(650, 600));
        setBackground(UITheme.BACKGROUND);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UITheme.BACKGROUND);

        JPanel loginCard = createLoginCard();
        mainPanel.add(loginCard, new GridBagConstraints());

        add(mainPanel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createLoginCard() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(UITheme.PANEL_BG);
        card.setPreferredSize(new Dimension(400, 450));
        card.setMinimumSize(new Dimension(350, 400));
        card.setMaximumSize(new Dimension(500, 550));
        card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        JPanel header = createHeader();
        JPanel form = createForm();

        card.add(header, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);

        return card;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(UITheme.SIDEBAR_BG);
        header.setPreferredSize(new Dimension(400, 120));
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("<html><center>Control de Incidencias y<br>Eventos Escolares</center></html>");
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        titleLabel.setForeground(java.awt.Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("CIEE");
        subtitleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 28));
        subtitleLabel.setForeground(UITheme.SIDEBAR_LOGO);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        header.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        header.add(subtitleLabel, gbc);

        return header;
    }

    private JPanel createForm() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UITheme.PANEL_BG);
        form.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = createLabel("Usuario");
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        form.add(usernameLabel, gbc);

        usernameField = new PlaceholderTextField("Ingresa tu usuario");
        usernameField.setFont(UITheme.FONT_INPUT);
        usernameField.setPreferredSize(new Dimension(0, 40));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        form.add(usernameField, gbc);

        JLabel passwordLabel = createLabel("Contraseña");
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        form.add(passwordLabel, gbc);

        JPanel passwordPanel = createPasswordField();
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 30, 0);
        form.add(passwordPanel, gbc);

        loginButton = new javax.swing.JButton("Ingresar");
        loginButton.setFont(UITheme.FONT_BUTTON);
        loginButton.setBackground(UITheme.SIDEBAR_BG);
        loginButton.setForeground(java.awt.Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(0, 48));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginButton.setBackground(UITheme.SIDEBAR_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginButton.setBackground(UITheme.SIDEBAR_BG);
            }
        });

        loginButton.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
            dispose();
        });

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        form.add(loginButton, gbc);

        return form;
    }

    private JPanel createPasswordField() {
        passwordField = new PlaceholderPasswordField("Ingresa tu contraseña");
        passwordField.setFont(UITheme.FONT_INPUT);
        passwordField.setPreferredSize(new Dimension(0, 40));

        togglePasswordButton = new JToggleButton();
        togglePasswordButton.setIcon(FontIcon.of(MaterialDesignE.EYE, 18, UITheme.TEXT));
        togglePasswordButton.setSelectedIcon(FontIcon.of(MaterialDesignE.EYE_OFF, 18, UITheme.TEXT));
        togglePasswordButton.setBackground(UITheme.TOGGLE_BG);
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setOpaque(true);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.setPreferredSize(new Dimension(40, 40));
        togglePasswordButton.setToolTipText("Mostrar contraseña");

        togglePasswordButton.addActionListener(e -> {
            if (togglePasswordButton.isSelected()) {
                passwordField.setEchoChar((char) 0);
                togglePasswordButton.setBackground(UITheme.TOGGLE_ACTIVE);
                togglePasswordButton.setToolTipText("Ocultar contraseña");
            } else {
                passwordField.setEchoChar('•');
                togglePasswordButton.setBackground(UITheme.TOGGLE_BG);
                togglePasswordButton.setToolTipText("Mostrar contraseña");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UITheme.PANEL_BG);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(togglePasswordButton, BorderLayout.EAST);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_LABEL);
        label.setForeground(UITheme.TEXT);
        return label;
    }
}