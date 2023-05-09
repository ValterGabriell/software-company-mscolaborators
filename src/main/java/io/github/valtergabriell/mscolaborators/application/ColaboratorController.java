package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping(value = "/lead", params = {"cpf"})
    public ResponseEntity<Response<Long>> getLead(@RequestParam("cpf") Long cpf) {
        Response<Long> leadId = colaboratorService.findLeadIdByColaborator(cpf);
        return new ResponseEntity<>(leadId, HttpStatus.OK);
    }

    @GetMapping(value = "/inactives")
    public ResponseEntity<List<Colaborator>> findAllInactivesColaborators() {
        List<Colaborator> allInactivesColaborators = colaboratorService.findAllInactivesColaborators();
        return new ResponseEntity<>(allInactivesColaborators, HttpStatus.OK);
    }

    @GetMapping(value = "/actives")
    public ResponseEntity<List<Colaborator>> findAllActivesColaborators() {
        List<Colaborator> allInactivesColaborators = colaboratorService.findAllActivesColaborators();
        return new ResponseEntity<>(allInactivesColaborators, HttpStatus.OK);
    }

    @PutMapping(value = "/name", params = {"cpf", "name"})
    public ResponseEntity<Response<Colaborator>> updateName(@RequestParam("cpf") Long cpf, @RequestParam("name") String name) {
        Response<Colaborator> colaborator = colaboratorService.updateColaboratorName(cpf, name);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }

    @PutMapping(value = "/email", params = {"cpf", "email"})
    public ResponseEntity<Response<Colaborator>> updateEmail(@RequestParam("cpf") Long cpf, @RequestParam("email") String email) {
        Response<Colaborator> colaborator = colaboratorService.updateColaboratorEmail(cpf, email);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }

    @PutMapping(value = "/phone", params = {"cpf", "phone"})
    public ResponseEntity<Response<Colaborator>> updatePhone(@RequestParam("cpf") Long cpf, @RequestParam("phone") String phone) {
        Response<Colaborator> colaborator = colaboratorService.updateColaboratorPhone(cpf, phone);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }

    @PutMapping(value = "/lead", params = {"cpf", "lead"})
    public ResponseEntity<Response<Colaborator>> updateLead(@RequestParam("cpf") Long cpf, @RequestParam("lead") Long lead) {
        Response<Colaborator> colaborator = colaboratorService.updateColaboratorLead(cpf, lead);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }

    @PutMapping(value = "/status", params = {"cpf"})
    public ResponseEntity<Response<Colaborator>> updateName(@RequestParam("cpf") Long cpf) {
        Response<Colaborator> colaborator = colaboratorService.updateColaboratorstatus(cpf);
        return new ResponseEntity<>(colaborator, HttpStatus.OK);
    }
}
