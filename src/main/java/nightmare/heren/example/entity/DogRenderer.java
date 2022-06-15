package nightmare.heren.example.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/*
 * 一个用来提供模型、阴影大小和贴图的渲染器
 */
public class DogRenderer extends MobEntityRenderer<DogEntity, DogModel> {

    public DogRenderer(EntityRendererFactory.Context context) {
        super(context, new DogModel(context.getPart(EntityTestingClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(DogEntity entity) {
        return new Identifier("entitytesting", "textures/entity/cube/cube.png");
    }
}