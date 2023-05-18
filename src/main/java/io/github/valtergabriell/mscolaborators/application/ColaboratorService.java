package io.github.valtergabriell.mscolaborators.application;

import io.github.valtergabriell.mscolaborators.application.domain.Colaborator;
import io.github.valtergabriell.mscolaborators.application.domain.dto.Response;
import io.github.valtergabriell.mscolaborators.common.ModelMapperSingleton;
import io.github.valtergabriell.mscolaborators.exception.RequestExceptions;
import io.github.valtergabriell.mscolaborators.infra.external.requests.MsJobsRequests;
import io.github.valtergabriell.mscolaborators.infra.external.requests.MsLeadRequests;
import io.github.valtergabriell.mscolaborators.infra.external.requests.queue.send.SendRequestToCreateNewClient;
import io.github.valtergabriell.mscolaborators.infra.repository.ColaboratorsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ColaboratorService {

    private final ColaboratorsRepo colaboratorsRepo;
    private final MsLeadRequests msLeadRequests;
    private final MsJobsRequests msJobsRequests;
    private final SendRequestToCreateNewClient sendRequestToCreateNewClient;

    public ColaboratorService(ColaboratorsRepo colaboratorsRepo, MsLeadRequests msLeadRequests, MsJobsRequests msJobsRequests, SendRequestToCreateNewClient sendRequestToCreateNewClient) {
        this.colaboratorsRepo = colaboratorsRepo;
        this.msLeadRequests = msLeadRequests;
        this.msJobsRequests = msJobsRequests;
        this.sendRequestToCreateNewClient = sendRequestToCreateNewClient;
    }

    public Colaborator createNewColaborator(Colaborator colaborators, Long cnpj, BigDecimal income) {
        if (income == null) {
            throw new RequestExceptions("Renda precisa ser passada!");
        }
        //getting lead from mslead
        boolean present = colaboratorsRepo.findById(colaborators.getId()).isPresent();
        if (present) {
            throw new RequestExceptions("Usuario ja exsitente!");
        }

        if (String.valueOf(colaborators.getId()).length() != 11) {
            throw new RequestExceptions("O tamanho do CPF está incorreto, precisa ter 11 digitos!");
        }

        //sendRequestToCreateNewClient.createNewClient(colaborators.toModel(income));
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

    public List<Colaborator> findAllInactivesColaborators() {
        return colaboratorsRepo.returnAllInactivesColaborators();
    }

    public List<Colaborator> findAllActivesColaborators() {
        return colaboratorsRepo.returnAllActivesColaborators();
    }

    public Response<Long> findLeadIdByColaborator(Long cpf) {
        boolean present = colaboratorsRepo.findById(cpf).isPresent();
        Long id = colaboratorsRepo.returnLeadId(cpf);
        Response<Long> response = new Response<>();
        if (!present) {
            response.setMessage("Colaborador não encontrado");
        } else {
            response.setData(id);
            response.setMessage("Sucesso!");
        }
        return response;
    }

    public Response<Colaborator> updateColaboratorName(Long cpf, String name) {
        Colaborator colaborator = colaboratorsRepo.findById(cpf).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        colaborator.setName(name);
        colaboratorsRepo.save(colaborator);

        Response<Colaborator> response = new Response<>();
        response.setData(colaborator);
        response.setMessage("Sucesso!");
        return response;
    }

    public Response<Colaborator> updateColaboratorEmail(Long cpf, String email) {
        Colaborator colaborator = colaboratorsRepo.findById(cpf).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        colaborator.setEmail(email);
        colaboratorsRepo.save(colaborator);

        Response<Colaborator> response = new Response<>();
        response.setData(colaborator);
        response.setMessage("Sucesso!");
        return response;
    }

    public Response<Colaborator> updateColaboratorPhone(Long cpf, String phone) {
        Colaborator colaborator = colaboratorsRepo.findById(cpf).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        colaborator.setPhone(phone);
        colaboratorsRepo.save(colaborator);

        Response<Colaborator> response = new Response<>();
        response.setData(colaborator);
        response.setMessage("Sucesso!");
        return response;
    }


    public Response<Colaborator> updateColaboratorLead(Long cpf, Long lead) {
        Colaborator colaborator = colaboratorsRepo.findById(cpf).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        colaborator.setLead(lead);
        colaboratorsRepo.save(colaborator);

        Response<Colaborator> response = new Response<>();
        response.setData(colaborator);
        response.setMessage("Sucesso!");
        return response;
    }

    public Response<Colaborator> updateColaboratorstatus(Long cpf) {
        Colaborator colaborator = colaboratorsRepo.findById(cpf).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        colaborator.setActive(!colaborator.getActive());
        colaboratorsRepo.save(colaborator);

        Response<Colaborator> response = new Response<>();
        response.setData(colaborator);
        response.setMessage("Sucesso!");
        return response;
    }

    public void deleteColaboratorById(Long colaboratorId){
        Colaborator colaborator = colaboratorsRepo.findById(colaboratorId).orElseThrow(() -> new RequestExceptions("Colaborador não encontrado"));
        msJobsRequests.deleteAllJobs(colaboratorId);
        colaboratorsRepo.delete(colaborator);
    }

    public void deleteAllEmployeesWhenLeadIsDeleted(Long leadId) {
        List<Colaborator> colaborators = colaboratorsRepo.returnAllColaboratorsByLead(leadId);
        if (colaborators.isEmpty()){
            throw new RequestExceptions("Este líder não possui colaboradores salvos");
        }
        for (Colaborator colaborator : colaborators) {
            deleteColaboratorById(colaborator.getId());
        }
        ResponseEntity.status(204).body("Colaboradores do líder com id " + leadId + " deletados com sucesso!");
    }
}
