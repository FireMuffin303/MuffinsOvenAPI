package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public record OvenBoatVariant(boolean raft, Supplier<Block> planks, Supplier<Item> boatItem, Supplier<Item> chestBoatItem) {



    public static class Builder{
        boolean raft = false;
        Supplier<Block> planks;
        Supplier<Item> boatItem;
        Supplier<Item> chestBoatItem;

        public Builder(Supplier<Item> boatItem,Supplier<Item> chestBoatItem,Supplier<Block> planks){
            this.boatItem = boatItem;
            this.chestBoatItem = chestBoatItem;
            this.planks = planks;
        }


        public Builder raft(){
            this.raft = true;
            return this;
        }

        public Builder planks(Supplier<Block> planks){
            this.planks = planks;
            return this;
        }

        public Builder chestBoat(Supplier<Item> item){
            this.chestBoatItem = item;
            return this;
        }

        public Builder boat(Supplier<Item> item){
            this.boatItem = item;
            return this;
        }

        public OvenBoatVariant build(){
            return new OvenBoatVariant(this.raft,this.planks,this.boatItem,this.chestBoatItem);
        }
    }

}
