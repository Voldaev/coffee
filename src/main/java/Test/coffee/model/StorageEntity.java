package Test.coffee.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "storage")
public class StorageEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "saved")
    private Integer saved;
}
