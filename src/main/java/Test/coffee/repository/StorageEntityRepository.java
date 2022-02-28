package Test.coffee.repository;

import Test.coffee.model.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageEntityRepository extends JpaRepository<StorageEntity, String> {
}