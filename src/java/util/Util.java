package util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {

    /**
     * Converte do formato dd/mm/aaaa para aaaa-mm-dd
     *
     * @param data Data dd/mm/aaaa
     * @return Date 
     */
    public static Date formataDataBanco(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date novaData = new Date(format.parse(data).getTime());
            return novaData;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Converte do formato aaaa-mm-dd para dd/mm/aaaa
     *
     * @param data Data do banco aaaa-mm-dd
     * @return data normal
     */
    public static String formataDataPadrao(String data) {
        if (data != null && data.length() >= 10) {
            String ano = data.substring(0, 4);
            String mes = data.substring(5, 7);
            String dia = data.substring(8, 10);

            return dia + "/" + mes + "/" + ano;
        } else {
            return null;
        }
    }
}
