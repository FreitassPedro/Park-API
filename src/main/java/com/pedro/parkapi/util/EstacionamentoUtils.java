package com.pedro.parkapi.util;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstacionamentoUtils {
    private static final double PRIMEIROS_15_MINUTES = 5.00;
    private static final double PRIMEIROS_60_MINUTES = 9.25;
    private static final double ADICIONAL_15_MINUTES = 1.75;
    private static final double DESCONTO_PERCENTUAL = 0.30;

    public static BigDecimal calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        long minutes = entrada.until(saida, ChronoUnit.MINUTES);
        double total = 0.0;

        if (minutes <= 15) {
            total = PRIMEIROS_15_MINUTES;
        } else if (minutes <= 60) {
            total = PRIMEIROS_60_MINUTES;
        } else {
            minutes -= 60;
            total = PRIMEIROS_60_MINUTES + (minutes / 15) * ADICIONAL_15_MINUTES;
            if ((minutes % 15) > 0) total += ADICIONAL_15_MINUTES;

        }

        return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calcularDesconto(BigDecimal custo, long numeroDeVezes) {

        BigDecimal desconto = new BigDecimal(0);
        if (numeroDeVezes % 10 == 0 && numeroDeVezes > 0) {
            desconto = custo.multiply(new BigDecimal(DESCONTO_PERCENTUAL));
        }

        return desconto.setScale(2, RoundingMode.HALF_EVEN);
    }

    //2023-03-16T15:20:12.2213213
    public static String gerarRecibo() {
        LocalDateTime date = LocalDateTime.now();
        String recibo = date.toString().substring(0, 19);
        return recibo.replace("-", "")
                .replace(":", "")
                .replace("T", "-");
    }

}
