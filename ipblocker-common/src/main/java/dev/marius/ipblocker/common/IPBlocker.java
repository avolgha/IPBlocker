/*
 * Copyright by Marius ( https://github.com/MagicDev-Marius/ ) 2021
 */

package dev.marius.ipblocker.common;

import dev.marius.ipblocker.common.util.List;
import org.jetbrains.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h1>IPBlocker</h1>
 * <em>What it is doing: <br>Plugin checks, if there are more than a given amount of players online with the same ip address</em>
 *
 * @author Marius ( https://github.com/MagicDev-Marius/ )
 * @version 1.0.0
 */
public final class IPBlocker {

    @NotNull
    private static final Map<String, List> map = new HashMap<>();

    /**
     * Call only with Address and player class
     *
     * @see IPBlocker#canNotJoin(InetSocketAddress, Class, Integer, Boolean)
     */
    public static synchronized boolean canNotJoin(final InetSocketAddress address, final Class<?> player) throws Exception {
        return canNotJoin(address, player, 2);
    }

    /**
     * Call with Address, player class, and max amount of players with same ip address
     *
     * @see IPBlocker#canNotJoin(InetSocketAddress, Class, Integer, Boolean)
     */
    public static synchronized boolean canNotJoin(final InetSocketAddress address, final Class<?> player, final Integer maxTimes) throws Exception {
        return canNotJoin(address, player, maxTimes, false);
    }

    /**
     * Call with Address, player class, and send if boolean is true a message to
     * the online players with the same ip
     *
     * @see IPBlocker#canNotJoin(InetSocketAddress, Class, Integer, Boolean)
     */
    public static synchronized boolean canNotJoin(final InetSocketAddress address, final Class<?> player, final Boolean sendMessage) throws Exception {
        return canNotJoin(address, player, 2, sendMessage);
    }

    /**
     * Call with Address, player class, max amount of players with same ip address and send
     * if boolean is true a message to the other online players with the same ip
     *
     * @see IPBlocker#canNotJoin(InetSocketAddress, Class, Integer, Boolean)
     */
    public static synchronized boolean canNotJoin(final InetSocketAddress address, final Class<?> player, final Integer maxTimes, final Boolean sendMessage) throws Exception {
        final String wantedIP = getWantedIPString(address);
        if (wantedIP.isEmpty())
            throw new IllegalArgumentException("Given InetSocketAddress cannot be read");
        if (!map.containsKey(wantedIP)) {
            map.put(wantedIP, new List(1, Collections.singleton(player), address));
            return false;
        }
        final List list = map.get(wantedIP);
        if (list == null)
            throw new RuntimeException("Cannot read \"list\" element from map.");
        final int count = list.get(0, 0);
        if (count >= maxTimes) {
            if (sendMessage) {
                final AtomicReference<Exception> exceptionAtomicReference = new AtomicReference<>();
                list.get(1, Collections.singletonList(player)).forEach(clazz -> {
                    try {
                        final Method sendMethod = clazz.getMethod("sendMessage", String.class);
                        sendMethod.invoke(null, "§8[§bIPBlocker§8] §cThere was a person that trying to connect with the same IP Address as you. We stopped that!");
                    } catch (NoSuchMethodException e) {
                        exceptionAtomicReference.set(e);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                if (exceptionAtomicReference.get() != null)
                    throw exceptionAtomicReference.get();
            }
            return true;
        } else {
            map.remove(wantedIP);
            map.put(wantedIP, new List(count+1, list.get(1, Collections.singletonList(player)), address));
            return false;
        }
    }

    @NotNull
    @Contract("null -> fail")
    private static synchronized String getWantedIPString(final InetSocketAddress address) {
        if (address == null)
            throw new IllegalArgumentException("Given InetSocketAddress is null.");
        final String hostName = address.getHostName();
        final String[] splitHostName = hostName.split("\\.");
        if (splitHostName.length != 4)
            throw new IllegalArgumentException("Given InetSocketAddress is not valid.");
        return splitHostName[0] + splitHostName[1];
    }

}
