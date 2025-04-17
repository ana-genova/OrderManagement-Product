package br.com.fiap.product_management.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SkuGenerator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");

    public static String generateSKU(Long productId) {
        String datePart = dateFormat.format(new Date());
        return datePart + "-" + String.format("%04d", productId);
    }
}
