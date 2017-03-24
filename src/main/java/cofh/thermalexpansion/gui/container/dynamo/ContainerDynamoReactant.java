package cofh.thermalexpansion.gui.container.dynamo;

import cofh.lib.gui.slot.ISlotValidator;
import cofh.lib.gui.slot.SlotValidated;
import cofh.thermalexpansion.block.dynamo.TileDynamoReactant;
import cofh.thermalexpansion.gui.container.ContainerTEBase;
import cofh.thermalexpansion.util.fuels.ReactantManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerDynamoReactant extends ContainerTEBase implements ISlotValidator {

	TileDynamoReactant myTile;

	public ContainerDynamoReactant(InventoryPlayer inventory, TileEntity tile) {

		super(inventory, tile);

		myTile = (TileDynamoReactant) tile;
		addSlotToContainer(new SlotValidated(this, myTile, 0, 44, 35));
	}

	@Override
	public boolean isItemValid(ItemStack stack) {

		return ReactantManager.validReactant(stack);
	}

}
