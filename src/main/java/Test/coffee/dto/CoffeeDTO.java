package Test.coffee.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CoffeeDTO {
    private Integer id;
    private String name;
    private LocalDateTime ready;
}
