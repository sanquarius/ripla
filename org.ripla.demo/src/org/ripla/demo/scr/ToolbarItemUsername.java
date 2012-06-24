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

package org.ripla.demo.scr;

import org.osgi.service.useradmin.User;
import org.ripla.demo.Activator;
import org.ripla.web.RiplaApplication;
import org.ripla.web.interfaces.IToolbarActionListener;
import org.ripla.web.interfaces.IToolbarItemCreator;
import org.ripla.web.services.IToolbarItem;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * A provider for the <code>IToolbarItem</code> service.
 * This toolbar item displays the user's name.
 * 
 * @author Luthiger
 */
public class ToolbarItemUsername implements IToolbarItem {

	/* (non-Javadoc)
	 * @see org.ripla.web.services.IToolbarItem#getComponent()
	 */
	@Override
	public Component getComponent() {
		return null;
	}
	
	public IToolbarItemCreator getCreator() {
		return new IToolbarItemCreator() {
			@Override
			public Component createToolbarItem(RiplaApplication inApplication, User inUser) {
				if (inUser == null) {
					return null;
				}
				
				HorizontalLayout out = new HorizontalLayout();
				out.setSizeUndefined();
				out.addComponent(new Label(Activator.getMessages().getFormattedMessage("toolbar.username.label", inUser.getName())));
				return out;
			}			
		};
	}

	/* (non-Javadoc)
	 * @see org.ripla.web.services.IToolbarItem#getPosition()
	 */
	@Override
	public int getPosition() {
		return 5;
	}

	/* (non-Javadoc)
	 * @see org.ripla.web.services.IToolbarItem#registerToolbarActionListener(org.ripla.web.interfaces.IToolbarActionListener)
	 */
	@Override
	public void registerToolbarActionListener(IToolbarActionListener inListener) {
		// this item has no action
	}

}