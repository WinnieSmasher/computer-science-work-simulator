package io.github.winniesmasher.novamart.tag;

public record TagResponse(long id, String name, String slug, String description) {

    public static TagResponse from(ProductTag tag) {
        return new TagResponse(tag.id(), tag.name(), tag.slug(), tag.description());
    }
}

