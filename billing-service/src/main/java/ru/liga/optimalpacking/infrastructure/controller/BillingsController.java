package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.optimalpacking.billings.getbillingdetail.GetBillingDetailQuery;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;
import ru.liga.optimalpacking.billings.shared.dto.Response;
import ru.liga.optimalpacking.billings.shared.dto.ResponseCode;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/v1/billings")
@RequiredArgsConstructor
public class BillingsController {

    private final Pipeline pipeline;

    @GetMapping
    public ResponseEntity<Response<GetBillingDetailResponse>> getBillingDetail(@RequestParam("userId") String userId,
                                                                               @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS XXX") OffsetDateTime from,
                                                                               @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS XXX") OffsetDateTime to) {
        return new ResponseEntity<>(
                new Response<>(ResponseCode.OK,
                        pipeline.send(new GetBillingDetailQuery(userId, from, to))), HttpStatus.OK);
    }
}