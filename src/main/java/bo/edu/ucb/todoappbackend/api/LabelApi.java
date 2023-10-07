package bo.edu.ucb.todoappbackend.api;

import bo.edu.ucb.todoappbackend.bl.AuthBl;
import bo.edu.ucb.todoappbackend.bl.LabelBl;
import bo.edu.ucb.todoappbackend.dto.LabelDto;
import bo.edu.ucb.todoappbackend.dto.ResponseDto;
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

    /** Endpoint que retorna el detalle de una etiqueta por ID.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param labelId: El ID de la etiqueta a obtener.
     */
    @GetMapping("/api/v1/labels/{labelId}")
    public ResponseDto<LabelDto> getLabelById(@PathVariable Long labelId, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.getLabelById(labelId));
        }
    }

    /** Endpoint que retorna todas las etiquetas de un usuario.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     */
    @GetMapping("/api/v1/labels")
    public ResponseDto<List<LabelDto>> getAllLabels(@RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.getAllLabelsByUserId(authBl.getUserIdFromToken(token)));
        }
    }

    /** Endpoint que permite crear una etiqueta.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param labelDto: La etiqueta a crear.
     */
    @PostMapping("/api/v1/labels")
    public ResponseDto<LabelDto> createLabel(@RequestBody LabelDto labelDto, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.createLabel(labelDto));
        }
    }

    /** Endpoint que permite modificar una etiqueta.
     * @param token: El token JWT que se obtuvo al autenticar al usuario.
     * @param labelId: El ID de la etiqueta a modificar.
     * @param labelDto: La etiqueta con los nuevos datos.
     */
    @PutMapping("/api/v1/labels/{labelId}")
    public ResponseDto<LabelDto> updateLabel(@PathVariable Long labelId, @RequestBody LabelDto labelDto, @RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return new ResponseDto<>("TODO-0002", "Token inválido");
        }
        else {
            return new ResponseDto<>(this.labelBl.modifyLabelById(labelId, labelDto));
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
