package io.github.winniesmasher.novamart.product;

import io.github.winniesmasher.novamart.common.ApiResponse;
import io.github.winniesmasher.novamart.common.PageResult;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/api/admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.ok(service.create(request));
    }

    @GetMapping("/api/admin/products/{id}")
    public ApiResponse<ProductResponse> get(@PathVariable long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PutMapping("/api/admin/products/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable long id, @Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/api/admin/products/{id}")
    public ApiResponse<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ApiResponse.ok();
    }

    @GetMapping("/api/products")
    public ApiResponse<PageResult<ProductResponse>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ApiResponse.ok(service.search(keyword, page, size));
    }
}

