package bsu.pischule.encryptednotes.service;

import bsu.pischule.company.RSA;
import bsu.pischule.company.RealCFB;
import bsu.pischule.encryptednotes.dto.EncryptedNoteResponse;
import bsu.pischule.encryptednotes.dto.SessionKeyResponse;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    public String generateSessionKey() {
        return ("amogus");
    }

    public SessionKeyResponse GenerateData() {
        return rsa(generateSessionKey());
    }

    public SessionKeyResponse rsa(String message) {
        //сгенерили ключи
        RSA rsa = new RSA(512);
        rsa.generateKeys();
        var privateKey = rsa.privateKey;
        var publicKey = rsa.publicKey;
        //отправили ключ на сервак

        // на котором зашифровали message
        RSA rsa1 = new RSA(512);
        var enc = rsa1.encrypt(message, publicKey);
        //отправили зашифрованный текст клиенту

        //расшифровали на клиенте
        RSA rsa2 = new RSA(512);
        var res = rsa2.decrypt(enc, privateKey);

        return new SessionKeyResponse(res, rsa.cipherToString(enc), publicKey.toString(), privateKey.toString());
    }

    public EncryptedNoteResponse cfb(String message, String sessionKey) {
        sessionKey = sessionKey.replaceAll("[^a-zA-Z0-9]", "");
        var enc = RealCFB.encrypt(message, sessionKey);
        var dec = RealCFB.decrypt(enc, sessionKey);

        return new EncryptedNoteResponse(dec, enc);
    }

    public EncryptedNoteResponse encryptText(String text, String key) {
        return cfb(text, generateSessionKey());
    }
}
