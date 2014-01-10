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
package vn.com.mks.ca.gui;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

/**
 * @author ThachLN
 */
public class NProgressBar implements PaintListener {
    private ProgressBar progressBar;
    Composite parent;
    /**
     * @param parent
     * @param style
     */
    public NProgressBar(Composite parent, int style) {
        progressBar = new ProgressBar(parent, style);
        this.parent = parent;
    }

    /**
     * [Explain the description for this method here].
     * 
     * @param arg0
     * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
     */
    @Override
    public void paintControl(PaintEvent event) {
        Display display = parent.getShell().getDisplay();
        double value = this.getSelection() * 1.0 / (this.getMaximum() - this.getMinimum()) * 100;
        // Percent of completed project
        DecimalFormat df = new DecimalFormat("00.00");
        String str = df.format(value);
        String string = str + "%";

        Point point = this.getSize();
        Font font = new Font(display, "Courier", 10, SWT.BOLD);
        event.gc.setFont(font);
        event.gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

        FontMetrics fontMetrics = event.gc.getFontMetrics();
        int stringWidth = fontMetrics.getAverageCharWidth() * string.length();
        int stringHeight = fontMetrics.getHeight();

        event.gc.drawString(string, (point.x - stringWidth) / 2, (point.y - stringHeight) / 2, true);
        font.dispose();
    }

    /**
     * [Give the description for method].
     * @return
     */
    private Point getSize() {
        return progressBar.getSize();
    }

    /**
     * [Give the description for method].
     * @return
     */
    private int getMaximum() {
        return progressBar.getMaximum();
    }

    /**
     * [Give the description for method].
     * @return
     */
    private int getMinimum() {
        return progressBar.getMinimum();
    }

    /**
     * [Give the description for method].
     * @return
     */
    private int getSelection() {
        return progressBar.getSelection();
    }

    /**
     * [Give the description for method].
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setBounds(int x, int y, int width, int height) {
        progressBar.setBounds(x, y, width, height);
    }

    /**
     * [Give the description for method].
     * @param visible
     */
    public void setVisible(boolean visible) {
        progressBar.setVisible(visible);      
    }

    /**
     * [Give the description for method].
     */
    public void dispose() {
        progressBar.dispose();
    }

}
