package io.egargo.spring_docker.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;

public class Timezone {
	public Date getDate() {
		Instant utcNow = Instant.now();
		DateTimeZone asiaManila = DateTimeZone.forID("Asia/Manila");
		DateTime nowManila = utcNow.toDateTime(asiaManila);

		return nowManila.toDate();
	}
}
