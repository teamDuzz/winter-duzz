package com.duzz.backend.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static List<String[]> readCsv(String path) {
        List<String[]> content = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(path, Charset.forName("EUC-KR")))) {
            String line;
            while ((line = br.readLine()) != null) {
                var values = line.split(",");
                content.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
