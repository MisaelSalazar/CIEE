package views;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import views.components.PlaceholderPasswordField;
import views.components.PlaceholderTextField;

public class Login extends JFrame {

    private static final Color COLOR_BACKGROUND = new Color(240, 242, 245);
    private static final Color COLOR_PANEL_BG = Color.WHITE;
    private static final Color COLOR_HEADER = new Color(26, 26, 46);
    private static final Color COLOR_TEXT = new Color(51, 51, 51);
    private static final Color COLOR_PLACEHOLDER = new Color(170, 170, 170);
    private static final Color COLOR_BUTTON = new Color(26, 26, 46);
    private static final Color COLOR_BUTTON_HOVER = new Color(40, 40, 70);
    private static final Color COLOR_TOGGLE_BG = new Color(230, 230, 230);
    private static final Color COLOR_TOGGLE_ACTIVE = new Color(200, 200, 220);
    private static final Color COLOR_BORDER = new Color(200, 200, 200);

    private static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 16);

    private PlaceholderTextField usernameField;
    private PlaceholderPasswordField passwordField;
    private JToggleButton togglePasswordButton;
    private JButton loginButton;

    public Login() {
        initFrame();
    }

    private void initFrame() {
        setTitle("CIEE - Inicio de Sesión");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 500));
        setPreferredSize(new Dimension(650, 600));
        setBackground(COLOR_BACKGROUND);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(COLOR_BACKGROUND);

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
        card.setBackground(COLOR_PANEL_BG);
        card.setPreferredSize(new Dimension(400, 450));
        card.setMinimumSize(new Dimension(350, 400));
        card.setMaximumSize(new Dimension(500, 550));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        JPanel header = createHeader();
        JPanel form = createForm();

        card.add(header, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);

        return card;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(COLOR_HEADER);
        header.setPreferredSize(new Dimension(400, 120));
        header.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("<html><center>Control de Incidencias y<br>Eventos Escolares</center></html>");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("CIEE");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        subtitleLabel.setForeground(new Color(150, 150, 200));
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
        form.setBackground(COLOR_PANEL_BG);
        form.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = createLabel("Usuario");
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        form.add(usernameLabel, gbc);

        usernameField = new PlaceholderTextField("Ingresa tu usuario");
        usernameField.setFont(FONT_INPUT);
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

        loginButton = new JButton("Ingresar");
        loginButton.setFont(FONT_BUTTON);
        loginButton.setBackground(COLOR_BUTTON);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(0, 48));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginButton.setBackground(COLOR_BUTTON_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginButton.setBackground(COLOR_BUTTON);
            }
        });

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        form.add(loginButton, gbc);

        return form;
    }

    private JPanel createPasswordField() {
        passwordField = new PlaceholderPasswordField("Ingresa tu contraseña");
        passwordField.setFont(FONT_INPUT);
        passwordField.setPreferredSize(new Dimension(0, 40));

        togglePasswordButton = new JToggleButton("VER");
        togglePasswordButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        togglePasswordButton.setBackground(COLOR_TOGGLE_BG);
        togglePasswordButton.setForeground(COLOR_TEXT);
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setOpaque(true);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.setPreferredSize(new Dimension(70, 40));
        togglePasswordButton.setToolTipText("Mostrar contraseña");

        togglePasswordButton.addActionListener(e -> {
            if (togglePasswordButton.isSelected()) {
                passwordField.setEchoChar((char) 0);
                togglePasswordButton.setText("OCULTAR");
                togglePasswordButton.setBackground(COLOR_TOGGLE_ACTIVE);
                togglePasswordButton.setToolTipText("Ocultar contraseña");
            } else {
                passwordField.setEchoChar('•');
                togglePasswordButton.setText("VER");
                togglePasswordButton.setBackground(COLOR_TOGGLE_BG);
                togglePasswordButton.setToolTipText("Mostrar contraseña");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL_BG);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(togglePasswordButton, BorderLayout.EAST);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_TEXT);
        return label;
    }
}
