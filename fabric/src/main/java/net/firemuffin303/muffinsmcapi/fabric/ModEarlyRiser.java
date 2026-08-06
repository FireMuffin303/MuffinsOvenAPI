package net.firemuffin303.muffinsmcapi.fabric;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.firemuffin303.muffinsmcapi.fabric.api.IRecipeBookInitializer;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ModEarlyRiser implements Runnable{
    @Override
    public void run() {
        List<IRecipeBookInitializer> iRecipeBookInitializerList = FabricLoader.getInstance().getEntrypoints("ovenapi_recipe", IRecipeBookInitializer.class);

        iRecipeBookInitializerList.forEach(iRecipeBookInitializer -> iRecipeBookInitializer.register(OvenRecipeBookRegistry.INSTANCE));

        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String itemstack = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_1799") + ';';
        String recipeBookType = remapper.mapClassName("intermediary","net.minecraft.class_5421");
        String recipeBookCategories = remapper.mapClassName("intermediary","net.minecraft.class_314");


        OvenRecipeBookRegistry.INSTANCE.getRecipeBook().forEach((typeID,recipe) -> {
            ClassTinkerers.enumBuilder(recipeBookType).addEnum(typeID).build();
        });

        OvenRecipeBookRegistry.INSTANCE.getRecipeCategory().forEach(category -> {
            ClassTinkerers.enumBuilder(recipeBookCategories, ItemStack[].class)
                    .addEnum(category.id(), () -> new Object[]{ category.stacks().get().stream().map(ItemStack::new).toArray(ItemStack[]::new) }).build();
        });
    }
}
