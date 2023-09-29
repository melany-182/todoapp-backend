package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.LabelBl;
import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
import bo.edu.ucb.todoappbackend.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LabelApi {

    private final LabelBl labelBl;
    private final AuthBl authBl;

    @Autowired
    public LabelApi(LabelBl labelBl, AuthBl authBl) {
        this.labelBl = labelBl;
        this.authBl = authBl;
    }

    /** Endpoint que retorna todas las etiquetas de un usuario.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     */
    @GetMapping("/api/v1/labels")
    public ResponseDto<List<Label>> getAllLabels(@RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.getAllLabelsByUserId(authBl.getUserIdFromToken(token)));
        }
    }

    /** Endpoint que permite crear una etiqueta.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param label: La etiqueta a crear.
     */
    @PostMapping("/api/v1/labels")
    public ResponseDto<Label> createLabel(@RequestBody Label label, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            this.labelBl.createLabel(label);
            return new ResponseDto<>(label);
        }
    }

    /** Endpoint que permite modificar una etiqueta.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param labelId: El ID de la etiqueta a modificar.
     * @param label: La etiqueta con los nuevos datos.
     */
    @PutMapping("/api/v1/labels/{labelId}")
    public ResponseDto<Label> updateLabel(@PathVariable Long labelId, @RequestBody Label label, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.updateLabelById(labelId, label));
        }
    }

    /** Endpoint que permite eliminar una etiqueta.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param labelId: El ID de la etiqueta a eliminar.
     */
    @DeleteMapping("/api/v1/labels/{labelId}")
    public ResponseDto<String> deleteLabel(@PathVariable Long labelId, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            this.labelBl.deleteLabelById(labelId);
            return new ResponseDto<>("Etiqueta eliminada");
        }
    }
}
