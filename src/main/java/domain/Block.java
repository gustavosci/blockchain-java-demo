package domain;

import java.util.Date;

import utils.StringUtil;

public class Block {

    private String hash;

    private String previousHash;

    private String data; // simple message

    private Long timestamp;

    public Block(final String data, final String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    private String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timestamp) + data);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(final String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(final String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }
}
