package org.example.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CsvConvertorUtil {
    private static String listToCsvString(List list) {
        StringBuilder builder = new StringBuilder();
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        Arrays.stream(fields)
                .forEach(field -> builder
                        .append(field.getName())
                        .append(";"));
        builder.deleteCharAt(builder.length() - 1);
        builder.append('\n');
        for (Object o : list) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    builder.append(field.get(o).toString()).append(';');
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public static void createCsvFile(String fileName, List list) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(CsvConvertorUtil.listToCsvString(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
