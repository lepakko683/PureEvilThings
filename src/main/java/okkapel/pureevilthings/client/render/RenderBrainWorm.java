package okkapel.pureevilthings.client.render;

import okkapel.pureevilthings.entity.EntityBrainWorm;
import okkapel.pureevilthings.ref.Reference;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBrainWorm extends Render {
	private static ResourceLocation tex = new ResourceLocation(Reference.MODID, "textures/entity/brainWorm.png");
	
	
	@Override
	public void doRender(Entity e, double x, double y, double z, float f1, float f2) {
		render((EntityBrainWorm)e, x, y, z, f1, f2);
	}
	
	private void render(EntityBrainWorm e, double x, double y, double z, float f1, float f2) {
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(tex);
		
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		
		// +X
		t.addVertexWithUV(1d, -1d, -1d, 0f, 1f);
		t.addVertexWithUV(1d, -1d, 1d, 0f, 1f);
		t.addVertexWithUV(1d, 1d, 1d, 0f, 1f);
		t.addVertexWithUV(1d, 1d, -1d, 0f, 1f);
		
		// -X
		t.addVertexWithUV(-1d, -1d, -1d, 0f, 1f);
		t.addVertexWithUV(-1d, -1d, 1d, 0f, 1f);
		t.addVertexWithUV(-1d, 1d, 1d, 0f, 1f);
		t.addVertexWithUV(-1d, 1d, -1d, 0f, 1f);
		
		t.draw();
		
		GL11.glPopMatrix();
	}
	

	@Override
	protected ResourceLocation getEntityTexture(Entity ent) {
		return tex;
	}

}
