package views.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField implements FocusListener {

    private String placeholder;
    private Color placeholderColor = new Color(170, 170, 170);
    private Color textColor = new Color(51, 51, 51);
    private boolean showingPlaceholder = false;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        initPlaceholder();
    }

    private void initPlaceholder() {
        addFocusListener(this);
        setPlaceholderText(placeholder);
    }

    public void setPlaceholderText(String text) {
        this.placeholder = text;
        if (getText().isEmpty()) {
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

    public String getTextDirect() {
        if (showingPlaceholder) {
            return "";
        }
        return super.getText();
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
        if (getText().isEmpty()) {
            showingPlaceholder = true;
            super.setText(placeholder);
            setForeground(placeholderColor);
        }
    }
}
