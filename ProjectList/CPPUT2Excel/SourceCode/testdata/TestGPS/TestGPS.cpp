#include "stdafx.h"
#include <cppunit/config/SourcePrefix.h>
#include "TestGPS.h"

CPPUNIT_TEST_SUITE_REGISTRATION(TestGPS);

/**
* Define Test Cases for GPS
*/
void TestGPS::setUp() {
    gpsInfo.Open(10);
}

void TestGPS::tearDown() {
    gpsInfo.Close();
}


void TestGPS::testOpen() {
	int ntimes = 1000; // try 1000 times

	int i = 0;
	double altitude;
	while (i < ntimes) {
		gpsInfo.GetGPSInfo();
		altitude = gpsInfo.gpsInfo.altitude;
		if (altitude > 0.0) {
			break;
		}
		i++;
	}
	CPPUNIT_ASSERT(altitude > 0.0);
}