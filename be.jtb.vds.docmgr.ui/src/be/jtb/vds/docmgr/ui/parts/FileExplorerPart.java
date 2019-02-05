package be.jtb.vds.docmgr.ui.parts;

import java.io.File;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import be.jtb.vds.docmgr.ui.components.FileContentProvider;
import be.jtb.vds.docmgr.ui.components.FileLabelProvider;

public class FileExplorerPart {

	private TreeViewer listViewer;
	private IBaseLabelProvider fileLabelProvider;

	@PostConstruct
	public void createComposite(Composite parent) {
		listViewer = new TreeViewer(parent);
		listViewer.getTree().setBounds(10, 10, 200, 200);
		listViewer.setContentProvider(new FileContentProvider());
		fileLabelProvider = new FileLabelProvider();
		listViewer.setLabelProvider(fileLabelProvider);
//		listViewer.setInput(new File("C:\\"));
		listViewer.setInput(new File("/"));
		listViewer.addTreeListener(new ITreeViewerListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				reviewImage(event.getSource());
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				reviewImage(event.getSource());
			}
		});

		listViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(final DoubleClickEvent event) {
				final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (selection == null || selection.isEmpty())
					return;

				final Object sel = selection.getFirstElement();
				final ITreeContentProvider provider = (ITreeContentProvider) listViewer.getContentProvider();

				if (provider.hasChildren(sel)) {
					if (listViewer.getExpandedState(sel)) {
						listViewer.collapseToLevel(sel, AbstractTreeViewer.ALL_LEVELS);
					} else {
						listViewer.expandToLevel(sel, 1);
					}
					reviewImage(event.getSource());
				}
				else{
					File f = (File)sel; 
					if(f.isFile() && f.getName().endsWith(".pdf")) {
						System.out.println(f);
					}
				}
			}
		});

	}

	private void reviewImage(Object source) {
		System.out.println(source);
	}

	@Focus
	public void setFocus() {
		listViewer.getTree().setFocus();
	}

}