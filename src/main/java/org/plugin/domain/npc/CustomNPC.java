package org.plugin.domain.npc;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.Objects;

@Getter
@Setter
public class CustomNPC {
    private Location location;
    private String name;
    private NPCType npcType;


    public CustomNPC(Location location, String name) {
        this.location = location;
        this.name = name;
        this.npcType = NPCType.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomNPC customNPC = (CustomNPC) o;
        return Objects.equals(location, customNPC.location) && Objects.equals(name, customNPC.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, name);
    }
}
