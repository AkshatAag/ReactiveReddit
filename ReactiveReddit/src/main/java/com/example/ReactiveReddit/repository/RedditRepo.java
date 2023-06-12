package com.example.ReactiveReddit.repository;

import com.example.ReactiveReddit.entity.ChildrenData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RedditRepo extends ReactiveMongoRepository<ChildrenData, String> {
    Flux<ChildrenData> findByAuthorName(String authorName);

    Flux<ChildrenData> findByTextRegex(String keyword);


}
