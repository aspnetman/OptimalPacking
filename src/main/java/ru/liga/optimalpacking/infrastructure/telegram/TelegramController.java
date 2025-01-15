package ru.liga.optimalpacking.infrastructure.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.optimalpacking.infrastructure.cli.CommandLineProcessor;

@Slf4j
@RequiredArgsConstructor
public class TelegramController extends TelegramLongPollingBot {

    private final String botName;

    private final CommandLineProcessor commandLineProcessor;

    public TelegramController(String botToken, String botName, CommandLineProcessor commandLineProcessor) {
        super(botToken);
        this.botName = botName;
        this.commandLineProcessor = commandLineProcessor;
    }

    /**
     * Запускает слушателя бота
     */
    public void listen() {
        try {
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            log.error("Произошла ошибка", e);
        }
    }

    /**
     * Получает сообщения от бота
     *
     * @param update информация от бота
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            commandLineProcessor.processCommand(update.getMessage().getText());
        }
    }

    /**
     * Получает имя бота
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }
}
