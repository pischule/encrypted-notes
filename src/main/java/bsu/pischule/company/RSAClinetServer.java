package bsu.pischule.company;

import java.math.BigInteger;

public class RSAClinetServer {
    // типо в постоянной памяти памяти
    private BigInteger clientCachedPrivateKey;
    // типо в постоянной памяти памяти
    private int publicBitLength;

    //сессионный ключ, который шифруется, decrypted будет капслок
    public String sessionKey = "";

    public void start() {
        //клиент отправляет bitlength, парсим json или можно забить и ничего не слать
        //****************************** bitlength
        publicBitLength = 512;

        /////////////////////////////////////////////////////////////////////////

        RSA rsa = new RSA(publicBitLength);
        rsa.generateKeys();

        clientCachedPrivateKey = rsa.privateKey;

        // как будто отправляем на сервак публичный ключ
        sendPublicKeyToServer(rsa.publicKey);
    }

    private  void sendPublicKeyToServer(BigInteger publicKey) {

        // как будто принимаем от клиента
        receivePublicKeyFromClient(publicKey);
    }

    private  void receivePublicKeyFromClient(BigInteger publicKey) {
        RSA rsa = new RSA(publicBitLength);

        var encryptedMessage = rsa.encrypt(sessionKey, publicKey);

        // как будто отправляем клиенту
        sendEncryptedToClient(encryptedMessage);
    }

    private  void sendEncryptedToClient(BigInteger encryptedMessage) {
        // как будто клиент принял
        receiveEncryptedFromServer(encryptedMessage);
    }

    private  void receiveEncryptedFromServer(BigInteger encryptedMessage) {
        RSA rsa = new RSA(publicBitLength);

        String decryptedMessage = rsa.decrypt(encryptedMessage, clientCachedPrivateKey);

        //а тут уже отправляем расшифрованную версию jsonom
//        System.err.println("Decrypted:\n"+decryptedMessage);
    }
}
