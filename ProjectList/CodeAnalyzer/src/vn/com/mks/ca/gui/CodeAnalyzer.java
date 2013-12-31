package vn.com.mks.ca.gui;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import vn.com.mks.swt.IconCache;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CodeAnalyzer {
    final static Logger LOG = Logger.getLogger("CodeAnalyzer");

    private static Shell shlCodeAnalyzer;
    private static Table table;

 // File: Currently visible directory
    private static final int[] TABLEWIDTHS = new int[] {250, 120, 80, 60};
    private static final String[] TABLEHEADERS = {
        "Folder",
        "File",
        "Method",
        "Step"
    };
    
    private static IconCache iconCache = new IconCache();
    private static SelectionAdapter eventHandler = new AppEventHandler();
    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        Display display = Display.getDefault();
        
        iconCache.initResources(display);
        
        shlCodeAnalyzer = new Shell();
        shlCodeAnalyzer.setImage(iconCache.stockImages[iconCache.iconShell]);
        shlCodeAnalyzer.setSize(900, 600);
        shlCodeAnalyzer.setText("Code Analyzer");
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        shlCodeAnalyzer.setLayout(gridLayout);
        
        createMenuBar(shlCodeAnalyzer);
        
        createToolBar(shlCodeAnalyzer);
        
        SashForm sashForm = new SashForm(shlCodeAnalyzer, SWT.NONE);
        processDrag(sashForm);
        sashForm.setOrientation(SWT.HORIZONTAL);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        
        createTreeView(sashForm);
        createTableView(sashForm);
        
        sashForm.setWeights(new int[]{2,5});
        
        createStatusBar();
        
        shlCodeAnalyzer.open();
        shlCodeAnalyzer.layout();
        while (!shlCodeAnalyzer.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    /**
     * [Give the description for method].
     * @param sashForm
     */
    private static void processDrag(SashForm sashForm) {
        DropTarget dropTarget = new DropTarget(sashForm, DND.DROP_LINK | DND.DROP_COPY | DND.DROP_MOVE);
        Transfer[] transferType = new Transfer[]{FileTransfer.getInstance()}; 
        dropTarget.setTransfer(transferType);
        
        DropTargetListener dropTargetListener = new DropHandler();
        
        dropTarget.addDropListener(dropTargetListener);
    }


    /**
     * [Give the description for method].
     */
    private static void createStatusBar() {
        GridData gridData;
        Label lblNewLabel = new Label(shlCodeAnalyzer, SWT.NONE);
        lblNewLabel.setText("Status");

        gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL);
        gridData.widthHint = 185;
        lblNewLabel.setLayoutData(gridData);
        new Label(shlCodeAnalyzer, SWT.NONE);
        new Label(shlCodeAnalyzer, SWT.NONE);
    }

    /**
     * [Give the description for method].
     */
    private static void createToolBar(Shell shell) {
        ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
        
        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gridData.horizontalSpan = 3;
        toolBar.setLayoutData(gridData);
        
        ToolItem tltmOpenfile = new ToolItem(toolBar, SWT.NONE);
        tltmOpenfile.setToolTipText("Open File");
        tltmOpenfile.setImage(iconCache.stockImages[iconCache.iconOpenFile]);
        // tltmOpenfile.setText("OpenFile");
        
        ToolItem tltmOpenfolder = new ToolItem(toolBar, SWT.NONE);
        tltmOpenfolder.setToolTipText("Open Folder");
        tltmOpenfolder.setImage(iconCache.stockImages[iconCache.iconOpenFolder]);
        //tltmOpenfolder.setText("OpenFolder");
        
        ToolItem tltmExportToExcel = new ToolItem(toolBar, SWT.NONE);
        tltmExportToExcel.setToolTipText("Export to Excel");
        tltmExportToExcel.setImage(iconCache.stockImages[iconCache.iconExportToExcel]);
        //tltmExportToExcel.setText("Export to Excel");
    }


    /**
     * [Give the description for method].
     * @param shell
     */
    private static void createMenuBar(Shell shell) {
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
        mntmSetting.setText("Setting...");
        
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
    private static void createTableView(Composite parent) {
        Composite compositeTable = new Composite(parent, SWT.NONE);
        GridLayout gl_compositeTable = new GridLayout();
        gl_compositeTable.numColumns = 1;
        gl_compositeTable.marginHeight = gl_compositeTable.marginWidth = 2;
        gl_compositeTable.horizontalSpacing = gl_compositeTable.verticalSpacing = 0;
        compositeTable.setLayout(gl_compositeTable);
        Label tableContentsOfLabel = new Label(compositeTable, SWT.BORDER);
        tableContentsOfLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL));

        table = new Table(compositeTable, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
        table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

        for (int i = 0; i < 4; ++i) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(TABLEHEADERS[i]);
            column.setWidth(TABLEWIDTHS[i]);
        }
        table.setHeaderVisible(true);

    }
}
