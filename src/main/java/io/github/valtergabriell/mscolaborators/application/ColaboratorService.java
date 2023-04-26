package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Lead;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Response;
import io.github.valtergabriell.mscolaborators.common.ModelMapperSingleton;
import io.github.valtergabriell.mscolaborators.exception.RequestExceptions;
import io.github.valtergabriell.mscolaborators.infra.repository.ColaboratorsRepo;
import io.github.valtergabriell.mscolaborators.infra.requests.MsLeadRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ColaboratorService {

    private final ColaboratorsRepo colaboratorsRepo;
    private final MsLeadRequests msLeadRequests;

    public ColaboratorService(ColaboratorsRepo colaboratorsRepo, MsLeadRequests msLeadRequests) {
        this.colaboratorsRepo = colaboratorsRepo;
        this.msLeadRequests = msLeadRequests;
    }

    public Colaborator createNewColaborator(Colaborator colaborators, Long cnpj) {
        //getting lead from mslead
        Lead lead = msLeadRequests.findLeadByCnpj(cnpj);
        boolean present = colaboratorsRepo.findById(colaborators.getId()).isPresent();
        if (present) {
            throw new RequestExceptions("Usuario ja exsitente!");
        }

        if (String.valueOf(colaborators.getId()).length() != 11) {
            throw new RequestExceptions("O tamanho do CPF está incorreto, precisa ter 11 digitos!");
        }

        LocalDate localDateTime = LocalDate.now();
        Colaborator colaborator = ModelMapperSingleton.getInstance().map(colaborators, Colaborator.class);

        colaborator.setHireDate(localDateTime);
        colaborator.setPassword(colaborators.getPassword());
        colaborator.setActive(true);
        colaborator.setLead(lead.getId());
        colaboratorsRepo.save(colaborator);

        return colaborator;
    }


    public Response<Colaborator> findColaboratorById(Long cpf) {
        Optional<Colaborator> colaborator = colaboratorsRepo.findById(cpf);
        Response<Colaborator> response = new Response<>();
        if (colaborator.isEmpty()) {
            response.setMessage("Colaborador não encontrado");
        } else {
            response.setData(colaborator.get());
            response.setMessage("Sucesso!");
        }
        return response;
    }
}
