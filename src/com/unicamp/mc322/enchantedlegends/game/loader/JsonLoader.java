package com.unicamp.mc322.enchantedlegends.game.loader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public abstract class JsonLoader {
    private static final String ENCHANTED_LEGENDS_PACKAGE = "com.unicamp.mc322.enchantedlegends";
    protected final JSONParser parser;

    public JsonLoader() {
        this.parser = new JSONParser();
    }

    public abstract Object parseToObject(Object json) throws ParseException, IOException;

    protected static <T> T buildClass(Class<T> clazz, JSONObject classJson) {
        try {
            T classObj = clazz.getDeclaredConstructor().newInstance();
            Map<Field, Object> fields = new HashMap<>();
            List<Field> allFields = new ArrayList<>();
            Class<?> supers = clazz;
            while (supers != null) {
                allFields.addAll(0, Arrays.asList(supers.getDeclaredFields()));
                supers = supers.getSuperclass();
            }

            allFields = allFields.stream().filter(f -> !Modifier.isFinal(f.getModifiers())).collect(Collectors.toList());
            allFields.forEach(field -> buildFieldFromJSONObject(field, classObj, classJson, fields));
            return classObj;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(clazz);
            e.printStackTrace();
            return null;
        }
    }

    private static <T> void buildFieldFromJSONObject(Field field, T obj, JSONObject jsonObject, Map<Field, Object> fields) {
        if (Modifier.isFinal(field.getModifiers())) {
            return; // cannot and should not set final field
        }

        field.setAccessible(true);
        Class<?> fieldClass = field.getType();
        Object fieldJson = jsonObject.get(field.getName());

        if (fieldJson == null) {
            return;
        }

        try {
            Object toFill;
            if (fieldClass.isPrimitive()) {
                toFill = getAsJavaClass(field, fieldJson);
            } else if (fieldClass.getPackage().getName().startsWith(ENCHANTED_LEGENDS_PACKAGE)) {
                if (fieldClass.isInterface() || Modifier.isAbstract(fieldClass.getModifiers())) {
                    String className = (String) ((JSONObject) fieldJson).get("type");
                    Class<?> actualType = Class.forName(className);
                    toFill = buildClass(actualType, (JSONObject) fieldJson);
                } else {
                    toFill = buildClass(fieldClass, (JSONObject) fieldJson);
                }
            } else if (fieldClass == List.class) {
                toFill = getAsList((JSONArray) fieldJson);
            } else {
                toFill = getAsJavaClass(field, fieldJson);
            }

            if (toFill != null) {
                fields.put(field, toFill);
                field.set(obj, toFill);
            }
        } catch (IllegalAccessException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private static List<Object> getAsList(JSONArray listJson) {
        List<Object> list = new ArrayList<>();

        try {
            for (Object o : listJson) {
                String objectActualType = (String) ((JSONObject) o).get("type");
                Class<?> objClass = Class.forName(objectActualType);
                list.add(buildClass(objClass, (JSONObject) o));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static Object getAsJavaClass(Field field, Object jsonObject) {
        if (jsonObject instanceof Long && field.getType() == Integer.TYPE) {
            return Math.toIntExact((Long) jsonObject);
        }

        return jsonObject;
    }

    private static Object[] toVarArgs(List<Object> list) {
        return list.toArray(new Object[0]);
    }

    private static Class<?>[] toClassVarArgs(List<Class<?>> list) {
        return list.toArray(new Class<?>[0]);
    }
}
