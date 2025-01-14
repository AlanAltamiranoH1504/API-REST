package altamirano.hernandez.api_restful.controllers;

import altamirano.hernandez.api_restful.entities.Producto;
import altamirano.hernandez.api_restful.services.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, Object> findById(@PathVariable int id){
        Producto producto = productoService.findById(id).orElse(null);
        Map<String, Object> json = new HashMap<>();

        if (producto != null){
            json.put("resultado", "producto encontrado");
            json.put("producto", producto);
        }else{
            json.put("resultado", "producto no encontrado");
        }
        return json;
    }

    //Metodo que guarda un registro
    @PostMapping("/")
    public Map<String, Object> save(@Valid @RequestBody Producto producto, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();

        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            json.put("errores", errores);
        }else{
            productoService.save(producto);
            json.put("result", producto);
        }
        return json;
    }

    //Metodo que actualiza un registro
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable int id, @Valid @RequestBody Producto producto, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();

        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            json.put("errores", errores);
        }else{
            Producto productoExistente = productoService.findById(id).orElse(null);
            if (productoExistente != null){
                productoExistente.setId(id);
                if (producto.getNombre() != null){
                    productoExistente.setNombre(producto.getNombre());
                }
                if (producto.getPrecio() != 0){
                    productoExistente.setPrecio(producto.getPrecio());
                }
                if (producto.getDescripcion() != null){
                    productoExistente.setDescripcion(producto.getDescripcion());
                }
                json.put("resultado", productoExistente);
            }
        }
        return json;
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
