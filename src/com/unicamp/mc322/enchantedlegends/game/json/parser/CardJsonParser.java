package com.unicamp.mc322.enchantedlegends.game.json.parser;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CardJsonParser extends JsonParser {
    private static final String ENCHANTED_LEGENDS_PACKAGE = "com.unicamp.mc322.enchantedlegends";

    public static void main(String[] args) throws ParseException {
        {
            String json = "{\"card\": {\n" +
                    "      \"type\": \"com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types.AttackToLevelUpChampion\",\n" +
                    "      \"name\": \"Garen\",\n" +
                    "      \"cost\": 5,\n" +
                    "      \"damage\": 5,\n" +
                    "      \"health\": 5,\n" +
                    "      \"effects\": [],\n" +
                    "      \"levelUpPoints\": 2,\n" +
                    "      \"upgrades\": [\n" +
                    "          {\n" +
                    "            \"type\": \"com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.MagicUpgrade\",\n" +
                    "            \"trait\":\n" +
                    "            {\n" +
                    "              \"type\": \"com.unicamp.mc322.enchantedlegends.game.card.trait.ElusiveTrait\"\n" +
                    "            }\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"type\": \"com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.DamageUpgrade\",\n" +
                    "            \"damagePoints\": 1\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"type\": \"com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.HealthUpgrade\",\n" +
                    "            \"healthPoints\": 1\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      }\n" +
                    "    }";

            System.out.println(json);
            Card card = new CardJsonParser().parseToObject(json);
            System.out.println(card);
        }
    }

    static <T> T buildClass(Class<T> clazz, JSONObject classJson) {
        try {
            T classObj = clazz.getDeclaredConstructor().newInstance();
            List<Field> allFields = new ArrayList<>();
            Class<?> supers = clazz;
            while (supers != null) {
                allFields.addAll(Arrays.asList(supers.getDeclaredFields()));
                supers = supers.getSuperclass();
            }

            allFields.forEach(field -> buildFieldFromJSONObject(field, classObj, classJson));
            return classObj;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    static <T> void buildFieldFromJSONObject(Field field, T obj, JSONObject jsonObject) {
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
                if (fieldClass.isInterface()) {
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

    @Override
    @SuppressWarnings("unchecked")
    public Card parseToObject(String json) throws ParseException {
        JSONObject cardGot = ((Map<String, JSONObject>) this.parser.parse(json)).get("card");
        String cardType = (String) cardGot.get("type");
        Card card = null;
        try {
            Class<?> cardClass = Class.forName(cardType);
            card = (Card) buildClass(cardClass, cardGot);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return card;
    }
}
