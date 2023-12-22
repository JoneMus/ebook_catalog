package com.jonemus.ebook_catalog;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.HashMap;
// import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.jonemus.ebook_catalog.services.Ebook_service;
// import com.jonemus.ebook_catalog.data.Ebook;

@SpringBootTest
class EbookCatalogApplicationTests {
	Ebook_service ebook_service = new Ebook_service();

	@Test
	void contextLoads() {
	}


	@Test
	void test_add_ebook() {
		// Map<String, Object> ebook = new HashMap<>();
		// ebook.put("author", "name");
		// Ebook response = ebook_service.add_new_ebook(ebook);
		// assertEquals(ebook, response);

		// ebook.put("title", "name");
		// ebook.put("format", "name");	

		// response = ebook_service.add_new_ebook(ebook);
		// assertEquals(ebook, response);
	}

	@Test
	void test_get_ebooks() {

	}


}
