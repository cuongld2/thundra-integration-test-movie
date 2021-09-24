package common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.thucydides.core.reports.json.gson.CollectionAdapter;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

public class IgnoreEmptyAdapter implements JsonSerializer<Object> {

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        Field[] fields = src.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getDeclaringClass() == String.class) {
                handleSetStringValue(src, result, field);
            } else if (field.getDeclaringClass() == Collection.class) {
                CollectionAdapter collectionAdapter = new CollectionAdapter();
                JsonElement jsonElement =
                        collectionAdapter.serialize(Collections.singleton(field), field.getDeclaringClass(),
                                context);
                result.add(field.getName(), jsonElement);
            }
        }
        return result;
    }

    private void handleSetStringValue(Object src, JsonObject result, Field field) {
        try {
            field.setAccessible(true);
            String fieldValue = (String) field.get(src);
            if (StringUtils.isNotEmpty(fieldValue)) {
                result.addProperty(field.getName(), (String) field.get(src));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void handleSetCollection(Object src, JsonObject result, Field field) {
        field.setAccessible(true);
    }

    private void handleSetIntValue(Object src, JsonObject result, Field field) {
        try {
            int fieldValue = field.getInt(src);
            result.addProperty(field.getName(), fieldValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
