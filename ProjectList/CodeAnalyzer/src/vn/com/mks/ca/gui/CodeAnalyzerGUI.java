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

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import vn.com.mks.ca.AppCons;
import vn.com.mks.swt.IconCache;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * @author thachln
 *
 */
public class CodeAnalyzerGUI {
    final static Logger LOG = Logger.getLogger("CodeAnalyzerGUI");
    protected Shell shell;

    

    protected Table table = null;

 // File: Currently visible directory
    private static final int[] TABLEWIDTHS = new int[] {250, 120, 80, 60};
    private static final String[] TABLEHEADERS = {
        "Folder",
        "File",
        "Method",
        "Step"
    };
    
    private static IconCache iconCache = new IconCache();
    
    /** This member must be set before method #createContents. */
    private AppEventHandler eventHandler = null;
    
    /**
     * This method is supported for WindowBuilder.
     * @param args
     */
    public static void main(String[] args) {
        try {
            CodeAnalyzerGUI window = new CodeAnalyzerGUI();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        iconCache.initResources(display);
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Get value of eventHandler.
     * @return the eventHandler
     */
    public AppEventHandler getEventHandler() {
        return eventHandler;
    }

    /**
     * Set the value for eventHandler.
     * @param eventHandler the eventHandler to set
     */
    public void setEventHandler(AppEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        
        
        shell.setImage(iconCache.stockImages[iconCache.iconShell]);
        shell.setSize(900, 600);
        shell.setText("Code Analyzer");
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        shell.setLayout(gridLayout);
        
        createMenuBar(shell);
        
        createToolBar(shell);
        
        SashForm sashForm = new SashForm(shell, SWT.NONE);
        settingProcessDrag(sashForm);
        sashForm.setOrientation(SWT.HORIZONTAL);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        
        createTreeView(sashForm);
        createTableView(sashForm);
        

        sashForm.setWeights(new int[]{2, 3});
        
        createStatusBar();

    }

    /**
     * [Give the description for method].
     * @param sashForm
     */
    private void settingProcessDrag(SashForm sashForm) {
        DropTarget dropTarget = new DropTarget(sashForm, DND.DROP_LINK | DND.DROP_COPY | DND.DROP_MOVE);
        Transfer[] transferType = new Transfer[]{FileTransfer.getInstance()}; 
        dropTarget.setTransfer(transferType);
        
        dropTarget.addDropListener(eventHandler);
    }


    /**
     * [Give the description for method].
     */
    private void createStatusBar() {
        GridData gridData;
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setText("Status");

        gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL);
        gridData.widthHint = 185;
        lblNewLabel.setLayoutData(gridData);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
    }

    /**
     * [Give the description for method].
     */
    private void createToolBar(Shell shell) {
        ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
        
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        toolBar.setLayoutData(gridData);
        
        ToolItem tltmOpenfile = new ToolItem(toolBar, SWT.NONE);
        tltmOpenfile.setToolTipText("Open File");
        tltmOpenfile.setImage(iconCache.stockImages[iconCache.iconOpenFile]);
        // tltmOpenfile.setText("OpenFile");
        tltmOpenfile.addSelectionListener(eventHandler);
        
        ToolItem tltmOpenfolder = new ToolItem(toolBar, SWT.NONE);
        tltmOpenfolder.addSelectionListener(eventHandler);
        tltmOpenfolder.setToolTipText("Open Folder");
        tltmOpenfolder.setImage(iconCache.stockImages[iconCache.iconOpenFolder]);
        tltmOpenfolder.setData(Command.CMD_OPEN_FOLDER);
        //tltmOpenfolder.setText("OpenFolder");
        
        ToolItem tltmExportToExcel = new ToolItem(toolBar, SWT.NONE);
        tltmExportToExcel.setToolTipText("Export to Excel");
        tltmExportToExcel.setImage(iconCache.stockImages[iconCache.iconExportToExcel]);
        //tltmExportToExcel.setText("Export to Excel");
        tltmExportToExcel.addSelectionListener(eventHandler);
    }


    /**
     * [Give the description for method].
     * @param shell
     */
    private void createMenuBar(Shell shell) {
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
        
        MenuItem mntmFile = new MenuItem(menuBar, SWT.CASCADE);
        mntmFile.setText("File");
        
        Menu menu = new Menu(mntmFile);
        mntmFile.setMenu(menu);
        
        MenuItem mntmOpenFile = new MenuItem(menu, SWT.NONE);
        mntmOpenFile.setText("Open File...");
        mntmOpenFile.setData(Command.CMD_OPEN_FILE);
        
        MenuItem mntmOpenFolder = new MenuItem(menu, SWT.NONE);
        mntmOpenFolder.addSelectionListener(eventHandler);
        mntmOpenFolder.setText("Open Folder...");
        mntmOpenFolder.setData(Command.CMD_OPEN_FOLDER);
        
        new MenuItem(menu, SWT.SEPARATOR);
        
        MenuItem mntmExportToExcel = new MenuItem(menu, SWT.NONE);
        mntmExportToExcel.setText("Export to Excel");
        
        new MenuItem(menu, SWT.SEPARATOR);
        
        MenuItem mntmExit = new MenuItem(menu, SWT.NONE);
        mntmExit.setText("Exit");
        
        MenuItem mntmOption = new MenuItem(menuBar, SWT.CASCADE);
        mntmOption.setText("Option");
        
        Menu menu_1 = new Menu(mntmOption);
        mntmOption.setMenu(menu_1);
        
        MenuItem mntmSetting = new MenuItem(menu_1, SWT.NONE);
        mntmSetting.addSelectionListener(eventHandler);
        mntmSetting.setText("Setting...");
        mntmSetting.setData(Command.CMD_SETTING);
        
        MenuItem mntmHelp = new MenuItem(menuBar, SWT.CASCADE);
        mntmHelp.setText("Help");
        
        Menu menu_2 = new Menu(mntmHelp);
        mntmHelp.setMenu(menu_2);
        
        MenuItem mntmContent = new MenuItem(menu_2, SWT.NONE);
        mntmContent.setText("Content");
        
        MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
        mntmAbout.setText("About");
    }

    /**
     * [Give the description for method].
     * @param parent
     */
    private static void createTreeView(SashForm parent) {
        Composite compositeTree = new Composite(parent, SWT.NONE);
        GridLayout gl_compositeTree = new GridLayout();
        gl_compositeTree.marginHeight = gl_compositeTree.marginWidth = 2;
        gl_compositeTree.horizontalSpacing = gl_compositeTree.verticalSpacing = 0;
        compositeTree.setLayout(gl_compositeTree);
        
        Label lblSourceExplorer = new Label(compositeTree, SWT.NONE);
        lblSourceExplorer.setSize(87, 223);
        lblSourceExplorer.setText("Source Explorer");
        
        Tree tree = new Tree(compositeTree, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        tree.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
        
        TreeItem trtmRoot = new TreeItem(tree, SWT.NONE);
        trtmRoot.setText("Root");
        
        TreeItem trtmFolder = new TreeItem(trtmRoot, SWT.NONE);
        trtmFolder.setText("Folder 1");
        
        TreeItem trtmFolder_2 = new TreeItem(trtmFolder, SWT.NONE);
        trtmFolder_2.setText("Folder 1.1");
        trtmFolder.setExpanded(true);
        
        TreeItem trtmFolder_1 = new TreeItem(trtmRoot, SWT.NONE);
        trtmFolder_1.setText("Folder 2");
        trtmRoot.setExpanded(true);

    }

    /**
     * [Give the description for method].
     * @param parent SashForm
     */
    private void createTableView(Composite parent) {
        TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
        
        TabItem tbtmCodeCounter = new TabItem(tabFolder, SWT.NONE);
        tbtmCodeCounter.setText("Code Counter");
        
        Composite compositeTable = new Composite(tabFolder, SWT.NONE);
        tbtmCodeCounter.setControl(compositeTable);
        GridLayout gl_compositeTable = new GridLayout();
        gl_compositeTable.numColumns = 1;
        gl_compositeTable.marginHeight = gl_compositeTable.marginWidth = 2;
        gl_compositeTable.horizontalSpacing = gl_compositeTable.verticalSpacing = 0;
        compositeTable.setLayout(gl_compositeTable);
        //Label tableContentsOfLabel = new Label(compositeTable, SWT.BORDER);
        //tableContentsOfLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL));
        
                
                
                table = new Table(compositeTable, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
                table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
                table.setHeaderVisible(true);
                
                TabItem tbtmStaticCheck = new TabItem(tabFolder, SWT.NONE);
                tbtmStaticCheck.setText("Static check");
                
                TabItem tbtmUTCounter = new TabItem(tabFolder, SWT.NONE);
                tbtmUTCounter.setText("UT Counter");
                
                TabItem tbtmUTReport = new TabItem(tabFolder, SWT.NONE);
                tbtmUTReport.setText("UTReport");
        for (int i = 0; i < 4; ++i) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(TABLEHEADERS[i]);
            column.setWidth(TABLEWIDTHS[i]);
        }

    }


    /**
     * Get value of table.
     * @return the table
     */
    public Table getTable() {
        return table;
    }

}
