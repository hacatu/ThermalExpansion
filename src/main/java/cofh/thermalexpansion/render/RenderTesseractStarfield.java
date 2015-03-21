package cofh.thermalexpansion.render;

import cofh.core.render.ShaderHelper;
import cofh.lib.render.RenderHelper;
import cofh.repack.codechicken.lib.render.CCModel;
import cofh.repack.codechicken.lib.render.CCRenderState;
import cofh.thermalexpansion.block.ender.TileTesseract;
import cofh.thermalfoundation.render.shader.ShaderStarfield;
import cpw.mods.fml.client.registry.ClientRegistry;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderTesseractStarfield extends TileEntitySpecialRenderer {

	public static RenderTesseractStarfield instance = new RenderTesseractStarfield();

	public static void register() {

		ClientRegistry.bindTileEntitySpecialRenderer(TileTesseract.class, RenderTesseractStarfield.instance);
	}

	private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
	private static final Random field_147527_e = new Random(0);
	static CCModel modelCenter = CCModel.quadModel(24);
	FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);

	static {

		modelCenter.generateBlock(0, 0.14, 0.14, 0.14, 0.87, 0.87, 0.87).computeNormals();
	}

	public void renderTileEntityAt(World world, double x, double y, double z, float time) {
		field_147527_e.setSeed(31110L);

		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
		GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
		GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
		GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);

		Tessellator tessellator = Tessellator.instance;
		float tran = (world.getTotalWorldTime() % 50000L / 50000F);

		final int end = 8;
		for (int i = 0; i < end; ++i) {
			GL11.glPushMatrix();
			float f5 = end - i;
			float f7 = 1.0F / (f5 + 1.0F);

			if (i == 0) {
				this.bindTexture(field_147529_c);
				f7 = 0.0F;
				f5 = 65.0F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if (i == 1) {
				this.bindTexture(field_147526_d);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			}
			GL11.glTexGen(GL11.GL_S, GL11.GL_EYE_PLANE, this.func_147525_a(1, 0, 0, 0));
			GL11.glTexGen(GL11.GL_T, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 1, 0));
			GL11.glTexGen(GL11.GL_R, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 0, 1));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(0, 1, 0, 0));
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, tran, 0.0F);

			float f11 = (float)field_147527_e.nextDouble() * 0.5F + 0.1F;
			float f12 = (float)field_147527_e.nextDouble() * 0.5F + 0.4F;
			float f13 = (float)field_147527_e.nextDouble() * 0.5F + 0.5F;
			if (i == 0) {
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}
			f13 *= f7;
			f12 *= f7;
			f11 *= f7;

			tessellator.startDrawingQuads();
			tessellator.setColorOpaque_F(f11, f12, f13);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.87);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.87);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.setColorOpaque_F(f11, f12, f13);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.87);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.87);
			tessellator.draw();

			GL11.glTexGen(GL11.GL_S, GL11.GL_EYE_PLANE, this.func_147525_a(1, 0, 0, 0));
			GL11.glTexGen(GL11.GL_T, GL11.GL_EYE_PLANE, this.func_147525_a(0, 1, 0, 0));
			GL11.glTexGen(GL11.GL_R, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 0, 1));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 1, 0));

			tessellator.startDrawingQuads();
			tessellator.setColorOpaque_F(f11, f12, f13);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.14);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.14);

			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.87);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.87);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.87);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.87);
			tessellator.draw();

			GL11.glTexGen(GL11.GL_S, GL11.GL_EYE_PLANE, this.func_147525_a(0, 1, 0, 0));
			GL11.glTexGen(GL11.GL_T, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 1, 0));
			GL11.glTexGen(GL11.GL_R, GL11.GL_EYE_PLANE, this.func_147525_a(0, 0, 0, 1));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(1, 0, 0, 0));

			tessellator.startDrawingQuads();
			tessellator.setColorOpaque_F(f11, f12, f13);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.87);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.87);
			tessellator.addVertex(x + 0.14, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.14, y + 0.14, z + 0.14);
			tessellator.draw();

			GL11.glRotatef(180, 0, 0, 1);

			tessellator.startDrawingQuads();
			tessellator.setColorOpaque_F(f11, f12, f13);
			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.14);
			tessellator.addVertex(x + 0.87, y + 0.87, z + 0.87);
			tessellator.addVertex(x + 0.87, y + 0.14, z + 0.87);
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	private FloatBuffer func_147525_a(float x, float y, float z, float w) {

		this.field_147528_b.clear();
		this.field_147528_b.put(x).put(y).put(z).put(w);
		this.field_147528_b.flip();
		return this.field_147528_b;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

		if (!((TileTesseract) tile).isActive) {
			return;
		}

		if (ShaderStarfield.starfieldShader == 0) {
			renderTileEntityAt(tile.getWorldObj(), x, y, z, 1 - f);
			return;
		}

		GL11.glPushMatrix();

		CCRenderState.changeTexture(ShaderStarfield.starsTexture);

		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glScaled(1 + RenderHelper.RENDER_OFFSET, 1 + RenderHelper.RENDER_OFFSET, 1 + RenderHelper.RENDER_OFFSET);
		ShaderStarfield.alpha = 0;

		ShaderHelper.useShader(ShaderStarfield.starfieldShader, ShaderStarfield.callback);
		CCRenderState.startDrawing();
		RenderTesseract.instance.renderCenter(0, (TileTesseract) tile, -0.5, -0.5, -0.5);
		CCRenderState.draw();
		ShaderHelper.releaseShader();

		GL11.glPopMatrix();
	}
}
