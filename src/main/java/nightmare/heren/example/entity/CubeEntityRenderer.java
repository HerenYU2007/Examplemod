package nightmare.heren.example.entity;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;

public class CubeEntityRenderer<CubeEntityModel extends EntityModel<CubeEntity>> extends MobEntityRenderer<CubeEntity, CubeEntityModel> {

    public CubeEntityRenderer(Object entityRenderDispatcher) {
        super(entityRenderDispatcher, new CubeEntityModel(), 0.5f);
    }

    public CubeEntityRenderer(Object dispatcher) {
        super();
    }

    public CubeEntityRenderer(Object dispatcher) {
        super();
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier("entitytesting", "textures/entity/cube/cube.png");
    }
}