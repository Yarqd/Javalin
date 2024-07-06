package org.example.hexlet.dto;

public class BasePage {
    private String flash;

    public BasePage() {}

    public BasePage(String flash) {
        this.flash = flash;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }
}
