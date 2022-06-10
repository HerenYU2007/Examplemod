package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.item.Sherlock;

public class ExampleMod implements ModInitializer {

	public final static String MOD_ID = "example";

	@Override
	public void onInitialize(){
		Sherlock.register();
	}
}

