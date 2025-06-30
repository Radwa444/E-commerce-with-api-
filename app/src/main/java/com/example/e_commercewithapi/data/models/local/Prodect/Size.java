package com.example.e_commercewithapi.data.models.local.Prodect;

public class Size {
    private String size;
    private boolean selected;

    public Size(String size, Boolean selected) {
        this.size = size;
        this.selected = selected;
    }

    public String getSize() {
        return size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
