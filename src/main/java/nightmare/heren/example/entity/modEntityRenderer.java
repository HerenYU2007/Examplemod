package nightmare.heren.example.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/*
 * 一个用来提供模型、阴影大小和贴图的渲染器
 */
public class modEntityRenderer extends MobEntityRenderer<modEntity, modEntityModel> {

    public modEntityRenderer(Object dispatcher) {
        super((EntityRendererFactory.Context) dispatcher, new modEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(modEntity entity) {
        return new Identifier("entitytesting", "textures/entity/cube/cube.png");
    }
}