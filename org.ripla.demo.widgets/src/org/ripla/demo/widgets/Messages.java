package org.ripla.demo.widgets;

import org.ripla.web.util.AbstractMessages;

/**
 * Bundle specific messages.
 * 
 * @author Luthiger
 */
public class Messages extends AbstractMessages {
	private static final String BASE_NAME = "messages"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ripla.web.util.AbstractMessages#getLoader()
	 */
	@Override
	protected ClassLoader getLoader() {
		return getClass().getClassLoader(); // NOPMD by Luthiger on 06.09.12
											// 23:26
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ripla.web.util.AbstractMessages#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return BASE_NAME;
	}

}
