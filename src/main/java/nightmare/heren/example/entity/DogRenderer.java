package nightmare.heren.example.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import nightmare.heren.example.ClientMain;
import nightmare.heren.example.ExampleMod;
public class DogRenderer extends MobEntityRenderer<DogEntity, DogModel> {

    public DogRenderer(EntityRendererFactory.Context context) {
        super(context, new DogModel(context.getPart(ClientMain.MODEL_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(DogEntity entity) {
        return new Identifier(ExampleMod.MOD_ID, "textures/entity/dog.png");
    }
}