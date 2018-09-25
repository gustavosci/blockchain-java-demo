import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

import domain.Block;

public class BlockchainDemo {

    private static final Integer DIFFICULTY_MINE_BLOCK = 5;

    public static List<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {

        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(DIFFICULTY_MINE_BLOCK);

        blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(DIFFICULTY_MINE_BLOCK);

        blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(DIFFICULTY_MINE_BLOCK);

        System.out.println("\nEsta blockchain é válida? " + isChainValid());

        System.out.println("Prazer, eu sou a blockchain:\n");
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

        System.out.println("\nAdulterando a blockchain...");
        blockchain.get(1).setData("New data");
        System.out.println("\nEsta blockchain é válida? " + isChainValid());
    }

    public static Boolean isChainValid() {

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
