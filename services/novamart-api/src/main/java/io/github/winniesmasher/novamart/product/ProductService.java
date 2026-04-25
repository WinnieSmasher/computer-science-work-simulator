package io.github.winniesmasher.novamart.product;

import io.github.winniesmasher.novamart.common.BusinessException;
import io.github.winniesmasher.novamart.common.PageResult;
import io.github.winniesmasher.novamart.tag.ProductTagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductTagService tagService;

    public ProductService(ProductRepository repository, ProductTagService tagService) {
        this.repository = repository;
        this.tagService = tagService;
    }

    public ProductResponse create(ProductCreateRequest request) {
        tagService.ensureAllExist(request.tagIds());
        return ProductResponse.from(repository.saveNew(request));
    }

    public ProductResponse update(long id, ProductCreateRequest request) {
        tagService.ensureAllExist(request.tagIds());
        Product product = findRequired(id);
        return ProductResponse.from(repository.save(product.update(request)));
    }

    public ProductResponse get(long id) {
        return ProductResponse.from(findRequired(id));
    }

    public void delete(long id) {
        Product product = findRequired(id);
        repository.save(product.markDeleted());
    }

    public PageResult<ProductResponse> search(String keyword, int page, int size) {
        int safePage = Math.max(page, 1);
        int safeSize = Math.min(Math.max(size, 1), 100);
        String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase();

        List<ProductResponse> matched = repository.findAll().stream()
                .filter(product -> !product.deleted())
                .filter(product -> product.status() == ProductStatus.ACTIVE)
                .filter(product -> normalizedKeyword.isBlank()
                        || product.name().toLowerCase().contains(normalizedKeyword)
                        || product.description().toLowerCase().contains(normalizedKeyword))
                .map(ProductResponse::from)
                .toList();

        int from = Math.min((safePage - 1) * safeSize, matched.size());
        int to = Math.min(from + safeSize, matched.size());
        List<ProductResponse> items = matched.subList(from, to);
        boolean hasMore = to < matched.size();
        return new PageResult<>(items, safePage, safeSize, matched.size(), hasMore, !hasMore);
    }

    private Product findRequired(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("PRODUCT_NOT_FOUND", "product does not exist: " + id));
    }
}

