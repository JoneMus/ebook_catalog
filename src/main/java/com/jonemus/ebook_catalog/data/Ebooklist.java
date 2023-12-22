package com.jonemus.ebook_catalog.data;

import java.util.ArrayList;
import java.util.List;

public class Ebooklist {
    
    private List<Ebook> ebooks;

    public Ebooklist() {
        ebooks = new ArrayList<>();
    }

    public void add_ebook(Ebook ebook) {
        ebooks.add(ebook);
    }

    public List<Ebook> get_ebooks() {
        return ebooks;
    }

    public Ebook get_ebook(String ebook_id) {
        for (Ebook ebook : ebooks) {
            if (ebook.getId().equals(ebook_id)) {
                return ebook;
            }
        }
        return null;
    }

    public boolean remove(String ebook_id) {
        Ebook ebook = get_ebook(ebook_id);
        if (ebook != null) {
            ebooks.remove(ebook);
            return true;
        }
        return false;
    }
}
