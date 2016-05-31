package pack.restserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.restserver.entity.Cloth;
import pack.restserver.repository.ClothRepository;

import java.util.List;

@Service
public class ClothServiceImpl implements ClothService{

    @Autowired
    private ClothRepository repository;

    public List<Cloth> getAll() {
        return repository.findAll();
    }

    public Cloth getByID(long id) {
        return repository.getOne(id);
    }

    public Cloth add(Cloth cloth) {
        return repository.saveAndFlush(cloth);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
