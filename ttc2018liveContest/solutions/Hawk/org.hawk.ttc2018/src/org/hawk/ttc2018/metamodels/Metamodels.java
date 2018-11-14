/*******************************************************************************
 * Copyright (c) 2018 Aston University.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-3.0
 *
 * Contributors:
 *     Antonio Garcia-Dominguez - initial API and implementation
 ******************************************************************************/
package org.hawk.ttc2018.metamodels;

import java.io.InputStream;

public final class Metamodels {

	private Metamodels() {}

	public static InputStream getEcoreMetamodel() {
		return Metamodels.class.getResourceAsStream("Ecore.ecore");
	}

	public static InputStream getChangeSequenceMetamodel() {
		return Metamodels.class.getResourceAsStream("NMetaChanges.ecore");
	}

	public static InputStream getSocialMediaMetamodel() {
		return Metamodels.class.getResourceAsStream("social_network.ecore");
	}

}
