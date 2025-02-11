package ru.liga.optimalpacking.billings.getbillingdetail;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;
import ru.liga.optimalpacking.billings.shared.middlewares.IRequestNotFound;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public record GetBillingDetailQuery(String userId, OffsetDateTime from, OffsetDateTime to)
        implements Command<GetBillingDetailResponse>, IRequestNotFound {

    @Override
    public String getNotFoundErrorMessage() {
        return String.format("Детали счёта для пользователя %s с даты %s по дату %s не найдены.",
                userId,
                from.format(DateTimeFormatter.ISO_LOCAL_DATE),
                to.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
