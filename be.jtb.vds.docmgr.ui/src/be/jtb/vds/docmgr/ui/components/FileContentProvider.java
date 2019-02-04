package be.jtb.vds.docmgr.ui.components;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parent) {
		File file = (File) parent;
		return file.listFiles();
	}

	public Object[] getElements(Object inputElement) {
		File file = (File) inputElement;
		return file.listFiles();
	}

	@Override
	public Object getParent(Object element) {
		File file = (File) element;
		return file.getParentFile();
	}

	@Override
	public boolean hasChildren(Object element) {
		File file = (File) element;
		return file.isDirectory() && file.listFiles() != null && file.listFiles().length>0;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		System.err.println("Changed");
	}

}
