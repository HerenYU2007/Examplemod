package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import nightmare.heren.example.block.modBlocks;
import nightmare.heren.example.item.modItems;

public class ExampleMod implements ModInitializer {

	public final static String MOD_ID = "example";

	@Override
	public void onInitialize(){
		modItems.register();
		modBlocks.DogBlockRegister();
	}
}

