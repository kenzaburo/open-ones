/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package vn.com.mks.swt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

/**
 * Manages icons for the application. This is necessary as we could easily end
 * up creating thousands of icons bearing the same image.
 */
public class IconCache {
  // Stock images
  public final int iconShell = 0, iconOpenFile = 1, iconOpenFolder = 2,
      iconExportToExcel = 3;

  public final String[] stockImageLocations = { "/CodeAnalyzer.png",
      "/icon_OpenFile.png", "/icon_OpenFolder.png", "/icon_ExportToExcel.png"};

  public Image stockImages[];

  // Stock cursors
  public final int cursorDefault = 0, cursorWait = 1;

  public Cursor stockCursors[];

  // Cached icons
  private Hashtable iconCache; /* map Program to Image */

  public IconCache() {
  }

  /**
   * Loads the resources
   * 
   * @param display
   *            the display
   */
  public void initResources(Display display) {
    if (stockImages == null) {
      stockImages = new Image[stockImageLocations.length];

      for (int i = 0; i < stockImageLocations.length; ++i) {
        Image image = createStockImage(display, stockImageLocations[i]);
        if (image == null) {
          freeResources();
//          throw new IllegalStateException(SWTFileViewerDemo
//              .getResourceString("error.CouldNotLoadResources"));
        }
        stockImages[i] = image;
      }
    }
    if (stockCursors == null) {
      stockCursors = new Cursor[] { null,
          new Cursor(display, SWT.CURSOR_WAIT) };
    }
    iconCache = new Hashtable();
  }

  /**
   * Frees the resources
   */
  public void freeResources() {
    if (stockImages != null) {
      for (int i = 0; i < stockImages.length; ++i) {
        final Image image = stockImages[i];
        if (image != null)
          image.dispose();
      }
      stockImages = null;
    }
    if (iconCache != null) {
      for (Enumeration it = iconCache.elements(); it.hasMoreElements();) {
        Image image = (Image) it.nextElement();
        image.dispose();
      }
    }
    if (stockCursors != null) {
      for (int i = 0; i < stockCursors.length; ++i) {
        final Cursor cursor = stockCursors[i];
        if (cursor != null)
          cursor.dispose();
      }
      stockCursors = null;
    }
  }

  /**
   * Creates a stock image
   * 
   * @param display
   *            the display
   * @param path
   *            the relative path to the icon
   */
  private Image createStockImage(Display display, String path) {
    InputStream stream = IconCache.class.getResourceAsStream(path);
    ImageData imageData = new ImageData(stream);
    ImageData mask = imageData.getTransparencyMask();
    Image result = new Image(display, imageData, mask);
    try {
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Gets an image for a file associated with a given program
   * 
   * @param program
   *            the Program
   */
  public Image getIconFromProgram(Program program) {
    Image image = (Image) iconCache.get(program);
    if (image == null) {
      ImageData imageData = program.getImageData();
      if (imageData != null) {
        image = new Image(null, imageData, imageData
            .getTransparencyMask());
        iconCache.put(program, image);
      } else {
        image = stockImages[iconShell];
      }
    }
    return image;
  }
}