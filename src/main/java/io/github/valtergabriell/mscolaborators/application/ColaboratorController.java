package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("colaborator")
public class ColaboratorController {

    private final ColaboratorService colaboratorService;

    public ColaboratorController(ColaboratorService colaboratorService) {
        this.colaboratorService = colaboratorService;
    }

    @PostMapping(value = "/create", params = {"cnpj"})
    public ResponseEntity<Colaborators> createNewColaborator(@RequestBody Colaborators colaborators, @RequestParam Long cnpj) {
        Colaborators newColaborator = colaboratorService.createNewColaborator(colaborators, cnpj);
        return new ResponseEntity<>(newColaborator, HttpStatus.CREATED);
    }
}
