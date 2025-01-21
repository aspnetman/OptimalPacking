package ru.liga.optimalpacking.billings.getbillingdetail;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;
import ru.liga.optimalpacking.packages.shared.middlewares.IRequestNotFound;

public record GetBillingDetailQuery(String userId, String from,
                                    String to) implements Command<GetBillingDetailResponse>, IRequestNotFound {

    @Override
    public String GetNotFoundErrorMessage() {
        return String.format("Детали счёта для пользователя %s с даты %s по дату %s не найдены.", userId, from, to);
    }
}
