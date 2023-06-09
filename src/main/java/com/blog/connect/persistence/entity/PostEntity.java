package com.blog.connect.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostEntity.
 */
@Data
@Table(name = "posts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
  @Id
  @GeneratedValue
  @Column(name = "post_id")
  private Integer id;

  @Column(name = "theme")
  private String theme;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, insertable = false, updatable = false)
  private UserEntity creator;

  @Column(name = "creator_id")
  private Integer creatorId;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<CommentaryEntity> commentaries;

  @Column(name = "created_on")
  private LocalDateTime createdOn;
}
