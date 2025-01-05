package com.duzz.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class StateService {
    private final String filePath = "assets/state.txt";


    public Integer getState() {
        try {
            var f = new File(filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                try (var w = new FileWriter(f)) {
                    w.write("0");
                }
            }

            try (var r = new FileReader(f)) {
                return Integer.parseInt(new BufferedReader(r).readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(Integer state) {
        try (var w = new FileWriter(filePath)) {
            w.write(state.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
