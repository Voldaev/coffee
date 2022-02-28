package Test.coffee.controller;

import Test.coffee.dto.IngridDTO;
import Test.coffee.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Operation(description = "показывает текущий запас ингредиентов")
    @GetMapping(value = "/storage")
    public List<IngridDTO> supplies() {

        return storageService.readAll();
    }

    @Operation(description = "позволяет пополнить запас ингредиентов" +
            " или добавить новые")
    @PostMapping(value = "/storage")
    public void replenish(@RequestBody List<IngridDTO> income) {
        storageService.save(income);
    }

    @Operation(description = "удаляет ингридиент по имени (кавычки не нужны, сваггер врет!)")
    @DeleteMapping(value = "/storage")
    public void delete(@RequestBody String name){
        storageService.delete(name);
    }
}
