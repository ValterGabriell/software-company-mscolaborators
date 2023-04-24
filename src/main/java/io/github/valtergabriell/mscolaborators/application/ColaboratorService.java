package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborators;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Lead;
import io.github.valtergabriell.mscolaborators.common.ModelMapperSingleton;
import io.github.valtergabriell.mscolaborators.exceptions.RequestException;
import io.github.valtergabriell.mscolaborators.infra.repository.ColaboratorsRepo;
import io.github.valtergabriell.mscolaborators.infra.requests.MsLeadRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class ColaboratorService {

    private final ColaboratorsRepo colaboratorsRepo;
    private final MsLeadRequests msLeadRequests;

    public ColaboratorService(ColaboratorsRepo colaboratorsRepo, MsLeadRequests msLeadRequests) {
        this.colaboratorsRepo = colaboratorsRepo;
        this.msLeadRequests = msLeadRequests;
    }

    public Colaborators createNewColaborator(Colaborators colaborators, Long cnpj) {
        //getting lead from mslead
        Lead lead = msLeadRequests.findLeadByCnpj(cnpj);
        boolean present = colaboratorsRepo.findById(colaborators.getId()).isPresent();
        if (present) {
            throw new RequestException("Usuario ja exsitente!");
        }

        if (String.valueOf(colaborators.getId()).length() != 11) {
            throw new RequestException("O tamanho do CPF está incorreto, precisa ter 11 digitos!");
        }

        LocalDate localDateTime = LocalDate.now();
        Colaborators colaborator = ModelMapperSingleton.getInstance().map(colaborators, Colaborators.class);

        colaborator.setHireDate(localDateTime);
        colaborator.setPassword(colaborators.getPassword());
        colaborator.setActive(true);
        colaborator.setLead(lead.getId());
        colaboratorsRepo.save(colaborator);

        return colaborator;
    }


}
