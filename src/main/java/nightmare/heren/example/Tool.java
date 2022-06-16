package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Tool implements ModInitializer {
    private static final FabricItemSettings group = new FabricItemSettings().group(ModItemGroup.EXAMPLE);

    public static Item regItem(String name) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, name), new Item(group));
    }
    public static Item regEgg(String name,Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
    }

    public static final Block regBlock(String name, Block block) {
        Identifier i = new Identifier(ExampleMod.MOD_ID, name);
        Registry.register(Registry.ITEM, i, new BlockItem(block, group));
        return Registry.register(Registry.BLOCK, i, block);
    }

    @Override
    public void onInitialize() {

    }
}
