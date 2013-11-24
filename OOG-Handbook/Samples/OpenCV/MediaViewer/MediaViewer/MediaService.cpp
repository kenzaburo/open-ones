/********************************************************************************
* @class MediaService
* @description provides features:
*   - Load image from file path to screen via custom picture control "CCtrlPicture"
*   - Load given frame of file video to screen via custom picture control "CCtrlPicture"
* @solution using OpenCV-2.4.6
* @license BSD
* @copyright mks.com.vn
*********************************************************************************/
#include "StdAfx.h"
#include "MediaService.h"


MediaService::MediaService(void)
{
}


MediaService::~MediaService(void)
{
}


/**
* Demo load image from the file path.
* @param strImagePath
* @param ctrlImageViewer output
*/
void MediaService::LoadImage(CString strImagePath, CCtrlPicture &ctrlImageViewer) {

	ctrlImageViewer.CreateOffScreen(IMAGE_WIDTH, IMAGE_HEIGHT);
    ctrlImageViewer.Load(strImagePath);
}

/**
* Demo load image from a frame of video.
* @param strVideoPath
* @param frameNo
* @param ctrlImageViewer output
*/

void MediaService::LoadFrame(CString strVideoPath, int nFrameNo, CCtrlPicture &ctrlImageViewer) {
    bool bResult;
    ctrlImageViewer.CreateOffScreen(IMAGE_WIDTH, IMAGE_HEIGHT);

    string stdStrMediaPath = CW2A(strVideoPath);
    VideoCapture videoCapture = VideoCapture(stdStrMediaPath);
    double dAtTime = 1000.0; // First second

    if (videoCapture.isOpened() == FALSE) {
        bResult = videoCapture.open(stdStrMediaPath);
    } else {
        // No statement
    }

    // Read video to OpenCV Mat object
    Mat frame;
    MediaAnalyzer::CaptureImage(videoCapture, dAtTime, frame);

    // Debug: write frame to file
	ctrlImageViewer.Load(frame);
}

void LoadFrameUsingTempFile(CString strVideoPath, int nFrameNo, CCtrlPicture &ctrlImageViewer) {
    bool bResult;
    ctrlImageViewer.CreateOffScreen(IMAGE_WIDTH, IMAGE_HEIGHT);

    string stdStrMediaPath = CW2A(strVideoPath);
    VideoCapture videoCapture = VideoCapture(stdStrMediaPath);
    double dAtTime = 1000.0; // First second

    if (videoCapture.isOpened() == FALSE) {
        bResult = videoCapture.open(stdStrMediaPath);
    } else {
        // No statement
    }

    // Read video to OpenCV Mat object
    Mat frame;
    MediaAnalyzer::CaptureImage(videoCapture, dAtTime, frame);

    // Debug: write frame to file

    // MediaAnalyzer::writeImage(frame, stdStrMediaPath + ".jpg");


    CString strMediaOutputPath = strVideoPath + _T(".jpg");

    string stdStrMediaOutputPath = CW2A(strMediaOutputPath);
    // MediaAnalyzer::captureImageAtFrame(videoCapture, i, stdStrMediaOutputPath);

    MediaAnalyzer::CaptureImageAtFrame(videoCapture, nFrameNo, frame);
    MediaAnalyzer::WriteImage(frame, stdStrMediaOutputPath);
    
	// Load the image from to screen
	ctrlImageViewer.Load(strMediaOutputPath);
}