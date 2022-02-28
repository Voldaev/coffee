package Test.coffee.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "recipeEntity")
    private Set<IngridEntity> ingrid;

}
