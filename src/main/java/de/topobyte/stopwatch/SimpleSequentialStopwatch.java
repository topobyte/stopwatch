package de.topobyte.stopwatch;

import java.util.HashMap;
import java.util.Map;

public class SimpleSequentialStopwatch
{

	private Map<String, Long> times = new HashMap<>();
	private long start = 0;
	private String current = null;

	public void next(String key)
	{
		long now = System.currentTimeMillis();
		if (current != null) {
			times.put(current, now - start);
		}
		start = now;
		current = key;
	}

	public void done()
	{
		long now = System.currentTimeMillis();
		if (current != null) {
			times.put(current, now - start);
		}
	}

	public long time(String key)
	{
		return times.get(key);
	}

}
