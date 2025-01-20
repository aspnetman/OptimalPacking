package ru.liga.optimalpacking.infrastructure.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {
    public Options createOptions() {
        var options = new Options();

        // Удаление посылки
        var deleteOption = new Option("d", "delete", true, "Удаление посылки");
        options.addOption(deleteOption);

        // Редактирование посылки
        var editOption = new Option("e", "edit", true, "Редактирование посылки");
        options.addOption(editOption);

        // Опция для указания имени посылки
        var nameOption = new Option("n", "name", true, "Указание названия посылки");
        options.addOption(nameOption);

        // Опция для указания ширины посылки
        var widthOption = new Option("w", "width", true, "Указание ширины посылки");
        options.addOption(widthOption);

        // Опция для указания высоты посылки
        var heightOption = new Option("h", "height", true, "Указание высоты посылки");
        options.addOption(heightOption);

        // Опция для получения посылки по имени
        var getOption = new Option("g", "get", true, "Получение посылки по имени");
        options.addOption(getOption);

        // Опция для получения списка посылок
        var listOption = new Option("l", "list", false, "Получение списка посылок");
        options.addOption(listOption);

        // Опция для импорта посылок
        var importOption = new Option("i", "import", false, "Импорт посылок");
        options.addOption(importOption);

        // Опция для указания файла для импорта
        var importFileOption = new Option("f", "file", true, "Указание файла для импорта");
        options.addOption(importFileOption);

        // Опция для указания максимального количества грузовиков
        var maxTrucksOption = new Option("m", "maxTrucks", true, "Указание максимального количества грузовиков");
        options.addOption(maxTrucksOption);

        // Опция для указания алгоритма
        var packingAlgorithmOption = new Option("a", "packingAlgorithm", true, "Указание алгоритма");
        options.addOption(packingAlgorithmOption);

        // Опция для получения помощи
        var helpOption = new Option("s", "help", false, "Получение помощи");
        options.addOption(helpOption);

        // Опция для получения идентификатора пользователя
        var userIdOption = new Option("u", "userId", true, "Указание идентификатора пользователя");
        options.addOption(userIdOption);

        return options;
    }
}
