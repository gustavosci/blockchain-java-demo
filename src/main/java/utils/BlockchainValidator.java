package utils;

import java.util.ArrayList;
import java.util.List;

import domain.Block;

public class BlockchainValidator {

    public Boolean isChainValid(final List<Block> blockchain) {

        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Hash do bloco atual (" + i + ") é inválido!");
                return false;
            }

            if (!previousBlock.getHash().equals(previousBlock.calculateHash())) {
                System.out.println("Hash do bloco anterior (" + (i - 1) + ") é inválido!");
                return false;
            }
        }

        return true;
    }
}
