package io.github.winniesmasher.novamart.tag;

import io.github.winniesmasher.novamart.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/tags")
public class ProductTagController {

    private final ProductTagService service;

    public ProductTagController(ProductTagService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TagResponse> create(@Valid @RequestBody TagCreateRequest request) {
        return ApiResponse.ok(service.create(request));
    }

    @GetMapping
    public ApiResponse<List<TagResponse>> list() {
        return ApiResponse.ok(service.list());
    }
}

