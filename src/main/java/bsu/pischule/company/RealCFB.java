package bsu.pischule.company;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RealCFB {

    public static String encrypt(String message, String sessionKey)
    {
        try {
            byte[] m = message.getBytes();

//            esa(m);

            byte[] decodedKey = Base64.getDecoder().decode(sessionKey);
            byte[] trueKey = new byte[16];
            Arrays.fill(trueKey, (byte)4);
            for (int i = 0; i < 16 && i < decodedKey.length; i++) {
                trueKey[i] = decodedKey[i];
            }

            var originalKey = new SecretKeySpec(trueKey, 0, trueKey.length, "AES");
            SecretKey key_s = originalKey;

            byte[] stg = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
            IvParameterSpec iv = new IvParameterSpec(stg);

//            System.out.println("\n\n****CFB****");

            // CFB MODE
            Cipher c1 = Cipher.getInstance("AES/CFB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, key_s, iv);
            byte[] cfb = Base64.getEncoder().encode(c1.doFinal(m));

            String cfben = new String(cfb, "UTF-8");
//            System.out.println("Enrypted message is below.");
//            System.out.println(cfben);

//            decrypt(cfben, sessionKey);
            return cfben;
        } catch (Exception e) {
//            System.out.println(e);
        }
        return "encryptbug";
    }

    public static String decrypt(String encypted, String sessionKey)  {
        {
            try {
                var cfben = encypted;

                byte[] decodedKey = Base64.getDecoder().decode(sessionKey);
                byte[] trueKey = new byte[16];
                Arrays.fill(trueKey, (byte) 4);
                for (int i = 0; i < 16 && i < decodedKey.length; i++) {
                    trueKey[i] = decodedKey[i];
                }

                var originalKey = new SecretKeySpec(trueKey, 0, trueKey.length, "AES");
                SecretKey key_s = originalKey;

                byte[] stg = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
                IvParameterSpec iv = new IvParameterSpec(stg);

                Cipher c1 = Cipher.getInstance("AES/CFB/PKCS5Padding");


                // Decrypt
                c1.init(Cipher.DECRYPT_MODE, key_s, iv);
                byte[] cfbde = c1.doFinal(Base64.getDecoder().decode((cfben)));
//                System.out.println("\nDecrypted message is below.");
//                System.out.print(new String(cfbde, "UTF-8"));

                return new String(cfbde, "UTF-8");
            }
        catch (Exception e) {
//            System.out.println(e);
        }
            return "decryptbug";
        }
    }



    private static void esa(byte[] m) {
        String key = "12312ifn23rhu23irj2323r3 2rj3nr32orl32r23";
        byte[] k = key.getBytes();

        byte[] cipher = CFB228.encrypt(m, k);

        byte[] decipher = CFB228.decrypt(cipher, k);
        byte[] msgtxt = Base64.getEncoder().encode(decipher);

        System.out.println("\nEnrypted message is below.");
        for (int i = 0; i < msgtxt.length; i++) {

            System.out.print((char) msgtxt[i]);

        }
        System.out.println("\n\nDecrypted message is below.");
        for (int i = 0; i < decipher.length; i++) {

            System.out.print((char) decipher[i]);
        }
    }
}
