package nightmare.heren.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import nightmare.heren.example.entity.DogModel;
import nightmare.heren.example.entity.DogRenderer;

@Environment(EnvType.CLIENT)
public class ClientMain implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "cube"), "main");
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ExampleMod.DOG, DogRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, DogModel::getTexturedModelData);
    }
}