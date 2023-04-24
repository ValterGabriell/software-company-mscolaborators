package io.github.valtergabriell.mscolaborators.infra.requests;

import io.github.valtergabriell.mscolaborators.application.domain.dto.Lead;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mslead", path = "/lead")
public interface MsLeadRequests {
    @GetMapping(value = "find-by-id", params = {"cnpj"})
    Lead findLeadByCnpj(@RequestParam("cnpj") Long cnpj);
}
