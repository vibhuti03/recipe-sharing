package com.vibhuti.recipeSharing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tags")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<RecipeEntity> recipes;
}
