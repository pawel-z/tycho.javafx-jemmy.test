package at.bestsolution.efxclipse.testcases.e4.jemmy;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.jemmy.fx.SceneDock;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import at.bestsolution.efxclipse.runtime.workbench.fx.E4Application;
import at.bestsolution.efxclipse.testcases.e4.jemmy.MenuTestCase;
import at.bestsolution.efxclipse.testcases.e4.jemmy.PartTestCase;

/**
 * Runs all test suites
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ MenuTestCase.class, PartTestCase.class })
public class TestSuite {

	@BeforeClass
	public static void setUp() throws Exception {
		final IApplicationContext applicationContext = getApplicationContext();
		E4Application app = new E4Application();

		startJfxApplication(app, applicationContext);
		waitForApplication();
	}

	@SuppressWarnings("unchecked")
	private static IApplicationContext getApplicationContext() {
		Bundle bundle = Platform.getBundle("org.eclipse.osgi");
		BundleContext context = bundle.getBundleContext();
		ServiceReference<IApplicationContext>[] ref;
		try {

			ref = (ServiceReference<IApplicationContext>[]) context.getServiceReferences(IApplicationContext.class.getName(),
					"(eclipse.application.type=main.thread)"); //$NON-NLS-1$
		} catch (InvalidSyntaxException e) {
			return null;
		}
		if (ref == null || ref.length == 0)
			return null;
		// assumes the application context is available as a service
		IApplicationContext result = context.getService(ref[0]);
		if (result != null) {
			context.ungetService(ref[0]);
			return result;
		}
		return null;
	}

	/**
	 * starts Javafx-e4 application
	 * 
	 * @param app
	 * @param context
	 */
	private static void startJfxApplication(final IApplication app, final IApplicationContext context) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					app.start(context);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * waits for full start of application
	 */
	@SuppressWarnings("unchecked")
	protected static void waitForApplication() {
		boolean status = false;
		do {
			try {
				new SceneDock();
				// javafx Scene is present so we can start testing our
				// application
				status = true;
			} catch (org.jemmy.TimeoutExpiredException e) {
				// javafx Scene is not present yet
			}

		} while (!status);
	}
}
