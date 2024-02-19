package hu.bca.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookInfoDTO {

    @JsonProperty("first_publish_date")
    private String firstPublishDate;
}
