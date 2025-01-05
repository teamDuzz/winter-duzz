package com.duzz.backend.service;

import com.duzz.backend.util.TextFileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class StateService {
    private final String filePath = "assets/state.txt";
    private final TextFileStorage textFileStorage = new TextFileStorage("state");


    public Integer getState() {
        try {
            return Integer.parseInt(textFileStorage.read());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(Integer state) {
        try {
            textFileStorage.write(state.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
