package defeatedcrow.hac.main.api.orevein;

public enum EnumVein {
	RED(0, 5, 80),
	GREEN(1, 4, 25),
	BLUE(2, 4, 25),
	WHITE(3, 4, 25),
	BLACK(4, 3, 10),
	HIGH_RED(5, 5, 140),
	GUANO(8, 4, 25),
	SKARN(9, 8, 50),
	SKARN_UNDER(10, 8, 20),
	NETHER(11, 5, 25);

	public static final EnumVein[] VALUES = {
			RED,
			GREEN,
			BLUE,
			WHITE,
			BLACK,
			HIGH_RED,
			GUANO,
			SKARN,
			SKARN_UNDER,
			NETHER
	};

	public final int id;
	public final int range;
	public final int height;

	EnumVein(int i, int r, int h) {
		id = i;
		range = r;
		height = h;
	}

	public static EnumVein getType(String name) {
		for (EnumVein ret : VALUES) {
			if (ret.name().equalsIgnoreCase(name)) {
				return ret;
			}
		}
		return WHITE;
	}
}
