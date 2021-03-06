/*
 *   Wormhole X-Treme Plugin for Bukkit
 *   Copyright (C) 2011  Ben Echols
 *                       Dean Bailey
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wormhole_xtreme.wormhole.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;

import com.wormhole_xtreme.wormhole.permissions.PermissionsManager.PermissionLevel;

/**
 * The Class ConfigManager.
 */
public class ConfigManager
{

    /**
     * The Enum ConfigKeys.
     */
    public enum ConfigKeys
    {

        /** The BUIL t_ i n_ permission s_ enabled. */
        BUILT_IN_PERMISSIONS_ENABLED,

        /** The BUIL t_ i n_ defaul t_ permissio n_ level. */
        BUILT_IN_DEFAULT_PERMISSION_LEVEL,

        /** The PERMISSION SUPPORT DISABLE. */
        PERMISSIONS_SUPPORT_DISABLE,

        /** The SIMPLE PERMISSIONS. */
        SIMPLE_PERMISSIONS,

        /** The WORMHOL e_ us e_ i s_ teleport. */
        WORMHOLE_USE_IS_TELEPORT,

        /** The TIMEOU t_ activate. */
        TIMEOUT_ACTIVATE,

        /** The TIMEOU t_ shutdown. */
        TIMEOUT_SHUTDOWN,

        /** The BUIL d_ restrictio n_ enabled. */
        BUILD_RESTRICTION_ENABLED,

        /** The BUIL d_ restrictio n_ grou p_ one. */
        BUILD_RESTRICTION_GROUP_ONE,

        /** The BUIL d_ restrictio n_ grou p_ two. */
        BUILD_RESTRICTION_GROUP_TWO,

        /** The BUIL d_ restrictio n_ grou p_ three. */
        BUILD_RESTRICTION_GROUP_THREE,

        /** The US e_ cooldow n_ enabled. */
        USE_COOLDOWN_ENABLED,

        /** The US e_ cooldow n_ grou p_ one. */
        USE_COOLDOWN_GROUP_ONE,

        /** The US e_ cooldow n_ grou p_ two. */
        USE_COOLDOWN_GROUP_TWO,

        /** The US e_ cooldow n_ grou p_ three. */
        USE_COOLDOWN_GROUP_THREE,

        /** The HELP SUPPORT DISABLE. */
        HELP_SUPPORT_DISABLE,

        /** The WORLDS SUPPORT DISABLE key. */
        WORLDS_SUPPORT_ENABLED,

        /** The LOG LEVEL. */
        LOG_LEVEL
    }

    /**
     * The Enum StringTypes.
     */
    public static enum MessageStrings
    {

        /** The error header. */
        errorHeader("\u00A73:: \u00A75error \u00A73:: \u00A77"),

        /** The normal header. */
        normalHeader("\u00A73:: \u00A77"),

        /** The permission no. */
        permissionNo(errorHeader + "You lack the permissions to do this."),

        /** The target is self. */
        targetIsSelf(errorHeader + "Can't dial own gate without solar flare"),

        /** The target invalid. */
        targetInvalid(errorHeader + "Invalid gate target."),

        /** The target is active. */
        targetIsActive(errorHeader + "Target gate is currently active."),

        /** The gate not active. */
        gateNotActive(errorHeader + "No gate activated to dial."),

        /** The gate remove active. */
        gateRemoveActive(errorHeader + "Gate remotely activated."),

        /** The gate shutdown. */
        gateShutdown(normalHeader + "Gate successfully shutdown."),

        /** The gate activated. */
        gateActivated(normalHeader + "Gate successfully activated."),

        /** The gate deactivated. */
        gateDeactivated(normalHeader + "Gate successfully deactivated."),

        /** The gate dialed. */
        gateConnected(normalHeader + "Stargates connected."),

        /** The construct success. */
        constructSuccess(normalHeader + "Gate successfully constructed."),

        /** The construct name invalid. */
        constructNameInvalid(errorHeader + "Gate name invalid: "),

        /** The construct name too long. */
        constructNameTooLong(errorHeader + "Gate name too long: "),

        /** The construct name taken. */
        constructNameTaken(errorHeader + "Gate name already taken: "),

        /** The request invalid. */
        requestInvalid(errorHeader + "Invalid Request"),

