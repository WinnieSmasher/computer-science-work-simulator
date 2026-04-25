package io.github.winniesmasher.novamart.product;

import java.time.Instant;
import java.util.List;

public record Product(
        long id,
        String name,
        String description,
        long priceCents,
        ProductStatus status,
        List<Long> tagIds,
        boolean deleted,
        Instant createdAt,
        Instant updatedAt
) {
    public Product update(ProductCreateRequest request) {
        return new Product(
                id,
                request.name(),
                request.description(),
                request.priceCents(),
                request.status(),
                List.copyOf(request.tagIds()),
                deleted,
                createdAt,
                Instant.now()
        );
    }

    public Product markDeleted() {
        return new Product(id, name, description, priceCents, status, tagIds, true, createdAt, Instant.now());
    }
}

