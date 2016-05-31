package pack.restserver.service;

import pack.restserver.entity.Cloth;

import java.util.List;

public interface ClothService {

    List<Cloth> getAll();
    Cloth getByID(long id);
    Cloth add(Cloth cloth);
    void delete(long id);
}
