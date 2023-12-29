package com.jonemus.ebook_catalog.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jonemus.ebook_catalog.data.Ebook;
import com.jonemus.ebook_catalog.data.Ebooklist;

@Service
public class Ebook_service {
    
    Ebooklist ebooks = new Ebooklist();

    public Ebook add_new_ebook(Map<String, Object> ebook) {
        if (exists(
            ebook.get("author").toString(), 
            ebook.get("title").toString(), 
            ebook.get("format").toString())) {
            return null;
        } 
        Ebook new_ebook = new Ebook(
            ebook.get("author").toString(), 
            ebook.get("title").toString(), 
            ebook.get("format").toString()
        );
        ebooks.add_ebook(new_ebook);
        return new_ebook;
    }

    private boolean exists(String author, String title, String format) { 
            for (Ebook eb : ebooks.get_ebooks()) {
                if (eb.equals(author, title, format)) {
                    return true;
                }     
            }
        return false;
    }

    public List<Ebook> get_ebooks() {
        return ebooks.get_ebooks();
    }

    public Ebook get_ebook(String ebook_id) {
        return ebooks.get_ebook(ebook_id);  
    }

    public Ebook update_ebook(String ebook_id, Map<String, Object> update) {
        Ebook ebook = ebooks.get_ebook(ebook_id);
        if (ebook == null) {
            return null;
        }
        ebook.setAuthor(update.get("author").toString());
        ebook.setTitle(update.get("title").toString());
        ebook.setFormat(update.get("format").toString());
        return ebook;
    }

    public boolean ebook_deleted(String ebook_id) {
        return ebooks.remove(ebook_id);
    }

    public boolean validate_ebook_data(Map<String, Object> ebook) {
        if (
            !ebook.values().contains(ebook.get("author")) &&
            !ebook.values().contains(ebook.get("title")) &&
            !ebook.values().contains(ebook.get("format"))
            ) {
            return false;
        }
        else if (
            ebook.get("author").toString() == "" || 
            ebook.get("title").toString() == "" || 
            ebook.get("format").toString() == ""
            ) {
            return false;
        }
        else if (
            ebook.get("author").toString().trim().isEmpty()|| 
            ebook.get("title").toString().trim().isEmpty() || 
            ebook.get("format").toString().trim().isEmpty()
        ){
            return false;
        }
        return true;
    }
}
