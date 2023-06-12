package com.example.ReactiveReddit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildrenData {
    @Id
    private String id;
    @JsonProperty("author_fullname")
    private String authorName;
    @JsonProperty("subreddit")
    private String subReddit;
    @JsonProperty("selftext")
    private String text;
}
