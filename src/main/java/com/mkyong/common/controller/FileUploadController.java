package com.mkyong.common.controller;


import javax.servlet.http.HttpServletRequest;







import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.wc2.SvnCommit;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import com.mkyong.common.model.FileUpload;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**  
 Converting binary data into different forms.
 
 <P>Reads binary data into memory, and writes it back out.
 (If your're actually copying a file, there are better ways to do this.)
 
 <P>Buffering is used when reading and writing files, to minimize the number 
 of interactions with the disk.
*/



public class FileUploadController extends SimpleFormController{
	
	private static String UPLOADEDFILEPATH = "C:\\TEST2\\";
	
	 /** Change these settings before running this class. */
	  private static final String INPUT_FILE_NAME = "C:\\TEST1\\";
	  private static final String OUTPUT_FILE_NAME = "C:\\TEST2\\";
	
	public FileUploadController(){
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}
 
	@SuppressWarnings("deprecation")
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
	 HttpServletResponse response, Object command, BindException errors)
	 throws Exception {
 
			
		FileUpload file = (FileUpload)command;
		
		MultipartFile multipartFile = file.getFile();
		String filePathString  = "";
			
		String fileName="";
		String newpathfileName="";
		
		/*File f = new File("C:/test/file1");*/
	
		File f = new File(UPLOADEDFILEPATH);
		
		
	try {
				
		InputStream inputStream = null;
		OutputStream outputStream = null;
	
		if(multipartFile!=null){
			
			fileName = multipartFile.getOriginalFilename();
			
			// OutputStream fl = new FileOutputStream(f);
			
			
			
			System.out.println("Reading in binary file named fileName  from  user input: " + fileName);
			
	
			inputStream = multipartFile.getInputStream();
			if (multipartFile.getSize() > 10000) {
				System.out.println("multipartFile File Size:::" + multipartFile.getSize());
				
				// return new ModelAndView("FileUploadFail","fileName", "File Size too big");
				
			}
			System.out.println("multipartFile size::" + multipartFile.getSize());
			
			// newpathfileName = request.getRealPath("") + "/images/"	+ multipartFile.getOriginalFilename();
	
			newpathfileName = "c:/test2/" + multipartFile.getOriginalFilename();
					
			outputStream = new FileOutputStream(newpathfileName);
		
			System.out.println("fileName:" + multipartFile.getOriginalFilename());

			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
			
			
			// Reading in binary file named : C:\TEST1\waterspring.jpg
			
			
			// File fi = new File(INPUT_FILE_NAME + fileName);
			
			
			
			// System.out.println("File  File object --- fi " + fi);
			// System.out.println("File size: File object --- fi " + fi.length());
			
			
			/*
			//do whatever you want
			
			final SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
			try {
				svnOperationFactory.setAuthenticationManager(authManager);
			    final SvnCommit commit = svnOperationFactory.createCommit();
			    commit.setSingleTarget(SvnTarget.fromFile(fi));
			    commit.setCommitMessage("Commit message");
			    final SVNCommitInfo commitInfo = commit.run();
			} finally {
			    svnOperationFactory.dispose();
			}*/

		
			//** Run the example. *//
			  // public static void main(String... aArgs) {
			
			
			/*    BytesStreamsAndFiles myTest = new BytesStreamsAndFiles();
			    
			    
			    System.out.println(String.valueOf(INPUT_FILE_NAME));
			    
			    //read in the bytes
			    byte[] fileContents = myTest.read(INPUT_FILE_NAME + fileName);
			    //test.readAlternateImpl(INPUT_FILE_NAME);
			    //write it back out to a different file name
			    System.out.println(String.valueOf(OUTPUT_FILE_NAME));
			    
				
			    myTest.write(fileContents, OUTPUT_FILE_NAME + fileName);
			 */
			    
			
			
			
			//  Added SVNKit do whatever 
		}
		
		
		
	  } catch (Exception e) {
			e.printStackTrace();
	  }
	  
	  
	  return new ModelAndView("FileUploadSuccess","fileName",fileName);
 
	}

	/*@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}*/
	
	
	
	
	//
	
	public final class BytesStreamsAndFiles {

		   
		  /** Read the given binary file, and return its contents as a byte array.*/ 
		  byte[] read(String aInputFileName){
		    log("Reading in binary file named : " + aInputFileName);
		    File file = new File(aInputFileName);
		    log("File size: " + file.length());
		    byte[] result = new byte[(int)file.length()];
		    try {
		      InputStream input = null;
		      try {
		        int totalBytesRead = 0;
		        input = new BufferedInputStream(new FileInputStream(file));
		        while(totalBytesRead < result.length){
		          int bytesRemaining = result.length - totalBytesRead;
		          //input.read() returns -1, 0, or more :
		          int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
		          if (bytesRead > 0){
		            totalBytesRead = totalBytesRead + bytesRead;
		          }
		        }
		        /*
		         the above style is a bit tricky: it places bytes into the 'result' array; 
		         'result' is an output parameter;
		         the while loop usually has a single iteration only.
		        */
		        log("Num bytes read: " + totalBytesRead);
		      }
		      finally {
		        log("Closing input stream.");
		        input.close();
		      }
		    }
		    catch (FileNotFoundException ex) {
		      log("File not found.");
		    }
		    catch (IOException ex) {
		      log(ex);
		    }
		    return result;
		  }
		  
		  /**
		   Write a byte array to the given file. 
		   Writing binary data is significantly simpler than reading it. 
		  */
		  void write(byte[] aInput, String aOutputFileName){
		    log("Writing binary file...");
		    try {
		      OutputStream output = null;
		      try {
		        output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
		        output.write(aInput);
		      }
		      finally {
		        output.close();
		      }
		    }
		    catch(FileNotFoundException ex){
		      log("File not found.");
		    }
		    catch(IOException ex){
		      log(ex);
		    }
		  }
		  
		  /** Read the given binary file, and return its contents as a byte array.*/ 
		  byte[] readAlternateImpl(String aInputFileName){
		    log("Reading in binary file named : " + aInputFileName);
		    File file = new File(aInputFileName);
		    log("File size: " + file.length());
		    byte[] result = null;
		    try {
		      InputStream input =  new BufferedInputStream(new FileInputStream(file));
		      result = readAndClose(input);
		    }
		    catch (FileNotFoundException ex){
		      log(ex);
		    }
		    return result;
		  }
		  
		  /**
		   Read an input stream, and return it as a byte array.  
		   Sometimes the source of bytes is an input stream instead of a file. 
		   This implementation closes aInput after it's read.
		  */
		  byte[] readAndClose(InputStream aInput){
		    //carries the data from input to output :    
		    byte[] bucket = new byte[32*1024]; 
		    ByteArrayOutputStream result = null; 
		    try  {
		      try {
		        //Use buffering? No. Buffering avoids costly access to disk or network;
		        //buffering to an in-memory stream makes no sense.
		        result = new ByteArrayOutputStream(bucket.length);
		        int bytesRead = 0;
		        while(bytesRead != -1){
		          //aInput.read() returns -1, 0, or more :
		          bytesRead = aInput.read(bucket);
		          if(bytesRead > 0){
		            result.write(bucket, 0, bytesRead);
		          }
		        }
		      }
		      finally {
		        aInput.close();
		        //result.close(); this is a no-operation for ByteArrayOutputStream
		      }
		    }
		    catch (IOException ex){
		      log(ex);
		    }
		    return result.toByteArray();
		  }
		  
		  private void log(Object aThing){
		    System.out.println(String.valueOf(aThing));
		  }
		} 

	
	//
	

	
}