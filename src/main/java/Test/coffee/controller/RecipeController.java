package Test.coffee.controller;

import Test.coffee.dto.RecipeDTO;
import Test.coffee.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Operation(description = "добавляет новый рецепт (поле id не требуется)")
    @PostMapping(value = "/recipe")
    public void createRecipe(@RequestBody RecipeDTO recipeDTO) {
        recipeService.createRecipe(recipeDTO);
    }

    @Operation(description = "показывает все доступные рецепты")
    @GetMapping(value = "/recipe")
    public List<RecipeDTO> show() {
        return recipeService.show();
    }

    @Operation(description = "удаляет рецепт по id")
    @DeleteMapping(value = "/recipe")
    public void delete(@RequestBody Integer id) {
        recipeService.delete(id);
    }

}
