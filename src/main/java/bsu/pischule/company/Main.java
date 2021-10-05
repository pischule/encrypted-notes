package bsu.pischule.company;

public class Main {
    public static void main(String[] args) {
        String sessionKey = "sdhusdf09sfj8m94324vy2413y7bc43qy748d31nucr890sreu 8g24p2qy89 u237P81S?s:' w]08 d9a8 94u8ewyq8c387rx3qiurmg3z";

//        RSAClinetServer rsaClinetServer = new RSAClinetServer();
//        rsaClinetServer.sessionKey = sessionKey;
//        rsaClinetServer.start();

        //или упрощенная версия(все делаем на сервере а в конце все шлем клиенту(публичные, прайват ключи расшифрованное и зашифрованное сообщение
        rsa("privet world");

        // <===З
        cfb("hui sosal kbeshnik01256789123456fsdfd89n1572495f0432d 5249 05145 i319", sessionKey);
    }

    static void rsa(String message)
    {
        //сгенерили ключи
        RSA rsa = new RSA(512);
        rsa.generateKeys();
        var privateKey = rsa.privateKey;
        var publicKey = rsa.publicKey;
        //отправили ключ на сервак
        {

        }

        // на котором зашифровали message
        RSA rsa1 = new RSA(512);
        var enc = rsa1.encrypt(message, publicKey);
        //отправили зашифрованный текст клиенту
        {

        }

        //расшифровали на клиенте
        RSA rsa2 = new RSA(512);
        var res = rsa2.decrypt(enc, privateKey);

        System.out.println(res);
    }

    static void cfb(String message, String sessionKey)
    {
        sessionKey = sessionKey.replaceAll("[^a-zA-Z0-9]", "");
        var enc = RealCFB.encrypt(message, sessionKey);
        var dec = RealCFB.decrypt(enc, sessionKey);

        System.out.println(dec);
    }
}
