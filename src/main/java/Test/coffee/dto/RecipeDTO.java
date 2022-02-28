package Test.coffee.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class RecipeDTO {
    private Integer id;
    private String name;
    private Integer duration;
    private Set<IngridDTO> ingrs;
}
