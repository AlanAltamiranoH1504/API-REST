package altamirano.hernandez.api_restful.repositories;

import altamirano.hernandez.api_restful.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto, Integer> {
}