        /** The gate not specified. */
        gateNotSpecified(errorHeader + "No gate name specified."),

        /** The player build count restricted. */
        playerBuildCountRestricted(errorHeader + "You are at your max number of built gates."),

        /** The player use cooldown restricted. */
        playerUseCooldownRestricted(errorHeader + "You must wait longer before using a stargate."),

        /** The player use cooldown wait time. */
        playerUseCooldownWaitTime(errorHeader + "Current Wait (in seconds): ");

        /** The m. */
        private final String m;

        /**
         * Instantiates a new string types.
         * 
         * @param message
         *            the message
         */
        private MessageStrings(final String message)
        {
            m = message;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString()
        {
            return m;
        }
    }

    /** The Constant configurations. */
    private static final ConcurrentHashMap<ConfigKeys, Setting> configurations = new ConcurrentHashMap<ConfigKeys, Setting>();

    /**
     * Gets the builds the restriction group one.
     * 
     * @return the builds the restriction group one
     */
    public static int getBuildRestrictionGroupOne()
    {
        return isConfigurationKey(ConfigKeys.BUILD_RESTRICTION_GROUP_ONE)
            ? getSetting(ConfigKeys.BUILD_RESTRICTION_GROUP_ONE).getIntValue()
            : 1;
    }

    /**
     * Gets the builds the restriction group three.
     * 
     * @return the builds the restriction group three
     */
    public static int getBuildRestrictionGroupThree()
    {
        return isConfigurationKey(ConfigKeys.BUILD_RESTRICTION_GROUP_THREE)
            ? getSetting(ConfigKeys.BUILD_RESTRICTION_GROUP_THREE).getIntValue()
            : 3;
    }

    /**
     * Gets the builds the restriction group two.
     * 
     * @return the builds the restriction group two
     */
    public static int getBuildRestrictionGroupTwo()
    {
        return isConfigurationKey(ConfigKeys.BUILD_RESTRICTION_GROUP_TWO)
            ? getSetting(ConfigKeys.BUILD_RESTRICTION_GROUP_TWO).getIntValue()
            : 2;
    }

    /**
     * Get Built in default permission level settings from ConfigKeys. Return sane PermissionLevel.
     * Return default value if key is missing or broken.
     * 
     * @return the built in default permission level
     */
    public static PermissionLevel getBuiltInDefaultPermissionLevel()
    {
        Setting bidpl;
        if ((bidpl = ConfigManager.getConfigurations().get(ConfigKeys.BUILT_IN_DEFAULT_PERMISSION_LEVEL)) != null)
        {
            return bidpl.getPermissionLevel();
        }
        else
        {
            return PermissionLevel.WORMHOLE_USE_PERMISSION;
        }
    }

