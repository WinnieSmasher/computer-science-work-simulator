package io.github.winniesmasher.novamart.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TagCreateRequest(
        @NotBlank @Size(max = 40) String name,
        @NotBlank @Pattern(regexp = "^[a-z0-9-]+$") @Size(max = 60) String slug,
        @Size(max = 200) String description
) {
}

