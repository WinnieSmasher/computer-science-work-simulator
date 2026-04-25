package io.github.winniesmasher.novamart.tag;

import io.github.winniesmasher.novamart.common.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductTagService {

    private final ProductTagRepository repository;

    public ProductTagService(ProductTagRepository repository) {
        this.repository = repository;
    }

    public TagResponse create(TagCreateRequest request) {
        if (repository.existsBySlug(request.slug())) {
            throw new BusinessException("TAG_SLUG_EXISTS", "tag slug already exists: " + request.slug());
        }
        return TagResponse.from(repository.saveNew(request.name(), request.slug(), request.description()));
    }

    public List<TagResponse> list() {
        return repository.findAll().stream().map(TagResponse::from).toList();
    }

    public void ensureAllExist(Collection<Long> tagIds) {
        for (Long tagId : tagIds) {
            if (!repository.existsById(tagId)) {
                throw new BusinessException("TAG_NOT_FOUND", "tag does not exist: " + tagId);
            }
        }
    }
}

