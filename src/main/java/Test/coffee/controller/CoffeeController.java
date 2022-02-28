package Test.coffee.controller;

import Test.coffee.dto.CoffeeDTO;
import Test.coffee.service.CoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @Operation(description = "заваривает кофе по рецепту %номер рецепта, " +
            "если хватает ингредиентов и кофеварка свободна")
    @PostMapping(value = "/coffee")
    public void make(@RequestBody Integer id) {
        coffeeService.make(id);
    }


    @Operation(description = "все изготовленные напитки (в т.ч. в процессе)")
    @GetMapping(value = "/coffee")
    public List<CoffeeDTO> all() {
        return coffeeService.stats();
    }

}
