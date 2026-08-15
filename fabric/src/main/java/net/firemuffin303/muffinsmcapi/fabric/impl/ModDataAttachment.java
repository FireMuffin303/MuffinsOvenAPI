package net.firemuffin303.muffinsmcapi.fabric.impl;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.minecraft.resources.ResourceLocation;

public class ModDataAttachment {

    public static final AttachmentType<ResourceLocation> OVEN_BOAT_TYPE = AttachmentRegistry.create(
            MuffinsMcAPI.modid("oven_boat_type"),
            builder -> builder.initializer(() -> ResourceLocation.parse(""))
                    .persistent(ResourceLocation.CODEC)
                    .syncWith(ResourceLocation.STREAM_CODEC, AttachmentSyncPredicate.all()));
}
