package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Lead;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Response;
import io.github.valtergabriell.mscolaborators.common.ModelMapperSingleton;
import io.github.valtergabriell.mscolaborators.exception.RequestExceptions;
import io.github.valtergabriell.mscolaborators.infra.external.requests.MsLeadRequests;
import io.github.valtergabriell.mscolaborators.infra.external.requests.queue.send.SendRequestToCreateNewClient;
import io.github.valtergabriell.mscolaborators.infra.repository.ColaboratorsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ColaboratorService {

    private final ColaboratorsRepo colaboratorsRepo;
    private final MsLeadRequests msLeadRequests;
    private final SendRequestToCreateNewClient sendRequestToCreateNewClient;

    public ColaboratorService(ColaboratorsRepo colaboratorsRepo, MsLeadRequests msLeadRequests, SendRequestToCreateNewClient sendRequestToCreateNewClient) {
        this.colaboratorsRepo = colaboratorsRepo;
        this.msLeadRequests = msLeadRequests;
        this.sendRequestToCreateNewClient = sendRequestToCreateNewClient;
    }

    public Colaborator createNewColaborator(Colaborator colaborators, Long cnpj, BigDecimal income) {
        if (income == null){
            throw new RequestExceptions("Renda precisa ser passada!");
        }
        //getting lead from mslead
        Lead lead = msLeadRequests.findLeadByCnpj(cnpj);
        boolean present = colaboratorsRepo.findById(colaborators.getId()).isPresent();
        if (present) {
            throw new RequestExceptions("Usuario ja exsitente!");
        }

        if (String.valueOf(colaborators.getId()).length() != 11) {
            throw new RequestExceptions("O tamanho do CPF está incorreto, precisa ter 11 digitos!");
        }

        sendRequestToCreateNewClient.createNewClient(colaborators.toModel(income));
        return savingUserOnDB(colaborators, cnpj);
    }

    private Colaborator savingUserOnDB(Colaborator colaborators, Long cnpj) {
        LocalDate localDateTime = LocalDate.now();
        Colaborator colaborator = ModelMapperSingleton.getInstance().map(colaborators, Colaborator.class);
        colaborator.setHireDate(localDateTime);
        colaborator.setPassword(colaborators.getPassword());
        colaborator.setActive(true);
        colaborator.setLead(cnpj);
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
