package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.optimalpacking.billings.getbillingdetail.GetBillingDetailQuery;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/billings")
@RequiredArgsConstructor
public class BillingsController {

    private final Pipeline pipeline;

    @GetMapping
    public void getBillingDetail(@RequestParam("userId") String userId,
                                 @RequestParam("from") LocalDate from,
                                 @RequestParam("to") LocalDate to) {
        pipeline.send(new GetBillingDetailQuery(userId, from, to));
    }
}