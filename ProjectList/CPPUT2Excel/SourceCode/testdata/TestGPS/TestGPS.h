#pragma once

#include "resource.h"
#include "stdafx.h"
#include <cppunit/TestFixture.h>
#include <cppunit/extensions/HelperMacros.h>
#include "GPS.h"

class TestGPS : public CppUnit::TestFixture {
    CPPUNIT_TEST_SUITE(TestGPS);
    CPPUNIT_TEST(testOpen);
    CPPUNIT_TEST_SUITE_END();
public:

    void testOpen();
    virtual void setUp();
    virtual void tearDown();

private:
    CGpsInfo gpsInfo;
};
