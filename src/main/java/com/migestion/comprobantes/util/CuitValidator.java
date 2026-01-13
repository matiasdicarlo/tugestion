package com.migestion.comprobantes.util;

public class CuitValidator {

    public static boolean validarCUIT(String cuit) {
        if (cuit == null) return false;

        cuit = cuit.replace("-", "").trim();

        if (!cuit.matches("\\d{11}")) return false;

        int[] multiplicadores = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            int dig = Character.getNumericValue(cuit.charAt(i));
            suma += dig * multiplicadores[i];
        }

        int resto = suma % 11;

        int verificador = (resto == 0) ? 0 :
                (resto == 1) ? 9 :
                        (11 - resto);

        return verificador == Character.getNumericValue(cuit.charAt(10));
    }
}
