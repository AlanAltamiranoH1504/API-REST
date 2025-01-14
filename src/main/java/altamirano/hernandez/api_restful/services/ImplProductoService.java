package altamirano.hernandez.api_restful.services;

import altamirano.hernandez.api_restful.entities.Producto;
import altamirano.hernandez.api_restful.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImplProductoService implements IProductoService{
    //Inyectamos el repositorio
    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        List<Producto> productos = (List<Producto>) iProductoRepository.findAll();
        return productos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(int id) {
        Optional<Producto> producto = iProductoRepository.findById(id);
        return producto;
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        iProductoRepository.save(producto);
        return producto;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        iProductoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByProducto(Producto producto) {
        if (iProductoRepository.existsById(producto.getId())){
            iProductoRepository.deleteById(producto.getId());
        }else{
            System.out.println("Producto no encontrado");
        }
    }
}
