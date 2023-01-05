package me.minutz.trivsrv.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.yaml.snakeyaml.file.FileConfiguration;

public class PConfig {
	
	  private File contFile;
	  private FileConfiguration cont;

	
	  public PConfig(File contFile, FileConfiguration cont) {
		this.contFile = contFile;
		this.cont = cont;
	    if (!contFile.exists())
	    {
	      contFile.getParentFile().mkdirs();
	      copy(null, contFile);
	    }
	    try {
		      cont.load(contFile);
		    }
		    catch (Exception e)
		    {
		    }
	}

	public File getContFile() {
		return contFile;
	}


	public void setContFile(File contFile) {
		this.contFile = contFile;
	}


	public FileConfiguration getCont() {
		return cont;
	}


	public void setCont(FileConfiguration cont) {
		this.cont = cont;
	}


	private static void copy(InputStream in, File file)
	  {
	    try
	    {
	      OutputStream out = new FileOutputStream(file);
	      byte[] buffer = new byte[63];
	      int len;
	      while ((len = in.read(buffer)) > 0)
	      {
	        out.write(buffer, 0, len);
	      }
	      out.close();
	      in.close();
	    }
	    catch (Exception localException)
	    {
	    }
	  }

	public void save(){
		try {
			cont.save(contFile);
		} catch (IOException e) {
		}
	}
	

}
