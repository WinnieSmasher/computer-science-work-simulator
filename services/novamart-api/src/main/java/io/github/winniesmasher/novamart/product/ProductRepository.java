package io.github.winniesmasher.novamart.product;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final AtomicLong idSequence = new AtomicLong(1);
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    public Product saveNew(ProductCreateRequest request) {
        long id = idSequence.getAndIncrement();
        Instant now = Instant.now();
        Product product = new Product(
                id,
                request.name(),
                request.description(),
                request.priceCents(),
                request.status(),
                List.copyOf(request.tagIds()),
                false,
                now,
                now
        );
        products.put(id, product);
        return product;
    }

    public Product save(Product product) {
        products.put(product.id(), product);
        return product;
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values()).stream()
                .sorted((left, right) -> Long.compare(left.id(), right.id()))
                .toList();
    }
}

