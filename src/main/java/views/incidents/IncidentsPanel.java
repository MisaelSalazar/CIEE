package views.incidents;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import views.components.ui.UITheme;

public class IncidentsPanel extends javax.swing.JPanel {

    private final javax.swing.JTabbedPane tabbedPane;

    public IncidentsPanel() {
        setLayout(new java.awt.BorderLayout());
        setBackground(UITheme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(25, 30, 30, 30));

        tabbedPane = new javax.swing.JTabbedPane();
        tabbedPane.setUI(new FlatTabbedPaneUI());
        tabbedPane.setBackground(UITheme.BACKGROUND);
        UIManager.put("TabbedPane.tabInsets", new Insets(12, 24, 12, 24));

        tabbedPane.addTab("Gestión de Incidencias", new GestionIncidenciasPanel());
        tabbedPane.addTab("Seguimiento de Incidencias", new SeguimientoIncidenciasPanel());
        tabbedPane.addTab("Tipos de incidencias", new TiposIncidenciasPanel());

        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JLabel tabLabel = new JLabel(tabbedPane.getTitleAt(i));
            tabLabel.setHorizontalAlignment(JLabel.CENTER);
            tabbedPane.setTabComponentAt(i, tabLabel);
        }

        tabbedPane.addChangeListener(e -> updateTabAppearance());
        updateTabAppearance();

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }

    private void updateTabAppearance() {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            boolean selected = tabbedPane.getSelectedIndex() == i;
            JLabel label = (JLabel) tabbedPane.getTabComponentAt(i);
            label.setFont(UITheme.FONT_LABEL.deriveFont(selected
                    ? java.awt.Font.BOLD
                    : java.awt.Font.PLAIN));
            label.setForeground(selected ? UITheme.PRIMARY : UITheme.TEXT_MUTED);
            label.setBackground(selected ? UITheme.PANEL_BG : UITheme.BACKGROUND);
            label.setOpaque(true);
        }

        tabbedPane.repaint();
        tabbedPane.revalidate();
    }

    private static class FlatTabbedPaneUI extends BasicTabbedPaneUI {

        @Override
        protected void paintTabBackground(java.awt.Graphics g, int tabPlacement,
                                          int tabIndex, int x, int y, int w, int h,
                                          boolean isSelected) {
            g.setColor(isSelected ? UITheme.PANEL_BG : UITheme.BACKGROUND);
            g.fillRect(x, y, w, h);
        }

        @Override
        protected void paintTabBorder(java.awt.Graphics g, int tabPlacement,
                                      int tabIndex, int x, int y, int w, int h,
                                      boolean isSelected) {
            if (!isSelected) {
                return;
            }
            g.setColor(UITheme.PANEL_BG);
            g.fillRect(x, y, w, h);
            g.setColor(UITheme.PRIMARY);
            g.fillRect(x, y + h - 3, w, 3);
        }

        @Override
        protected void paintContentBorder(java.awt.Graphics g, int tabPlacement, int selectedIndex) {
            int tabBottom = calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
            int width = tabPane.getWidth();
            int height = tabPane.getHeight();

            g.setColor(UITheme.BACKGROUND);
            g.fillRect(0, tabBottom, width, height - tabBottom);

            g.setColor(UITheme.BORDER);
            g.fillRect(0, tabBottom - 1, width, 1);
        }
    }

    private static class GestionIncidenciasPanel extends javax.swing.JPanel {

        public GestionIncidenciasPanel() {
            setLayout(new java.awt.BorderLayout());
            setBackground(UITheme.BACKGROUND);

            add(createHeader(), java.awt.BorderLayout.NORTH);
            add(createTable(), java.awt.BorderLayout.CENTER);
        }

        private javax.swing.JPanel createHeader() {
            javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
            header.setOpaque(false);
            header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));

            javax.swing.JButton addButton = new javax.swing.JButton("Agregar Incidencia");
            addButton.setIcon(org.kordamp.ikonli.swing.FontIcon.of(
                    org.kordamp.ikonli.materialdesign2.MaterialDesignP.PLUS, 18, java.awt.Color.WHITE));
            addButton.setIconTextGap(10);
            addButton.setFont(UITheme.FONT_BUTTON);
            addButton.setBackground(UITheme.SIDEBAR_BG);
            addButton.setForeground(java.awt.Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.setBorderPainted(false);
            addButton.setOpaque(true);
            addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            addButton.setPreferredSize(new java.awt.Dimension(200, 44));
            addButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
            addButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_HOVER);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_BG);
                }
            });

            javax.swing.JPanel right = new javax.swing.JPanel(new java.awt.FlowLayout(
                    java.awt.FlowLayout.RIGHT, 0, 0));
            right.setOpaque(false);
            right.add(addButton);

            header.add(right, java.awt.BorderLayout.EAST);

            return header;
        }

        private javax.swing.JComponent createTable() {
            String[] columns = {
                    "#", "Escuela", "Estudiante", "Personal", "Tipo de incidente",
                    "Fecha de la incidencia", "Descripción", "Estado", "Usuario",
                    "Fecha de creación", "Última actualización", "Acciones"
            };

            Object[][] data = {
                    {"1", "Esc. Primaria Benito Juárez", "Juan Pérez", "Lic. María Gómez",
                            "Conductual", "2026-08-20", "Falta de respeto hacia un compañero.",
                            "Abierta", "admin", "2026-08-20 09:15", "2026-08-21 11:30", null},
                    {"2", "Esc. Secundaria Netzahualcóyotl", "Ana Torres", "Prof. Luis Ramírez",
                            "Académica", "2026-08-18", "Tareas sin entregar durante la semana.",
                            "Cerrada", "profesor", "2026-08-18 14:02", "2026-08-19 08:45", null},
                    {"3", "Esc. Primaria Benito Juárez", "Carlos Díaz", "Lic. María Gómez",
                            "Disciplinaria", "2026-08-22", "Uso de dispositivo en clase.",
                            "Abierta", "admin", "2026-08-22 10:40", "2026-08-22 10:40", null},
                    {"4", "Esc. Técnica No. 5", "Laura Cruz", "Prof. Elena Soto",
                            "Conductual", "2026-08-15", "Discusión con otro estudiante.",
                            "Cerrada", "profesor", "2026-08-15 12:20", "2026-08-16 09:00", null}
            };

            javax.swing.JTable table = new javax.swing.JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setFont(UITheme.FONT_CARD_BODY);
            table.setForeground(UITheme.TEXT);
            table.setBackground(UITheme.PANEL_BG);
            table.setRowHeight(42);
            table.setFillsViewportHeight(true);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setGridColor(UITheme.BORDER);
            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            table.setSelectionBackground(UITheme.TOGGLE_ACTIVE);
            table.setSelectionForeground(UITheme.TEXT);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setFont(UITheme.FONT_LABEL.deriveFont(java.awt.Font.BOLD));
            table.getTableHeader().setBackground(UITheme.SIDEBAR_BG);
            table.getTableHeader().setForeground(java.awt.Color.WHITE);
            table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 44));
            table.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, UITheme.PRIMARY));

            table.setDefaultRenderer(Object.class, new IncidenciaCellRenderer());

            for (int i = 0; i < table.getColumnCount(); i++) {
                int width;
                switch (i) {
                    case 0: width = 45; break;
                    case 1: width = 190; break;
                    case 2: width = 160; break;
                    case 3: width = 170; break;
                    case 4: width = 140; break;
                    case 5: width = 160; break;
                    case 6: width = 220; break;
                    case 7: width = 110; break;
                    case 8: width = 110; break;
                    case 9: width = 150; break;
                    case 10: width = 160; break;
                    default: width = 170;
                }
                table.getColumnModel().getColumn(i).setPreferredWidth(width);
            }

            javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(table);
            scroll.getViewport().setBackground(UITheme.PANEL_BG);
            scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            javax.swing.JScrollBar vBar = scroll.getVerticalScrollBar();
            vBar.setPreferredSize(new java.awt.Dimension(8, 0));
            vBar.setUnitIncrement(16);
            vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            javax.swing.JScrollBar hBar = scroll.getHorizontalScrollBar();
            hBar.setPreferredSize(new java.awt.Dimension(0, 8));
            hBar.setUnitIncrement(16);
            hBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            return scroll;
        }
    }

    private static class IncidenciaCellRenderer extends javax.swing.table.DefaultTableCellRenderer {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (column == 11) {
                javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
                actions.setBackground(isSelected ? UITheme.TOGGLE_ACTIVE : UITheme.PANEL_BG);

                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignE.EYE, "Ver detalles",
                        new Color(72, 160, 90)));
                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignP.PENCIL, "Editar",
                        new Color(200, 135, 20)));
                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignD.DELETE, "Eliminar",
                        new Color(210, 70, 70)));

                return actions;
            }

            setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));
            if (column == 0) {
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            }
            if (isSelected) {
                setBackground(UITheme.TOGGLE_ACTIVE);
                setForeground(UITheme.TEXT);
            } else {
                setBackground(row % 2 == 0 ? UITheme.PANEL_BG : new Color(248, 249, 251));
                setForeground(column == 7 ? uiForegroundForEstado(String.valueOf(value))
                        : UITheme.TEXT);
            }
            return c;
        }

        private javax.swing.JButton createActionButton(org.kordamp.ikonli.Ikon icon, String tip, Color bg) {
            javax.swing.JButton button = new javax.swing.JButton(
                    org.kordamp.ikonli.swing.FontIcon.of(icon, 15, java.awt.Color.WHITE));
            button.setToolTipText(tip);
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            button.setBackground(bg);
            button.setForeground(java.awt.Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setPreferredSize(new java.awt.Dimension(30, 30));
            button.setMinimumSize(new java.awt.Dimension(30, 30));
            button.setMaximumSize(new java.awt.Dimension(30, 30));
            return button;
        }

        private java.awt.Color uiForegroundForEstado(String estado) {
            if ("Abierta".equals(estado)) {
                return new Color(190, 120, 10);
            }
            if ("Cerrada".equals(estado)) {
                return new Color(70, 140, 80);
            }
            return UITheme.TEXT;
        }
    }

    private static class SeguimientoIncidenciasPanel extends javax.swing.JPanel {

        public SeguimientoIncidenciasPanel() {
            setLayout(new java.awt.BorderLayout());
            setBackground(UITheme.BACKGROUND);

            add(createHeader(), java.awt.BorderLayout.NORTH);
            add(createTable(), java.awt.BorderLayout.CENTER);
        }

        private javax.swing.JPanel createHeader() {
            javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
            header.setOpaque(false);
            header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));

            javax.swing.JButton addButton = new javax.swing.JButton("Nuevo Seguimiento");
            addButton.setIcon(org.kordamp.ikonli.swing.FontIcon.of(
                    org.kordamp.ikonli.materialdesign2.MaterialDesignP.PLUS, 18, java.awt.Color.WHITE));
            addButton.setIconTextGap(10);
            addButton.setFont(UITheme.FONT_BUTTON);
            addButton.setBackground(UITheme.SIDEBAR_BG);
            addButton.setForeground(java.awt.Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.setBorderPainted(false);
            addButton.setOpaque(true);
            addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            addButton.setPreferredSize(new java.awt.Dimension(220, 44));
            addButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
            addButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_HOVER);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_BG);
                }
            });

            javax.swing.JPanel right = new javax.swing.JPanel(new java.awt.FlowLayout(
                    java.awt.FlowLayout.RIGHT, 0, 0));
            right.setOpaque(false);
            right.add(addButton);

            header.add(right, java.awt.BorderLayout.EAST);

            return header;
        }

        private javax.swing.JComponent createTable() {
            String[] columns = {
                    "#", "Incidente", "Fecha del seguimiento", "Descripción",
                    "Estatus", "Personal", "Acciones"
            };

            Object[][] data = {
                    {"1", "Incidencia #1", "2026-08-22", "Se programaron tutorías para el alumno.",
                            "Abierta", "Lic. María Gómez", null},
                    {"2", "Incidencia #2", "2026-08-19", "Se notificó a los padres vía telefónica.",
                            "Cerrada", "Prof. Luis Ramírez", null},
                    {"3", "Incidencia #3", "2026-08-24", "Reunión con la dirección escolar.",
                            "Abierta", "Lic. María Gómez", null},
                    {"4", "Incidencia #4", "2026-08-17", "Se acordó un plan de seguimiento semanal.",
                            "Cerrada", "Prof. Elena Soto", null}
            };

            javax.swing.JTable table = new javax.swing.JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setFont(UITheme.FONT_CARD_BODY);
            table.setForeground(UITheme.TEXT);
            table.setBackground(UITheme.PANEL_BG);
            table.setRowHeight(42);
            table.setFillsViewportHeight(true);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setGridColor(UITheme.BORDER);
            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            table.setSelectionBackground(UITheme.TOGGLE_ACTIVE);
            table.setSelectionForeground(UITheme.TEXT);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setFont(UITheme.FONT_LABEL.deriveFont(java.awt.Font.BOLD));
            table.getTableHeader().setBackground(UITheme.SIDEBAR_BG);
            table.getTableHeader().setForeground(java.awt.Color.WHITE);
            table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 44));
            table.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, UITheme.PRIMARY));

            table.setDefaultRenderer(Object.class, new SeguimientoCellRenderer());

            for (int i = 0; i < table.getColumnCount(); i++) {
                int width;
                switch (i) {
                    case 0: width = 45; break;
                    case 1: width = 180; break;
                    case 2: width = 190; break;
                    case 3: width = 300; break;
                    case 4: width = 110; break;
                    case 5: width = 170; break;
                    default: width = 170;
                }
                table.getColumnModel().getColumn(i).setPreferredWidth(width);
            }

            javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(table);
            scroll.getViewport().setBackground(UITheme.PANEL_BG);
            scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            javax.swing.JScrollBar vBar = scroll.getVerticalScrollBar();
            vBar.setPreferredSize(new java.awt.Dimension(8, 0));
            vBar.setUnitIncrement(16);
            vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            javax.swing.JScrollBar hBar = scroll.getHorizontalScrollBar();
            hBar.setPreferredSize(new java.awt.Dimension(0, 8));
            hBar.setUnitIncrement(16);
            hBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            return scroll;
        }
    }

    private static class SeguimientoCellRenderer extends javax.swing.table.DefaultTableCellRenderer {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (column == 6) {
                javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
                actions.setBackground(isSelected ? UITheme.TOGGLE_ACTIVE : UITheme.PANEL_BG);

                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignE.EYE, "Ver detalles",
                        new Color(72, 160, 90)));
                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignP.PENCIL, "Editar",
                        new Color(200, 135, 20)));
                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignD.DELETE, "Eliminar",
                        new Color(210, 70, 70)));

                return actions;
            }

            setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));
            if (column == 0) {
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            }
            if (isSelected) {
                setBackground(UITheme.TOGGLE_ACTIVE);
                setForeground(UITheme.TEXT);
            } else {
                setBackground(row % 2 == 0 ? UITheme.PANEL_BG : new Color(248, 249, 251));
                setForeground(column == 4 ? uiForegroundForEstatus(String.valueOf(value))
                        : UITheme.TEXT);
            }
            return c;
        }

        private javax.swing.JButton createActionButton(org.kordamp.ikonli.Ikon icon, String tip, Color bg) {
            javax.swing.JButton button = new javax.swing.JButton(
                    org.kordamp.ikonli.swing.FontIcon.of(icon, 15, java.awt.Color.WHITE));
            button.setToolTipText(tip);
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            button.setBackground(bg);
            button.setForeground(java.awt.Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setPreferredSize(new java.awt.Dimension(30, 30));
            button.setMinimumSize(new java.awt.Dimension(30, 30));
            button.setMaximumSize(new java.awt.Dimension(30, 30));
            return button;
        }

        private java.awt.Color uiForegroundForEstatus(String estatus) {
            if ("Abierta".equals(estatus)) {
                return new Color(190, 120, 10);
            }
            if ("Cerrada".equals(estatus)) {
                return new Color(70, 140, 80);
            }
            return UITheme.TEXT;
        }
    }

    private static class TiposIncidenciasPanel extends javax.swing.JPanel {

        public TiposIncidenciasPanel() {
            setLayout(new java.awt.BorderLayout());
            setBackground(UITheme.BACKGROUND);

            add(createHeader(), java.awt.BorderLayout.NORTH);
            add(createTable(), java.awt.BorderLayout.CENTER);
        }

        private javax.swing.JPanel createHeader() {
            javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout());
            header.setOpaque(false);
            header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));

            javax.swing.JButton addButton = new javax.swing.JButton("Nuevo Tipo");
            addButton.setIcon(org.kordamp.ikonli.swing.FontIcon.of(
                    org.kordamp.ikonli.materialdesign2.MaterialDesignP.PLUS, 18, java.awt.Color.WHITE));
            addButton.setIconTextGap(10);
            addButton.setFont(UITheme.FONT_BUTTON);
            addButton.setBackground(UITheme.SIDEBAR_BG);
            addButton.setForeground(java.awt.Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.setBorderPainted(false);
            addButton.setOpaque(true);
            addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            addButton.setPreferredSize(new java.awt.Dimension(180, 44));
            addButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
            addButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_HOVER);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    addButton.setBackground(UITheme.SIDEBAR_BG);
                }
            });

            javax.swing.JPanel right = new javax.swing.JPanel(new java.awt.FlowLayout(
                    java.awt.FlowLayout.RIGHT, 0, 0));
            right.setOpaque(false);
            right.add(addButton);

            header.add(right, java.awt.BorderLayout.EAST);

            return header;
        }

        private javax.swing.JComponent createTable() {
            String[] columns = {
                    "#", "Nombre", "Descripción", "Acciones"
            };

            Object[][] data = {
                    {"1", "Conductual", "Comportamiento inadecuado del alumno.", null},
                    {"2", "Académica", "Problemas relacionados con el desempeño escolar.", null},
                    {"3", "Disciplinaria", "Faltas al reglamento interno de la institución.", null},
                    {"4", "Convivencia", "Conflictos entre estudiantes.", null}
            };

            javax.swing.JTable table = new javax.swing.JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setFont(UITheme.FONT_CARD_BODY);
            table.setForeground(UITheme.TEXT);
            table.setBackground(UITheme.PANEL_BG);
            table.setRowHeight(42);
            table.setFillsViewportHeight(true);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setGridColor(UITheme.BORDER);
            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            table.setSelectionBackground(UITheme.TOGGLE_ACTIVE);
            table.setSelectionForeground(UITheme.TEXT);
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setFont(UITheme.FONT_LABEL.deriveFont(java.awt.Font.BOLD));
            table.getTableHeader().setBackground(UITheme.SIDEBAR_BG);
            table.getTableHeader().setForeground(java.awt.Color.WHITE);
            table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 44));
            table.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, UITheme.PRIMARY));

            table.setDefaultRenderer(Object.class, new TipoIncidenciaCellRenderer());

            for (int i = 0; i < table.getColumnCount(); i++) {
                int width;
                switch (i) {
                    case 0: width = 45; break;
                    case 1: width = 240; break;
                    case 2: width = 500; break;
                    default: width = 160;
                }
                table.getColumnModel().getColumn(i).setPreferredWidth(width);
            }

            javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(table);
            scroll.getViewport().setBackground(UITheme.PANEL_BG);
            scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            javax.swing.JScrollBar vBar = scroll.getVerticalScrollBar();
            vBar.setPreferredSize(new java.awt.Dimension(8, 0));
            vBar.setUnitIncrement(16);
            vBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            javax.swing.JScrollBar hBar = scroll.getHorizontalScrollBar();
            hBar.setPreferredSize(new java.awt.Dimension(0, 8));
            hBar.setUnitIncrement(16);
            hBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = UITheme.BORDER;
                    this.trackColor = UITheme.PANEL_BG;
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
                    b.setPreferredSize(new java.awt.Dimension(0, 0));
                    b.setMinimumSize(new java.awt.Dimension(0, 0));
                    b.setMaximumSize(new java.awt.Dimension(0, 0));
                    return b;
                }
            });

            return scroll;
        }
    }

    private static class TipoIncidenciaCellRenderer extends javax.swing.table.DefaultTableCellRenderer {

        @Override
        public java.awt.Component getTableCellRendererComponent(
                javax.swing.JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (column == 3) {
                javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
                actions.setBackground(isSelected ? UITheme.TOGGLE_ACTIVE : UITheme.PANEL_BG);

                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignP.PENCIL, "Editar",
                        new Color(200, 135, 20)));
                actions.add(createActionButton(
                        org.kordamp.ikonli.materialdesign2.MaterialDesignD.DELETE, "Eliminar",
                        new Color(210, 70, 70)));

                return actions;
            }

            setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));
            if (column == 0) {
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            }
            if (isSelected) {
                setBackground(UITheme.TOGGLE_ACTIVE);
                setForeground(UITheme.TEXT);
            } else {
                setBackground(row % 2 == 0 ? UITheme.PANEL_BG : new Color(248, 249, 251));
                setForeground(UITheme.TEXT);
            }
            return c;
        }

        private javax.swing.JButton createActionButton(org.kordamp.ikonli.Ikon icon, String tip, Color bg) {
            javax.swing.JButton button = new javax.swing.JButton(
                    org.kordamp.ikonli.swing.FontIcon.of(icon, 15, java.awt.Color.WHITE));
            button.setToolTipText(tip);
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            button.setBackground(bg);
            button.setForeground(java.awt.Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setPreferredSize(new java.awt.Dimension(30, 30));
            button.setMinimumSize(new java.awt.Dimension(30, 30));
            button.setMaximumSize(new java.awt.Dimension(30, 30));
            return button;
        }
    }
}
