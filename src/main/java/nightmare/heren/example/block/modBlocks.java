package nightmare.heren.example.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import nightmare.heren.example.Tool;

public class modBlocks {
    //以下注册方块
    public static void registerModBlocks() {
        //add strength and require tool
        //strength can be higher
        Block DOG_BLOCK = Tool.regBlock("dog_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));

        Block TINA_BLOCK = Tool.regBlock("tina_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));

        Block GOLDDOGBLOCK = Tool.regBlock("gold_dog_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(5f).requiresTool()));

        Block MODICONBLOCK = Tool.regBlock("modicon_block",
                new Block(FabricBlockSettings.of(Material.METAL).strength(10f).requiresTool()));
    }
}
