package vn.com.mks.ca.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;

public class SettingDlg extends Dialog {

    protected Object result;
    protected Shell shell;
    private GridData gridData_1;
    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public SettingDlg(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
    }
    /**
     * Open the dialog.
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return result;
    }
    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM);
        shell.setSize(450, 300);
        shell.setText(getText());
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        
        shell.setLayout(gridLayout);
        
        SashForm sashForm = new SashForm(shell, SWT.NONE);
        sashForm.setOrientation(SWT.HORIZONTAL);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        
        createTreeView(sashForm);
        
        createMainSetting(sashForm);
        
        sashForm.setWeights(new int[]{2,5});

        createBottom(shell);
        
    }

    private void createTreeView(SashForm parent) {
        Composite compositeTree = new Composite(parent, SWT.NONE);
        
        GridLayout gl_compositeTree = new GridLayout();
        gl_compositeTree.marginHeight = gl_compositeTree.marginWidth = 2;
        gl_compositeTree.horizontalSpacing = gl_compositeTree.verticalSpacing = 0;
        compositeTree.setLayout(gl_compositeTree);
        
        Tree tree = new Tree(compositeTree, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        tree.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
        
        TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem.setText("New TreeItem");
        
        TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem_1.setText("New TreeItem");
        
        TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
        trtmNewTreeitem_2.setText("New TreeItem");

        
    }    

    private void createMainSetting(SashForm parent) {
        Composite compositeMain = new Composite(parent, SWT.NONE);
        
        GridLayout gl_compositeTable = new GridLayout();
        gl_compositeTable.numColumns = 1;
        gl_compositeTable.marginHeight = gl_compositeTable.marginWidth = 2;
        gl_compositeTable.horizontalSpacing = gl_compositeTable.verticalSpacing = 0;
        
        
        compositeMain.setLayout(gl_compositeTable);
    }
    
    private void createBottom(Shell parent) {
        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new RowLayout(SWT.HORIZONTAL));

        GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gridData.horizontalSpan = 3;
        composite.setLayoutData(gridData);
        
        Button btnOK = new Button(composite, SWT.NONE);
        btnOK.setText("OK");
        
        Button btnCancel = new Button(composite, SWT.NONE);
        btnCancel.setText("Cancel");

        
    }
}
