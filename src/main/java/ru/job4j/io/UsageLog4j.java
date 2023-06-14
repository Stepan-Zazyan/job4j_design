package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Stepan Zazyan";
        int age = 33;
        byte b = 127;
        short s = 16000;
        double d = 1.23;
        float f = 3.2f;
        long l = 900000;
        char c = 'a';
        LOG.debug("User info name : {}, age : {}, "
                + "b : {}, s : {}, d : {}, f : {}, "
                + "l : {}, c : {},", name, age, b, s, d, f, l, c);
    }
}
