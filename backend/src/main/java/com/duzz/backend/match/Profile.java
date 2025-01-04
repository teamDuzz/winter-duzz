package com.duzz.backend.match;
import lombok.Getter;

import java.util.*;
@Getter
public class Profile {
    String name;
    int [][] schedule=new int[7][48]; // 5일 x 12시간 (0: 불가, 1: 가능) 9:00 ~ 21:00

    public Profile(String name) {
        this.name = name;
    }
}
