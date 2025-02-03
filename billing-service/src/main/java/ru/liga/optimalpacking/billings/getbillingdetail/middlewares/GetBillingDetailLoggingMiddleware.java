package ru.liga.optimalpacking.billings.getbillingdetail.middlewares;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.billings.getbillingdetail.GetBillingDetailQuery;
import ru.liga.optimalpacking.billings.getbillingdetail.dto.GetBillingDetailResponse;

@Component
@Order(8)
@Slf4j
public class GetBillingDetailLoggingMiddleware implements Command.Middleware {
    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {
        if (c instanceof GetBillingDetailQuery) {
            log.info("Получение счёта для пользователя {} с даты {} по дату {}",
                    ((GetBillingDetailQuery) c).userId(),
                    ((GetBillingDetailQuery) c).from(),
                    ((GetBillingDetailQuery) c).to());

            var result = next.invoke();

            log.info("Счёт: \n {}", ((GetBillingDetailResponse) result).billingDetail());

            return result;
        } else {
            return next.invoke();
        }

    }
}
