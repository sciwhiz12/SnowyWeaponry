package dev.sciwhiz12.snowyweaponry.datagen;

import dev.sciwhiz12.snowyweaponry.Reference;
import dev.sciwhiz12.snowyweaponry.Reference.Items;
import dev.sciwhiz12.snowyweaponry.SnowyWeaponry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class Languages extends LanguageProvider {
    public Languages(PackOutput output) {
        super(output, SnowyWeaponry.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.snowy_weapons", "Snowy Weapons");

        add(Items.DIAMOND_CHUNK.get(), "Diamond Chunk");
        add(Items.NETHERITE_NUGGET.get(), "Netherite Nugget");

        add(Items.IRON_CORED_SNOWBALL.get(), "Iron-cored Snowball");
        add(Items.GOLD_CORED_SNOWBALL.get(), "Gold-cored Snowball");
        add(Items.DIAMOND_CORED_SNOWBALL.get(), "Diamond-cored Snowball");
        add(Items.NETHERITE_CORED_SNOWBALL.get(), "Netherite-cored Snowball");
        add(Items.EXPLOSIVE_SNOWBALL.get(), "Explosive Snowball");

        add(Items.WAFER_CONE.get(), "Wafer Cone");
        add(Items.SNOW_CONE.get(), "Snow Cone");
        add(Items.GOLDEN_SNOW_CONE.get(), "Golden Snow Cone");
        add(Items.POTION_SNOW_CONE.get(), "Dipped Snow Cone");

        add(Reference.EntityTypes.CORED_SNOWBALL.get(), "Snowball");

        addDamageSourceTranslations();

        addPotionConeTranslations();
    }

    void addPotionConeTranslations() {
        final String baseTranslationKey = Items.POTION_SNOW_CONE.get().getDescriptionId();

        add(baseTranslationKey + ".effect.awkward", "Awkward Dipped Snow Cone");
        add(baseTranslationKey + ".effect.empty", "Uncraftable Dipped Snow Cone");
        add(baseTranslationKey + ".effect.fire_resistance", "Snow Cone of Fire Resistance");
        add(baseTranslationKey + ".effect.harming", "Snow Cone of Harming");
        add(baseTranslationKey + ".effect.healing", "Snow Cone of Healing");
        add(baseTranslationKey + ".effect.infested", "Snow Cone of Infestation");
        add(baseTranslationKey + ".effect.invisibility", "Snow Cone of Invisibility");
        add(baseTranslationKey + ".effect.leaping", "Snow Cone of Leaping");
        add(baseTranslationKey + ".effect.levitation", "Snow Cone of Levitation");
        add(baseTranslationKey + ".effect.luck", "Lucky Snow Cone");
        add(baseTranslationKey + ".effect.mundane", "Mundane Dipped Snow Cone");
        add(baseTranslationKey + ".effect.night_vision", "Snow Cone of Night Vision");
        add(baseTranslationKey + ".effect.oozing", "Snow Cone of Oozing");
        add(baseTranslationKey + ".effect.poison", "Poisonous Snow Cone");
        add(baseTranslationKey + ".effect.regeneration", "Snow Cone of Regeneration");
        add(baseTranslationKey + ".effect.slow_falling", "Snow Cone of Slow Falling");
        add(baseTranslationKey + ".effect.slowness", "Snow Cone of Slowness");
        add(baseTranslationKey + ".effect.strength", "Snow Cone of Strength");
        add(baseTranslationKey + ".effect.swiftness", "Snow Cone of Swiftness");
        add(baseTranslationKey + ".effect.thick", "Thick Dipped Snow Cone");
        add(baseTranslationKey + ".effect.turtle_master", "Snow Cone of the Turtle Master");
        add(baseTranslationKey + ".effect.water", "Wet Snow Cone");
        add(baseTranslationKey + ".effect.water_breathing", "Snow Cone of Water Breathing");
        add(baseTranslationKey + ".effect.weakness", "Snow Cone of Weakness");
        add(baseTranslationKey + ".effect.weaving", "Snow Cone of Weaving");
        add(baseTranslationKey + ".effect.wind_charged", "Snow Cone of Wind Charging");
    }

    void addDamageSourceTranslations() {
        // Regular -cored snowballs
        add("death.attack.snowball", "%1$s was snowballed to death by %2$s");
        add("death.attack.snowball.item", "%1$s was snowballed to death by %2$s using %3$s");

        // Explosive snowballs
        add("death.attack.snowball.explosion", "%1$s blew up into icy pieces");
        add("death.attack.snowball.explosion.player", "%1$s was blown to icy pieces by %2$s");
        add("death.attack.snowball.explosion.player.item", "%1$s was blown to icy pieces by %2$s using %3$s");
    }
}
