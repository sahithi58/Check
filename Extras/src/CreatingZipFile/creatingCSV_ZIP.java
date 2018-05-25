package CreatingZipFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class creatingCSV_ZIP {

		public static void main(String[] args) throws IOException {
			String allExportPath = "C:\\myData\\8801795211294.zip";
			String csvFilePath="C:\\csv\\StoreMenu.csv";
			String zipFileName = generateZip(csvFilePath,allExportPath);
			Map<Integer, String> csv = generateCSV();
			File file = new File(zipFileName);
		    System.out.println("ZIP FILE CREATION"+file.getPath());
			System.out.println("CSV FILE CREATION"+csv);
		}

		private static Map<Integer, String> generateCSV(){
			final Map<Integer, String> fields = new HashMap<Integer, String>();
			fields.put(0, "1svalue");
			fields.put(1, "2value");
			fields.put(2, "3value");
			System.out.println("ZIP FILE CREATION"+fields.get(0));
			Entry<Integer, String> s = fields.entrySet().stream().findFirst().get();
			fields.entrySet().stream().findFirst().get().setValue("#" + s.getValue());
			return fields;
		}
		
		
		private static String generateZip(String csvFilePath, String allExportPath) throws IOException {
			final File zipPath = new File(allExportPath);
			final String newZipPath = zipPath.getAbsolutePath().replace(zipPath.getName(), "") + "ProductData.zip";
			final File newZipFile = new File(newZipPath);
			final byte[] buf = new byte[1024];
			final ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath));
			final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(newZipFile));
			final File csvFile = new File(csvFilePath);
			ZipEntry entry = zin.getNextEntry();
			while (entry != null)
			{
				final String name = entry.getName();
				boolean notInFiles = true;
				//Ignoring the .impex file generated.
				if (csvFilePath.equals(name))
				{
					notInFiles = false;
					break;
				}
				if (notInFiles)
				{
					out.putNextEntry(new ZipEntry(name));
					int len;
					while ((len = zin.read(buf)) > 0)
					{
						out.write(buf, 0, len);
					}
				}
				entry = zin.getNextEntry();
			}
			zin.close();
			final InputStream in = new FileInputStream(csvFile);
			out.putNextEntry(new ZipEntry(csvFile.getName()));
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			out.closeEntry();
			in.close();
			out.close();
			 if (csvFile.delete())
		        {
		           System.out.println("Temporary Storemenu csv deleted successfully");
		        }
		        if (zipPath.delete())
		        {
		            System.out.println("Created old ZIP File deleted successfully");
		        }
			return allExportPath;
			}
	
}
