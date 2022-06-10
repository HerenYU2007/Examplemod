package nightmare.heren.example;

import net.fabricmc.api.ModInitializer;
import nightmare.heren.example.block.dogBlocks;
import nightmare.heren.example.item.sherlockItem;

public class ExampleMod implements ModInitializer {

	public final static String MOD_ID = "example";

	@Override
	public void onInitialize(){
		sherlockItem.register();
		dogBlocks.DogBlockRegister();
	}
}

