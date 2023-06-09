package com.blog.connect.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** UserEntity. */
@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "email", length = 32, unique = true)
  private String email;

  @Column(name = "username", length = 32)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled", columnDefinition = "boolean default false")
  private Boolean enabled;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
  private List<PostEntity> posts;

  @Column(name = "created_on")
  private LocalDateTime createdOn;
}
