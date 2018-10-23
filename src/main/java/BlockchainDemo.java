import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

import domain.Block;
import utils.BlockchainValidator;

public class BlockchainDemo {

    private static final Integer DIFFICULTY_MINE_BLOCK = 5; // proof to work

    private static List<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {

        blockchain.add(new Block("Oi, eu sou o primeiro bloco", "0"));
        System.out.println("Tentando minerar o bloco 1... ");
        blockchain.get(0).mineBlock(DIFFICULTY_MINE_BLOCK);

        blockchain.add(new Block("Oi, eu sou o segundo bloco", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Tentando minerar o bloco 2... ");
        blockchain.get(1).mineBlock(DIFFICULTY_MINE_BLOCK);

        blockchain.add(new Block("Eu sou o terceiro bloco, tchau :(", blockchain.get(blockchain.size() - 1).getHash()));
        System.out.println("Tentando minerar o bloco 3... ");
        blockchain.get(2).mineBlock(DIFFICULTY_MINE_BLOCK);

        final BlockchainValidator validator = new BlockchainValidator();

        System.out.println("\nEsta blockchain é válida? " + validator.isChainValid(blockchain));

        System.out.println("Prazer, eu sou a blockchain:\n");
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

        System.out.println("\nAdulterando a blockchain...");
        blockchain.get(1).setData("New data");
        System.out.println("\nEsta blockchain é válida? " + validator.isChainValid(blockchain));
    }

}
