package nix.util;

import nix.annotation.Value;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Initializer <T>{

    private T object;
    private final Class<T> objectClass;

    public Initializer(Class<T> objectClass) {
        this.objectClass = objectClass;
    }


    public List<T> init(CsvTable table) {
        List<T> list = new ArrayList<>();

        for(int i=0 ;i<table.height(); i++){
            try {
                object = objectClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            for(Field field : objectClass.getDeclaredFields()){
                Value key = field.getAnnotation(Value.class);
                if (key == null) continue;
                String property = table.get(i, key.value());
                if (property == null) continue;

                field.setAccessible(true);
                String type = field.getType().getName();


                try {
                    if (int.class.getName().equals(type)) {
                        field.setInt(object, Integer.parseInt(property));
                    } else if (String.class.getName().equals(type)) {
                        field.set(object, property);
                    }
                    else {
                        System.out.println("This type is not provided!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            list.add(object);
        }
        return list;
    }

}
