package com.blog.connect.persistence.repository;

import com.blog.connect.persistence.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PostRepository.
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
  List<PostEntity> findAllByCreatorId(String creatorId);
}
