/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.organization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;
import org.kordamp.ikonli.swing.FontIcon;

/**
 *
 * @author Misae
 */
public class Resume extends javax.swing.JPanel {

    private static final Color COLOR_BACKGROUND = new Color(240, 242, 245);
    private static final Color COLOR_CARD_BG = Color.WHITE;
    private static final Color COLOR_TEXT = new Color(51, 51, 51);
    private static final Color COLOR_TEXT_MUTED = new Color(130, 130, 140);
    private static final Color COLOR_BORDER = new Color(220, 220, 220);
    private static final Color COLOR_PRIMARY = new Color(90, 90, 160);
    private static final Color COLOR_ALERT = new Color(200, 80, 80);

    private static final Font FONT_GREETING = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_CARD_TITLE = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FONT_CARD_VALUE = new Font("Segoe UI", Font.BOLD, 32);
    private static final Font FONT_CARD_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_CARD_MUTED = new Font("Segoe UI", Font.PLAIN, 13);

    public Resume() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(COLOR_BACKGROUND);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(25, 30, 30, 30));

        mainPanel.add(createGreetingPanel(), BorderLayout.NORTH);
        mainPanel.add(createCardsPanel(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createGreetingPanel() {
        JPanel greeting = new JPanel();
        greeting.setLayout(new BoxLayout(greeting, BoxLayout.Y_AXIS));
        greeting.setBackground(COLOR_BACKGROUND);
        greeting.setBorder(new EmptyBorder(0, 2, 20, 2));

        JLabel saludo = new JLabel("Hola, Usuario");
        saludo.setFont(FONT_GREETING);
        saludo.setForeground(COLOR_TEXT);

        JLabel subtitle = new JLabel("Bienvenido al panel de resumen. Aquí encontrarás un vistazo general.");
        subtitle.setFont(FONT_SUBTITLE);
        subtitle.setForeground(COLOR_TEXT_MUTED);

        greeting.add(saludo);
        greeting.add(Box.createVerticalStrut(6));
        greeting.add(subtitle);

        return greeting;
    }

    private JPanel createCardsPanel() {
        JPanel topRow = new JPanel(new GridBagLayout());
        topRow.setBackground(COLOR_BACKGROUND);

        Insets insets = new Insets(0, 0, 0, 15);

        addCard(topRow, createStatCard(
                "Permisos otorgados", "10",
                "Permisos vigentes",
                FontIcon.of(MaterialDesignS.SHIELD_ACCOUNT, 22, COLOR_PRIMARY),
                COLOR_PRIMARY), 0, insets);
        addCard(topRow, createStatCard(
                "Incidencias", "3",
                "Incidencias activas",
                FontIcon.of(MaterialDesignA.ALERT, 22, COLOR_ALERT),
                COLOR_ALERT), 1, insets);
        addCard(topRow, createStatCard(
                "Seguimiento de pendientes", "5",
                "Pendientes por resolver",
                FontIcon.of(MaterialDesignC.CALENDAR_CHECK, 22, COLOR_PRIMARY),
                COLOR_PRIMARY), 2, insets);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(COLOR_BACKGROUND);

        GridBagConstraints topGbc = new GridBagConstraints();
        topGbc.gridx = 0;
        topGbc.gridy = 0;
        topGbc.weightx = 1.0;
        topGbc.fill = GridBagConstraints.HORIZONTAL;
        topGbc.insets = new Insets(0, 0, 15, 0);
        content.add(topRow, topGbc);

        GridBagConstraints eventsGbc = new GridBagConstraints();
        eventsGbc.gridx = 0;
        eventsGbc.gridy = 1;
        eventsGbc.weightx = 1.0;
        eventsGbc.weighty = 1.0;
        eventsGbc.fill = GridBagConstraints.BOTH;
        content.add(createEventsCard(), eventsGbc);

        return content;
    }

    private void addCard(JPanel container, JPanel card, int gridX, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = insets;
        container.add(card, gbc);
    }

    private JPanel createStatCard(String title, String value, String caption,
                                  FontIcon icon, Color accent) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD_BG);
        card.setBorder(createCardBorder());
        card.setMinimumSize(new Dimension(180, 130));
        card.setPreferredSize(new Dimension(200, 130));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(15, 18, 0, 18));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FONT_CARD_TITLE);
        titleLabel.setForeground(COLOR_TEXT);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBorder(new EmptyBorder(0, 0, 0, 0));

        header.add(titleLabel, BorderLayout.CENTER);
        header.add(iconLabel, BorderLayout.EAST);

        JPanel body = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 10));
        body.setOpaque(false);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(FONT_CARD_VALUE);
        valueLabel.setForeground(accent);

        JLabel captionLabel = new JLabel(caption);
        captionLabel.setFont(FONT_CARD_MUTED);
        captionLabel.setForeground(COLOR_TEXT_MUTED);
        captionLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        body.add(valueLabel);
        body.add(Box.createHorizontalStrut(4));
        body.add(captionLabel);

        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);

        return card;
    }

    private JPanel createEventsCard() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD_BG);
        card.setBorder(createCardBorder());
        card.setMinimumSize(new Dimension(0, 160));
        card.setPreferredSize(new Dimension(0, 160));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(15, 18, 10, 18));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        JLabel titleLabel = new JLabel("Próximos eventos");
        titleLabel.setFont(FONT_CARD_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        left.add(titleLabel);

        JLabel iconLabel = new JLabel(FontIcon.of(MaterialDesignC.CALENDAR_MONTH, 20, COLOR_PRIMARY));
        iconLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        left.add(iconLabel);

        header.add(left, BorderLayout.WEST);

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(0, 18, 0, 5));
        body.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        body.add(createEventItem("Reunión de padres", "Lun 01 Sep - 09:00"));
        body.add(Box.createVerticalStrut(8));
        body.add(createEventItem("Festival de aniversario", "Jue 04 Sep - 10:00"));
        body.add(Box.createVerticalStrut(8));
        body.add(createEventItem("Consejo estudiantil", "Vie 05 Sep - 14:30"));
        body.add(Box.createVerticalStrut(8));
        body.add(createEventItem("Torneo deportivo", "Sáb 06 Sep - 08:30"));
        body.add(Box.createVerticalStrut(8));
        body.add(createEventItem("Feria de ciencias", "Mié 17 Sep - 11:00"));
        body.add(Box.createVerticalStrut(8));
        body.add(createEventItem("Día de la cultura", "Vie 19 Sep - 09:30"));

        JScrollPane scroll = new JScrollPane(
                body,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(new EmptyBorder(0, 0, 12, 0));
        scroll.getViewport().setBackground(COLOR_CARD_BG);
        javax.swing.JScrollBar vBar = scroll.getVerticalScrollBar();
        vBar.setPreferredSize(new Dimension(6, 0));
        vBar.setBackground(COLOR_CARD_BG);
        vBar.setUnitIncrement(14);
        vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = COLOR_BORDER;
                this.trackColor = COLOR_CARD_BG;
            }

            @Override
            protected javax.swing.JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected javax.swing.JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private javax.swing.JButton createZeroButton() {
                javax.swing.JButton b = new javax.swing.JButton();
                b.setPreferredSize(new Dimension(0, 0));
                b.setMinimumSize(new Dimension(0, 0));
                b.setMaximumSize(new Dimension(0, 0));
                return b;
            }
        });

        card.add(header, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);

        return card;
    }

    private JPanel createEventItem(String name, String date) {
        JPanel item = new JPanel(new BorderLayout());
        item.setOpaque(false);

        JPanel dot = new JPanel();
        dot.setPreferredSize(new Dimension(10, 10));
        dot.setBackground(COLOR_PRIMARY);
        dot.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        left.setOpaque(false);
        left.add(dot);

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setOpaque(false);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(FONT_CARD_BODY);
        nameLabel.setForeground(COLOR_TEXT);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(FONT_CARD_MUTED);
        dateLabel.setForeground(COLOR_TEXT_MUTED);

        text.add(nameLabel);
        text.add(Box.createVerticalStrut(2));
        text.add(dateLabel);

        item.add(left, BorderLayout.WEST);
        item.add(text, BorderLayout.CENTER);

        return item;
    }

    private Border createCardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                new EmptyBorder(2, 2, 2, 2));
    }
}
