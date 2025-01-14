package altamirano.hernandez.api_restful.services;

import altamirano.hernandez.api_restful.entities.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public abstract List<Producto> findAll();
    public abstract Optional<Producto> findById(int id);
    public abstract Producto save(Producto producto);
    public abstract void deleteById(int id);
    public abstract void deleteByProducto(Producto producto);
}
