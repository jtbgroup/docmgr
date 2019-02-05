package be.jtb.vds.docmgr.ui.components;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parent) {
		File file = (File) parent;
		File[] files = file.listFiles();
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.isDirectory() && o2.isFile()) {
					return 1;
				}else if (o2.isDirectory() && o1.isFile()) {
					return -1;
				}
				return o1.getPath().compareTo(o2.getPath());
			}
		});
		return files;
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
		return file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		System.err.println("Changed");
	}

}
