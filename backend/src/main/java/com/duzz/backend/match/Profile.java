package com.duzz.backend.match;
import lombok.Getter;

import java.util.*;
@Getter
public class Profile {
    String name;
    String number;
    int [][] schedule=new int[7][48]; // 7일 x 24시간 (0: 불가, 1: 가능) 9:00 ~ 21:00 월~일 월요일부터 인덱스 시작

    public Profile(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
