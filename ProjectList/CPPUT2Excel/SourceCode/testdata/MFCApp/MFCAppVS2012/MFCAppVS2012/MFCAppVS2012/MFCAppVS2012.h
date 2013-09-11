
// MFCAppVS2012.h : main header file for the PROJECT_NAME application
//

#pragma once

#ifndef __AFXWIN_H__
	#error "include 'stdafx.h' before including this file for PCH"
#endif

#include "resource.h"		// main symbols


// CMFCAppVS2012App:
// See MFCAppVS2012.cpp for the implementation of this class
//

class CMFCAppVS2012App : public CWinApp
{
public:
	CMFCAppVS2012App();

// Overrides
public:
	virtual BOOL InitInstance();

// Implementation

	DECLARE_MESSAGE_MAP()
};

extern CMFCAppVS2012App theApp;