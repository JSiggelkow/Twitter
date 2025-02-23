package de.dhbw.twitterbackend.dto;

import java.util.List;

public record StatusDTO(PostDTO parent, List<PostDTO> comments) {
}
