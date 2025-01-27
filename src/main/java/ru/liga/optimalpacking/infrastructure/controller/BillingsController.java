package ru.liga.optimalpacking.infrastructure.controller;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.liga.optimalpacking.billings.getbillingdetail.GetBillingDetailQuery;

@ShellComponent
@RequiredArgsConstructor
public class BillingsController {

    private final Pipeline pipeline;

    @ShellMethod(value = "Получение деталей счёта", key = "get-billing-detail")
    public void getBillingDetail(@ShellOption(value = {"--userId", "-u"}) String userId,
                                 @ShellOption(value = {"--from", "-f"}) String from,
                                 @ShellOption(value = {"--to", "-t"}) String to) {
        pipeline.send(new GetBillingDetailQuery(userId, from, to));
    }
}
