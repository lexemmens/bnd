package aQute.xlaunchpad;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Closeable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.runtime.ServiceComponentRuntime;

import aQute.launchpad.Launchpad;
import aQute.launchpad.LaunchpadBuilder;
import aQute.launchpad.Service;
import aQute.launchpad.junit.LaunchpadRunner;

@RunWith(LaunchpadRunner.class)
public class LaunchpadRunnerBasicTest {

	static LaunchpadBuilder builder = new LaunchpadBuilder().snapshot()
		.runfw("jar/org.apache.felix.framework-6.0.2.jar;version=file")
		.bundles(
			"org.osgi.util.promise, org.osgi.util.function, jar/org.apache.felix.scr-2.1.16.jar;version=file, org.assertj.core")
		.debug();

	@Component(service = X.class, enabled = false)
	public static class X {}

	@Service
	Launchpad				lp;

	@Service
	ServiceComponentRuntime	scr;

	@Test
	public void testProperties() throws Exception {

		assertThat(lp.waitForService(X.class, 100)
			.isPresent()).isFalse();

		try (Closeable c = lp.enable(X.class)) {
			assertThat(lp.waitForService(X.class, 100)
				.isPresent()).isTrue();
		}
		assertThat(lp.waitForService(X.class, 100)
			.isPresent()).isFalse();
	}

	@Test
	public void testProperties2() throws Exception {

		assertThat(lp.waitForService(X.class, 100)
			.isPresent()).isFalse();

		try (Closeable c = lp.enable(X.class)) {
			assertThat(lp.waitForService(X.class, 100)
				.isPresent()).isTrue();
		}
		assertThat(lp.waitForService(X.class, 100)
			.isPresent()).isFalse();
	}
}
