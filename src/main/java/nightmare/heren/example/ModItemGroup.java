package nightmare.heren.example;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nightmare.heren.example.item.ModItems;

public class ModItemGroup {
    public static final ItemGroup EXAMPLE = FabricItemGroupBuilder.build(new Identifier(ExampleMod.MOD_ID, "example"),
            () -> new ItemStack(ModItems.MODICON));
}
