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

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.jemmy.fx.NodeDock;
import org.jemmy.fx.SceneDock;
import org.jemmy.fx.control.TextInputControlDock;
import org.jemmy.interfaces.Keyboard.KeyboardButton;
import org.jemmy.interfaces.Keyboard.KeyboardButtons;
import org.junit.BeforeClass;
import org.junit.Test;

public class PartTestCase {
	protected static SceneDock scene;
	
	@BeforeClass
	public static void startApp() throws InterruptedException {
		try {
			scene = new SceneDock();
				
		} catch(Throwable t ) {
			t.printStackTrace();
		}
		
	}

	@Test
	public void moveWindowX() {
		TextInputControlDock dock = new TextInputControlDock(scene.asParent(),TextField.class,"text_windowX");
		dock.wrap().clear();
		dock.wrap().keyboard().pressKey(KeyboardButtons.D1);
		dock.wrap().keyboard().pressKey(KeyboardButtons.D0);
		dock.wrap().keyboard().pressKey(KeyboardButtons.D0);
		NodeDock button = new NodeDock(scene.asParent(), Button.class, "button_windowX");
		button.wrap().mouse().click();
	}
}
