/*******************************************************************************
 * Copyright (c) 2012 RelationWare, Benno Luthiger
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * RelationWare, Benno Luthiger
 ******************************************************************************/

package org.ripla.web.util;

import java.util.Locale;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for managing the OSGi preferences service.<br />
 * This class may be extended by subclasses.
 * 
 * @author Luthiger
 */
public class PreferencesHelper {
	private static final Logger LOG = LoggerFactory
			.getLogger(PreferencesHelper.class);

	public static final String KEY_SKIN = "org.ripla.skin";
	public static final String KEY_LANGUAGE = "org.ripla.language";

	private transient PreferencesService preferences;

	/**
	 * @param inPreferences
	 *            {@link PreferencesService} setter for the preferences service
	 */
	public final void setPreferences(final PreferencesService inPreferences) {
		if (inPreferences == null) {
			return;
		}
		preferences = inPreferences;
	}

	/**
	 * Release resources.
	 */
	public final void dispose() {
		preferences = null; // NOPMD by Luthiger on 10.09.12 00:20
	}

	/**
	 * Getter for the property with the specified key.
	 * 
	 * @param inKey
	 *            String the key for the property value
	 * @param inDftValue
	 *            String
	 * @return String the property's value (from the preferences) or the
	 *         specified default value
	 */
	public final String get(final String inKey, final String inDftValue) {
		if (preferences == null) {
			return inDftValue;
		}
		final Preferences lPreferences = preferences.getSystemPreferences();
		return lPreferences.get(inKey, inDftValue);
	}

	/**
	 * Setter for the value of the property with the specified key.
	 * 
	 * @param inKey
	 *            String
	 * @param inValue
	 *            String
	 */
	public final void set(final String inKey, final String inValue) {
		if (preferences == null) {
			return;
		}

		final Preferences lPreferences = preferences.getSystemPreferences();
		lPreferences.remove(inKey);
		lPreferences.put(inKey, inValue);
		savePreferences(lPreferences);
	}

	private void savePreferences(final Preferences inPreferences) {
		try {
			inPreferences.flush();
		}
		catch (final BackingStoreException exc) {
			LOG.error("Can't save preferences!", exc);
		}
	}

	/**
	 * @return String the key of the skin stored to the preferences (might be
	 *         <code>null</code>)
	 */
	public String getActiveSkinID() {
		return get(KEY_SKIN, null);
	}

	/**
	 * Retrieves the configured locale.
	 * 
	 * @param inDft
	 *            {@link Locale} the system's locale
	 * @return {@link Locale}
	 */
	public Locale getLocale(final Locale inDft) {
		final String lLanguage = get(KEY_LANGUAGE, inDft.getLanguage());
		return new Locale(lLanguage);
	}

}
