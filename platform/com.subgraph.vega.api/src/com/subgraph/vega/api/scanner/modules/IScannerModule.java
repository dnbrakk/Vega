/*******************************************************************************
 * Copyright (c) 2011 Subgraph.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Subgraph - initial API and implementation
 ******************************************************************************/
package com.subgraph.vega.api.scanner.modules;

public interface IScannerModule {
	String getModuleName();
	String getModuleCategoryName();
	boolean isEnabled();
	boolean isDifferential();
	boolean isTimeSensitive();
	IScannerModuleRunningTime getRunningTimeProfile();
}
