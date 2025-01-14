package altamirano.hernandez.api_restful.controllers;

import altamirano.hernandez.api_restful.entities.Producto;
import altamirano.hernandez.api_restful.services.IProductoService;
import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    //Inyectamos el service
    @Autowired
    private IProductoService productoService;

    @GetMapping("/prueba")
    public String prueba(){
        return "Prueba de API";
    }

    //Metodo que trae todos los registros
    @GetMapping("/getAll")
    public List<Producto> findAll(){
        List<Producto> productos = productoService.findAll();
        return productos;
    }

    //Metodo que trae un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()){
            return ResponseEntity.ok(producto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //Metodo que guarda un registro
    @PostMapping("/")
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.save(producto));
    }

    //Metodo que actualiza un registro
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable int id, @RequestBody Producto producto){
        Producto productoExistente = productoService.findById(id).orElse(null);
        if (productoExistente != null){
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setDescripcion(producto.getDescripcion());
            return ResponseEntity.ok(productoService.save(productoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    //Metodo que elimina un registro
    @PostMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable int id){
        Producto producto = productoService.findById(id).orElse(null);
        if (producto != null){
            productoService.deleteById(id);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
