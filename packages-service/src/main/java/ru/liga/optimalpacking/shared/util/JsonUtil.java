package ru.liga.optimalpacking.shared.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

/**
 * Класс для работы с json
 */
public class JsonUtil {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(OffsetDateTime.class, new LocalTimeAdapter())
                .create();
    }

    /**
     * Десериализует json в объект
     *
     * @param json     строка
     * @param classOfT тип целевого объекта
     * @return целевой объект
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Сериализует объект в json
     * @param src объект, который надо сериализовать
     * @return json строка
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static class LocalTimeAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {
        @Override
        public JsonElement serialize(OffsetDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString());
        }

        @Override
        public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return OffsetDateTime.parse(json.getAsString());
        }
    }
}