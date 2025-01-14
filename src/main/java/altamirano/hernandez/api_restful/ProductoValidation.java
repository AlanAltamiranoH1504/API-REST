package altamirano.hernandez.api_restful;

import altamirano.hernandez.api_restful.entities.Producto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductoValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Producto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Producto producto = (Producto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", null, "Campo no validado correctamente");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "Campo no validado correctamente");
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().equals("")){
            errors.rejectValue("descripcion", null, "Campo no validado correctamente");
        }
        if (producto.getPrecio() <= 0){
            errors.rejectValue("precio", null, "Campo precio debe ser mayor a 0");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().equals("")){
            errors.rejectValue("nombre", null, "Campo no validado correctamente");
        }
    }
}
