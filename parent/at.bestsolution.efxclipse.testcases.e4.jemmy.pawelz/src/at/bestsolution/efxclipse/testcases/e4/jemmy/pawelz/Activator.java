package at.bestsolution.efxclipse.testcases.e4.jemmy.pawelz;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	public static final String PLUGIN_ID = "at.bestsolution.efxclipse.testcases.e4.jemmy.pawelz"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private ServiceTracker locationTracker;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public static Activator getActivator() {
		return plugin;
	}
	
	public Location getInstanceLocation() {
		if (locationTracker == null) {
			Filter filter = null;
			try {
				filter = plugin.getBundle().getBundleContext().createFilter(Location.INSTANCE_FILTER);
			} catch (InvalidSyntaxException e) {
				// ignore this. It should never happen as we have tested the
				// above format.
			}
			locationTracker = new ServiceTracker(plugin.getBundle().getBundleContext(), filter, null);
			locationTracker.open();
		}
		return (Location) locationTracker.getService();
	}
}
