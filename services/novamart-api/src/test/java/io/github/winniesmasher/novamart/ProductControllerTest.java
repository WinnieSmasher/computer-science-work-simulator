package io.github.winniesmasher.novamart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = NovaMartApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    void adminProductListIncludesDraftsAndCanExposeDeletedItems() throws Exception {
        createProduct("Visible Lamp", "Active item for storefront", "ACTIVE");
        createProduct("Draft Probe", "Back office draft item", "DRAFT");
        createProduct("Archived Cable", "Deleted item for diagnosis", "ACTIVE");

        mockMvc.perform(delete("/api/admin/products/3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/admin/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items", hasSize(2)))
                .andExpect(jsonPath("$.data.items[*].name", hasItem("Visible Lamp")))
                .andExpect(jsonPath("$.data.items[*].name", hasItem("Draft Probe")))
                .andExpect(jsonPath("$.data.items[*].name", not(hasItem("Archived Cable"))));

        mockMvc.perform(get("/api/admin/products").param("includeDeleted", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.items", hasSize(3)))
                .andExpect(jsonPath("$.data.items[*].name", hasItem("Archived Cable")))
                .andExpect(jsonPath("$.data.items[*].deleted", hasItem(true)));
    }

    @Test
    void malformedProductRequestsUseSharedApiEnvelope() throws Exception {
        mockMvc.perform(post("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Broken Probe",
                                  "description": "Invalid enum should still use the API envelope",
                                  "priceCents": 1299,
                                  "status": "PUBLISHED",
                                  "tagIds": []
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("REQUEST_BODY_INVALID"))
                .andExpect(jsonPath("$.data").doesNotExist());

        mockMvc.perform(get("/api/products").param("page", "not-a-number"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("REQUEST_PARAMETER_INVALID"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    private void createProduct(String name, String description, String status) throws Exception {
        mockMvc.perform(post("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "%s",
                                  "description": "%s",
                                  "priceCents": 1299,
                                  "status": "%s",
                                  "tagIds": []
                                }
                                """.formatted(name, description, status)))
                .andExpect(status().isCreated());
    }
}
