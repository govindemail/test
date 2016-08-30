import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsableSpace {

	private static Object[] fileStores;

	protected static void init(String args[]) {
		//        fileTestArgDecoder.decode(args);
		//        workDir = fileTestArgDecoder.getWorkdir();
		List<FileStore> fsList = new ArrayList<FileStore>();
		Iterator<FileStore> fsIter =
				FileSystems.getDefault().getFileStores().iterator();

		while (fsIter.hasNext() && fsList.size() < 6 ) {
			fsList.add(fsIter.next());
		}

		fileStores = fsList.toArray();
	}
	/**
	 * Tested method: getUsableSpace()
	 * Assertion: Returns the number of bytes available to this Java virtual machine on the file store.
	 * Checking that result of the method is non negative and smaller than total file store space.
	 */

	public static void getUsableSpace(final FileStore fileStore) {
		System.out.println("==== getUsableSpace ====");

		try {
			long result = fileStore.getUsableSpace();
			System.out.println("filestore name="+fileStore);
			System.out.println("usable :"+result);
			if ( result < 0) {
				System.out.println("getUsableSpace() returned " +
						"negative value: " + result);
			}
			long total = fileStore.getTotalSpace();
			System.out.println("total  :"+total);
			System.out.println("difference(total-usable)= "+(total-result));
			if (result > total) {
				System.out.println("usable space (" + result + 
						") exceeds total space (" + total + ")");
			}
		} catch (IOException ioe) {
			// may be OK with removable media drives
			System.out.println("WARNING: " + ioe);
		}
		System.out.println("OK");
	}

	public static void main(String arsgs[]) {
		UsableSpace.init(null);
		for(int i=0; i<fileStores.length;i++) {
			FileStore fs = (FileStore) fileStores[i];
			UsableSpace.getUsableSpace(fs);

		}

	}
}
