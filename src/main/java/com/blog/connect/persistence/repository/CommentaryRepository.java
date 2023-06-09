package com.blog.connect.persistence.repository;

import com.blog.connect.persistence.entity.CommentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** CommentaryRepository. */
@Repository
public interface CommentaryRepository extends JpaRepository<CommentaryEntity, String> {}
