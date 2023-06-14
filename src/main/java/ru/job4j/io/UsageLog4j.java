package ru.job4j.io;

import org.apache.log4j.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws Exception {
        String name = "Stepan Zazyan";
        int age = 33;
        byte b = 127;
        short s = 16000;
        double d = 1.23;
        float f = 3.2f;
        long l = 900000L;
        char c = 'a';
        boolean baboolean = true;
        LOG.debug("User info name : {}, age : {}, "
                        + "b : {}, s : {}, d : {}, f : {}, "
                        + "l : {}, c : {}, baboolean : {}",
                name, age, b, s, d, f, l, c, baboolean);
    }
}

