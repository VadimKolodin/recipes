package ru.study.recipes.dom;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ObjectToDomTransformer {

    private static final List<Class> READY_TO_STRING_CLASSES = List.of(String.class,
                                                                       UUID.class,
                                                                       Integer.class,
                                                                       Long.class,
                                                                       Double.class,
                                                                       Float.class,
                                                                       Boolean.class);

    private final Document document;

    @SneakyThrows
    public <T> void transform(Element parent, T obj, String name) {
        Class<?> clazz = obj.getClass();

        if (clazz.isPrimitive() || clazz.isEnum() || READY_TO_STRING_CLASSES.contains(clazz)) {
            parent.setAttribute(name, obj.toString());
            return;
        }

        Element element = document.createElement(name);
        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if (fieldValue == null) {
                continue;
            }

            Class<?> fieldClazz = fieldValue.getClass();
            if (Collection.class.isAssignableFrom(fieldClazz)) {
                for (Object o : (Collection) fieldValue) {
                    transform(element, o, field.getName());
                }
            } else {
                transform(element, fieldValue, field.getName());
            }
        }

        parent.appendChild(element);

    }

}
