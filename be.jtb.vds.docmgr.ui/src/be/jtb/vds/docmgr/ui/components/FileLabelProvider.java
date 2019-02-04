package be.jtb.vds.docmgr.ui.components;

import java.io.File;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class FileLabelProvider extends LabelProvider {

	private static Image folderImage;
	private static Image fileImage;
	private static Image driveImage;

	static {
		try {
			folderImage = ImageDescriptor
					.createFromURL(new URL("platform:/plugin/be.jtb.vds.docmgr.ui/resources/img/folderOpen_16x16.png"))
					.createImage();
			fileImage = ImageDescriptor
					.createFromURL(new URL("platform:/plugin/be.jtb.vds.docmgr.ui/resources/img/file_16x16.png")).createImage();
			driveImage = ImageDescriptor
					.createFromURL(new URL("platform:/plugin/be.jtb.vds.docmgr.ui/resources/img/drive_16x16.png")).createImage();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

//			AbstractUIPlugin
//			.imageDescriptorFromPlugin("be.jtb.vds.docmgr.ui", "icons/folder.gif").createImage();
//	private static final Image driveImage = AbstractUIPlugin
//			.imageDescriptorFromPlugin("de.vogella.rcp.intro.filebrowser", "icons/filenav_nav.gif").createImage();
//	private static final Image fileImage = AbstractUIPlugin
//			.imageDescriptorFromPlugin("de.vogella.rcp.intro.filebrowser", "icons/file_obj.gif").createImage();

	@Override
	public Image getImage(Object element) {
		File file = (File) element;
		if (file.isDirectory())
			return file.getParent() != null ? folderImage : driveImage;
		return fileImage;
	}

	@Override
	public String getText(Object element) {
		String fileName = ((File) element).getName();
		if (fileName.length() > 0) {
			return fileName;
		}
		return ((File) element).getPath();
	}
}