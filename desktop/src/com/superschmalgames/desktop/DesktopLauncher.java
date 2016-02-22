package com.superschmalgames.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.superschmalgames.Utilities.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gator Quest";
		config.vSyncEnabled = true;  //May make rendering smoother, prevent screen tearing, etc. Remove if performance hit is too great.
		config.width = 1020;
		config.height = 612;
		new LwjglApplication(new MainClass(), config);
	}
}
