package com.example.miniproyecto_3_battlership.model.game;

import com.example.miniproyecto_3_battlership.model.Player.IPlayer;
import com.example.miniproyecto_3_battlership.model.Player.PlayerBot;
import com.example.miniproyecto_3_battlership.model.Player.PlayerPerson;
import com.example.miniproyecto_3_battlership.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Interface defining the core operations for the Battleship game.
 *
 * <p>This interface ensures consistent implementation of essential methods
 * related to player management and game logic.</p>
 *
 * <p>It extends {@code Serializable} to allow game state persistence.</p>
 *
 * @author Nicolas Cordoba
 * @author Samuel Arenas
 * @author Juan Manuel Ampudia
 */
public interface IGame extends Serializable {

    /**
     * Retrieves the bot player instance.
     *
     * @return the {@code PlayerBot} object representing the bot player.
     */
    PlayerBot getPlayerBot();

    /**
     * Updates the bot player instance with a new {@code PlayerBot}.
     *
     * @param playerBot the new {@code PlayerBot} instance to set.
     */
    void setPlayerBot(PlayerBot playerBot);

    /**
     * Retrieves the human player instance.
     *
     * @return the {@code PlayerPerson} object representing the human player.
     */
    PlayerPerson getPlayerPerson();

    /**
     * Verifies if a player has won the game.
     *
     * <p>The method delegates to the player's own verification logic, checking if
     * all enemy ships have been sunk.</p>
     *
     * @param player the {@code IPlayer} instance to check for victory.
     * @return {@code true} if the player has won, {@code false} otherwise.
     */
    boolean verifyWinner(IPlayer player);




}
