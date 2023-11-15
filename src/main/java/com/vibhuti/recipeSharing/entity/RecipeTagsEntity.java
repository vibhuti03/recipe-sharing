package com.vibhuti.recipeSharing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_tags")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeTagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String tagName;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;
}
