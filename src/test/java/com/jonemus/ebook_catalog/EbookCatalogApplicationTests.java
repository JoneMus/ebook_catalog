package com.jonemus.ebook_catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.jonemus.ebook_catalog.services.Ebook_service;
import com.jonemus.ebook_catalog.data.Ebook;

@SpringBootTest
class EbookCatalogApplicationTests {
	Ebook_service ebook_service = new Ebook_service();


	@Test
	void test_add_ebook() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "name");
		ebook.put("format", "name");	
		Ebook response = ebook_service.add_new_ebook(ebook);
		assertEquals(ebook.get("author"), response.getAuthor());
		assertEquals(ebook.get("title"), response.getTitle());
		assertEquals(ebook.get("format"), response.getFormat());
		assertTrue(response.getId().length() > 0);
	}

	@Test
	void test_add_same_ebook_again() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "name");
		ebook.put("format", "name");	

		Ebook response = ebook_service.add_new_ebook(ebook);
		assertEquals(ebook.get("author"), response.getAuthor());
		assertEquals(ebook.get("title"), response.getTitle());
		assertEquals(ebook.get("format"), response.getFormat());
		assertTrue(response.getId().length() > 0);
		// Adding the same book again
		Ebook response_2 = ebook_service.add_new_ebook(ebook);
		assertEquals(null, response_2);
	}

	@Test
	void test_add_invalid_ebook_emptyValue() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "");
		ebook.put("format", "");
		boolean validation = ebook_service.validate_ebook_data(ebook);
		assertFalse(validation);
	}

	@Test
	void test_add_invalid_ebook_emptyFields() {
		Map<String, Object> ebook = new HashMap<>();
		boolean validation = ebook_service.validate_ebook_data(ebook);
		assertFalse(validation);
	}

	@Test
	void test_add_invalid_ebook_hasWhiteSpaces() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", " ");
		ebook.put("title", "  ");
		ebook.put("format", " ");
		boolean validation = ebook_service.validate_ebook_data(ebook);
		assertFalse(validation);
	}

	@Test
	void test_get_ebooks() {
		Map<String, Object> ebook_1 = new HashMap<>();
		ebook_1.put("author", "name1");
		ebook_1.put("title", "book1");
		ebook_1.put("format", "name");	
		ebook_service.add_new_ebook(ebook_1);
		Map<String, Object> ebook_2 = new HashMap<>();
		ebook_2.put("author", "name2");
		ebook_2.put("title", "book2");
		ebook_2.put("format", "name");	
		ebook_service.add_new_ebook(ebook_2);

		List<Ebook> ebooks = ebook_service.get_ebooks();

		assertTrue(ebooks.size() == 2);

		assertEquals(ebook_1.get("author"), ebooks.get(0).getAuthor());
		assertEquals(ebook_1.get("title"), ebooks.get(0).getTitle());
		assertEquals(ebook_1.get("format"), ebooks.get(0).getFormat());

		assertEquals(ebook_2.get("author"), ebooks.get(1).getAuthor());
		assertEquals(ebook_2.get("title"), ebooks.get(1).getTitle());
		assertEquals(ebook_2.get("format"), ebooks.get(1).getFormat());
	}	


	@Test
	void test_get_ebook_withID() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "name");
		ebook.put("format", "name");	
		Ebook response = ebook_service.add_new_ebook(ebook);

		assertEquals(ebook_service.get_ebook(response.getId()), response);
	}

	@Test
	void test_update_ebook() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "name");
		ebook.put("format", "name");	

		Map<String, Object> ebook_updated = new HashMap<>();
		ebook_updated.put("author", "new_name");
		ebook_updated.put("title", "new_book");
		ebook_updated.put("format", "new_format");	

		Ebook response = ebook_service.add_new_ebook(ebook);
		
		Ebook response_updated = ebook_service.update_ebook(response.getId(), ebook_updated);

		assertEquals(ebook_updated.get("author"), response_updated.getAuthor());
		assertEquals(ebook_updated.get("title"), response_updated.getTitle());
		assertEquals(ebook_updated.get("format"), response_updated.getFormat());
	}

	@Test
	void test_delete_ebook() {
		Map<String, Object> ebook = new HashMap<>();
		ebook.put("author", "name");
		ebook.put("title", "name");
		ebook.put("format", "name");
		Ebook response = ebook_service.add_new_ebook(ebook);

		assertTrue(ebook_service.ebook_deleted(response.getId()));
	}
}
