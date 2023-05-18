package io.github.valtergabriell.mscolaborators.infra.external.requests;

import io.github.valtergabriell.mscolaborators.application.domain.dto.Lead;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msjobs", path = "/job")
public interface MsJobsRequests {
    @DeleteMapping(value = "delete-all-jobs-by-colaborator", params = {"colaboratorId"})
    void deleteAllJobs(@RequestParam("colaboratorId") Long colaboratorId);
}
