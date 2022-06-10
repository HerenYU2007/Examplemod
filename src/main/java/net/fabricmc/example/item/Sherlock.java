package net.fabricmc.example.item;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sherlock {

    private static Item SHERLOCK = r("sherlock_dog",new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item r(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
    }
    public static void register(){

    }
}
