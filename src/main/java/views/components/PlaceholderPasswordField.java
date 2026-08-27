package views.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;

public class PlaceholderPasswordField extends JPasswordField implements FocusListener {

    private String placeholder;
    private Color placeholderColor = new Color(170, 170, 170);
    private Color textColor = new Color(51, 51, 51);
    private boolean showingPlaceholder = false;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
        initPlaceholder();
    }

    private void initPlaceholder() {
        addFocusListener(this);
        setPlaceholderText(placeholder);
    }

    public void setPlaceholderText(String text) {
        this.placeholder = text;
        if (getPassword().length == 0) {
            showingPlaceholder = true;
            super.setText(placeholder);
            setForeground(placeholderColor);
        }
    }

    @Override
    public void setText(String t) {
        if (t == null || t.isEmpty()) {
            showingPlaceholder = true;
            super.setText(placeholder);
            setForeground(placeholderColor);
        } else {
            showingPlaceholder = false;
            super.setText(t);
            setForeground(textColor);
        }
    }

    public String getPasswordDirect() {
        if (showingPlaceholder) {
            return "";
        }
        return new String(getPassword());
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (showingPlaceholder) {
            showingPlaceholder = false;
            super.setText("");
            setForeground(textColor);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getPassword().length == 0) {
            showingPlaceholder = true;
            super.setText(placeholder);
            setForeground(placeholderColor);
        }
    }
}
