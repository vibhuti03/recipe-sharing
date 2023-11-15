package com.vibhuti.recipeSharing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "recipe_tags_map",
//    joinColumns = {
//            @JoinColumn(name = "recipe_id", referencedColumnName = "id")
//    },
//    inverseJoinColumns = {
//            @JoinColumn(name = "tag_name", referencedColumnName = "tagName")
//    })
    private Set<TagsEntity> tags = new HashSet<>();

    private String recipeFileLocation;

}
