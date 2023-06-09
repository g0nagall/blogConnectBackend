package com.blog.connect.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentaryEntity.
 */
@Data
@Table(name = "commentary")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentaryEntity {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, insertable = false, updatable = false)
  private UserEntity creator;

  @Column(name = "creator_id")
  private Integer creatorId;

  @Column(name = "commentary")
  private String commentary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false, insertable = false, updatable = false)
  private PostEntity post;

  @Column(name = "post_id")
  private Integer postId;

  @Column(name = "created_on")
  private LocalDateTime createdOn;
}
