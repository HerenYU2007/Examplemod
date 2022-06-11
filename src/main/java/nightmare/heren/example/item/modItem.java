package nightmare.heren.example.item;

import nightmare.heren.example.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class modItem {

    private static Item r(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
    }

    private static final Item SHERLOCKDOG = r("sherlockdog",  //英杰狗
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));







    public static void register(){
    }
}
