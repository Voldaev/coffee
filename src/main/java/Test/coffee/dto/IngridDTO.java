package Test.coffee.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class IngridDTO {
    private String name;
    private Integer count;
}
