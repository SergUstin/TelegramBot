package com.example.telegrambot.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;


/**
 * Базовый класс для параметров приложения.
 * Вызывает инициализацию у классов наследников и распечатывать в лог значения параметров.
 */
@Slf4j
public abstract class AppProperties {

    @SneakyThrows
    @PostConstruct
    private void init() {
        Class<? extends AppProperties> type = this.getClass();

        StringBuilder sb = new StringBuilder(1024);
        sb.append(type.getSimpleName());
        sb.append(" values:");

        List<Field> fields = ClassUtils.getFields(type, AppProperties.class, false);
        for (Field field : fields) {
            sb.append("\n\t");
            sb.append(field.getName());
            sb.append(" = ");
            field.setAccessible(true);
            sb.append(field.get(this));
        }

        log.info(sb.toString());
    }
}
