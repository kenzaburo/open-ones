/********************************************************************************
* @class MediaAnalyzer
* @description provides utilities to processing media such as: video, image
*   - Determime type of media by extension of file
*   - Capture frame of video by given number of frame
*   - Capture frame of video by time
*   - Save frame to file jpeg
* @solution using OpenCV-2.4.6
* @license BSD
* @copyright mks.com.vn
*********************************************************************************/

#include "StdAfx.h"
#include "MediaAnalyzer.h"

string MediaAnalyzer::GetExtension(string strFilePath) {
	size_t nLen = strFilePath.length();
	size_t nPos = nLen - 4; // extension: .xxx

	if (nPos <= 0) {
		return strFilePath;
	} else {
		return strFilePath.substr(nPos, 4);
	}
}

bool MediaAnalyzer::IsVideo(string strMediaPath) {
	const string VIDEO_EXTS[3] = { ".avi", ".mp4", ".wmv"};
	string ext = GetExtension(strMediaPath);

	size_t nLen = sizeof(VIDEO_EXTS) / sizeof(VIDEO_EXTS[0]);
	
	for (size_t i = 0; i < nLen; i++) {
		if (VIDEO_EXTS[i].compare(ext) == 0) {
			return TRUE;
		}
	}

	return FALSE;
}

bool MediaAnalyzer::IsPicture(string strMediaPath) {
	const string IMAGE_EXTS[2] = { ".jpg", ".png"};
	string ext = GetExtension(strMediaPath);

	size_t nLen = sizeof(IMAGE_EXTS) / sizeof(IMAGE_EXTS[0]);
	
	for (size_t i = 0; i < nLen; i++) {
		if (IMAGE_EXTS[i].compare(ext) == 0) {
			return TRUE;
		}
	}

	return FALSE;
}

bool MediaAnalyzer::IsVideo(CString strMediaPath) {
	bool bResult;
	string stdStrMediaPath = CW2A(strMediaPath);

	bResult = IsVideo(stdStrMediaPath);
    
	return bResult;
}

bool MediaAnalyzer::IsPicture(CString strMediaPath) {
	bool bResult;
	string stdStrMediaPath = CW2A(strMediaPath);

	bResult = IsPicture(stdStrMediaPath);
    
	return bResult;
}

bool MediaAnalyzer::WriteImage(Mat &frame, String strOutputPath) {
    vector<int> compression_params; //vector that stores the compression parameters of the image
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY); //specify the compression technique
    compression_params.push_back(98); //specify the compression quality

	bool bResult = imwrite(strOutputPath, frame, compression_params); // write the image to file

    return bResult;
}

/**
*/
bool MediaAnalyzer::CaptureImageAtFrame(VideoCapture &videoCapture, const int nFrameNo, string strOutputPath) {
    bool bResult = TRUE;
    Mat frame;

	if (videoCapture.isOpened() == FALSE) {
        return FALSE;
    }

    vector<int> compression_params; //vector that stores the compression parameters of the image
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY); //specify the compression technique
    compression_params.push_back(98); //specify the compression quality


    videoCapture.set(CV_CAP_PROP_POS_FRAMES, nFrameNo);
    videoCapture.read(frame);

    bResult = imwrite(strOutputPath, frame, compression_params); //write the image to file

    return bResult;
}

bool MediaAnalyzer::CaptureImageAtFrame(VideoCapture &videoCapture, const int nFrameNo, Mat &frame) {
    bool bResult = TRUE;

	if (videoCapture.isOpened() == FALSE) {
        return FALSE;
    }

    vector<int> compression_params; //vector that stores the compression parameters of the image
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY); //specify the compression technique
    compression_params.push_back(98); //specify the compression quality


    videoCapture.set(CV_CAP_PROP_POS_FRAMES, nFrameNo);
    bResult = videoCapture.read(frame);

    return bResult;
}

/**
*/
bool MediaAnalyzer::CaptureImage(VideoCapture &videoCapture, const double dMilisecond, string strOutputPath) {
    bool bResult = TRUE;
    Mat frame;

	if (!videoCapture.isOpened()) {
        return FALSE;
    } else {
        // No statement
    }

    vector<int> compression_params; //vector that stores the compression parameters of the image
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY); // specify the compression technique
    compression_params.push_back(98); //specify the compression quality


    videoCapture.set(CV_CAP_PROP_POS_MSEC, dMilisecond);
    videoCapture.read(frame);

    bResult = imwrite(strOutputPath, frame, compression_params); // write the image to file

    return bResult;
}

/**
* @param frame output
* @return
* TRUE
* 
*/
bool MediaAnalyzer::CaptureImage(VideoCapture &videoCapture, const double dMilisecond, Mat &frame) {
    bool bResult = TRUE;

    vector<int> compression_params;							//vector that stores the compression parameters of the image
    compression_params.push_back(CV_IMWRITE_JPEG_QUALITY);  //specify the compression technique
    compression_params.push_back(98);						//specify the compression quality

	if (!videoCapture.isOpened()) {
        return FALSE;
    } else {
        // No statement
    }

    videoCapture.set(CV_CAP_PROP_POS_MSEC, dMilisecond);
    
	bResult = videoCapture.read(frame);

    return bResult;
}