    /**
     * Get Built in permissions enabled settings from ConfigKeys. Return sane boolean value.
     * Return default value if key is missing or broken.
     * 
     * @return the built in permissions enabled
     */
    public static boolean getBuiltInPermissionsEnabled()
    {
        Setting bipe;
        if ((bipe = ConfigManager.getConfigurations().get(ConfigKeys.BUILT_IN_PERMISSIONS_ENABLED)) != null)
        {
            return bipe.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the configurations.
     * 
     * @return the configurations
     */
    protected static ConcurrentHashMap<ConfigKeys, Setting> getConfigurations()
    {
        return configurations;
    }

    /**
     * Gets the Help plugin support status.
     * 
     * @return true, if Help plugin support is disabled.
     */
    public static boolean getHelpSupportDisable()
    {
        Setting hsd;
        if ((hsd = ConfigManager.getConfigurations().get(ConfigKeys.HELP_SUPPORT_DISABLE)) != null)
        {
            return hsd.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Get Log Level setting from ConfigKeys. Return sane Level value.
     * Return default value if key is missing or broken.
     * 
     * @return the log level
     */
    public static Level getLogLevel()
    {
        Setting ll;
        if ((ll = ConfigManager.getConfigurations().get(ConfigKeys.LOG_LEVEL)) != null)
        {
            return ll.getLevel();
        }
        else
        {
            return Level.INFO;
        }
    }

    /**
     * Gets the Permissions plugin support status.
     * 
     * @return true, if Permissions plugin support is disabled.
     */
    public static boolean getPermissionsSupportDisable()
    {
        Setting psd;
        if ((psd = ConfigManager.getConfigurations().get(ConfigKeys.PERMISSIONS_SUPPORT_DISABLE)) != null)
        {
            return psd.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the setting.
     * 
     * @param configKey
     *            the config key
     * @return the setting
     */
    private static Setting getSetting(final ConfigKeys configKey)
    {
        return getConfigurations().get(configKey);
    }

    /**
     * Gets the simple permissions.
     * 
     * @return the simple permissions
     */
    public static boolean getSimplePermissions()
    {
        Setting sp;
        if ((sp = ConfigManager.getConfigurations().get(ConfigKeys.SIMPLE_PERMISSIONS)) != null)
        {
            return sp.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Get Timeout Activate setting from ConfigKeys.
     * Return default value if key is missing or broken.
     * 
     * @return Timeout in seconds.
     */
    public static int getTimeoutActivate()
    {
        Setting ta;
        if ((ta = ConfigManager.getConfigurations().get(ConfigKeys.TIMEOUT_ACTIVATE)) != null)
        {
            return ta.getIntValue();
        }
        else
        {
            return 30;
        }
    }

    /**
     * Get Timeout Shutdown setting from ConfigKeys.
     * Return default value if key is missing or broken.
     * 
     * @return Timeout in seconds.
     */
    public static int getTimeoutShutdown()
    {
        Setting ts;
        if ((ts = ConfigManager.getConfigurations().get(ConfigKeys.TIMEOUT_SHUTDOWN)) != null)
        {
            return ts.getIntValue();
        }
        else
        {
            return 38;
        }
    }

    /**
     * Gets the use cooldown group one.
     * 
     * @return the use cooldown group one
     */
    public static int getUseCooldownGroupOne()
    {
        return isConfigurationKey(ConfigKeys.USE_COOLDOWN_GROUP_ONE)
            ? getSetting(ConfigKeys.USE_COOLDOWN_GROUP_ONE).getIntValue()
            : 120;
    }

    /**
     * Gets the use cooldown group three.
     * 
     * @return the use cooldown group three
     */
    public static int getUseCooldownGroupThree()
    {
        return isConfigurationKey(ConfigKeys.USE_COOLDOWN_GROUP_THREE)
            ? getSetting(ConfigKeys.USE_COOLDOWN_GROUP_THREE).getIntValue()
            : 60;
    }

    /**
     * Gets the use cooldown group two.
     * 
     * @return the use cooldown group two
     */
    public static int getUseCooldownGroupTwo()
    {
        return isConfigurationKey(ConfigKeys.USE_COOLDOWN_GROUP_TWO)
            ? getSetting(ConfigKeys.USE_COOLDOWN_GROUP_TWO).getIntValue()
            : 30;
    }

    /*
     * Get Built in permissions enabled settings from ConfigKeys. Return sane boolean value.
     * Return default value if key is missing or broken.
     */
    /**
     * Gets the wormhole use is teleport.
     * 
     * @return the wormhole use is teleport
     */
    public static boolean getWormholeUseIsTeleport()
    {
        Setting bipe;
        if ((bipe = ConfigManager.getConfigurations().get(ConfigKeys.WORMHOLE_USE_IS_TELEPORT)) != null)
        {
            return bipe.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks if is builds the restriction enabled.
     * 
     * @return true, if is builds the restriction enabled
     */
    public static boolean isBuildRestrictionEnabled()
    {
        return ConfigManager.getConfigurations().get(ConfigKeys.BUILD_RESTRICTION_ENABLED) != null
            ? ConfigManager.getConfigurations().get(ConfigKeys.BUILD_RESTRICTION_ENABLED).getBooleanValue()
            : false;
    }

    /**
     * Checks if is configuration key.
     * 
     * @param configKey
     *            the config key
     * @return true, if is configuration key
     */
    private static boolean isConfigurationKey(final ConfigKeys configKey)
    {
        return getConfigurations().containsKey(configKey);
    }

    /**
     * Checks if is use cooldown enabled.
     * 
     * @return true, if is use cooldown enabled
     */
    public static boolean isUseCooldownEnabled()
    {
        return ConfigManager.getConfigurations().get(ConfigKeys.USE_COOLDOWN_ENABLED) != null
            ? ConfigManager.getConfigurations().get(ConfigKeys.USE_COOLDOWN_ENABLED).getBooleanValue()
            : false;
    }

    /**
     * Checks if is wormhole worlds support enabled.
     * 
     * @return true, if is wormhole worlds support enabled
     */
    public static boolean isWormholeWorldsSupportEnabled()
    {
        Setting wsd;
        if ((wsd = ConfigManager.getConfigurations().get(ConfigKeys.WORLDS_SUPPORT_ENABLED)) != null)
        {
            return wsd.getBooleanValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Sets the builds the restriction enabled.
     * 
     * @param b
     *            the new builds the restriction enabled
     */
    public static void setBuildRestrictionEnabled(final boolean b)
    {
        ConfigManager.setConfigValue(ConfigKeys.BUILD_RESTRICTION_ENABLED, b);
    }

    /**
     * Sets the builds the restriction group one.
     * 
     * @param count
     *            the new builds the restriction group one
     */
    public static void setBuildRestrictionGroupOne(final int count)
    {
        setConfigValue(ConfigKeys.BUILD_RESTRICTION_GROUP_ONE, count);
    }

    /**
     * Sets the builds the restriction group three.
     * 
     * @param count
     *            the new builds the restriction group three
     */
    public static void setBuildRestrictionGroupThree(final int count)
    {
        setConfigValue(ConfigKeys.BUILD_RESTRICTION_GROUP_THREE, count);
    }

    /**
     * Sets the builds the restriction group two.
     * 
     * @param count
     *            the new builds the restriction group two
     */
    public static void setBuildRestrictionGroupTwo(final int count)
    {
        setConfigValue(ConfigKeys.BUILD_RESTRICTION_GROUP_TWO, count);
    }

    /**
     * Sets the config value.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public static void setConfigValue(final ConfigKeys key, final Object value)
    {
        if ((key != null) && isConfigurationKey(key) && (value != null))
        {
            getConfigurations().get(key).setValue(value);
        }
    }

    /**
     * Sets the simple permissions.
     * 
     * @param b
     *            the new simple permissions
     */
    public static void setSimplePermissions(final boolean b)
    {
        ConfigManager.setConfigValue(ConfigKeys.SIMPLE_PERMISSIONS, b);
    }

    /**
     * Set timeout activate setting in ConfigKeys.
     * 
     * @param i
     *            Timeout in seconds.
     */
    public static void setTimeoutActivate(final int i)
    {
        ConfigManager.setConfigValue(ConfigKeys.TIMEOUT_ACTIVATE, i);
    }

    /**
     * Set timeout shutdown setting in ConfigKeys.
     * 
     * @param i
     *            the new timeout shutdown
     */
    public static void setTimeoutShutdown(final int i)
    {
        ConfigManager.setConfigValue(ConfigKeys.TIMEOUT_SHUTDOWN, i);
    }

    /**
     * Sets the up configs.
     * 
     * @param pdf
     *            the new up configs
     */
    public static void setupConfigs(final PluginDescriptionFile pdf)
    {
        Configuration.loadConfiguration(pdf);
    }

    /**
     * Sets the use cooldown enabled.
     * 
     * @param b
     *            the new use cooldown enabled
     */
    public static void setUseCooldownEnabled(final boolean b)
    {
        ConfigManager.setConfigValue(ConfigKeys.USE_COOLDOWN_ENABLED, b);
    }

    /**
     * Sets the use cooldown group one.
     * 
     * @param time
     *            the new use cooldown group one
     */
    public static void setUseCooldownGroupOne(final int time)
    {
        setConfigValue(ConfigKeys.USE_COOLDOWN_GROUP_ONE, time);
    }

    /**
     * Sets the use cooldown group three.
     * 
     * @param time
     *            the new use cooldown group three
     */
    public static void setUseCooldownGroupThree(final int time)
    {
        setConfigValue(ConfigKeys.USE_COOLDOWN_GROUP_THREE, time);
    }

    /**
     * Sets the use cooldown group two.
     * 
     * @param time
     *            the new use cooldown group two
     */
    public static void setUseCooldownGroupTwo(final int time)
    {
        setConfigValue(ConfigKeys.USE_COOLDOWN_GROUP_TWO, time);
    }
}