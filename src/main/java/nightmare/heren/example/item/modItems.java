package nightmare.heren.example.item;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import nightmare.heren.example.ExampleMod;
import nightmare.heren.example.Tool;

public class ModItems {

   public static Item MODICON = Tool.regItem("modicon");
   public static Item SHIT=Tool.regItem("shit");
   public static void ModItemRegister(){
       //英杰狗
       Tool.regItem("sherlockdog");
       Tool.regEgg("dog_egg",new SpawnEggItem(ExampleMod.DOG, 12895428, 11382189, new Item.Settings().group(ExampleMod.GROUP)));
   }
}
