package nightmare.heren.example.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import nightmare.heren.example.Tool;

public class ModBlocks {
    public final static Block DOG_BLOCK = Tool.regBlock("dog_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));
    public final static Block TINA_BLOCK = Tool.regBlock("tina_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));

    public static void registerModBlocks() {
        //add strength and require tool
        //strength can be higher
        Tool.regBlock("gold_dog_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));

        Tool.regBlock("modicon_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(10f).requiresTool()));

        Tool.regBlock("shit_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool()));
    }
}