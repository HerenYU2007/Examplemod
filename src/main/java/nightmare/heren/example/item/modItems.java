package nightmare.heren.example.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nightmare.heren.example.ExampleMod;
import nightmare.heren.example.ModItemGroup;

public class modItems {

   public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
    }

   public static Item MODICON = registerItem("modicon",  //英杰狗
            new Item(new FabricItemSettings().group(ModItemGroup.EXAMPLE)));

   public static void ModItemRegister(){
       Item SHERLOCKDOG = registerItem("sherlockdog",
               new Item(new FabricItemSettings().group(ModItemGroup.EXAMPLE)));

   }







    public static void register(){
    }
}
