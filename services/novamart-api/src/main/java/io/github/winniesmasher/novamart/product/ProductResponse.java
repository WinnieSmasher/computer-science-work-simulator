package io.github.winniesmasher.novamart.product;

import java.util.List;

public record ProductResponse(
        long id,
        String name,
        String description,
        long priceCents,
        ProductStatus status,
        List<Long> tagIds,
        boolean deleted
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.description(),
                product.priceCents(),
                product.status(),
                product.tagIds(),
                product.deleted()
        );
    }
}

