package chiefarug.mods.manastorm_reactor;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("manastorm_reactor")
public class ManaStormReactor {
    public static final ForgeConfigSpec.DoubleValue FACTOR;
    private static final ForgeConfigSpec config;
    static {
        var builder = new ForgeConfigSpec.Builder();
        FACTOR = builder
                .comment("The factor that is applied to a mana storm's burst entities mana contents")
                .defineInRange("factor", 10d, 1d / 300, 1000);
        config = builder.build();
    }

    public ManaStormReactor() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, config);
    }
}
