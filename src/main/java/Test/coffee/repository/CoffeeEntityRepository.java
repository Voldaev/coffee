package Test.coffee.repository;

import Test.coffee.model.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeEntityRepository extends JpaRepository<CoffeeEntity, Integer> {

    @Query(value = "SELECT * FROM coffee WHERE readytime > current_timestamp",nativeQuery = true)
    List<CoffeeEntity> interfered();

}