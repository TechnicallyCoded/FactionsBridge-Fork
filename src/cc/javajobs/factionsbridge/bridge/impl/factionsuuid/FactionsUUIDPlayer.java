package cc.javajobs.factionsbridge.bridge.impl.factionsuuid;

import cc.javajobs.factionsbridge.bridge.IFaction;
import cc.javajobs.factionsbridge.bridge.IFactionPlayer;
import cc.javajobs.factionsbridge.bridge.IRelationship;
import com.massivecraft.factions.FPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * FactionsUUID implementation of IFactionPlayer.
 *
 * @author Callum Johnson
 * @since 26/02/2021 - 14:50
 */
public class FactionsUUIDPlayer implements IFactionPlayer {

    protected final FPlayer fpl;

    public FactionsUUIDPlayer(FPlayer fpl) {
        this.fpl = fpl;
    }

    /**
     * Method to get the unique Id of the Faction Player.
     *
     * @return UUID (UniqueId).
     */
    @Override
    public UUID getUniqueId() {
        return UUID.fromString(fpl.getId());
    }

    /**
     * Method to get the Name of the Faction Player.
     *
     * @return name of the Player.
     */
    @Override
    public String getName() {
        return fpl.getName();
    }

    /**
     * Method to get the Faction linked to the IFactionPlayer.
     *
     * @return faction of the player.
     */
    @Override
    public IFaction getFaction() {
        return new FactionsUUIDFaction(fpl.getFaction());
    }

    /**
     * Method to determine if the Player is in a Faction & if the Faction isn't a System Faction.
     * <p>
     * Some Factions implementations, if a Player isn't in a Faction, the Player is assumed
     * to be "factionless" which is defaulted to Wilderness.
     * </p>
     *
     * @return {@code true} if the player is in a faction other than Wilderness/WarZone/SafeZone.
     */
    @Override
    public boolean hasFaction() {
        // FactionsUUID only checks for '0', this method removes WarZone and SafeZone issues too.
        return fpl.hasFaction() && !getFaction().isServerFaction();
    }

    /**
     * Method to get the Offline form of the Player.
     *
     * @return {@link OfflinePlayer}
     */
    @Override
    public OfflinePlayer getOfflinePlayer() {
        return fpl.getOfflinePlayer();
    }

    /**
     * Method to get the Online form of the Player.
     *
     * @return {@link Player}
     * @see IFactionPlayer#isOnline()
     */
    @Override
    public Player getPlayer() {
        return fpl.getPlayer();
    }

    /**
     * Uses Bukkit methodology to determine if the Player is online.
     *
     * @return {@code true} = yes, {@code false} = no.
     */
    @Override
    public boolean isOnline() {
        return fpl.isOnline();
    }

    /**
     * Method to get the relationship from a Player to a Faction.
     *
     * @param other faction to test
     * @return {@link IRelationship}
     */
    @Override
    public IRelationship getRelationTo(IFaction other) {
         return getFaction().getRelationTo(other);
    }

    /**
     * Method to get the relationship from a Player to another Player.
     *
     * @param other IFactionPlayer to test
     * @return {@link IRelationship}
     */
    @Override
    public IRelationship getRelationTo(IFactionPlayer other) {
        return getRelationTo(other.getFaction());
    }

    /**
     * Method to return the IFactionPlayer as an Object (API friendly)
     *
     * @return object of API.
     */
    @Override
    public Object asObject() {
        return fpl;
    }

}
