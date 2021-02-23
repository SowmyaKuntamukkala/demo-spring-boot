package com.example.demospringboot.utils;

import java.util.function.Predicate;

public class PhoneNumberValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        if (s != null && s.startsWith("+91") && !s.equalsIgnoreCase("") && s.length() == 13 ){
            return true;
        }
        return false;
    }
}
