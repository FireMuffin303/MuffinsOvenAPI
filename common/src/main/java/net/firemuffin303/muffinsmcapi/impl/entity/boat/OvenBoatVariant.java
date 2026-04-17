package net.firemuffin303.muffinsmcapi.impl.entity.boat;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record OvenBoatVariant(boolean raft, Supplier<Item> planks,Supplier<Item> boatItem,Supplier<Item> chestBoatItem) {



    public static class Builder{
        boolean raft = false;
        Supplier<Item> planks;
        Supplier<Item> boatItem;
        Supplier<Item> chestBoatItem;


        public Builder raft(){
            this.raft = true;
            return this;
        }

        public Builder planks(Supplier<Item> planks){
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
