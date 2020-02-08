package de.topobyte.stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestSimpleSequentialStopwatch
{

	@Test
	public void test()
	{
		SimpleSequentialStopwatch watch = new SimpleSequentialStopwatch();

		int total = 0;

		List<String> phases = new ArrayList<>();
		Map<String, Integer> times = new HashMap<>();
		for (int i = 1; i <= 4; i++) {
			String name = "phase" + i;
			phases.add(name);
			int len = 100 * i;
			times.put(name, len);
			total += len;
		}

		for (String phase : phases) {
			watch.next(phase);
			sleep(times.get(phase));
		}
		watch.done();

		for (String phase : phases) {
			int expected = times.get(phase);
			long measured = watch.time(phase);
			Assert.assertTrue(measured >= expected);
			Assert.assertTrue(measured < expected + 100);
		}

		Assert.assertTrue(watch.total() >= total);
		Assert.assertTrue(watch.total() < total + 100);
	}

	private void sleep(int millis)
	{
		long start = System.currentTimeMillis();
		long remaining = millis;
		while (remaining > 0) {
			try {
				Thread.sleep(remaining);
				long now = System.currentTimeMillis();
				remaining = start - now + millis;
			} catch (InterruptedException e) {
				// continue
			}
		}
	}

}
