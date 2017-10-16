package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.climate.recipe.FluidCraftRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FoodFluidRecipe {

	public static void load() {
		loadFluidRecipes();
		loadCookingRecipes();
	}

	static void loadFluidRecipes() {

		regNonFoodrecipe(null, null, 0F, new FluidStack(FoodInit.hotSpring, 1000), DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSalt"
				});

		regNonFoodrecipe(null, null, 0F, new FluidStack(FoodInit.hotSpring, 1000), DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"dustSulfur"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood", "pulpWood", "dustAsh"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood", "pulpWood", "dustLime"
				});

		regNonFoodrecipe(new ItemStack(Items.PAPER, 4, 0), null, 0F, new FluidStack(FoodInit.blackLiquor, 50),
				DCHeatTier.KILN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"pulpWood", "pulpWood", "dustAlkali"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustAsh"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustLime"
				});

		regNonFoodrecipe(new ItemStack(MainInit.repairPutty, 1, 2), null, 0F, null, DCHeatTier.NORMAL, null, null,
				false, new FluidStack(FoodInit.oil, 500), new Object[] {
						"dustAlkali"
				});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1, 17), null, 0F, null, DCHeatTier.KILN, null, null, false, null,
				new Object[] {
						"dustBismuth"
				});

		regNonFoodrecipe(new ItemStack(MainInit.gems, 1, 18), null, 0F, null, DCHeatTier.KILN, null, null, false, null,
				new Object[] {
						"dustApatite"
				});

		regNonFoodrecipe(new ItemStack(MainInit.miscDust, 1, 9), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						new ItemStack(Items.DYE, 1, 15)
				});

		regNonFoodrecipe(new ItemStack(Items.SLIME_BALL, 1, 0), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"cropHerb", "dustCrystal", new ItemStack(MachineInit.reagent, 1, 1)
				});

		regNonFoodrecipe(new ItemStack(FoodInit.meat, 1, 4), null, 0F, null, DCHeatTier.OVEN, null, null, false,
				new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"itemLeather"
				});

	}

	static void loadCookingRecipes() {
		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.cream, 200),
					DCHeatTier.NORMAL, null, null, false, new FluidStack(MainInit.milk, 1000), (Object[]) null);
		}

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.tomatoJuice, 200),
				DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"listAllveggie"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.coffee, 1000), DCHeatTier.OVEN,
				null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 0)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.greenTea, 1000),
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.blackTea, 1000),
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 2)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.stock, 1000), DCHeatTier.OVEN,
				null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodAnyMeat", "cropHerb", "listAllveggie"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FluidRegistry.WATER, 1000),
				DCHeatTier.HOT, null, null, false, null, new Object[] {
						new ItemStack(Blocks.ICE, 1, 0)
				});

		FluidCraftRecipe salt = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.HOT, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		RecipeAPI.registerFluidRecipes.addRecipe(salt, DCHeatTier.HOT);

		FluidCraftRecipe salt2 = new FluidCraftRecipe(new ItemStack(MainInit.foodMaterials, 1, 0), null, null,
				DCHeatTier.KILN, DCHumidity.DRY, null, 0, false, new FluidStack(FluidRegistry.WATER, 1000),
				(Object[]) null);
		salt2.requiredHum().add(DCHumidity.NORMAL);
		salt2.requiredHum().add(DCHumidity.WET);
		RecipeAPI.registerFluidRecipes.addRecipe(salt2, DCHeatTier.KILN);

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt", new ItemStack(Items.ROTTEN_FLESH)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt", new ItemStack(Items.BEEF)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.DRY, null, false, null, new Object[] {
						"dustSalt", new ItemStack(Items.PORKCHOP)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt", new ItemStack(Items.EGG)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt", "cropHerb", "foodAnyMeat"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(MainInit.bakedApple, 3, 3), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 100), new Object[] {
						"dustSalt", "cropHerb", "foodViscera"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 0), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"seedCoffee"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, null, new Object[] {
						"cropTea"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.teaLeaves, 1, 2), null, 0F, null,
				DCHeatTier.WARM, DCHumidity.WET, null, false, null, new Object[] {
						new ItemStack(FoodInit.teaLeaves, 1, 1)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 0), null, 0F, null, DCHeatTier.WARM,
				DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSalt"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 1), null, 0F, null, DCHeatTier.WARM,
				DCHumidity.WET, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"foodRennet"
				});

		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.dairy, 1, 1), null, 0F, null,
					DCHeatTier.WARM, DCHumidity.WET, null, false, new FluidStack(MainInit.milk, 1000), new Object[] {
							"foodRennet"
					});
		}

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.ricebowl, 1, 0), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice", new ItemStack(Blocks.BROWN_MUSHROOM)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.ricebowl, 1, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 200), new Object[] {
						"foodRice", new ItemStack(Blocks.RED_MUSHROOM)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 0), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropOnion", "cropCarrot", "listAllveggie"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"egg", "cropSpinach"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 1), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"egg", "cropSeaweed"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodRice", "foodCheese"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 2), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
						"foodRice", "cropHerb"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 3), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodRice", "cropTomato"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropPumpkin", "cropOnion", "foodCream"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 4), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropPumpkin", "cropOnion", "foodButter"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 5), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"listAllveggie", "foodAnyMeat", "cropBeetroot"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0), new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
						"foodCream"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"foodCream", new ItemStack(Items.CHORUS_FRUIT, 1, 0)
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 8), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropSpinach", "cropLotusRoot"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 9), null, 0F, null,
				DCHeatTier.OVEN, null, null, false, new FluidStack(FoodInit.stock, 1000), new Object[] {
						"cropTomato", "foodRice", "fishSquid"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, DCHeatTier.OVEN,
				null, null, false, new FluidStack(FoodInit.coffee, 1000), new Object[] {
						"dustSugar", "foodCream", "foodAgar"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.cake, 3, 3), null, 0F, null, DCHeatTier.OVEN,
				null, null, false, new FluidStack(FoodInit.coffee, 1000), new Object[] {
						"dustSugar", "foodCream", "foodGelatine"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, DCHeatTier.OVEN,
				null, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSugar", "cropLemon", "foodAgar"
				});

		RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.cake, 3, 4), null, 0F, null, DCHeatTier.OVEN,
				null, null, false, new FluidStack(FoodInit.cream, 1000), new Object[] {
						"dustSugar", "cropLemon", "foodGelatine"
				});

		if (MainInit.milk != null) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 6), null, 0F, null,
					DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.milk, 1000), new Object[] {
							new ItemStack(Blocks.BROWN_MUSHROOM, 1, 0), new ItemStack(Blocks.RED_MUSHROOM, 1, 0),
							"foodButter"
					});

			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.bowlSoup, 3, 7), null, 0F, null,
					DCHeatTier.OVEN, null, null, false, new FluidStack(MainInit.milk, 1000), new Object[] {
							"cropOnion", new ItemStack(Items.CHORUS_FRUIT, 1, 0)
					});
		}

		if (!DCIntegrationCore.loadedBoP) {
			RecipeAPI.registerFluidRecipes.addRecipe(new ItemStack(FoodInit.meat, 1, 3), null, 0F, null,
					DCHeatTier.COLD, DCHumidity.DRY, null, false, null, new Object[] {
							"cropSeaweed"
					});

			RecipeAPI.registerFluidRecipes.addRecipe(null, null, 0F, new FluidStack(FoodInit.stock, 1000),
					DCHeatTier.OVEN, null, null, false, new FluidStack(FluidRegistry.WATER, 1000), new Object[] {
							"cropKelp"
					});
		}

	}

	static void regNonFoodrecipe(ItemStack out, ItemStack sec, float chance, FluidStack outF, DCHeatTier heat,
			DCHumidity hum, DCAirflow air, boolean cooling, FluidStack inF, Object... input) {
		RecipeAPI.registerFluidRecipes.addRecipe(out, sec, chance, outF, heat, hum, air, cooling, inF, input);
		RecipeAPI.registerReactorRecipes.addRecipe(out, sec, chance, outF, null, heat, null, inF, null, input);
	}

}