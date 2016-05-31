package pack.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.restserver.entity.Cloth;

public interface ClothRepository extends JpaRepository<Cloth, Long>{

}
