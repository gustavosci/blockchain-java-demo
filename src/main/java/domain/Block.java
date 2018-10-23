package domain;

import java.util.Calendar;
import java.util.Date;

import utils.StringUtil;

public class Block {

    private String hash;

    private String previousHash;

    private String data;

    private Long timestamp;

    private Integer nonce; // times for hash generate

    public Block(final String data, final String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timestamp) + data + Integer.toString(nonce));
    }

    public void mineBlock(final Integer difficulty) { // número de "0" necessários para resolver o hash do bloco

        // A dificuldade de mineração desta blockchain é encontrar o primeiro hash com X "0" no início, onde X é difficulty

        final Calendar inicio = Calendar.getInstance();

        final String target = new String(new char[difficulty]).replace('\0', '0');

        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }

        final Calendar fim = Calendar.getInstance();

        System.out.println("Bloco minerado: " + hash);
        System.out.println("Tempo de mineração: " + (fim.getTimeInMillis() - inicio.getTimeInMillis()) + " ms\n");
    }

    public String getHash() {
        return hash;
    }

    public void setData(final String data) {
        this.data = data;
    }
}
