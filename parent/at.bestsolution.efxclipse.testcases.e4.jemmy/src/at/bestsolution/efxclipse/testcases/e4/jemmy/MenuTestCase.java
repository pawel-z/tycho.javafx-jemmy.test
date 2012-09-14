/*******************************************************************************
 * Copyright (c) 2012 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.efxclipse.testcases.e4.jemmy;

import org.jemmy.fx.SceneDock;
import org.jemmy.fx.control.MenuBarDock;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuTestCase {
	protected static SceneDock scene;
	private static MenuBarDock menuBar;
	
	@BeforeClass
	public static void startApp() throws InterruptedException {
		try {
			scene = new SceneDock();
			menuBar = new MenuBarDock(scene.asParent());
		} catch(Throwable t ) {
			t.printStackTrace();
		}
		
	}
	
	@Test
	public void menu() {
		menuBar.asMenuOwner().push("M1","Check - HI 1");
	}
}
