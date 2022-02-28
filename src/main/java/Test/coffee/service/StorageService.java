package Test.coffee.service;

import Test.coffee.dto.IngridDTO;
import Test.coffee.model.StorageEntity;
import Test.coffee.repository.StorageEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StorageService {

    private final StorageEntityRepository storageEntityRepository;

    public StorageService(StorageEntityRepository storageEntityRepository) {
        this.storageEntityRepository = storageEntityRepository;
    }

    public List<IngridDTO> readAll() {
        List<StorageEntity> list = storageEntityRepository.findAll();
        ArrayList<IngridDTO> result = new ArrayList<>();
        for (StorageEntity entity : list) {
            result.add(IngridDTO.builder().name(entity.getName()).count(entity.getSaved()).build());
        }
        return result;
    }

    public void save(List<IngridDTO> income) {
        StorageEntity entity;
        for (IngridDTO ingrid : income) {
            Optional<StorageEntity> optional = storageEntityRepository.findById(ingrid.getName());
            if (optional.isPresent()) {
                entity = optional.get();
                entity.setSaved(entity.getSaved()+ingrid.getCount());
                storageEntityRepository.save(entity);
            } else {
                entity = new StorageEntity();
                entity.setName(ingrid.getName());
                entity.setSaved(ingrid.getCount());
                storageEntityRepository.save(entity);
            }
        }
    }

    public void delete(String name) {
        storageEntityRepository.deleteById(name);
    }
}
