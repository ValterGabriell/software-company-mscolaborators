package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("colaborator")
public class ColaboratorController {

    private final ColaboratorService colaboratorService;

    public ColaboratorController(ColaboratorService colaboratorService) {
        this.colaboratorService = colaboratorService;
    }

    @PostMapping(value = "/create", params = {"cnpj"})
    public ResponseEntity<Colaborator> createNewColaborator(@RequestBody Colaborator colaborator, @RequestParam Long cnpj, @RequestParam BigDecimal income) {
        Colaborator newColaborator = colaboratorService.createNewColaborator(colaborator, cnpj, income);
        return new ResponseEntity<>(newColaborator, HttpStatus.CREATED);
    }

    @GetMapping(params = {"cpf"})
    public ResponseEntity<Response<Colaborator>> getColaboratorById(@RequestParam("cpf") Long cpf) {
        Response<Colaborator> colaborator = colaboratorService.findColaboratorById(cpf);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }

}
