package com.jonemus.ebook_catalog.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jonemus.ebook_catalog.data.Ebook;
import com.jonemus.ebook_catalog.services.Ebook_service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class Ebook_controller {
    
    @Autowired
    Ebook_service ebookService;

    @PostMapping("ebooks")
    public ResponseEntity<Ebook> add_ebook(@RequestBody Map<String, Object> ebook) {
        if (!ebookService.validate_ebook_data(ebook)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Ebook new_ebook = ebookService.add_new_ebook(ebook);
        
        if (new_ebook != null) {
            return new ResponseEntity<>(new_ebook , HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("ebooks")
    public ResponseEntity<List<Ebook>> get_ebooks() {
        return new ResponseEntity<>(ebookService.get_ebooks(), HttpStatus.OK);
    }
    
    @GetMapping("ebooks/{ebook_id}")
    public ResponseEntity<Ebook> get_ebook(@PathVariable String ebook_id) {
        Ebook ebook = ebookService.get_ebook(ebook_id);
        if (ebook != null) {
            return new ResponseEntity<Ebook>(ebook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("ebooks/{ebook_id}")
    public ResponseEntity<Ebook> update_ebook(@PathVariable String ebook_id, @RequestBody Map<String, Object> ebook) {
        if (!ebookService.validate_ebook_data(ebook)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Ebook updated = ebookService.update_ebook(ebook_id, ebook);
        if (updated != null) {
            return new ResponseEntity<>(updated ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("ebooks/{ebook_id}")
    public ResponseEntity<String> delete_ebook(@PathVariable String ebook_id) {
        if (ebookService.ebook_deleted(ebook_id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
