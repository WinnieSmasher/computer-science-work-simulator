package io.github.winniesmasher.novamart.tag;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductTagRepository {

    private final AtomicLong idSequence = new AtomicLong(1);
    private final Map<Long, ProductTag> tags = new ConcurrentHashMap<>();

    public ProductTag saveNew(String name, String slug, String description) {
        long id = idSequence.getAndIncrement();
        Instant now = Instant.now();
        ProductTag tag = new ProductTag(id, name, slug, description, now, now);
        tags.put(id, tag);
        return tag;
    }

    public ProductTag save(ProductTag tag) {
        tags.put(tag.id(), tag);
        return tag;
    }

    public List<ProductTag> findAll() {
        return new ArrayList<>(tags.values()).stream()
                .sorted((left, right) -> Long.compare(left.id(), right.id()))
                .toList();
    }

    public Optional<ProductTag> findById(long id) {
        return Optional.ofNullable(tags.get(id));
    }

    public boolean existsById(long id) {
        return tags.containsKey(id);
    }

    public boolean existsBySlug(String slug) {
        return tags.values().stream().anyMatch(tag -> tag.slug().equals(slug));
    }
}

