package io.github.winniesmasher.novamart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = NovaMartApplication.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void supportsProductCrudSearchAndLogicalDelete() throws Exception {
        mockMvc.perform(post("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Lunar Notebook",
                                  "description": "Notebook for space science field notes",
                                  "priceCents": 1299,
                                  "status": "ACTIVE",
                                  "tagIds": []
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.data.name").value("Lunar Notebook"))
                .andExpect(jsonPath("$.data.deleted").value(false));

        mockMvc.perform(get("/api/products").param("keyword", "Lunar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items[*].name", hasItem("Lunar Notebook")))
                .andExpect(jsonPath("$.data.hasMore").value(false))
                .andExpect(jsonPath("$.data.isEnd").value(true));

        mockMvc.perform(delete("/api/admin/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"));

        mockMvc.perform(get("/api/products").param("keyword", "Lunar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items[*].name", not(hasItem("Lunar Notebook"))));
    }
}

