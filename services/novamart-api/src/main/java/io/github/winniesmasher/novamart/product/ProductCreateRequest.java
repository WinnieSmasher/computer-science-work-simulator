package io.github.winniesmasher.novamart.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record ProductCreateRequest(
        @NotBlank @Size(max = 80) String name,
        @NotBlank @Size(max = 500) String description,
        @Min(1) long priceCents,
        @NotNull ProductStatus status,
        @Size(max = 20) List<@NotNull Long> tagIds
) {
    public ProductCreateRequest {
        tagIds = tagIds == null ? List.of() : Collections.unmodifiableList(new ArrayList<>(tagIds));
    }
}
