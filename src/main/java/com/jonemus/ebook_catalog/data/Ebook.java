package com.jonemus.ebook_catalog.data;
import java.util.UUID;

public class Ebook {
    
    private final UUID id = UUID.randomUUID();
    private String author;
    private String title;
    private String format;

    public Ebook(){}
    
    public Ebook(String author, String title, String format) {
        this.author = author;
        this.title = title;
        this.format = format;
    }

    public boolean equals(String author, String title, String format) {
        if (this.author.equals(author) && 
            this.title.equals(title) && 
            this.format.equals(format)) {
            return true;
        } 
        return false;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getFormat() {
        return this.format;
    }

    public String getId() {
        return this.id.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
