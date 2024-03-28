package com.math_app;

public class ImageTextPair {
    private String imagePath;
    private String text;

    public ImageTextPair(String imagePath, String text) {
        this.imagePath = imagePath;
        this.text = text;
    }

    // Getters
    public String getImagePath() {
        return imagePath;
    }

    public String getText() {
        return text;
    }
}

