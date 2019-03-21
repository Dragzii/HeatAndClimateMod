package defeatedcrow.hac.main.worldgen;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.recipes.BlockContainerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class CaravanGenEvent {

	Random rand = new Random();

	@SubscribeEvent
	public void populate(PopulateChunkEvent.Post event) {
		if (event.getWorld().provider.getDimension() == 0) {
			if (!event.isHasVillageGenerated() && CaravanGenPos.isSuitableChunk(event.getChunkX(), event
					.getChunkZ(), event.getWorld()) && CaravanGenPos.getCaravanPartNum(event.getChunkX(), event
							.getChunkZ(), event.getWorld()) > -1) {
				// DCLogger.infoLog("caravan gen: x " + event.getChunkX() + ", z " + event.getChunkZ());
				WorldGenCaravanBase wgn = new WorldGenCaravanBase();
				wgn.generate(event.getRand(), event.getChunkX(), event.getChunkZ(), event.getWorld(), event
						.getGen(), event.getWorld().getChunkProvider());
			}
		}
	}

	@SubscribeEvent
	public void onLoad(ChunkEvent.Load event) {
		if (event.getWorld().provider.getDimension() == 0) {
			if (event.getChunk().isTerrainPopulated() && !event.getWorld().isRemote) {
				World world = event.getWorld();
				Chunk chunk = event.getChunk();
				int cx = chunk.x;
				int cz = chunk.z;
				int px = cx << 4;
				int pz = cz << 4;
				int py = 68;
				BlockPos pos = new BlockPos(px, py, pz);
				EnumSeason season = DCTimeHelper.getSeasonEnum(world);
				IBlockState s1 = world.getBlockState(pos.add(8, -7, 8));
				IBlockState s2 = world.getBlockState(pos.add(7, -7, 7));
				IBlockState s3 = world.getBlockState(pos.add(8, -4, 8));
				IBlockState s4 = world.getBlockState(pos.add(7, -4, 7));
				CaravanType type = CaravanType.getType(s2.getBlock());
				if (type == CaravanType.BROKEN) {
					type = CaravanType.getType(s4.getBlock());
				}
				int num = CaravanGenPos.getCaravanPartNum(cx, cz, world);

				if (num >= 0 && getSeason(s1) != null && type != CaravanType.BROKEN) {
					if (type == CaravanType.STANDBY || type == CaravanType.UNINIT) {
						if (num == 4) {
							Village village = world.getVillageCollection().getNearestVillage(pos.add(7, 0, 7), 16);
							if (village == null || village.getNumVillagers() < 10) {
								ForgeRegistry registry = (ForgeRegistry) ForgeRegistries.VILLAGER_PROFESSIONS;
								int id = registry.getID(MainInit.trader);
								EntityVillager vil1 = new EntityVillager(world, id);
								vil1.setPosition(px, 69, pz);
								world.spawnEntity(vil1);
								EntityVillager vil2 = new EntityVillager(world, id);
								vil2.setPosition(px + 1D, 69, pz);
								world.spawnEntity(vil2);
								EntityLlama lla = new EntityLlama(world);
								lla.setPosition(px + 3D, 69, pz);
								world.spawnEntity(lla);
							} else {
								List<Entity> list = world.getEntitiesInAABBexcluding(null, new AxisAlignedBB(pos
										.add(-5, 0, -5), pos.add(20, 1, 20)), EntitySelectors.IS_ALIVE);
								EntityVillager vil1 = null;
								EntityVillager vil2 = null;
								for (Entity e : list) {
									if (e instanceof EntityVillager) {
										if (vil1 == null) {
											vil1 = (EntityVillager) e;
										} else if (vil2 == null) {
											vil2 = (EntityVillager) e;
										} else {
											break;
										}
									}
								}
								if (vil1 != null) {
									vil1.setDead();
								}
								if (vil2 != null) {
									vil2.setDead();
								}
							}
						} else {
							int cx2 = (num % 3) - 1;
							int cz2 = (num / 3) - 1;
							int[] room = CaravanGenPos.getRoomNum(cx + cx2 - 1, cz + cz2 - 1, world);
							int gate = room[0] * 2 + 1;

							EnumFacing face = EnumFacing.SOUTH;
							if (num == 7) {
								face = EnumFacing.NORTH;
							}
							if (num == 3) {
								face = EnumFacing.EAST;
							}
							if (num == 5) {
								face = EnumFacing.WEST;
							}

							int t = num / 2;
							if ((num & 1) == 1 && num != gate) {
								switch (room[t]) {
								case 1:
									updateInterior0(world, pos, rand, face);
									break;
								case 3:
									updateInterior1(world, pos, rand, face);
									break;
								default:
								}
							}
						}
						DCLogger.debugLog("Caravanserai Updated: " + cx + ", " + cz);
						world.setBlockState(pos.add(7, -7, 7), Blocks.DIAMOND_BLOCK.getDefaultState(), 2);
					} else if (getSeason(s1) != season) {
						Village village = null;
						List<Village> villages = world.getVillageCollection().getVillageList();
						for (Village v : villages) {
							if (v.getCenter().distanceSq(pos.add(7, 0, 7)) < 32D * 32D) {
								village = v;
								break;
							}
						}
						if (village != null) {
							DCLogger.debugLog("Caravanserai Stand-By: " + cx + ", " + cz);
							world.setBlockState(pos.add(7, -7, 7), Blocks.EMERALD_BLOCK.getDefaultState(), 2);
						} else {
							DCLogger.debugLog("Broken Caravanserai: " + cx + ", " + cz);
							world.setBlockState(pos.add(7, -7, 7), Blocks.LAPIS_BLOCK.getDefaultState(), 2);
						}
						world.setBlockState(pos.add(8, -7, 8), MainInit.gemBlock.getDefaultState()
								.withProperty(DCState.TYPE16, season.id), 2);
					}

				} else if (type == CaravanType.BROKEN) {
					if (world.getBlockState(pos.add(7, -7, 7)).getBlock() == Blocks.LAPIS_BLOCK || world
							.getBlockState(pos.add(7, -4, 7)).getBlock() == Blocks.LAPIS_BLOCK) {
						Village village = null;
						List<Village> villages = world.getVillageCollection().getVillageList();
						for (Village v : villages) {
							if (v.getCenter().distanceSq(pos.add(7, 0, 7)) < 32D * 32D) {
								village = v;
								break;
							}
						}
						if (village != null) {
							DCLogger.debugLog("Caravanserai Reconstructed: " + cx + ", " + cz);
							world.setBlockState(pos.add(7, -7, 7), Blocks.EMERALD_BLOCK.getDefaultState(), 2);
						}
						world.setBlockState(pos.add(8, -7, 8), MainInit.gemBlock.getDefaultState()
								.withProperty(DCState.TYPE16, season.id), 2);
					}
				}
			}
		}
	}

	private void updateInterior0(World world, BlockPos pos, Random rand, EnumFacing face) {
		int[] i = {
				0,
				3,
				7,
				11,
				14
		};
		for (int x : i) {
			BlockPos p1 = rotate(pos, x, 1, 8, face);
			BlockPos p2 = rotate(pos, x + 1, 1, 8, face);
			IBlockState cont = BlockContainerUtil.INS.getRondomContainer2(rand);
			world.setBlockState(p1, cont, 2);
			world.setBlockState(p2, cont, 2);
			if (rand.nextBoolean()) {
				world.setBlockState(p1.up(), cont, 2);
			}
			if (rand.nextBoolean()) {
				world.setBlockState(p2.up(), cont, 2);
			}
			if (rand.nextBoolean()) {
				BlockPos p3 = rotate(pos, x, 1, 7, face);
				world.setBlockState(p3, cont, 2);
			}
		}
	}

	private void updateInterior1(World world, BlockPos pos, Random rand, EnumFacing face) {
		BlockPos p1 = rotate(pos, 0, 1, 9, face);
		TileEntity tile1 = world.getTileEntity(p1);
		if (tile1 != null && tile1 instanceof TileEntityChest) {
			((TileEntityChest) tile1).setLootTable(LootTableList.CHESTS_ABANDONED_MINESHAFT, rand.nextLong());
			((TileEntityChest) tile1).fillWithLoot(null);
		}

		BlockPos p2 = rotate(pos, 15, 1, 9, face);
		TileEntity tile2 = world.getTileEntity(p2);
		if (tile2 != null && tile2 instanceof TileEntityChest) {
			((TileEntityChest) tile2).setLootTable(LootTableList.CHESTS_VILLAGE_BLACKSMITH, rand.nextLong());
			((TileEntityChest) tile2).fillWithLoot(null);
		}

		for (int x = 5; x < 11; x++) {
			BlockPos p3 = rotate(pos, x, 1, 9, face);
			IBlockState cont = BlockContainerUtil.INS.getRondomContainer1(rand);
			world.setBlockState(p3, cont, 2);
			if (rand.nextBoolean()) {
				BlockPos p4 = rotate(pos, x, 2, 9, face);
				world.setBlockState(p4, cont, 2);
			}
			if (rand.nextBoolean()) {
				BlockPos p4 = rotate(pos, x, 1, 8, face);
				IBlockState cont2 = BlockContainerUtil.INS.getRondomContainer1(rand);
				world.setBlockState(p4, cont2, 2);
			}
		}

	}

	private BlockPos rotate(BlockPos pos, int x, int y, int z, EnumFacing face) {
		int x1 = x;
		int y1 = y;
		int z1 = z;
		if (face == EnumFacing.NORTH) {
			x1 = 15 - x;
			z1 = 15 - z;
		}
		if (face == EnumFacing.EAST) {
			x1 = z;
			z1 = 15 - x;
		}
		if (face == EnumFacing.WEST) {
			x1 = 15 - z;
			z1 = x;
		}
		return pos.add(x1, y1, z1);
	}

	public enum CaravanType {
		UNINIT,
		STANDBY,
		LOADED,
		BROKEN;

		public static CaravanType getType(Block block) {
			if (block == Blocks.IRON_BLOCK) {
				return UNINIT;
			} else if (block == Blocks.DIAMOND_BLOCK) {
				return LOADED;
			} else if (block == Blocks.EMERALD_BLOCK) {
				return STANDBY;
			} else {
				return BROKEN;
			}
		}
	}

	public EnumSeason getSeason(IBlockState block) {
		if (block.getBlock() instanceof DCSimpleBlock) {
			int v = block.getValue(DCState.TYPE16);
			for (EnumSeason s : EnumSeason.values()) {
				if (s.id == v) {
					return s;
				}
			}
		}
		return null;
	}

}
