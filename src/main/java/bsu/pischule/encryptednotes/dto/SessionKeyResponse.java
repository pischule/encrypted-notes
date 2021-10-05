package bsu.pischule.encryptednotes.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class SessionKeyResponse {
    private String sessionKey;
    private String encryptedSessionKey;
    private String publicKey;
    private String privateKey;
}
