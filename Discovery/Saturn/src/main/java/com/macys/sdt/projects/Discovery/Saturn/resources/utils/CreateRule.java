package com.macys.sdt.projects.Discovery.Saturn.resources.utils;

import java.util.Random;

public class CreateRule {
    public static String getRandomColor() {
        String[] color = new String[]{"Black", "Blue", "Red", "yellow", "green"};
        int index = new Random().nextInt(color.length);

        String random_color = (color[index]);

        return random_color;
    }
    public static String getRandomBrand() {
        String[] brand = new String[]{"Calvin Klein", "Ralph Lauren", "Michael Kors", "Gucci", "Burberry", "Tory Burch"};
        int index = new Random().nextInt(brand.length);

        String random_brand = (brand[index]);

        return random_brand;
    }
}


