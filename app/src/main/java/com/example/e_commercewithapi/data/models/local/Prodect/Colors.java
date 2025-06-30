package com.example.e_commercewithapi.data.models.local.Prodect;

public class Colors {
    private String color;
    private boolean selected;

    public Colors(String color, Boolean selected) {
        this.color = color;
        this.selected = selected;
    }

    public String getColor() {
        return color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
