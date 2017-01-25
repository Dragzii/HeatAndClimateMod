package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemMeatMaterials extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"viscera",
			"rennet" };

	public ItemMeatMaterials() {
		super();
		maxMeta = 1;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/food_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}