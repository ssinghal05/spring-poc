package io.pivotal.rest.springpoc.repository;

import io.pivotal.rest.springpoc.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
