package domain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {

        try {

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            final ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            keyGen.initialize(ecSpec, random);

            final KeyPair keyPair = keyGen.generateKeyPair();

            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}
